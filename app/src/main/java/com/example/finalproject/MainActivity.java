package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int MY_REQUEST_CODE = 7117;
    List<AuthUI.IdpConfig> providers;
    EditText txtUsername, txtPaswword;
    Button btn_sign_out;
    Button btn_sign_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Handdle sign out button
        btn_sign_out = (Button)findViewById(R.id.btn_sign_out);
        btn_sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log out
                AuthUI.getInstance()
                        .signOut(MainActivity.this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                btn_sign_out.setEnabled(false);
                                showSignInOption();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        // Handle sign in button
        btn_sign_in = (Button)findViewById(R.id.btn_sign_in);
        txtPaswword = (EditText)findViewById(R.id.password);
        txtUsername = (EditText)findViewById(R.id.username);

        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txtUsername.getText().toString().trim();
                String password = txtPaswword.getText().toString().trim();

                if(TextUtils.isEmpty(username)) {
                    Toast.makeText(MainActivity.this, "Please enter username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)) {
                    Toast.makeText(MainActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        // Init providers
        providers  = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(), // Email builder
                new AuthUI.IdpConfig.PhoneBuilder().build() // Email builder
        );

        showSignInOption();
    }

    private void showSignInOption() {
        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build(), MY_REQUEST_CODE
        );
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == MY_REQUEST_CODE) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if(resultCode == RESULT_OK) {
                // Get User
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                // show user on Toast
                Toast.makeText(this, "" + user.getEmail(), Toast.LENGTH_SHORT).show();
                // set button signout
                btn_sign_out.setEnabled(true);
            } else {
                Toast.makeText(this, ""+response.getError().getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
