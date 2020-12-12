package com.example.genediet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DocHome extends AppCompatActivity  {
    Spinner spinner1,spinner2,spinner3;
    Button submit;
    DatabaseReference databaseReference;
    ArrayList<String> names;

    ArrayList<String> arraylist_parent;
    ArrayAdapter<String> arrayAdapter_parent;
    ArrayList<String> arrayList_DNAA,arrayList_DNAB,arrayList_DNAC;
    ArrayAdapter<String> arrayAdapter_child;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        spinner1 = (Spinner)findViewById(R.id.spinner1);
        spinner2 = (Spinner)findViewById(R.id.spinner2);
        spinner3 = (Spinner)findViewById(R.id.spinner2);
        submit = (Button) findViewById(R.id.submit);


        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("user").child("patients").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot dataSnapshot) {
                for(DataSnapshot childSnapshot:dataSnapshot.getChildren()) {

                    String spinnername = childSnapshot.child("name").getValue(String.class);
                    names.add(spinnername);
                }
                ArrayAdapter<String> arrayAdapter_dbspinner = new ArrayAdapter<>(getApplicationContext(),
                        android.R.layout.simple_spinner_item,names);
                arrayAdapter_dbspinner.setDropDownViewResource(android.R.layout.simple_spinner_item);
                spinner1.setAdapter(arrayAdapter_dbspinner);

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError databaseError) {

            }
        });

        arraylist_parent = new ArrayList<>();
        arraylist_parent.add("DNA-A");
        arraylist_parent.add("DNA-B");
        arraylist_parent.add("DNA-C");

        arrayAdapter_parent = new ArrayAdapter<>(getApplicationContext(),
                R.layout.textview_dnacat,arraylist_parent);

        spinner2.setAdapter(arrayAdapter_parent);

        //.....child process.......
        arrayList_DNAA = new ArrayList<>();
        arrayList_DNAA.add("ARHGAP31");
        arrayList_DNAA.add("BAP1");
        arrayList_DNAA.add("BMPR1A");
        arrayList_DNAA.add("CAVIN1");
        arrayList_DNAA.add("CEP290");

        arrayList_DNAB = new ArrayList<>();
        arrayList_DNAB.add("CHD7");
        arrayList_DNAB.add("BCR");
        arrayList_DNAB.add("BMPR2");
        arrayList_DNAB.add("CCM2");
        arrayList_DNAB.add("CHM");

        arrayList_DNAC = new ArrayList<>();
        arrayList_DNAC.add("APOB");
        arrayList_DNAC.add("CEP57");
        arrayList_DNAC.add("CLN5");
        arrayList_DNAC.add("CLN6");
        arrayList_DNAC.add("CLN8");

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0)
                {
                    arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(),
                            R.layout.textview_dnasubcat,arrayList_DNAA);
                }
                if(position==1)
                {
                    arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(),
                            R.layout.textview_dnasubcat,arrayList_DNAB);
                }
                if(position==2)
                {
                    arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(),
                            R.layout.textview_dnasubcat,arrayList_DNAC);
                }
                spinner3.setAdapter(arrayAdapter_child);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ////////end process












    }


}