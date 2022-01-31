package com.example.multipurposeproject;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;
import java.util.Objects;

public class Videoplayer extends AppCompatActivity {
    VideoView v1;
    MediaController m1;
    Button b1,b2,b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoplayer);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Videoplayer");
        v1 = findViewById(R.id.videoView);
        b1 = findViewById(R.id.button5);
        b2 = findViewById(R.id.button8);
        b3 = findViewById(R.id.button9);
        m1 = new MediaController(this);
        v1.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.v);
        v1.setMediaController(m1);
        m1.setAnchorView(v1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v1.start();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v1.pause();
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Videoplayer.this,Menu.class);
                startActivity(i);
                finish();
            }
        });
    }

}