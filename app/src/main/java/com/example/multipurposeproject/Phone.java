package com.example.multipurposeproject;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import java.util.Objects;

public class Phone extends AppCompatActivity {
    EditText e1;
    ImageView i1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Phone");
        e1 = findViewById(R.id.etnumber2);
        i1 = findViewById(R.id.imageView40);

        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = e1.getText().toString();
                if(s.isEmpty()){
                    e1.setError("Please enter the mobile number!");
                    return;
                }
                else{
                    if(s.length()!=10){
                        e1.setError("Enter valid mobile number!");
                        return;
                    }
                    dialContactPhone(e1.getText().toString());

                }
            }
        });
    }
    private void dialContactPhone(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }
}