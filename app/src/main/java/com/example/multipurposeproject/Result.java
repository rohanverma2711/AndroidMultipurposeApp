package com.example.multipurposeproject;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Objects;

public class Result extends AppCompatActivity {
    TextView t1;
    Button b1;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Result");

        t1 = findViewById(R.id.scoretext);
        b1 = findViewById(R.id.myresult);

        t1.setText(Integer.toString(Quiz.score));
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Result.this,Menu.class);
                startActivity(i);
                finish();
            }
        });


    }
}