package com.example.multipurposeproject;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import java.util.Objects;

public class Quiz extends AppCompatActivity {
    RadioButton r1,r2,r3,r4;
    Button b1;
    static int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Quiz");

        r1 = findViewById(R.id.radioButton);
        r2 = findViewById(R.id.radioButton2);
        r3 = findViewById(R.id.radioButton3);
        r4 = findViewById(R.id.radioButton4);
        b1 = findViewById(R.id.but1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score = 0;
                if(r4.isChecked()){
                    ++score;
                }
                else{
                    --score;
                }

                Intent i = new Intent(Quiz.this,Quiz2.class);
                startActivity(i);
                finish();
            }
        });

    }
}