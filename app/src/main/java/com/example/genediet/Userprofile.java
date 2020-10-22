package com.example.genediet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.jetbrains.annotations.NotNull;

public class Userprofile extends AppCompatActivity {
    ImageView profileimage;
    TextView profilename, profileage, profilegene, profileweight;
    Button profileupdate;
    String userID;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    FirebaseStorage firebaseStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);

        profileimage = (ImageView) findViewById(R.id.profileimagedp);
        profilename = (TextView) findViewById(R.id.profilename);
        profileage = (TextView) findViewById(R.id.profileage);
        profilegene = (TextView) findViewById(R.id.profilegene);
        profileweight = (TextView) findViewById(R.id.profileweight);
        profileupdate = (Button) findViewById(R.id.login);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseAuth = FirebaseAuth.getInstance();
        userID = firebaseAuth.getCurrentUser().getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        DatabaseReference databaseReference = firebaseDatabase.getReference().child("user").child("patients").child(userID);;


        /*StorageReference storageReference = firebaseStorage.getReference();
        storageReference.child(firebaseAuth.getUid()).child("Images/Profile Pic").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).fit().centerCrop().into(profilePic);
            }
        });*/

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot dataSnapshot) {
                Profile profile = dataSnapshot.getValue(Profile.class);
                profilename.setText("Name: " + profile.getName());
                profileage.setText("Age: " + profile.getAge());
                profilegene.setText("Email: " + profile.getGenetype());
                profileweight.setText("Email: " + profile.getWeight());

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError databaseError) {
                Toast.makeText(Userprofile.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();


            }
        });

        profileupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Userprofile.this, UpdateProfile.class));

            }
        });


    }
}