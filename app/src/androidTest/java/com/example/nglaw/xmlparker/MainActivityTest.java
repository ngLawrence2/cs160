package com.example.nglaw.xmlparker;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Created by jeffrey on 11/8/17.
 */
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    private MainActivity mActivity = null;

    Instrumentation.ActivityMonitor monitor_post = getInstrumentation().addMonitor(post_activity.class.getName(), null, false);
    Instrumentation.ActivityMonitor monitor_search = getInstrumentation().addMonitor(search_act.class.getName(), null, false);
    Instrumentation.ActivityMonitor monitor_login = getInstrumentation().addMonitor(activity_login.class.getName(), null, false);

    /*
    private Instrumentation.ActivityMonitor monitor_post;
    private Instrumentation.ActivityMonitor monitor_search;
    private Instrumentation.ActivityMonitor monitor_login;
     */

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
        /*
        super.setUp();
        monitor_post = new Instrumentation.ActivityMonitor(post_activity.class.getName(), null, false);
        monitor_search = new Instrumentation.ActivityMonitor(search_act.class.getName(), null, false);
        monitor_login = new Instrumentation.ActivityMonitor(activity_login.class.getName(), null, false);
        getInstrumentation().addMonitor(monitor_post);
        getInstrumentation().addMonitor(monitor_search);
        getInstrumentation().addMonitor(monitor_login);
         */
    }

    @After
    public  void tearDown() throws Exception {
        mActivity = null;
    }

    @Test
    public void testLaunchOfPost_activityOnButtonClick() throws Exception {
        assertNotNull(mActivity.findViewById(R.id.postButton));
        onView(withId(R.id.postButton)).perform(click());
        Activity postActivity = getInstrumentation().waitForMonitorWithTimeout(monitor_post, 5000);
        assertNotNull("post_activity Activity was not started", postActivity);
        postActivity.finish();
    }
    @Test
    public void testLaunchOfSearch_actOnButtonClick() throws Exception {
        assertNotNull(mActivity.findViewById(R.id.searchButton));
        onView(withId(R.id.searchButton)).perform(click());
        Activity postActivity = getInstrumentation().waitForMonitorWithTimeout(monitor_search, 5000);
        assertNotNull("search_act Activity was not started", postActivity);
        postActivity.finish();
    }
    @Test
    public void testLaunchOfActivity_loginOnButtonClick() throws Exception {
        assertNotNull(mActivity.findViewById(R.id.loginButton));
        onView(withId(R.id.loginButton)).perform(click());
        Activity loginActivity = getInstrumentation().waitForMonitorWithTimeout(monitor_login, 5000);
        assertNotNull("activity_login Activity was not started", loginActivity);
        loginActivity.finish();
    }
}

