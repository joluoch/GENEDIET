package com.example.genediet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class settings extends AppCompatActivity {

    EditText fullname,age,genetype,weight;
    Button btnsave,btnback;
    ImageView mprofile;
    FirebaseAuth mAuth;
    DatabaseReference mPatientDatabase;
    String userID,mName,mAge,mGenetype,mWeight,mdp;
    Uri resultUri;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        fullname = (EditText)findViewById(R.id.fullname);
        age = (EditText)findViewById(R.id.age);
        mprofile = (ImageView)findViewById(R.id.profileimage);
        genetype = (EditText)findViewById(R.id.genetype);
        weight = (EditText)findViewById(R.id.weight);
        btnsave = (Button)findViewById(R.id.Save);
        btnback = (Button)findViewById(R.id.back);


        mAuth = FirebaseAuth.getInstance();
        userID = mAuth.getCurrentUser().getUid();
        mPatientDatabase = FirebaseDatabase.getInstance().getReference().child("user").child("patients").child(userID);

        getUserInfo();

        mprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });


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
                    if(map.get("ProfileImageUrl")!=null){
                        mdp = map.get("ProfileImageUrl").toString();
                        Glide.with(getApplication()).load(mdp).into(mprofile);
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

        if(resultUri != null){
            StorageReference filepath = FirebaseStorage.getInstance().getReference().child("profile_image").child(userID);
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getApplication().getContentResolver(),resultUri);
            } catch (IOException e) {
                e.printStackTrace();
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
            byte[] data = baos.toByteArray();
            UploadTask uploadTask= filepath.putBytes(data);

            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    finish();
                    return;

                }
            });

            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//check this
                    //Task<Uri> downloadUrl = taskSnapshot.getStorage().getDownloadUrl();
                    Uri downloadurl = taskSnapshot.getUploadSessionUri();
                    Map newImage = new HashMap();
                    newImage.put("ProfileImageUrl",downloadurl.toString());
                    mPatientDatabase.updateChildren(newImage);

                    finish();
                    return;

                }
            });
        }else {

            finish();
        }



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 1 && requestCode == Activity.RESULT_OK){
            final Uri imageUri= data.getData();
            resultUri = imageUri;
            mprofile.setImageURI(resultUri);
        }
    }
}
