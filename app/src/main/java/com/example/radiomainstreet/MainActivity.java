package com.example.radiomainstreet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ImageView image_bplay;
    ImageView image_bpause;
    ImageView image_face;
    ImageView image_insta;
    ImageView image_what;
    ImageView image_web;
    ImageView image_tik;
    ImageView image_compartir;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        image_bplay = findViewById(R.id.image_bplay);
        image_bpause = findViewById(R.id.image_bpause);
        image_face = findViewById(R.id.image_face);
        image_insta = findViewById(R.id.image_insta);
        image_what = findViewById(R.id.image_what);
        image_web = findViewById(R.id.image_web);
        image_tik = findViewById(R.id.image_tik);

        image_bplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, RadioService.class);
                startService(intent);
            }
        });

        image_bpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                Intent intent = new Intent(MainActivity.this, RadioService.class);
                stopService(intent);
            }
        });

        image_face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/RadioMainStreet")));
            }
        });

        image_insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/radiomainstreet/")));
            }
        });

        image_what.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.link/u4o4nd")));
            }
        });

        image_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://radiomainstreet.com/")));
            }
        });

        image_tik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tiktok.com/@radiomainstreet")));
            }
        });


        image_compartir = findViewById(R.id.image_compartir);


        image_compartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                compartirApp();
            }
        });
    }

    private void compartirApp() {

        Intent compartirIntent = new Intent(Intent.ACTION_SEND);
        compartirIntent.setType("text/plain");
        compartirIntent.putExtra(Intent.EXTRA_SUBJECT, "¡Te recomiendo esta aplicación!");
        compartirIntent.putExtra(Intent.EXTRA_TEXT, "¡Descarga esta increíble aplicación desde la Play Store!\n" +
                "https://play.google.com/store/apps/details?id=com.radiomainstreetfm.radiomainstreet&pcampaignid=web_share");


        startActivity(Intent.createChooser(compartirIntent, "Compartir aplicación"));
    }
}