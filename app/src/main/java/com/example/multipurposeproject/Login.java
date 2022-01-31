package com.example.multipurposeproject;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import org.jetbrains.annotations.NotNull;

public class Login extends AppCompatActivity {
    EditText e1,e2;
    ImageView i1,i2;
    TextView t1;
    SignInButton signInButton;
    FirebaseAuth firebaseAuth;
    GoogleSignInClient googleSignInClient;
    ProgressBar progressBar;
    Vibrator v1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.sound2);
        MediaPlayer mediaPlayer1 = MediaPlayer.create(this,R.raw.sound5);
        t1 = findViewById(R.id.textView11);
        i1 = findViewById(R.id.imageView15);
        i2 = findViewById(R.id.imageView10);
        e1 = findViewById(R.id.name1);
        e2 = findViewById(R.id.password);
        v1 = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        progressBar = findViewById(R.id.progressBar);
        signInButton = findViewById(R.id.signin);
        firebaseAuth = FirebaseAuth.getInstance();

        SpannableString content = new SpannableString(t1.getText());
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        t1.setText(content);

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken("323778330959-e7i13o7luaasd3v5jr7c1qdafig8vqi1.apps.googleusercontent.com").requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(Login.this,googleSignInOptions);
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                  String s1 = e1.getText().toString();
                  String s2 = e2.getText().toString();
                  int index = s1.indexOf('@');
                  if(s1.isEmpty()){
                      e1.setError("Email is required!");
                      return;
                  }
                  else if(!s1.substring(index+1).equals("gmail.com")){
                      e1.setError("Incorrect Email");
                      return;
                  }
                  else{
                      if(s2.isEmpty()){
                          e2.setError("Password is required!");
                          return;
                      }
                  }

                  firebaseAuth.signInWithEmailAndPassword(s1,s2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                      @Override
                      public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                          if(task.isSuccessful()){
                              mediaPlayer.start();
                              Intent i = new Intent(Login.this, Loading.class);
                              startActivity(i);
                              finish();
                          }
                          else{
                              //################### SQLite database Code #####################
                              SQLiteDatabase sql = openOrCreateDatabase("Details",MODE_PRIVATE,null);
                              String s = "select * from User where Email='"+s1+"'";
                              Cursor c1 = sql.rawQuery(s,null);
                              if(c1.getCount()>0){
                                  mediaPlayer.start();
                                  Intent i = new Intent(Login.this, Loading.class);
                                  startActivity(i);
                                  finish();
                              }
                              else{
                                  mediaPlayer1.start();
                                  Toast.makeText(Login.this, "No such user exist!!", Toast.LENGTH_SHORT).show();
                              }
                          }
                      }
                  });
            }
        });



        // Moving from Login Activity to OTP1 Activity.
        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v1.vibrate(50);
                Intent i = new Intent(Login.this, OTP1.class);
                startActivity(i);
            }
        });



        // Moving from Login Activity to Register Activity.
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v1.vibrate(50);
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });



        // Google signin button.
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = googleSignInClient.getSignInIntent();
                startActivityForResult(i,65);
            }
        });
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            Intent i = new Intent(Login.this, Menu.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==65){
            Task<GoogleSignInAccount> signInAccountTask=GoogleSignIn.getSignedInAccountFromIntent(data);
            if(signInAccountTask.isSuccessful()){
                try{
                    GoogleSignInAccount googleSignInAccount = signInAccountTask.getResult(ApiException.class);
                    if(googleSignInAccount!=null){
                        AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(),null);
                        firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Intent k = new Intent(Login.this, Loading.class);
                                    startActivity(k);
                                    finish();
                                }
                                else{
                                    Toast.makeText(Login.this, "Not saved!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
                catch (ApiException e){
                    e.printStackTrace();
                }
            }
        }
    }
}