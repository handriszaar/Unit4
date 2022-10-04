package com.example.intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import id.ac.polinema.intentexercise.model.User;

public class RegisterActivity extends AppCompatActivity {
    private ImageView imageProfile;
    private EditText inputFullname, inputEmail, inputPassword, inputConfirmPassword, inputHomepage, inputAbout;

    private static final String TAG = RegisterActivity.class.getCanonicalName();
    private static final int GALLERY_REQUEST_CODE = 1;

    private Uri imageUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        imageProfile = findViewById(R.id.image_profile);
        inputFullname = findViewById(R.id.text_fullname);
        inputEmail = findViewById(R.id.text_email);
        inputPassword = findViewById(R.id.text_password);
        inputConfirmPassword = findViewById(R.id.text_confirm_password);
        inputHomepage = findViewById(R.id.text_homepage);
        inputAbout = findViewById(R.id.text_about);
    }

    // Fungsi untuk mengecek inputan
    private String checkStringField(EditText et, String msg) {
        if (et.length() == 0) {
            et.requestFocus();
            et.setError(msg);
            return null;
        }
        return et.getText().toString();
    }

    public void handleOk(View view) {
        String profile = null, fullname, email, password, confirmPassword, homepage, about;
        if (imageUri != null) {
            profile = imageUri.toString();
        }
        fullname = checkStringField(inputFullname, "Fullname is required");
        if (fullname == null) return;
        email = checkStringField(inputEmail, "Email is required");
        if (email == null) return;
        password = checkStringField(inputPassword, "Password is required");
        if (password == null) return;
        confirmPassword = checkStringField(inputConfirmPassword, "Password confirmation is required");
        if (confirmPassword == null) return;
        homepage = checkStringField(inputHomepage, "Homepage is required");
        if (homepage == null) return;
        about = checkStringField(inputAbout, "About is required");
        if (about == null) return;

        if (!password.equals(confirmPassword)) {
            inputPassword.setText("");
            inputConfirmPassword.setText("");
            inputPassword.setError("Password doesn't match");
            inputConfirmPassword.setError("Password doesn't match");
            inputPassword.requestFocus();
            return;
        }

        User user = new User(profile, fullname, email, password, homepage, about);
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void handleImageEdit(View view) {
        startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), GALLERY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "Load image cancelled", Toast.LENGTH_SHORT).show();
            return;
        }
        if (requestCode == GALLERY_REQUEST_CODE) {
            if (data != null) {
                try {
                    imageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    imageProfile.setImageBitmap(bitmap);
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                }
            }
            else Toast.makeText(this, "Condition not met", Toast.LENGTH_SHORT).show();
        }
    }
}