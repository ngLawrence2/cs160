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
import android.app.ProgressDialog;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity_register extends AppCompatActivity {
    TextView fnTV,lnTV,regEmailTV,regPWTV;

    private EditText nameText, nameText2, pwText2, emailText2;
    private Button regButton;
    private FirebaseAuth mAuth;
    private ProgressDialog mProgress;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_activity);

        fnTV = (TextView) findViewById(R.id.fnTV);
        lnTV = (TextView) findViewById(R.id.lnTV);
        regEmailTV = (TextView) findViewById(R.id.regEmailTV);
        regPWTV = (TextView) findViewById(R.id.regPWTV);

        mAuth = FirebaseAuth.getInstance();
        mProgress = new ProgressDialog(this);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

        nameText = (EditText) findViewById(R.id.nameText);
        nameText2 = (EditText) findViewById(R.id.nameText2);
        pwText2 = (EditText) findViewById(R.id.pwText2);
        emailText2 = (EditText) findViewById(R.id.emailText2);
        regButton = (Button) findViewById(R.id.regButton);

        regButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startRegister();
            }
        });
    }
    private void startRegister(){
        final String firstName = nameText.getText().toString();
        final String lastName = nameText2.getText().toString();
        String email = emailText2.getText().toString();
        String password = pwText2.getText().toString();

        if(isFullNameValid(firstName,lastName) && isEmailValid(email) && isPasswordValid(password)){
            mProgress.setMessage("Signing up...");
            mProgress.show();
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        String user_id = mAuth.getCurrentUser().getUid();
                        DatabaseReference curent_user_db = mDatabase.child(user_id);
                        curent_user_db.child("first_name").setValue(firstName);
                        curent_user_db.child("last_name").setValue(lastName);

                        mProgress.dismiss();

                        Intent postRegister = new Intent(activity_register.this, MainActivity.class);
                        postRegister.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(postRegister);

                    }

                }
            });
        }else{
            Toast.makeText(activity_register.this, "Must fill in required fields. Please provide valid email and password must be 6 or more characters", Toast.LENGTH_LONG).show();
        }
    }

    public boolean isEmailValid(String s){
        if(s.matches("^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$")) return true;
        return false;
    }

    public boolean isPasswordValid(String s){
        if(s.length() >= 6) return true;
        return false;
    }

    public boolean isFullNameValid(String first, String last){
        if(first.length() >= 2 && last.length() >=2
                &&first.matches("[a-zA-Z]+") && last.matches("[a-zA-Z]+")){
            return true;
        }
        return false;
    }
}
