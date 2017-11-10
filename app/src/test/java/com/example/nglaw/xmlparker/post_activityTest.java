package com.example.nglaw.xmlparker;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nglaw on 11/7/2017.
 */
public class post_activityTest {
    @Test
    public void onCreate() throws Exception {

    }

    @Test
    public void isValidAddress() throws Exception {
        post_activity myActivity = new post_activity();
        boolean result = myActivity.isValidAddress("1 washington square");
       assertEquals(true,result);
        result = myActivity.isValidAddress("132 s 3rd street");
        assertEquals(true,result);
    }
    @Test
    public void isValidZip() throws Exception {
        post_activity myActivity = new post_activity();
        boolean result = myActivity.isValidZip("93122");
        assertEquals(true,result);
    }
    @Test
    public void isValidCity() throws Exception {
        post_activity myActivity = new post_activity();
        boolean result = myActivity.isValidCity("san jose");
        assertEquals(true,result);

    }

    /**
     *
     * Test by Kevin Tan 11/9/2017
     */
    @Test
    public void isValidContact() throws Exception{
        post_activity myActivity = new post_activity();
        boolean result = myActivity.isValidContact("1234567890");
        assertEquals(true,result);
        result = myActivity.isValidContact("asd1234512");
        assertEquals(false,result);
        result = myActivity.isValidContact("9871239874");
        assertEquals(true, result);
    }

    /**
     *
     * Test by Kevin Tan 11/9/2017
     */
    @Test
    public void isValidDate() throws Exception{
        post_activity myActivity = new post_activity();
        boolean result = myActivity.isValidDate("11//09/2017");
        assertEquals(false,result);
        result = myActivity.isValidDate("11/09/2017");
        assertEquals(true,result);
        result = myActivity.isValidDate("11092017");
        assertEquals(false,result);
        result = myActivity.isValidDate("11/9/2017");
        assertEquals(true,result);
        result = myActivity.isValidDate("1/9/2017");
        assertEquals(true,result);
        result = myActivity.isValidDate("123asdasadazxc");
        assertEquals(false,result);
    }

    /**
     *
     * Test by Kevin Tan 11/9/2017
     */
     @Test
        public void isValidIndex() throws Exception{
        post_activity myActivity = new post_activity();
        boolean result = myActivity.isValidIndex("san jose 95136 11/9/2017");
        assertEquals(true, result);
        result = myActivity.isValidIndex("san jose 982712 11/9/2017");
        assertEquals(false, result);
        result = myActivity.isValidIndex("san 2jose 95136 11/9/2017");
        assertEquals(false, result);
        result = myActivity.isValidIndex("san jose 9513611/9/2017");
        assertEquals(true, result);
        result = myActivity.isValidIndex("san jose 95136 11/92017");
        assertEquals(false, result);
        result = myActivity.isValidIndex("asdasdaSfds 95136 11/9/2017");
        assertEquals(false, result);
    }
    @Test
    public void isValidStartTime1() throws Exception {
        post_activity myActivity = new post_activity();
        boolean result = myActivity.isValidStartTime("07:33");
        assertEquals(true,result);
    }

    @Test
    public void isValidStartTime2() throws Exception {
        post_activity myActivity = new post_activity();
        boolean result = myActivity.isValidStartTime("25:33");
        assertEquals(false,result);
    }

    @Test
    public void isValidStartTime3() throws Exception {
        post_activity myActivity = new post_activity();
        boolean result = myActivity.isValidStartTime("100:33");
        assertEquals(false,result);
    }

    @Test
    public void isValidEndTime1() throws Exception {
        post_activity myActivity = new post_activity();
        boolean result = myActivity.isValidEndTime("22:54");
        assertEquals(true,result);
    }

    @Test
    public void isValidEndTime2() throws Exception {
        post_activity myActivity = new post_activity();
        boolean result = myActivity.isValidEndTime("00:54");
        assertEquals(true,result);
    }

    @Test
    public void isValidEndTime3() throws Exception {
        post_activity myActivity = new post_activity();
        boolean result = myActivity.isValidEndTime("54:00");
        assertEquals(false,result);
    }

    @Test
    public void isValidPrice1() throws Exception {
        post_activity myActivity = new post_activity();
        boolean result = myActivity.isValidPrice("74");
        assertEquals(true,result);
    }

    @Test
    public void isValidPrice2() throws Exception {
        post_activity myActivity = new post_activity();
        boolean result = myActivity.isValidPrice("100");
        assertEquals(false,result);
    }

    @Test
    public void isValidPrice3() throws Exception {
        post_activity myActivity = new post_activity();
        boolean result = myActivity.isValidPrice("-1");
        assertEquals(false,result);
    }
}