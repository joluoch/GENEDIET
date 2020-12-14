package com.example.genediet;

import androidx.annotation.NonNull;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DocHome extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner Dnaaspinner,Dnabspinner,Dnacspinner,Dnaaspinnerval,Dnabspinnerval,Dnacspinnerval;
    Button submit;
    //DatabaseReference databaseReference;
    //ArrayList<String> names;

    /*ArrayList<String> arraylist_parent;
    ArrayAdapter<String> arrayAdapter_parent;
    ArrayList<String> arrayList_DNAA,arrayList_DNAB,arrayList_DNAC;
    ArrayAdapter<String> arrayAdapter_child;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Dnaaspinner = (Spinner)findViewById(R.id.spinner1);
        Dnabspinner = (Spinner)findViewById(R.id.spinner3);
        Dnacspinner = (Spinner)findViewById(R.id.spinner5);
        Dnaaspinnerval = (Spinner)findViewById(R.id.spinner2);
        Dnabspinnerval = (Spinner)findViewById(R.id.spinner4);
        Dnacspinnerval = (Spinner)findViewById(R.id.spinner6);
        submit = (Button) findViewById(R.id.docsubmit);




      ///DNA  SPINNERS
        Dnaaspinner.setOnItemSelectedListener(this);


        ArrayAdapter<CharSequence> dnab = ArrayAdapter.createFromResource(
                this,
                R.array.DNA_B,
                android.R.layout.simple_spinner_item
        );
        dnab.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Dnabspinner.setAdapter(dnab);
        Dnabspinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> dnac = ArrayAdapter.createFromResource(
                this,
                R.array.DNA_C,
                android.R.layout.simple_spinner_item
        );
        dnac.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Dnacspinner.setAdapter(dnac);
        Dnacspinner.setOnItemSelectedListener(this);
        ///END DNA VALUE SPINNER

        ///START VALUE

        Dnaaspinnerval.setOnItemSelectedListener(this);



        ArrayAdapter<CharSequence> dnabval = ArrayAdapter.createFromResource(
                this,
                R.array.values,
                android.R.layout.simple_spinner_item
        );
        dnabval.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Dnabspinnerval.setAdapter(dnabval);
        Dnabspinnerval.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> dnacvalue = ArrayAdapter.createFromResource(
                this,
                R.array.values,
                android.R.layout.simple_spinner_item
        );
        dnacvalue.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Dnacspinnerval.setAdapter(dnacvalue);
        Dnacspinnerval.setOnItemSelectedListener(this);

        ///END


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

            case R.id.logoutMenu:
                FirebaseAuth.getInstance().signOut();
                Intent intToMain = new Intent(DocHome.this, DocPat.class);
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