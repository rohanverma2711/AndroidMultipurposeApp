package com.example.multipurposeproject;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {




    TextView t1;
    Button b1;

    //@SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = b1.findViewById(R.id.button);
        t1 = t1.findViewById(R.id.textView5);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, Login.class);
                startActivity(i);
            }

            private void startActivity(Intent i) {
            }
        });
        SpannableString content = new SpannableString(t1.getText());
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        t1.setText(content);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent j = new Intent(MainActivity.this, Register.class);
                startActivity(j);

            }
        });
    }


}
