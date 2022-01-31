package com.example.multipurposeproject;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import org.jetbrains.annotations.NotNull;

public class OTP2 extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6;
    Button b1;
    FirebaseAuth firebaseAuth;
    String phone,otp;
    MediaPlayer mediaPlayer,mediaPlayer1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp2);
        b1 = findViewById(R.id.button4);
        mediaPlayer = MediaPlayer.create(this, R.raw.sound4);
        mediaPlayer1 = MediaPlayer.create(this,R.raw.sound2);
        e1 = findViewById(R.id.o1);
        e2 = findViewById(R.id.o2);
        e3 = findViewById(R.id.o3);
        e4 = findViewById(R.id.o4);
        e5 = findViewById(R.id.o5);
        e6 = findViewById(R.id.o6);
        final ProgressBar progressBar = findViewById(R.id.progressBar4);
        firebaseAuth = FirebaseAuth.getInstance();
        phone = getIntent().getStringExtra("mobile").toString();
        otp = getIntent().getStringExtra("otp").toString();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!e1.getText().toString().isEmpty() && !e2.getText().toString().isEmpty() && !e3.getText().toString().isEmpty() && !e4.getText().toString().isEmpty() && !e5.getText().toString().isEmpty() && !e6.getText().toString().isEmpty()){
                      String s1 = e1.getText().toString() +
                              e2.getText().toString() +
                              e3.getText().toString() +
                              e4.getText().toString() +
                              e5.getText().toString() +
                              e6.getText().toString();
                      if(otp!=null){
                          progressBar.setVisibility(View.VISIBLE);
                          b1.setVisibility(View.INVISIBLE);

                          PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(otp,s1);

                          firebaseAuth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                              @Override
                              public void onComplete(@NonNull @NotNull Task<AuthResult> task) {

                                  if(task.isSuccessful()){
                                      mediaPlayer1.start();
                                      Intent i = new Intent(OTP2.this, Loading.class);
                                      i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                      startActivity(i);
                                  }
                                  else{
                                      Toast.makeText(OTP2.this, "Enter the correct OTP!", Toast.LENGTH_SHORT).show();
                                  }
                              }
                          });
                      }
                      else{
                          Toast.makeText(OTP2.this, "Check your internet connection!", Toast.LENGTH_SHORT).show();
                      }
                }
                else{
                    Toast.makeText(OTP2.this, "Please fill complete OTP!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Warning! You can not switch at this point.");
        builder.setNegativeButton("OK",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert=builder.create();
        alert.show();
    }

}
