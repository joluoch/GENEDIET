package com.example.genediet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DocLogin extends AppCompatActivity {
    EditText username,password;
    Button signin;
    TextView notreg,Forgotpassword;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_login);
        mAuth = FirebaseAuth.getInstance();
        username = (EditText) findViewById(R.id.docusername);
        password = (EditText) findViewById(R.id.doccpassword);
        signin = (Button) findViewById(R.id.doclogin);
        notreg = (TextView) findViewById(R.id.doccnotreg);
        Forgotpassword = (TextView) findViewById(R.id.doccForgotpassword);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mAuth.getCurrentUser();
                if( mFirebaseUser != null && mFirebaseUser.isEmailVerified() ){
                    Toast.makeText(DocLogin.this,"You are logged in",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(DocLogin.this, Home.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(DocLogin.this,"Please Login",Toast.LENGTH_SHORT).show();
                }
            }
        };

        signin.setOnClickListener(new View.OnClickListener() {
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
                    Toast.makeText(DocLogin.this,"Fields Are Empty!",Toast.LENGTH_SHORT).show();
                }
                else  if(!(email.isEmpty() && pwd.isEmpty()&& mAuth.getCurrentUser().isEmailVerified())){
                    mAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(DocLogin.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful() && mAuth.getCurrentUser().isEmailVerified()){
                                //Toast.makeText(Login.this,"Login Error, Please Login Again",Toast.LENGTH_SHORT).show();
                                Toast.makeText(DocLogin.this,"Please Check Email to verify account",Toast.LENGTH_SHORT).show();
                            }
                            else if(mAuth.getCurrentUser() != null && mAuth.getCurrentUser().isEmailVerified()){
                                Intent intToHome = new Intent(DocLogin.this,DocHome.class);
                                startActivity(intToHome);
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(DocLogin.this,"Error Occurred!",Toast.LENGTH_SHORT).show();

                }

            }
        });

        notreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(DocLogin.this, DocSignup.class);
                startActivity(p);

            }
        });
        Forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(DocLogin.this,password.class);
                startActivity(t);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }
    @Override
    protected void onStop() {
        super.onStop();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

}
