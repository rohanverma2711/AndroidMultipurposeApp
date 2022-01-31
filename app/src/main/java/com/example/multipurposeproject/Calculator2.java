package com.example.multipurposeproject;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Locale;
import java.util.Objects;

public class Calculator2 extends AppCompatActivity {
    ImageView i1,i2,i3,i4;
    Button b1,b2,b3,b4;
    EditText e1,e2,e3;
    TextView t1;
    TextToSpeech ts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator2);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Calculator");

        ts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                ts.setLanguage(Locale.ENGLISH);
                ts.setSpeechRate(1.0f);
            }
        });

        i1 = findViewById(R.id.add);
        i2 = findViewById(R.id.sub);
        i3 = findViewById(R.id.mul);
        i4 = findViewById(R.id.div);

        b1 = findViewById(R.id.sin);
        b2 = findViewById(R.id.cos);
        b3 = findViewById(R.id.tan);
        b4 = findViewById(R.id.clr);

        e1 = findViewById(R.id.etnum1);
        e2 = findViewById(R.id.etnum2);
        e3 = findViewById(R.id.etangle);

        t1 = findViewById(R.id.result);

        i1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                if(s1.isEmpty() || s2.isEmpty()){
                    Toast.makeText(Calculator2.this, "Both fields are required!", Toast.LENGTH_SHORT).show();
                }
                else{
                    float n = Float.parseFloat(s1) + Float.parseFloat(s2);
                    String s = Float.toString(n);
                    t1.setText(Float.toString(n));
                    ts.speak("The result is "+s, TextToSpeech.QUEUE_FLUSH,null);

                }
            }
        });

        i2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                if(s1.isEmpty() || s2.isEmpty()){
                    Toast.makeText(Calculator2.this, "Both fields are required!", Toast.LENGTH_SHORT).show();
                }
                else{
                    float n = Float.parseFloat(s1) - Float.parseFloat(s2);
                    String s = Float.toString(n);
                    t1.setText(s);
                    ts.speak("The result is "+s, TextToSpeech.QUEUE_FLUSH,null);


                }
            }
        });

        i3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                if(s1.isEmpty() || s2.isEmpty()){
                    Toast.makeText(Calculator2.this, "Both fields are required!", Toast.LENGTH_SHORT).show();
                }
                else{
                    float n = Float.parseFloat(s1) * Float.parseFloat(s2);
                    String s = Float.toString(n);
                    String st = s.substring(0,4);
                    t1.setText(st);
                    ts.speak("The result is "+st, TextToSpeech.QUEUE_FLUSH,null);

                }
            }
        });

        i4.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                if(s1.isEmpty() || s2.isEmpty()){
                    Toast.makeText(Calculator2.this, "Both fields are required!", Toast.LENGTH_SHORT).show();
                }
                else{
                    float n = Float.parseFloat(s1) / Float.parseFloat(s2);
                    String s = Float.toString(n);
                    t1.setText(s);
                    ts.speak("The result is "+s, TextToSpeech.QUEUE_FLUSH,null);
                }
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"SetTextI18n", "DefaultLocale"})
            @Override
            public void onClick(View v) {
                String s3 = e3.getText().toString();
                if(s3.isEmpty()){
                    e3.setError("This field is required!");
                    return;
                }
                double deg = Double.parseDouble(s3);
                double rad = Math.toRadians(deg);
                String d = Double.toString(Math.sin(rad));
                String st = d.substring(0,3);
                t1.setText(st);
                ts.speak("The result is "+st,TextToSpeech.QUEUE_FLUSH,null);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"SetTextI18n", "DefaultLocale"})
            @Override
            public void onClick(View v) {
                String s3 = e3.getText().toString();
                if(s3.isEmpty()){
                    e3.setError("This field is required!");
                    return;
                }
                double deg = Double.parseDouble(s3);
                double rad = Math.toRadians(deg);
                String d = Double.toString(Math.cos(rad));
                String st = d.substring(0,3);
                t1.setText(st);
                ts.speak("The result is "+st,TextToSpeech.QUEUE_FLUSH,null);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"SetTextI18n", "DefaultLocale"})
            @Override
            public void onClick(View v) {
                String s3 = e3.getText().toString();
                if(s3.isEmpty()){
                    e3.setError("This field is required!");
                    return;
                }
                double deg = Double.parseDouble(s3);
                double rad = Math.toRadians(deg);
                String d = Double.toString(Math.tan(rad));
                String st = d.substring(0,3);
                t1.setText(st);
                ts.speak("The result is "+st,TextToSpeech.QUEUE_FLUSH,null);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e1.setText("");
                e2.setText("");
                e3.setText("");
            }
        });
    }
}