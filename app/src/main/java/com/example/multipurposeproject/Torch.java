package com.example.multipurposeproject;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Objects;

public class Torch extends AppCompatActivity {
    CameraManager cm;
    ImageView i1;
    TextView t1;
    Button b1;
    private boolean b = false;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_torch);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Torch");
        cm = (CameraManager) getSystemService(CAMERA_SERVICE);
        i1 = findViewById(R.id.imageView27);
        t1 = findViewById(R.id.textView29);
        b1 = findViewById(R.id.button9);

        i1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if(!b){
                    try {
                        String s = cm.getCameraIdList()[0];
                        cm.setTorchMode(s,true);
                        i1.setImageResource(R.drawable.on);
                        t1.setText("ON");
                        b = true;

                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }

                }

                else{
                    try {
                        String s = cm.getCameraIdList()[0];
                        cm.setTorchMode(s,false);
                        i1.setImageResource(R.drawable.off);
                        t1.setText("OFF");
                        b = false;

                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Torch.this,Menu.class);
                startActivity(i);
            }
        });

    }
}