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
import org.hamcrest.core.IsInstanceOf;
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
public class registerSpotTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void registerSpotTest() {

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.searchButton), withText("Search"), isDisplayed()));
        appCompatButton.perform(click());



        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.destinationAddress), isDisplayed()));
        appCompatEditText.perform(replaceText("213 market street"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.destinationCity), isDisplayed()));
        appCompatEditText2.perform(replaceText("san jose"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.destinationZipCode), isDisplayed()));
        appCompatEditText3.perform(replaceText("95112"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.timeOpen), isDisplayed()));
        appCompatEditText4.perform(replaceText("09:00am"), closeSoftKeyboard());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.dateOpen), isDisplayed()));
        appCompatEditText5.perform(replaceText("10/31/17"), closeSoftKeyboard());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.searchButton), withText("Search"), isDisplayed()));
        appCompatButton2.perform(click());



        ViewInteraction appCompatTextView = onView(
                allOf(withId(android.R.id.text1), withText("address=322 4th street\n price=2.00\n contact=(123)456-7890\n city=san jose\n date=10/31/17\n startTime=09:00 am\n endTime=10:00 pm\n zip=95112"),
                        childAtPosition(
                                withId(R.id.myListView),
                                0),
                        isDisplayed()));
        appCompatTextView.perform(click());



        ViewInteraction button = onView(
                allOf(withId(R.id.parkingSpotButton),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                        1),
                                1),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
