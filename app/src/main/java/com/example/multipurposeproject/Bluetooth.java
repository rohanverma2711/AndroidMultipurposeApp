package com.example.multipurposeproject;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Bluetooth extends AppCompatActivity {
    ImageView i1;
    TextView t1;
    Button b1,b2;
    BluetoothAdapter ba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        getSupportActionBar().setTitle("Bluetooth");

        i1 = findViewById(R.id.imageView2);
        t1 = findViewById(R.id.textView24);
        b1 = findViewById(R.id.button6);
        b2 = findViewById(R.id.button7);
        ba = BluetoothAdapter.getDefaultAdapter();

        b1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                    ba.enable();
                    i1.setVisibility(View.VISIBLE);
                    i1.setImageResource(R.drawable.bluetooth);
                    t1.setVisibility(View.VISIBLE);
                    t1.setText("Bluetooth enable");
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                ba.disable();
                i1.setImageResource(R.drawable.bluetoothoff);
                t1.setText("Bluetooth disable");
            }
        });


    }
}