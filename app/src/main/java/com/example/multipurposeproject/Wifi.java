package com.example.multipurposeproject;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Objects;

public class Wifi extends AppCompatActivity {
    WifiManager wifiManager;
    ImageView i1;
    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Wifi");

        i1 = findViewById(R.id.imageView39);
        t1 = findViewById(R.id.textView39);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);

        i1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (t1.getText().toString().equals("OFF")) {
                    wifiManager.setWifiEnabled(true);
                    i1.setImageResource(R.drawable.wifion);
                    t1.setText("ON");
                } else {
                    wifiManager.setWifiEnabled(false);
                    i1.setImageResource(R.drawable.wifioff);
                    t1.setText("OFF");
                }
            }
        });

    }
}