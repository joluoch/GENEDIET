package com.example.genediet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DocPat extends AppCompatActivity {
    Button Doc,patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_pat);

        Doc = (Button) findViewById(R.id.doctor);
        patient = (Button) findViewById(R.id.patient);

        Doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(DocPat.this,DocLogin.class);
                startActivity(p);
            }
        });
        patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(DocPat.this,Login.class);
                startActivity(p);

            }
        });
    }
}