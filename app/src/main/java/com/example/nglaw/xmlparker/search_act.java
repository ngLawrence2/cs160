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


                Intent intent= new Intent(search_act.this,searchRes.class);
               String index = city+zip+dateOpen;
                intent.putExtra("index", index);
                startActivity(intent);

            }
        });

    }
}
