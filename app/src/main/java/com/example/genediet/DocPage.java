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
    Spinner spdna1,spdna2,spdna3,spdna4,spdna5,spdnb1,
            spdnb2,spdnb3,spdnb4,spdnb5,spdnc1,spdnc2,spdnc3,spdnc4,spdnc5;
    Button btnsubmit;
    FirebaseAuth mFirebaseAuth;
    FirebaseUser mFirebaseUser;
    FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_page);

        spdna1 = (Spinner)findViewById(R.id.spinnera1);
        spdna2 = (Spinner)findViewById(R.id.spinnera2);
        spdna3 = (Spinner)findViewById(R.id.spinnera3);
        spdna4 = (Spinner)findViewById(R.id.spinnera4);
        spdna5 = (Spinner)findViewById(R.id.spinnera5);
        spdnb1 = (Spinner)findViewById(R.id.spinnerb1);
        spdnb2 = (Spinner)findViewById(R.id.spinnerb2);
        spdnb3 = (Spinner)findViewById(R.id.spinnerb3);
        spdnb4 = (Spinner)findViewById(R.id.spinnerb4);
        spdnb5 = (Spinner)findViewById(R.id.spinnerb5);
        spdnc1 = (Spinner)findViewById(R.id.spinnerc1);
        spdnc2 = (Spinner)findViewById(R.id.spinnerc2);
        spdnc3 = (Spinner)findViewById(R.id.spinnerc3);
        spdnc4 = (Spinner)findViewById(R.id.spinnerc4);
        spdnc5 = (Spinner)findViewById(R.id.spinnerc5);


        btnsubmit = (Button) findViewById(R.id.docsubmit);

        //DNA-A

        spdna1.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> dna2 = ArrayAdapter.createFromResource(
                this,
                R.array.values,
                android.R.layout.simple_spinner_item
        );
        dna2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spdna2.setAdapter(dna2);
        spdna2.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> dna3 = ArrayAdapter.createFromResource(
                this,
                R.array.values,
                android.R.layout.simple_spinner_item
        );
        dna3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spdna3.setAdapter(dna3);
        spdna3.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> dna4 = ArrayAdapter.createFromResource(
                this,
                R.array.values,
                android.R.layout.simple_spinner_item
        );
        dna4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spdna4.setAdapter(dna4);
        spdna4.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> dna5 = ArrayAdapter.createFromResource(
                this,
                R.array.values,
                android.R.layout.simple_spinner_item
        );
        dna5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spdna5.setAdapter(dna5);
        spdna5.setOnItemSelectedListener(this);

        //DNA-A  END

        //DNA-B
        ArrayAdapter<CharSequence> dnb1 = ArrayAdapter.createFromResource(
                this,
                R.array.values,
                android.R.layout.simple_spinner_item
        );
        dnb1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spdnb1.setAdapter(dnb1);
        spdnb1.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> dnb2 = ArrayAdapter.createFromResource(
                this,
                R.array.values,
                android.R.layout.simple_spinner_item
        );
        dnb2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spdnb2.setAdapter(dnb2);
        spdnb2.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> dnb3 = ArrayAdapter.createFromResource(
                this,
                R.array.values,
                android.R.layout.simple_spinner_item
        );
        dnb3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spdnb3.setAdapter(dnb3);
        spdnb3.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> dnb4 = ArrayAdapter.createFromResource(
                this,
                R.array.values,
                android.R.layout.simple_spinner_item
        );
        dnb4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spdnb4.setAdapter(dnb4);
        spdnb4.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> dnb5 = ArrayAdapter.createFromResource(
                this,
                R.array.values,
                android.R.layout.simple_spinner_item
        );
        dnb5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spdnb5.setAdapter(dnb5);
        spdnb5.setOnItemSelectedListener(this);

        //DNA-B END

        //DNA-C START
        ArrayAdapter<CharSequence> dnc1 = ArrayAdapter.createFromResource(
                this,
                R.array.values,
                android.R.layout.simple_spinner_item
        );
        dnc1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spdnc1.setAdapter(dnc1);
        spdnc1.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> dnc2 = ArrayAdapter.createFromResource(
                this,
                R.array.values,
                android.R.layout.simple_spinner_item
        );
        dnc2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spdnc2.setAdapter(dnc2);
        spdnc2.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> dnc3 = ArrayAdapter.createFromResource(
                this,
                R.array.values,
                android.R.layout.simple_spinner_item
        );
        dnc3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spdnc3.setAdapter(dnc3);
        spdnc3.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> dnc4 = ArrayAdapter.createFromResource(
                this,
                R.array.values,
                android.R.layout.simple_spinner_item
        );
        dnc4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spdnc4.setAdapter(dnc4);
        spdnc4.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> dnc5 = ArrayAdapter.createFromResource(
                this,
                R.array.values,
                android.R.layout.simple_spinner_item
        );
        dnc5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spdnc5.setAdapter(dnc5);
        spdnc5.setOnItemSelectedListener(this);
        //DNA-C END


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