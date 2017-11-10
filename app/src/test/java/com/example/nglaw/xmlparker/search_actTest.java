package com.example.nglaw.xmlparker;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jeffrey Shin on 11/8/17.
 */
public class search_actTest {
    @Test
    public void onCreate() throws Exception {

    }

    @Test
    public void isDestinationAddressValid() throws Exception {
        post_activity myActivity = new post_activity();
        boolean result = myActivity.isValidAddress("1 Washington Square");
        assertEquals(true,result);
        result = myActivity.isValidAddress("132 s 3rd street");
        assertEquals(true,result);
    }
    @Test
    public void isDestinationCityValid() throws Exception {
        post_activity myActivity = new post_activity();
        boolean result = myActivity.isValidCity("San Jose");
        assertEquals(true,result);
    }
    @Test
    public void isDestinationZipValid() throws Exception {
        post_activity myActivity = new post_activity();
        boolean result = myActivity.isValidZip("93122");
        assertEquals(true,result);

    }
}