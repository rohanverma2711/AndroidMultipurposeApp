package com.example.multipurposeproject;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import java.util.Objects;

public class Quiz3 extends AppCompatActivity {
    RadioButton r1,r2,r3,r4;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz3);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Question 3");

        r1 = findViewById(R.id.radioButton);
        r2 = findViewById(R.id.radioButton2);
        r3 = findViewById(R.id.radioButton3);
        r4 = findViewById(R.id.radioButton4);
        b1 = findViewById(R.id.but3);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(r2.isChecked()){
                    ++Quiz.score;
                }
                else{
                    --Quiz.score;
                }

                Intent i = new Intent(Quiz3.this,Result.class);
                startActivity(i);
                finish();
            }
        });
    }
}