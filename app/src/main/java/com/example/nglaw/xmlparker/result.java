package com.example.nglaw.xmlparker;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.AsyncListUtil;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import static java.lang.Math.PI;
import static java.lang.Math.round;

public class result extends AppCompatActivity implements AsyncResponse {
    TextView text;
    Button btn;
    DatabaseReference ref;
    private FirebaseAuth mAuth;


    double mapsLatLong[] = new double[2];
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

        mAuth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference().child("ParkingPost");

        long startTime = System.currentTimeMillis();
        String destinationAddress = intent.getStringExtra("address");
        double latlongA[];
        double latlongB[];
        text.setText(val);
        String parkingSpotAddress = text.getText().toString();
        String addrcity=  parkingSpotAddress.substring(parkingSpotAddress.indexOf("city=") + 5, parkingSpotAddress.indexOf("date")).trim();
        String addrZip= parkingSpotAddress.substring(parkingSpotAddress.indexOf("zip=") + 4).trim();

        String addr = parkingSpotAddress.substring(parkingSpotAddress.indexOf("address=") + 8, parkingSpotAddress.indexOf("price")).trim() + ", " + addrcity  + ", " + addrZip ;
        addr = addr.substring(0, addr.indexOf("owner="));
        final  String owner= parkingSpotAddress.substring(parkingSpotAddress.indexOf("owner=") +6).trim();
        final  String loggedinUser = mAuth.getCurrentUser().getEmail();
        if(!owner.equals(loggedinUser)) {
        double dist = 0.0;
        Location locationA = new Location("point A");
        Location locationB = new Location("point B");

        try {
            latlongA = getLatLong(destinationAddress);
            locationA.setLatitude(latlongA[0]);
            locationA.setLongitude(latlongA[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }



      //  Toast.makeText(result.this, addr, Toast.LENGTH_LONG).show();


            try {
                latlongB = getLatLong(addr);
                locationB.setLatitude(latlongB[0]);
                locationB.setLongitude(latlongB[1]);

            } catch (Exception e) {
                e.printStackTrace();
            }
            dist = locationA.distanceTo(locationB);
            dist = dist / 1000;
            String distMiles = String.format("%.2f", dist);

            if (dist > 100 || dist == 0) {
                try {
                    GetCoordinates task = new GetCoordinates();
                    task.execute(addr).get();
                    //   Toast.makeText(result.this, "lat= " + mapsLatLong[0], Toast.LENGTH_LONG).show();
                    //  Toast.makeText(result.this, "long= " + mapsLatLong[1], Toast.LENGTH_LONG).show();
                    locationB.setLatitude(mapsLatLong[0]);
                    locationB.setLongitude(mapsLatLong[1]);
                    dist = locationA.distanceTo(locationB);
                    dist = dist / 1000;
                    distMiles = String.format("%.2f", dist);
                } catch (Exception e) {

                }
            }

            if (dist > 100 || dist == 0) {
                try {
                    GetCoordinates task = new GetCoordinates();
                    task.execute(destinationAddress).get();
                    // Toast.makeText(result.this, "latA= " + mapsLatLong[0], Toast.LENGTH_LONG).show();
                    //    Toast.makeText(result.this, "longB= " + mapsLatLong[1], Toast.LENGTH_LONG).show();
                    locationA.setLatitude(mapsLatLong[0]);
                    locationA.setLongitude(mapsLatLong[1]);
                    dist = locationB.distanceTo(locationA);
                    dist = dist / 1000;
                    distMiles = String.format("%.2f", dist);
                } catch (Exception e) {

                }
            }


            String distanceString = "distance = " + distMiles + " miles";
            if (dist > 100) {
                distanceString = "";
            }

            text.setText(val + "\n" + distanceString);

        }
        long elapsedTime = System.currentTimeMillis() - startTime;
        Toast.makeText(result.this, elapsedTime + "", Toast.LENGTH_LONG).show();

        //Toast.makeText(result.this, owner, Toast.LENGTH_LONG).show();


        btn = (Button)findViewById(R.id.parkingSpotButton);


        if(owner.equals(loggedinUser)) {
             btn.setText("Delete");
        }
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
                if(!owner.equals(loggedinUser)) {
                    builder.setTitle("Success!")
                            .setMessage("Please contact: " + contact)
                            .setCancelable(false)
                            .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent home = new Intent(result.this, MainActivity.class);
                                    startActivity(home);
                                    dialog.cancel();

                                }
                            });
                }else {
                    builder.setTitle("Success!")
                            .setMessage("Post deleted")
                            .setCancelable(false)
                            .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent home = new Intent(result.this, MainActivity.class);
                                    startActivity(home);
                                    dialog.cancel();

                                }
                            });
                }
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    public double[] getLatLong(String destination) {

        double[] latlong = new double[2];
        Geocoder gc = new Geocoder(this, Locale.getDefault());
        if(gc.isPresent()){
                try {
                    List<Address> list = gc.getFromLocationName(destination, 1);
                    Address address = list.get(0);
                    double lat = address.getLatitude();
                    double lng = address.getLongitude();
                    latlong[0] = lat;
                    latlong[1] = lng;
                    return latlong;
                } catch(IOException e) {
                    e.printStackTrace();
                }
        }
        return latlong;
    }

    @Override
    public void processFinish(String output) {
        try {
            JSONObject jsonObject= new JSONObject(output);
            String lat = ((JSONArray)jsonObject.get("results")).getJSONObject(0).getJSONObject("geometry").getJSONObject("location")
                    .get("lat").toString();
            double lati= Double.parseDouble(lat);
            String lng = ((JSONArray)jsonObject.get("results")).getJSONObject(0).getJSONObject("geometry").getJSONObject("location")
                    .get("lng").toString();
            double longi= Double.parseDouble(lng);
            mapsLatLong[0]=lati;
            mapsLatLong[1]=longi;

        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    private class GetCoordinates extends AsyncTask<String, Void, String> {
      //  public AsyncResponse delegate = null;
        ProgressDialog dialog = new ProgressDialog(result.this);
        public double lati;
        public double longi;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {

           String response;

            try {
                String address = params[0];
                HTTPDataHandler http = new HTTPDataHandler();
                String url = String.format("https://maps.googleapis.com/maps/api/geocode/json?address=%s", address);
                response = http.getHttpData(url);
                try {
                    JSONObject jsonObject= new JSONObject(response);
                    String lat = ((JSONArray)jsonObject.get("results")).getJSONObject(0).getJSONObject("geometry").getJSONObject("location")
                            .get("lat").toString();
                    double lati= Double.parseDouble(lat);
                    String lng = ((JSONArray)jsonObject.get("results")).getJSONObject(0).getJSONObject("geometry").getJSONObject("location")
                            .get("lng").toString();
                    double longi= Double.parseDouble(lng);
                    mapsLatLong[0] = lati;
                    mapsLatLong[1] = longi;


                }catch(Exception e) {
                    e.printStackTrace();
                }
                return response;
            }catch (Exception e) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

        }
    }

}
