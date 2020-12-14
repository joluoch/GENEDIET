package com.example.genediet;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinnerprot,spinnerstarch,spinnervit,valprot,valstarch,valvit;
    Button submit;
    FirebaseAuth mFirebaseAuth;
    FirebaseUser mFirebaseUser;
    FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        spinnerprot = (Spinner)findViewById(R.id.patspinner);
        spinnerstarch = (Spinner)findViewById(R.id.patspinner3);
        spinnervit = (Spinner)findViewById(R.id.patspinner5);
        valprot = (Spinner)findViewById(R.id.patspinner2);
        valstarch = (Spinner)findViewById(R.id.patspinner4);
        valvit = (Spinner)findViewById(R.id.patspinner6);
        submit = (Button) findViewById(R.id.patsubmit);

        ///FOOD


        spinnerprot.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> starch = ArrayAdapter.createFromResource(
                this,
                R.array.STARCH,
                android.R.layout.simple_spinner_item
        );
        starch.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerstarch.setAdapter(starch);
        spinnerstarch.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> vitamins = ArrayAdapter.createFromResource(
                this,
                R.array.VITAMINS,
                android.R.layout.simple_spinner_item
        );
        vitamins.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnervit.setAdapter(vitamins);
        spinnervit.setOnItemSelectedListener(this);
        ///END FOOD

        valprot.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> valst = ArrayAdapter.createFromResource(
                this,
                R.array.quantity,
                android.R.layout.simple_spinner_item
        );
        valst.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        valstarch.setAdapter(valst);
        valstarch.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> vitvalue = ArrayAdapter.createFromResource(
                this,
                R.array.quantity,
                android.R.layout.simple_spinner_item
        );
        vitvalue.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        valvit.setAdapter(vitvalue);
        valvit.setOnItemSelectedListener(this);






    }
    @SuppressLint("ResourceType")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.registerMenu:{
                startActivity(new Intent(Home.this, settings.class));
                break;
            }
            case R.id.profileMenu:
                startActivity(new Intent(Home.this, Userprofile.class));
                break;
            case R.id.DoctorMenu:
                startActivity(new Intent(Home.this, DocHome.class));
                break;
            case R.id.logoutMenu:
                FirebaseAuth.getInstance().signOut();
                Intent intToMain = new Intent(Home.this, DocPat.class);
                startActivity(intToMain);
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}



