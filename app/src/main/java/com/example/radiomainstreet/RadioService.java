package com.example.radiomainstreet;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.PowerManager;
import android.net.wifi.WifiManager;
import android.util.Log;

import java.io.IOException;

public class RadioService extends Service implements AudioManager.OnAudioFocusChangeListener, MediaPlayer.OnPreparedListener {
    private static final String STREAM_URL = "https://radiolatina.info/9930/stream";
    private static final String TAG = "RadioService"; // Define una etiqueta para los registros de depuración
    private MediaPlayer mediaPlayer;
    private WifiManager.WifiLock wifiLock;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: Servicio creado");


        mediaPlayer = new MediaPlayer();


        mediaPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);


        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        int result = audioManager.requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
        if (result != AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

        }


        mediaPlayer.setOnPreparedListener(this);

        try {
            mediaPlayer.setDataSource(STREAM_URL);
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
            stopSelf();
        }


        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        wifiLock = wifiManager.createWifiLock(WifiManager.WIFI_MODE_FULL, "MyWifiLock");
        wifiLock.acquire();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        if (wifiLock != null && wifiLock.isHeld()) {
            wifiLock.release();
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mediaPlayer.start();
        Log.d(TAG, "onPrepared: Reproducción iniciada");
    }

    @Override
    public void onAudioFocusChange(int focusChange) {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}