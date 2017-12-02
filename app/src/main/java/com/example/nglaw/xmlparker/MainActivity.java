package com.example.nglaw.xmlparker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;


import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    public Button postButton;
    public Button searchButton;
    public Button myPostsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        postButton=(Button)findViewById(R.id.postButton);
        postButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent postActivity= new Intent(MainActivity.this,post_activity.class);
                startActivity(postActivity);
            }
        });

        searchButton=(Button)findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent searchActivity= new Intent(MainActivity.this,search_act.class);
                startActivity(searchActivity);
            }
        });

        myPostsButton=(Button)findViewById(R.id.myPostButton);
        myPostsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent mypostact= new Intent(MainActivity.this,activity_my_post.class);
                startActivity(mypostact);
            }
        });


    }
}
