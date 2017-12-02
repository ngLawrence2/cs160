package com.example.nglaw.xmlparker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class activity_my_post extends AppCompatActivity {
    ListView listview;
    DatabaseReference ref;
    ArrayList<String> c;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_post);

        mAuth = FirebaseAuth.getInstance();
        listview= (ListView)findViewById(R.id.myListView);

        c=new ArrayList<String>();

        Intent intent = getIntent();
        String index = intent.getStringExtra("index");
        final String address = intent.getStringExtra("address");

        String owner = mAuth.getCurrentUser().getEmail();


        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,c);
        // Set The Adapter


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedpost = (String)parent.getItemAtPosition(position);
                Intent i= new Intent(activity_my_post.this,result.class);
                i.putExtra("post",selectedpost);
                i.putExtra("address", address);
                startActivity(i);
            }
        });

        ref = FirebaseDatabase.getInstance().getReference().child("ParkingPost");

        Query query =  ref.orderByChild("owner").equalTo(owner);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists()) {
                    adapter.add("No results");
                } else {

                    for(DataSnapshot ds: dataSnapshot.getChildren()) {
                        String l= ds.getValue().toString();
                        l= l.replaceAll(",", "\n");
                        l= l.replaceAll("\\{","");
                        l= l.replaceAll("\\}" , "");
                        l=l.substring(l.indexOf("address"), l.indexOf("index")) + l.substring(l.indexOf("zip"));
                        adapter.add(l);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        listview.setAdapter(adapter);

    }

}
