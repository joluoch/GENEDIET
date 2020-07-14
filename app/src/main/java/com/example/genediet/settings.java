package com.example.genediet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class settings extends AppCompatActivity {

    EditText fullname,age,genetype,weight;
    Button btnsave,btnback;
    FirebaseAuth mAuth;
    DatabaseReference mPatientDatabase;
    String userID,mName,mAge,mGenetype,mWeight;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        fullname = (EditText)findViewById(R.id.fullname);
        age = (EditText)findViewById(R.id.age);
        genetype = (EditText)findViewById(R.id.genetype);
        weight = (EditText)findViewById(R.id.weight);
        btnsave = (Button)findViewById(R.id.Save);
        btnback = (Button)findViewById(R.id.back);


        mAuth = FirebaseAuth.getInstance();
        userID = mAuth.getCurrentUser().getUid();
        mPatientDatabase = FirebaseDatabase.getInstance().getReference().child("user").child("patients").child(userID);

        getUserInfo();

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveUserInformation();

            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

                return;
            }
        });

    }
    private void getUserInfo(){

        mPatientDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()&& dataSnapshot.getChildrenCount()>0){
                    Map<String, Object> map = (Map<String, Object>)dataSnapshot.getValue();
                    if(map.get("name")!=null){
                        mName = map.get("name").toString();
                        fullname.setText(mName);
                    }
                    if(map.get("age")!=null){
                        mAge = map.get("age").toString();
                        age.setText(mAge);
                    }
                    if(map.get("genetype")!=null){
                        mGenetype = map.get("genetype").toString();
                        genetype.setText(mGenetype);
                    }
                    if(map.get("weight")!=null){
                        mWeight = map.get("weight").toString();
                        weight.setText(mWeight);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void saveUserInformation() {

        mName = fullname.getText().toString();
        mAge = age.getText().toString();
        mGenetype = genetype.getText().toString();
        mWeight = weight.getText().toString();

        Map userInfo = new HashMap();
        userInfo.put("name",mName);
        userInfo.put("age",mAge);
        userInfo.put("genetype",mGenetype);
        userInfo.put("weight",mWeight);
        mPatientDatabase.updateChildren(userInfo);

        finish();
    }


}
