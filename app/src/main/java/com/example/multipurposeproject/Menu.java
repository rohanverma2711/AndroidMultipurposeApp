package com.example.multipurposeproject;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Menu extends AppCompatActivity {
    ImageView i1,i2,i3,i4,i5,i6,i7,i8,i9,i10,i11,i12;
    TextView t1,t2;
    GoogleSignInClient googleSignInClient;
    FirebaseAuth firebaseAuth;
    MediaPlayer mediaPlayer;
    Vibrator v1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        i2 = findViewById(R.id.imageView20);
        i3 = findViewById(R.id.imageView21);
        i4 = findViewById(R.id.imageView29);
        i5 = findViewById(R.id.imageView30);
        i6 = findViewById(R.id.imageView31);
        i7 = findViewById(R.id.imageView32);
        i8 = findViewById(R.id.imageView36);
        i9 = findViewById(R.id.imageView37);
        i10 = findViewById(R.id.imageView43);
        i11 = findViewById(R.id.imageView38);
        i12 = findViewById(R.id.imageView46);
        v1 = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        i1 = findViewById(R.id.imageView24);
        t1 = findViewById(R.id.textView7);
        t2 = findViewById(R.id.textView47);
        t2.setText(t2.getText()+"\u00a9");
        mediaPlayer = MediaPlayer.create(this, R.raw.sound3);
        firebaseAuth = FirebaseAuth.getInstance();
        googleSignInClient = GoogleSignIn.getClient(Menu.this,GoogleSignInOptions.DEFAULT_SIGN_IN);
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        getSupportActionBar().setTitle("Menu");
        if(firebaseUser!=null){
            Glide.with(Menu.this).load(firebaseUser.getPhotoUrl()).into(i1);
        }

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                firebaseAuth.signOut();
                googleSignInClient.signOut();
                Intent k = new Intent(Menu.this, MainActivity.class);
                k.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(k);

            }
        });


        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v1.vibrate(50);
                Intent i = new Intent(Menu.this,Calculator2.class);
                startActivity(i);

            }
        });

        i3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v1.vibrate(50);
                Intent i = new Intent(Menu.this,Torch.class);
                startActivity(i);

            }
        });

        i4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v1.vibrate(50);
                Intent i = new Intent(Menu.this,Camera.class);
                startActivity(i);

            }
        });

        i5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v1.vibrate(50);
                Intent i = new Intent(Menu.this,Bluetooth.class);
                startActivity(i);

            }
        });

        i6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v1.vibrate(50);
                Intent i = new Intent(Menu.this,Videoplayer.class);
                startActivity(i);

            }
        });

        i7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v1.vibrate(50);
                Intent i = new Intent(Menu.this,Audioplayer.class);
                startActivity(i);

            }
        });

        i8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v1.vibrate(50);
                Intent i = new Intent(Menu.this,Browser.class);
                startActivity(i);
            }
        });

        i9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v1.vibrate(50);
                Intent i = new Intent(Menu.this,Phone.class);
                startActivity(i);
            }
        });

        i10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v1.vibrate(50);
                Intent i = new Intent(Menu.this,Sms.class);
                startActivity(i);
            }
        });

        i11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v1.vibrate(50);
                Intent i = new Intent(Menu.this,Wifi.class);
                startActivity(i);
            }
        });

        i12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v1.vibrate(50);
                Intent i = new Intent(Menu.this,Quiz.class);
                startActivity(i);
            }
        });
    }

}