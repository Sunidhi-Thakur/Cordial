package com.example.cordial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    EditText email,password;
    ProgressBar progressbar;
    TextView signUp, forgot;
    Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signUp = findViewById(R.id.sign_up);
        signIn = findViewById(R.id.sign_in);
        forgot = findViewById(R.id.forgot);
        progressbar = findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUp.class);
                startActivity(intent);
            }
        });


        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final EditText resetMail = new EditText(v.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password");
                passwordResetDialog.setMessage("Enter the linked E-mail to get reset link.");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String mail = resetMail.getText().toString();
                        mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>(){

                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(LoginActivity.this, "Reset link sent to your E-mail", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginActivity.this, "Error! Reset link not sent." + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });


                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                passwordResetDialog.create().show();

            }
        });



        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String s_email = email.getText().toString().trim();
                String s_password = password.getText().toString().trim();

                if (TextUtils.isEmpty(s_email)) {
                    email.setError("Email is required");
                    return;
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(s_email).matches()){
                    email.setError("Please enter a valid e-mail");
                    return;
                }

                if (TextUtils.isEmpty(s_password)) {
                    password.setError("Password is required");
                    return;
                }

                if(s_password.length() < 6){
                    password.setError("Password too short");
                    return;
                }

                progressbar.setVisibility(View.VISIBLE);

                mAuth.signInWithEmailAndPassword(s_email, s_password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    progressbar.setVisibility(View.GONE);
                                    Intent intent = new Intent(LoginActivity.this, Dashboard.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                } else {
                                    progressbar.setVisibility(View.GONE);
                                    Toast.makeText(getApplicationContext(), Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
    }
}
