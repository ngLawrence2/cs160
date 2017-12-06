package com.example.nglaw.xmlparker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class search_act extends AppCompatActivity {
    TextView destinationAddressText, destinationCityText, destinationZipText, timeOpenText,dateOpenText;
    EditText destinationAddressBox,destinationCityBox,destinationZipBox,timeOpenBox, dateOpenBox;
    Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_act);



        destinationAddressText = (TextView) findViewById(R.id.destinationAddressText);
        destinationAddressBox = (EditText) findViewById(R.id.destinationAddress);
        destinationAddressBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                destinationAddressText.setText("");
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        destinationCityText = (TextView) findViewById(R.id.destinationCityText);
        destinationCityBox = (EditText) findViewById(R.id.destinationCity);
        destinationCityBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                destinationCityText.setText("");
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        destinationZipText = (TextView) findViewById(R.id.destinationZipCodeText);
        destinationZipBox = (EditText) findViewById(R.id.destinationZipCode);
        destinationZipBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                destinationZipText.setText("");
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        timeOpenText = (TextView) findViewById(R.id.timeOpenText);
        timeOpenBox = (EditText) findViewById(R.id.timeOpen);
        timeOpenBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                timeOpenText.setText("");
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        dateOpenText = (TextView) findViewById(R.id.dateOpenText);
        dateOpenBox = (EditText) findViewById(R.id.dateOpen);
        dateOpenBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                dateOpenText.setText("");
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });




        searchButton=(Button) findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

              String dateOpen= dateOpenBox.getText().toString().trim();
                String timeOpen=timeOpenBox.getText().toString().toLowerCase().trim();
                String zip= destinationZipBox.getText().toString().trim();
                String city= destinationCityBox.getText().toString().toLowerCase().trim();

                Calendar calendar= Calendar.getInstance();
                int currentYear = calendar.get(Calendar.YEAR);
                int currentDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                int currentMonth=calendar.get(Calendar.MONTH);
                String currentDate = (currentMonth+1) + "/" + (currentDayOfMonth-1) + "/" +currentYear;


                final int currentDate1 = currentMonth+currentDayOfMonth+currentYear-1-2000;
             //   Toast.makeText(search_act.this, currentDate, Toast.LENGTH_LONG).show();
              DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("ParkingPost");


               // Query query =  ref.orderByChild("date").endAt(currentDate1);

                Query query =  ref.orderByChild("date");
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot d: dataSnapshot.getChildren()) {

                        int myDate=0 ;
                            String date1 = d.getValue() + "";
                            String date= date1.substring(date1.indexOf("date=")+5);
                            date = date.substring(0,date.indexOf("/"));
                            myDate += Integer.parseInt(date);
                            date =  date1.substring(date1.indexOf("date=")+8);
                            date = date.substring(0,date.indexOf("/"));
                            myDate += Integer.parseInt(date);
                            date = date1.substring(date1.indexOf("date=")+11);
                            date = date.substring(0,date.indexOf(","));
                            myDate += Integer.parseInt(date);


                          //  Toast.makeText(search_act.this, date, Toast.LENGTH_LONG).show();
                            if(myDate<currentDate1) {
                                d.getRef().removeValue();
                            }

                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });



                //changes to listview of results
                Intent intent= new Intent(search_act.this,searchRes.class);

                String addr= destinationAddressBox.getText().toString().trim() + city + zip;
               String index = city+zip+dateOpen;
                intent.putExtra("index", index);
                intent.putExtra("address", addr);
                startActivity(intent);

            }
        });

    }
}
