package com.example.nglaw.xmlparker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class activity_login extends AppCompatActivity {
    TextView emailTV, pwTV;
    EditText emailText, pwText;
    Button loginButton2;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);

        mAuth = FirebaseAuth.getInstance();
        final TextView registerLink = (TextView) findViewById(R.id.regText);

        registerLink.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent registerIntent = new Intent(activity_login.this, activity_register.class);
                activity_login.this.startActivity(registerIntent);
            }
        });
        emailText = (EditText) findViewById(R.id.emailText);
        pwText = (EditText) findViewById(R.id.pwText);
        emailTV = (TextView) findViewById(R.id.emailTV);
        pwTV = (TextView) findViewById(R.id.pwTV);


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null){
                    Intent postLogin= new Intent(activity_login.this,MainActivity.class);
                    startActivity(postLogin);
                    /*Intent intent= new Intent(activity_login.this,MainActivity.class);
                    intent.putExtra("email", emailText.getText().toString());
                    startActivity(intent);*/
                    Toast.makeText(activity_login.this, "Login Successful", Toast.LENGTH_LONG).show();
                }
            }
        };
        loginButton2 = (Button)findViewById(R.id.loginButton2);

        loginButton2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                startSignIn();
            }
        });
    }

    protected void onStop() {
        super.onStop();  // Always call the superclass method first
       mAuth= FirebaseAuth.getInstance();
        mAuth.signOut();
       // Toast.makeText(getApplicationContext(), "onStop called", Toast.LENGTH_LONG).show();
    }

    protected void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    private void startSignIn(){
        String email = emailText.getText().toString();
        String password = pwText.getText().toString();
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            Toast.makeText(activity_login.this, "Fields are empty", Toast.LENGTH_LONG).show();
        }
        else {
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(activity_login.this, "Invalid username or password", Toast.LENGTH_LONG).show();
                        }
                }
            });
        }
    }

}
