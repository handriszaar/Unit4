package com.example.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import id.ac.polinema.intentexercise.model.User;

public class ProfileActivity extends AppCompatActivity {
    private TextView tvAbout, tvFullname, tvEmail, tvHomepage;
    private ImageView imgProfile;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvAbout = findViewById(R.id.label_about);
        tvFullname = findViewById(R.id.label_fullname);
        tvEmail = findViewById(R.id.label_email);
        tvHomepage = findViewById(R.id.label_homepage);
        imgProfile = findViewById(R.id.image_profile_picture);

        user = getIntent().getParcelableExtra("user");
        tvAbout.setText(user.getAbout());
        tvFullname.setText(user.getFullname());
        tvEmail.setText(user.getEmail());
        tvHomepage.setText(user.getHomepage());

        if (user.getProfile() != null)
            try {
                Uri imageUri = Uri.parse(user.getProfile());
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                imgProfile.setImageBitmap(bitmap);
            } catch (Exception e) {
                Log.e(this.getClass().getCanonicalName(), e.getMessage());
                Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
            }
    }

    public void handleVisit(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(user.getHomepage())));
    }
}