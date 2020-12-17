package com.example.genediet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import au.com.bytecode.opencsv.CSVWriter;
import helpers.DBHelper;

public class DocPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spdna1,spdna2,spdna3,spdna4,spdna5,spdnb1,
            spdnb2,spdnb3,spdnb4,spdnb5,spdnc1,spdnc2,spdnc3,spdnc4,spdnc5;
    String item1,item2,item3,item4,item5,item6,item7,item8,item9,item10,item11,item12,item13,item14,item15;
    Button btnsubmit;
    FirebaseAuth mFirebaseAuth;
    FirebaseUser mFirebaseUser;
    FirebaseAuth.AuthStateListener mAuthStateListener;
    public static final int REQUEST_PERMISSIONS = 100;//Any integer
    SQLiteDatabase sqLiteDatabaseObj;
    String SQLiteDataBaseQueryHolder;

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

        spdna1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item1=String.valueOf(parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> dna2 = ArrayAdapter.createFromResource(
                this,
                R.array.values,
                android.R.layout.simple_spinner_item
        );
        dna2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spdna2.setAdapter(dna2);
        spdna2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item2=String.valueOf(parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> dna3 = ArrayAdapter.createFromResource(
                this,
                R.array.values,
                android.R.layout.simple_spinner_item
        );
        dna3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spdna3.setAdapter(dna3);
        spdna3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item3=String.valueOf(parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> dna4 = ArrayAdapter.createFromResource(
                this,
                R.array.values,
                android.R.layout.simple_spinner_item
        );
        dna4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spdna4.setAdapter(dna4);
        spdna4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item4=String.valueOf(parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> dna5 = ArrayAdapter.createFromResource(
                this,
                R.array.values,
                android.R.layout.simple_spinner_item
        );
        dna5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spdna5.setAdapter(dna5);
        spdna5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item5=String.valueOf(parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //DNA-A  END

        //DNA-B
        ArrayAdapter<CharSequence> dnb1 = ArrayAdapter.createFromResource(
                this,
                R.array.values,
                android.R.layout.simple_spinner_item
        );
        dnb1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spdnb1.setAdapter(dnb1);
        spdnb1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item6=String.valueOf(parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> dnb2 = ArrayAdapter.createFromResource(
                this,
                R.array.values,
                android.R.layout.simple_spinner_item
        );
        dnb2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spdnb2.setAdapter(dnb2);
        spdnb2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item7=String.valueOf(parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> dnb3 = ArrayAdapter.createFromResource(
                this,
                R.array.values,
                android.R.layout.simple_spinner_item
        );
        dnb3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spdnb3.setAdapter(dnb3);
        spdnb3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item8=String.valueOf(parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> dnb4 = ArrayAdapter.createFromResource(
                this,
                R.array.values,
                android.R.layout.simple_spinner_item
        );
        dnb4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spdnb4.setAdapter(dnb4);
        spdnb4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item9=String.valueOf(parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> dnb5 = ArrayAdapter.createFromResource(
                this,
                R.array.values,
                android.R.layout.simple_spinner_item
        );
        dnb5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spdnb5.setAdapter(dnb5);
        spdnb5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item10=String.valueOf(parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //DNA-B END

        //DNA-C START
        ArrayAdapter<CharSequence> dnc1 = ArrayAdapter.createFromResource(
                this,
                R.array.values,
                android.R.layout.simple_spinner_item
        );
        dnc1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spdnc1.setAdapter(dnc1);
        spdnc1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item11=String.valueOf(parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> dnc2 = ArrayAdapter.createFromResource(
                this,
                R.array.values,
                android.R.layout.simple_spinner_item
        );
        dnc2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spdnc2.setAdapter(dnc2);
        spdnc2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item12=String.valueOf(parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> dnc3 = ArrayAdapter.createFromResource(
                this,
                R.array.values,
                android.R.layout.simple_spinner_item
        );
        dnc3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spdnc3.setAdapter(dnc3);
        spdnc3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item13=String.valueOf(parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> dnc4 = ArrayAdapter.createFromResource(
                this,
                R.array.values,
                android.R.layout.simple_spinner_item
        );
        dnc4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spdnc4.setAdapter(dnc4);
        spdnc4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item14=String.valueOf(parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> dnc5 = ArrayAdapter.createFromResource(
                this,
                R.array.values,
                android.R.layout.simple_spinner_item
        );
        dnc5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spdnc5.setAdapter(dnc5);
        spdnc5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item15=String.valueOf(parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ActivityCompat.requestPermissions(DocPage.this,
                new String[]{Manifest.permission .WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSIONS);
        //DNA-C END

        /*SQLiteDatabase mydatabase = openOrCreateDatabase("DNA",MODE_PRIVATE,null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS doctors_table(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "ARHGAP31 VARCHAR,BAP1 VARCHAR,BMPR1A VARCHAR,CAVIN1 VARCHAR," +
                "CEP290 VARCHAR,CHD7 VARCHAR,BCR VARCHAR,BMPR2 VARCHAR," +
                "CCM2 VARCHAR,CHM VARCHAR,APOB VARCHAR,CEP57 VARCHAR,CLN5 VARCHAR,CLN6 VARCHAR,CLN8 VARCHAR)");*/
        sqLiteDatabaseObj = openOrCreateDatabase("FoodRec", MODE_PRIVATE, null);
        sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS doctors_table(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,ARHGAP31 VARCHAR,BAP1 VARCHAR,BMPR1A VARCHAR,CAVIN1 VARCHAR, CEP290 VARCHAR,CHD7 VARCHAR,BCR VARCHAR,BMPR2 VARCHAR,CCM2 VARCHAR,CHM VARCHAR,APOB VARCHAR,CEP57 VARCHAR,CLN5 VARCHAR,CLN6 VARCHAR,CLN8 VARCHAR)");
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InsertData();
                mergedView();
            }
        });
    }
    public void InsertData(){
        SQLiteDataBaseQueryHolder = "INSERT INTO doctors_table (ARHGAP31,BAP1,BMPR1A,CAVIN1 ,CEP290,CHD7,BCR,BMPR2 , CCM2 ,CHM ,APOB ,CEP57,CLN5,CLN6,CLN8) VALUES('" + item1 + "', " +
                "'" + item2 + "', '" + item3 + "', '" + item4 + "', '" + item5 + "', '" + item6 + "', '" + item7 + "' , '" + item8 + "', '" + item9 + "', '" + item10 + "', '" + item11 + "'," +
                " '" + item12 + "', '" + item13 + "',+ '" + item14 + "', '" + item15 + "');";


        /*SQLiteDataBaseQueryHolder = "INSERT INTO AndroidJSonTable (ARHGAP31,ARHGAP31,BAP1,BMPR1A,CAVIN1 ,CEP290,CHD7,BCR,BMPR2 ,CCM2 ,CHM ,APOB ,CEP57,CLN5,CLN6,CLN8) VALUES('"+spdna1+"', '"+spdna2+", '"+spdna3+", '"+spdna4+", '"+spdna5+", '"+spdnb1+"" +
                ", '"+spdnb2+"" + ", '"+spdnb3+", '"+spdnb4+", '"+spdnb5+", '"+spdnc1+", '"+spdnc2+", '"+spdnc3+",+ '"+spdnc4+", '"+spdnc5+"');";
*/
        sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);

        Toast.makeText(DocPage.this,"Data Inserted Successfully", Toast.LENGTH_LONG).show();


    }
    public void mergedView(){
        SQLiteDataBaseQueryHolder="CREATE VIEW merged AS SELECT * FROM doctors_table  LEFT OUTER JOIN patients_table";
        sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);
        Toast.makeText(this, "Merged View created successfully", Toast.LENGTH_SHORT).show();

        saveToCSV();
    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
//                                           @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (grantResults.length > 0
//                && grantResults[0] == PackageManager.RE) {
//            goToCamera(null);
//        } else {
//            // permission denied, boo! Disable the
//            // functionality that depends on this permission.
//        }
//    }

    public void saveToCSV(){
        //CHECK IF YOU HAVE WRITE PERMISSIONS OR RETURN
        int permission = ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(), "Storage permissions not granted", Toast.LENGTH_SHORT).show();
            return;
        }
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

        File dbFile=getDatabasePath("FoodRec.db");
        DBHelper dbHelper=new DBHelper(getApplicationContext());
        File export_dire=new File(Environment.getExternalStorageDirectory(),"");
        if (!export_dire.exists()){
            export_dire.mkdirs();
        }
        File file=new File(export_dire,"data.csv");
        try{
            file.createNewFile();
            CSVWriter csvWriter=new CSVWriter(new FileWriter(file));
            sqLiteDatabaseObj=dbHelper.getReadableDatabase();
            Cursor cursorCsv=sqLiteDatabaseObj.rawQuery("SELECT * FROM merged",null);
            csvWriter.writeNext(cursorCsv.getColumnNames());

            while (cursorCsv.moveToNext()){
                String arrStr[]={cursorCsv.getString(0)};
                csvWriter.writeNext(arrStr);
            }
            Toast.makeText(this, "Data exported successfully!!", Toast.LENGTH_SHORT).show();
        csvWriter.close();
        cursorCsv.close();
        }catch (IOException e){
            e.printStackTrace();
        }
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