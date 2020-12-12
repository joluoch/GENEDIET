package com.example.genediet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DocSignup extends AppCompatActivity {
    EditText username, password,repeatPasword;
    Button btnSignUp;
    TextView haveAc;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_signup);
        mAuth = FirebaseAuth.getInstance();
        username = (EditText) findViewById(R.id.docregmail);
        password = (EditText) findViewById(R.id.docregpassword);
        //repeatPasword = (EditText) findViewById(R.id.reppassword);
        haveAc = (TextView) findViewById(R.id.dochaveAc);
        btnSignUp = (Button) findViewById(R.id.docSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = username.getText().toString();
                String pwd = password.getText().toString();
                if(email.isEmpty()){
                    username.setError("Please enter email id");
                    username.requestFocus();
                }
                else  if(pwd.isEmpty()){
                    password.setError("Please enter your password");
                    password.requestFocus();
                }
                else  if(email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(DocSignup.this,"Fields Are Empty!",Toast.LENGTH_SHORT).show();
                }
                else  if(!(email.isEmpty() && pwd.isEmpty())){
                    mAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(DocSignup.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(DocSignup.this,"SignUp Unsuccessful, Please Try Again",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                mAuth.getCurrentUser().sendEmailVerification()
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(DocSignup.this,"SignUp Successful . Please check email",Toast
                                                            .LENGTH_SHORT).show();
                                                    String user_id = mAuth.getCurrentUser().getUid();
                                                    DatabaseReference current_user_db = FirebaseDatabase.getInstance()
                                                            .getReference().child("user").child("Doctors").child(user_id);
                                                    current_user_db.setValue(true);
                                                    Intent e = new Intent(DocSignup.this,DocLogin.class);
                                                    startActivity(e);
                                                    Log.d("TAG", "Email sent.");
                                                }
                                                else {
                                                    Toast.makeText(DocSignup.this,task.getException().getMessage(),Toast
                                                            .LENGTH_SHORT).show();
                                                }
                                            }
                                        });


                            }
                        }
                    });
                }
                else{
                    Toast.makeText(DocSignup.this,"Error Occurred!",Toast.LENGTH_SHORT).show();

                }
            }
        });

        haveAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DocSignup.this,DocLogin.class);
                startActivity(i);
            }
        });
    }
    }
