package com.example.cordial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SignUp extends AppCompatActivity {

    EditText name, email, password;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;
    Button signUp;
    TextView signIn;
    FirebaseFirestore mStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signUp = findViewById(R.id.sign_up);
        signIn = findViewById(R.id.sign_in);
        mAuth = FirebaseAuth.getInstance();
        mStore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String s_name = name.getText().toString().trim();
                final String s_email = email.getText().toString().trim();
                String s_password = password.getText().toString().trim();

                if (TextUtils.isEmpty(s_email)) {
                    email.setError("Email is required");
                    return;
                }


                if (TextUtils.isEmpty(s_password)) {
                    password.setError("Password is required");
                    return;
                }

                if (TextUtils.isEmpty(s_name)) {
                    name.setError("Name is required");
                    return;
                }

                if(s_password.length() < 6){
                    password.setError("Password too short");
                    return;
                }


                if (!Patterns.EMAIL_ADDRESS.matcher(s_email).matches()) {
                    email.setError("Please enter a valid e-mail");
                    email.requestFocus();
                }


                progressBar.setVisibility(View.VISIBLE);

                mAuth.createUserWithEmailAndPassword(s_email, s_password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(SignUp.this, "User Registered.",
                                            Toast.LENGTH_SHORT).show();
                                    userID = mAuth.getCurrentUser().getUid();
                                    DocumentReference documentReference = mStore.collection("Users").document(userID);
                                    Map<String, Object> user = new HashMap<>();
                                    user.put("Name", s_name);
                                    user.put("E-mail", s_email);
                                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Log.d("onSuccess","User Profile is created for" + userID);
                                        }
                                    });
                                    Intent intent = new Intent(SignUp.this, Dashboard.class);
                                    startActivity(intent);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    progressBar.setVisibility(View.GONE);
                                    if (task.getException() instanceof FirebaseAuthUserCollisionException)
                                        Toast.makeText(SignUp.this, "User already registered.",
                                                Toast.LENGTH_SHORT).show();
                                    else {
                                        Toast.makeText(getApplicationContext(), Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
            }
        });
    }
}
