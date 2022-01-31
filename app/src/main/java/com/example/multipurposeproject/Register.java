package com.example.multipurposeproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.jetbrains.annotations.NotNull;

public class Register extends AppCompatActivity {
    EditText e1,e2,e3,e4;
    ImageView i1;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
    MediaPlayer mediaPlayer;
    Vibrator v1;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        e1 = findViewById(R.id.name1);
        e2 = findViewById(R.id.email);
        e3 = findViewById(R.id.password);
        e4 = findViewById(R.id.number);
        i1 = findViewById(R.id.imageView15);
        v1 = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        progressBar = findViewById(R.id.progressBar2);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        mediaPlayer = MediaPlayer.create(this,R.raw.sound2);

        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference = firebaseDatabase.getReference("users");
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                String s3 = e3.getText().toString();
                String s4 = e4.getText().toString();
                int index = s2.indexOf('@');

                if(s1.isEmpty()){
                    e1.setError("Name is required!");
                    return;
                }
                else if(s2.isEmpty()){
                    e2.setError("Email is required!");
                    return;
                }
                else if(!s2.substring(index+1).equals("gmail.com")){
                    e2.setError("Incorrect email!!");
                    return;
                }
                else if(s4.isEmpty()){
                    e4.setError("Phone number is required!");
                    return;
                }
                else if(s4.length()!=10){
                    e4.setError("Enter valid mobile number!");
                    return;
                }
                else{
                    if(s3.isEmpty()){
                        e3.setError("Password is required!");
                        return;
                    }
                    else if(s3.length()<6){
                        Toast.makeText(Register.this, "Password should be of length 6 or more!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.createUserWithEmailAndPassword(s2,s3).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            // Saving data in Realtime Database.
                            Users users = new Users(s1,s2,s3,s4);
                            databaseReference.child(s4).setValue(users);

                            // Creating SQL database , Checking user data and adding new data to database.
                            SQLiteDatabase sql = openOrCreateDatabase("Details",MODE_PRIVATE,null);
                            sql.execSQL("create table if not exists User (Name varchar, Email varchar, Password integer, Number integer)");
                            String s = "select * from User where Email='"+s2+"'";
                            Cursor c1 = sql.rawQuery(s,null);
                            if(c1.getCount()>0){
                                Toast.makeText(Register.this, "User already exist!!", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                sql.execSQL("insert into User values ('"+s1+"','"+s2+"','"+s3+"','"+s4+"')");
                                Toast.makeText(Register.this, "Added in SQL!!", Toast.LENGTH_SHORT).show();
                            }
                            // Clearing the fields.
                            e1.setText("");
                            e2.setText("");
                            e3.setText("");
                            e4.setText("");
                            mediaPlayer.start();
                            Toast.makeText(Register.this, "Account created successfully!!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(Register.this, "User already exist!", Toast.LENGTH_SHORT).show();

                        }
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
            }
        });
    }
}