package com.example.nglaw.xmlparker;

import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class result extends AppCompatActivity {
    TextView text;
    Button btn;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Selected Post");
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        String val=intent.getStringExtra("post");
        text = (TextView)findViewById(R.id.textView);
        text.setText(val);

        ref = FirebaseDatabase.getInstance().getReference().child("ParkingPost");





        btn = (Button)findViewById(R.id.parkingSpotButton);
        if(text.getText().toString().equals("No results")) {
            btn.setVisibility(View.INVISIBLE);
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = text.getText().toString();
                final String address1 = address.substring(address.indexOf("address=")+8, address.indexOf("price")).trim();
                final String contact = address.substring(address.indexOf("contact=")+8, address.indexOf("city"));
                Query query =  ref.orderByChild("address").equalTo(address1);

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot d: dataSnapshot.getChildren()) {
                            d.getRef().removeValue();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
                AlertDialog.Builder builder = new AlertDialog.Builder(result.this);
                builder.setTitle("Success!")
                        .setMessage("Please contact: " + contact)
                        .setCancelable(false)
                        .setNegativeButton("Close",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent home= new Intent(result.this,MainActivity.class);
                                startActivity(home);
                                dialog.cancel();

                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

}
