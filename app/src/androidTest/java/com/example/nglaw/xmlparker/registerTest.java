package com.example.nglaw.xmlparker;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
/**
 * Created by Kevin on 11/9/2017.
 */

public class registerTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void registerTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.loginButton), withText("Login"), isDisplayed()));
        appCompatButton.perform(click());

        //Need a way to click on the TextView register Link
        login

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.nameText), isDisplayed()));
        appCompatEditText.perform(replaceText("Kevin"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.nameText2), isDisplayed()));
        appCompatEditText2.perform(replaceText("Tan"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.pwText2), isDisplayed()));
        appCompatEditText3.perform(replaceText("12345123123"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.emailText2), isDisplayed()));
        appCompatEditText4.perform(replaceText("asd123123f@asdf.com"), closeSoftKeyboard());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.regButton), withText("Register Here"), isDisplayed()));
        appCompatButton3.perform(click());



    }
}
