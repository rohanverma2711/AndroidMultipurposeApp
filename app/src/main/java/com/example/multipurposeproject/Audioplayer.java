package com.example.multipurposeproject;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Audioplayer extends AppCompatActivity {
    Button b1,b2,b3;
    TextView t1;
    MediaPlayer m1;
    ImageView i1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audioplayer);
        getSupportActionBar().setTitle("Audioplayer");

        b1 = findViewById(R.id.button2);
        b2 = findViewById(R.id.button3);
        b3 = findViewById(R.id.button9);
        t1 = findViewById(R.id.textView23);
        i1 = findViewById(R.id.imageView26);
        i1.setVisibility(View.INVISIBLE);
        m1 = MediaPlayer.create(this,R.raw.d1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i1.setVisibility(View.VISIBLE);
                m1.start();
                t1.setText("Playing...");
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m1.pause();
                t1.setText("Paused");
                i1.setVisibility(View.INVISIBLE);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Audioplayer.this,Menu.class);
                startActivity(i);
                finish();
            }
        });

    }
}