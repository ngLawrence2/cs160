package com.example.nglaw.xmlparker;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;


import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;


public class post_activity extends AppCompatActivity {
    TextView addressText,cityText,zipText,dateAvailableText,startTimeText,endTimeText,priceText,contactText;
    EditText addressBox,cityBox,zipBox,dateAvailableBox,startTimeBox,endTimeBox,priceBox,contactBox;
    private FirebaseAuth mAuth;
    Button sendData;
    DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_activity);
        ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://parkhere-a09f8.firebaseio.com/");

        mAuth = FirebaseAuth.getInstance();

        final String user =    mAuth.getCurrentUser().getEmail();
        addressText = (TextView) findViewById(R.id.addressText);
        addressBox = (EditText) findViewById(R.id.addressBox);
        addressBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                addressText.setText("");
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        cityText = (TextView) findViewById(R.id.cityText);
        cityBox = (EditText) findViewById(R.id.cityBox);
        cityBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                cityText.setText("");
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        zipText = (TextView) findViewById(R.id.zipText);
        zipBox = (EditText) findViewById(R.id.zipBox);
        zipBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                zipText.setText("");
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        dateAvailableText = (TextView) findViewById(R.id.dateAvailableText);
        dateAvailableBox = (EditText) findViewById(R.id.dayAvailableBox);
        dateAvailableBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                dateAvailableText.setText("");
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        startTimeText = (TextView) findViewById(R.id.startTimeText);
        startTimeBox = (EditText) findViewById(R.id.startTimeBox);
        startTimeBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                startTimeText.setText("");
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        endTimeText = (TextView) findViewById(R.id.endTimeText);
        endTimeBox = (EditText) findViewById(R.id.endTimeBox);
        endTimeBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                endTimeText.setText("");
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        priceText = (TextView) findViewById(R.id.priceText);
        priceBox = (EditText) findViewById(R.id.priceBox);
        priceBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                priceText.setText("");
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        contactText = (TextView) findViewById(R.id.contactText);
        contactBox = (EditText) findViewById(R.id.contactBox);
        contactBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                contactText.setText("");
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        sendData= (Button)findViewById(R.id.postButton);
        sendData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String dateAvail= dateAvailableBox.getText().toString().trim();
                String address = addressBox.getText().toString().toLowerCase().trim();
                String city = cityBox.getText().toString().toLowerCase().trim();
                String zip = zipBox.getText().toString().trim();

                String startTime = startTimeBox.getText().toString().trim();
                String endTime = endTimeBox.getText().toString().trim();
                String price = priceBox.getText().toString().trim();
                String index = city + zip + dateAvail;
                String contact = contactBox.getText().toString().trim();
                Post post = new Post(address, city, zip, dateAvail, startTime, endTime, price, index, contact, user);
                if(isValidAddress(address) && isValidCity(city) && isValidZip(zip)) {
                    //if parking spot is a single day
                    if (dateAvail.indexOf("-") == -1) {

                  /*  String address = addressBox.getText().toString().toLowerCase().trim();
                    String city = cityBox.getText().toString().toLowerCase().trim();
                    String zip = zipBox.getText().toString().trim();

                    String startTime = startTimeBox.getText().toString().trim();
                    String endTime = endTimeBox.getText().toString().trim();
                    String price = priceBox.getText().toString().trim();
                    String index = city + zip + dateAvail;
                    String contact = contactBox.getText().toString().trim();
                    Post post = new Post(address, city, zip, dateAvail, startTime, endTime, price, index, contact, user);*/
                        ref.child("ParkingPost").push().setValue(post);
                        AlertDialog.Builder builder = new AlertDialog.Builder(post_activity.this);
                        builder.setTitle("Success!")
                                .setMessage("Your post has been posted!")
                                .setCancelable(false)
                                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent home = new Intent(post_activity.this, MainActivity.class);
                                        startActivity(home);
                                        dialog.cancel();

                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();

                    }
                } else {
                    Toast.makeText(post_activity.this, "incorrect input format", Toast.LENGTH_LONG).show();
                }

                if(isValidAddress(address) && isValidCity(city) && isValidZip(zip)) {
                    //parking spot on multiple days such as 10/31/17 - 11/02/17
                    if (dateAvail.indexOf("-") > 0) {
                        String firstDateMonth = dateAvail.substring(0, dateAvail.indexOf("/"));
                        String firstDateDay = dateAvail.substring(dateAvail.indexOf("/") + 1, dateAvail.indexOf("/") + 3);
                        String firstDateYear = dateAvail.substring(dateAvail.indexOf("/") + 4, dateAvail.indexOf("-"));
                        String inputString = firstDateDay + "-" + firstDateMonth + "-20" + firstDateYear;
                        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

                        String secondDateMonth = dateAvail.substring(dateAvail.indexOf("-") + 1, dateAvail.indexOf("-") + 3);
                        String secondDateDay = dateAvail.substring(dateAvail.indexOf("-") + 4, dateAvail.indexOf("-") + 6);
                        String secondDateYear = dateAvail.substring(dateAvail.indexOf("-") + 7, dateAvail.indexOf("-") + 9);
                        String secondDateString = secondDateDay + "-" + secondDateMonth + "-20" + secondDateYear;


                        Date firstDate;
                        Date secondDate;
                        try {
                            firstDate = dateFormat.parse(inputString);
                            secondDate = dateFormat.parse(secondDateString);
                            int days = (int) getDateDiffString(firstDate, secondDate) + 1;

                            Calendar cal = Calendar.getInstance();

                            if (days < 7) {
                                cal.setTime(firstDate);
                                while (cal.getTime().before(secondDate)) {

                                    String dates = cal.getTime().toString();

                                    Date date = cal.getTime();

                                    String formattedDate1 = new SimpleDateFormat("MM/dd/yyy HH:mm:ss").format(date);
                                    String myDate = formattedDate1.substring(0, formattedDate1.indexOf(" "));
                                    myDate.replace(" ", "");
                                    myDate = myDate.substring(0, 6) + myDate.substring(8, 10);
                                    index = city + zip + myDate;
                                    Post post1 = new Post(address, city, zip, myDate, startTime, endTime, price, index, contact, user);

                                    ref.child("ParkingPost").push().setValue(post1);

                                    cal.add(Calendar.DATE, 1);
                                }

                                String dates = cal.getTime().toString();

                                Date date = cal.getTime();

                                String formattedDate1 = new SimpleDateFormat("MM/dd/yyy HH:mm:ss").format(date);
                                String myDate = formattedDate1.substring(0, formattedDate1.indexOf(" "));
                                myDate.replace(" ", "");


                                myDate = myDate.substring(0, 6) + myDate.substring(8, 10);
                                index = city + zip + myDate;
                                Post post1 = new Post(address, city, zip, myDate, startTime, endTime, price, index, contact, user);

                                ref.child("ParkingPost").push().setValue(post1);


                                AlertDialog.Builder builder = new AlertDialog.Builder(post_activity.this);
                                builder.setTitle("Success!")
                                        .setMessage("Your post has been posted!")
                                        .setCancelable(false)
                                        .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                Intent home = new Intent(post_activity.this, MainActivity.class);
                                                startActivity(home);
                                                dialog.cancel();

                                            }
                                        });
                                AlertDialog alert = builder.create();
                                alert.show();

                            } else {
                                Toast.makeText(post_activity.this, "max number of days is 7", Toast.LENGTH_LONG).show();
                            }

                        } catch (Exception e) {
                            Toast.makeText(post_activity.this, "check format", Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    Toast.makeText(post_activity.this, "incorrect input format", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public boolean isValidAddress(String s) {
      s = s.replaceAll(" ","");
        if(s.matches("\\d+[A-z]?\\d?[A-z]+")) {
            return true;
        }
        return false;
    }
    public boolean isValidZip(String s) {
    if(s.matches("[0-9]{5}")) {
        return true;
    }
    return false;
    }

    public boolean isValidCity(String s) {
      s= s.replaceAll(" ","");
        if(s.matches("[A-z]+")) {
            return true;
        }
        return false;
    }

    public boolean isValidIndex(String s){
        int theLength = 0;
        s = s.replaceAll(" ","");
        for(int i = 0; i< s.length();i++){
            if(Character.isDigit(s.charAt(i))) {
                break;
            }
            else {
                theLength++;
            }
        }
        if(isValidCity(s.substring(0,theLength-1))){
            if(isValidZip(s.substring(theLength, theLength+5))){
                if(isValidDate(s.substring(theLength+5))){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean isValidDate(String s){
        if(s.substring(0,2).matches("[0-9]{2}") && s.substring(2,3).equals("/") && s.substring(3,5).matches("[0-9]{2}") && s.substring(5,6).equals("/") && s.substring(6,10).matches("[0-9]{4}") ){
            return true;
        }
        else if(s.substring(0,2).matches("[0-9]{2}") && s.substring(2,3).equals("/") && s.substring(3,4).matches("[0-9]{1}") && s.substring(4,5).equals("/") && s.substring(5,9).matches("[0-9]{4}") ){
            return true;
        }
        else if(s.substring(0,1).matches("[0-9]{1}") && s.substring(1,2).equals("/") && s.substring(2,3).matches("[0-9]{1}") && s.substring(3,4).equals("/") && s.substring(4,8).matches("[0-9]{4}") ){
            return true;
        }
        return false;
    }
    public boolean isValidContact(String s){
        if(s.matches("[0-9]{10}")) {
            return true;
        }
        return false;
    }
    public boolean isValidStartTime(String s) {
        s = s.replaceAll(":","");
        int hours = Integer.parseInt(s.substring(0,2));
        int minutes = Integer.parseInt(s.substring(2,4));
        if((hours <= 23 && minutes <= 59) && s.length() == 4) {
            return true;
        }
        return false;
    }

    public boolean isValidEndTime(String s) {
        s = s.replaceAll(":","");
        int hours = Integer.parseInt(s.substring(0,2));
        int minutes = Integer.parseInt(s.substring(2,4));
        if((hours <= 23 && minutes <= 59) && s.length() == 4) {
            return true;
        }
        return false;
    }

    public boolean isValidPrice(String s) {
        s= s.replaceAll("$","");
        if(s.matches("[0-9]{2}")) {
            return true;
        }
        return false;
    }

    public long getDateDiffString(Date dateOne, Date dateTwo)
    {
        long timeOne = dateOne.getTime();
        long timeTwo = dateTwo.getTime();
        long oneDay = 1000 * 60 * 60 * 24;
        long delta = (timeTwo - timeOne) / oneDay;

        if (delta > 0) {
           return delta;
        }
        else {
            delta *= -1;
            return delta;
        }
    }
}
