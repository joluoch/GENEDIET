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
    Spinner spprot1,spprot2,spprot3,spst1,spst2,spvit1,spvit2,spvit3,spvit4,spvit5;
    Button submit;
    FirebaseAuth mFirebaseAuth;
    FirebaseUser mFirebaseUser;
    FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        spprot1 = (Spinner)findViewById(R.id.spinnerprot1);
        spprot2 = (Spinner)findViewById(R.id.spinnerprot2);
        spprot3 = (Spinner)findViewById(R.id.spinnerprot3);
        spst1 = (Spinner)findViewById(R.id.spinnerstarch1);
        spst2 = (Spinner)findViewById(R.id.spinnerstarch2);
        spvit1 = (Spinner)findViewById(R.id.spinnervit1);
        spvit2 = (Spinner)findViewById(R.id.spinnervit2);
        spvit3 = (Spinner)findViewById(R.id.spinnervit3);
        spvit4 = (Spinner)findViewById(R.id.spinnervit4);
        spvit5 = (Spinner)findViewById(R.id.spinnervit5);
        submit = (Button) findViewById(R.id.patsubmit);

        ///protein

        spprot1.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> prot2 = ArrayAdapter.createFromResource(
                this,
                R.array.quantity,
                android.R.layout.simple_spinner_item
        );
        prot2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spprot2.setAdapter(prot2);
        spprot2.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> prot3 = ArrayAdapter.createFromResource(
                this,
                R.array.quantity,
                android.R.layout.simple_spinner_item
        );
        prot3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spprot3.setAdapter(prot3);
        spprot3.setOnItemSelectedListener(this);
        //end protein

        //start starch

        ArrayAdapter<CharSequence> st1 = ArrayAdapter.createFromResource(
                this,
                R.array.quantity,
                android.R.layout.simple_spinner_item
        );
        st1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spst1.setAdapter(st1);
        spst1.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> st2 = ArrayAdapter.createFromResource(
                this,
                R.array.quantity,
                android.R.layout.simple_spinner_item
        );
        st2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spst2.setAdapter(st2);
        spst2.setOnItemSelectedListener(this);

        //end starch

        //start vitamins

        ArrayAdapter<CharSequence> vit1 = ArrayAdapter.createFromResource(
                this,
                R.array.quantity,
                android.R.layout.simple_spinner_item
        );
        vit1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spvit1.setAdapter(vit1);
        spvit1.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> vit2 = ArrayAdapter.createFromResource(
                this,
                R.array.quantity,
                android.R.layout.simple_spinner_item
        );
        vit2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spvit2.setAdapter(vit2);
        spvit2.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> vit3 = ArrayAdapter.createFromResource(
                this,
                R.array.quantity,
                android.R.layout.simple_spinner_item
        );
        vit3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spvit3.setAdapter(vit3);
        spvit3.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> vit4 = ArrayAdapter.createFromResource(
                this,
                R.array.quantity,
                android.R.layout.simple_spinner_item
        );
        vit4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spvit4.setAdapter(vit4);
        spvit4.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> vit5 = ArrayAdapter.createFromResource(
                this,
                R.array.quantity,
                android.R.layout.simple_spinner_item
        );
        vit5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spvit5.setAdapter(vit5);
        spvit5.setOnItemSelectedListener(this);

        //end vitamins


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
                startActivity(new Intent(Home.this, DocPage.class));
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



