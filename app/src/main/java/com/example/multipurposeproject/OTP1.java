package com.example.multipurposeproject;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.TimeUnit;

public class OTP1 extends AppCompatActivity {
    TextView t1;
    Button b1;
    MediaPlayer mediaPlayer;
    CountryCodePicker ccp;
    EditText e1;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp1);
        getSupportActionBar().hide();
        mediaPlayer = MediaPlayer.create(this,R.raw.sound2);
        ccp = findViewById(R.id.ccp);
        e1 = findViewById(R.id.edittext1);
        ccp.registerCarrierNumberEditText(e1);
        b1 = findViewById(R.id.button4);
        progressBar = findViewById(R.id.progressBar3);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(e1.getText().toString().isEmpty()){
                    e1.setError("Please enter the mobile number!");
                    return;
                }
                else if(e1.getText().toString().length()!=10){
                    e1.setError("Enter valid mobile number!");
                    return;
                }
                else{
                    progressBar.setVisibility(View.VISIBLE);
                    b1.setVisibility(View.INVISIBLE);
                    
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            ccp.getFullNumberWithPlus(), 60, TimeUnit.SECONDS, OTP1.this,
                            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(@NonNull @NotNull PhoneAuthCredential phoneAuthCredential) {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    b1.setVisibility(View.VISIBLE);
                                }

                                @Override
                                public void onVerificationFailed(@NonNull @NotNull FirebaseException e) {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    b1.setVisibility(View.VISIBLE);
                                    Toast.makeText(OTP1.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCodeSent(@NonNull @NotNull String s, @NonNull @NotNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    super.onCodeSent(s, forceResendingToken);
                                    progressBar.setVisibility(View.INVISIBLE);
                                    b1.setVisibility(View.VISIBLE);
                                    mediaPlayer.start();
                                    Intent i = new Intent(OTP1.this,OTP2.class);
                                    i.putExtra("mobile",ccp.getFullNumberWithPlus().trim());
                                    i.putExtra("otp",s);
                                    startActivity(i);
                                }
                            }
                    );
                }
            }
        });
    }
}