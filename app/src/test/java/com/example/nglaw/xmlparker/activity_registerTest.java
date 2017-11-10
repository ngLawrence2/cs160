package com.example.nglaw.xmlparker;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by lamjay415 on 11/8/2017.
 */
public class activity_registerTest {
    @Test
    public void isEmailValid() throws Exception {
        activity_register test = new activity_register();
        boolean output = test.isEmailValid("lamjay415@gmail.com");
        assertEquals(true,output);
        boolean output2 = test.isEmailValid("abcdefg");
        assertEquals(false,output2);
    }

    @Test
    public void isPasswordValid() throws Exception {
        activity_register test = new activity_register();
        boolean output = test.isPasswordValid("sjsucs160");
        assertEquals(true,output);
        boolean output2 = test.isPasswordValid("aaa");
        assertEquals(false,output2);
    }
    @Test
    public void isFullNameValid() throws Exception {
        activity_register test = new activity_register();
        boolean output = test.isFullNameValid("Jay","Lam");
        assertEquals(true,output);
        boolean output2 = test.isFullNameValid("J@y", "La");
        assertEquals(false,output);
    }
}