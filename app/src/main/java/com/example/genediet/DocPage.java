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

public class DocPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinnerdna,spinnerdnb,spinnerdnc,dnaval,dnbval,dncval;
    Button btnsubmit;
    FirebaseAuth mFirebaseAuth;
    FirebaseUser mFirebaseUser;
    FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_page);

        spinnerdna = (Spinner)findViewById(R.id.doctorspinner);
        spinnerdnb = (Spinner)findViewById(R.id.doctorspinner3);
        spinnerdnc = (Spinner)findViewById(R.id.doctorspinner5);
        dnaval = (Spinner)findViewById(R.id.doctorspinner2);
        dnbval = (Spinner)findViewById(R.id.doctorspinner4);
        dncval = (Spinner)findViewById(R.id.doctorspinner6);
        btnsubmit = (Button) findViewById(R.id.doctorsubmit);

        //DNA

        spinnerdna.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> dnb = ArrayAdapter.createFromResource(
                this,
                R.array.DNA_B,
                android.R.layout.simple_spinner_item
        );
        dnb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerdnb.setAdapter(dnb);
        spinnerdnb.setOnItemSelectedListener(this);


        ArrayAdapter<CharSequence> dnc = ArrayAdapter.createFromResource(
                this,
                R.array.DNA_C,
                android.R.layout.simple_spinner_item
        );
        dnc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerdnc.setAdapter(dnc);
        spinnerdnc.setOnItemSelectedListener(this);

        //DNA  END

        //VALUE

        dnaval.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> valdnb = ArrayAdapter.createFromResource(
                this,
                R.array.values,
                android.R.layout.simple_spinner_item
        );
        valdnb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dnbval.setAdapter(valdnb);
        dnbval.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> valdnc = ArrayAdapter.createFromResource(
                this,
                R.array.values,
                android.R.layout.simple_spinner_item
        );
        valdnc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dncval.setAdapter(valdnc);
        dncval.setOnItemSelectedListener(this);

        //VALUE END


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
                startActivity(new Intent(DocPage.this, settings.class));
                break;
            }
            case R.id.profileMenu:
                startActivity(new Intent(DocPage.this, Userprofile.class));
                break;

            case R.id.logoutMenu:
                FirebaseAuth.getInstance().signOut();
                Intent intToMain = new Intent(DocPage.this, DocPat.class);
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