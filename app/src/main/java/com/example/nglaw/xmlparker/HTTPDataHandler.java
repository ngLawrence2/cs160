package com.example.nglaw.xmlparker;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by nglaw on 11/18/2017.
 */

public class HTTPDataHandler {

    public HTTPDataHandler() {
    }
    public String getHttpData(String requesturl) {
        URL url;
        String response = "";
        try {
            url = new URL(requesturl);
            HttpURLConnection conn =  (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            int responseCode = conn.getResponseCode();
            if(responseCode==HttpURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while((line=br.readLine())!=null) {
                    response +=line;
                }
            }else {
                response ="";
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
