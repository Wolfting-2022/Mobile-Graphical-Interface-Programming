package algonquin.cst2335.chen0869;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(5128);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ViewInteraction appCompatEditText = onView(withId(R.id.my_editText));
        appCompatEditText.perform(replaceText("12345"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(withId(R.id.my_button));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.my_textView));
        textView.check(matches(withText("You shall not pass!")));
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

    /**
     * Tests the behavior of MainActivity when entering a password lacking a lowercase letter.
     * This test automates the UI interaction by:
     * 1. Finding the EditText view and typing in a password "password123#$*" that intentionally includes uppercase letters, numbers, and special characters, but no lowercase letters.
     * 2. Clicking the login button to submit the password.
     * 3. Checking if the TextView displays the message "You shall not pass!" indicating the password does not meet complexity requirements.
     */

    @Test
    public void testFindMissingUpperCase(){
        ViewInteraction appCompatEditText = onView(withId(R.id.my_editText));
        appCompatEditText.perform(replaceText("abc@#123"));

        ViewInteraction materialButton = onView(withId(R.id.my_button));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.my_textView));
        textView.check(matches(withText("You shall not pass!")));
    }
    @Test
    public void testFindMissingLowerCase(){
        // find the view
        ViewInteraction appCompatEditText = onView(withId(R.id.my_editText));
        // type in password123#$*
        appCompatEditText.perform(replaceText("ABC@#123"));

        // find the button
        ViewInteraction materialButton = onView(withId(R.id.my_button));
        // click the button
        materialButton.perform(click());

        // find the text view
        ViewInteraction textView = onView(withId(R.id.my_textView));
        // check the view
        textView.check(matches(withText("You shall not pass!")));
    }


    @Test
    public void testFindMissingMumber(){
        ViewInteraction appCompatEditText = onView(withId(R.id.my_editText));
        appCompatEditText.perform(replaceText("ABC@#abc"));

        ViewInteraction materialButton = onView(withId(R.id.my_button));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.my_textView));
        textView.check(matches(withText("You shall not pass!")));
    }

    @Test
    public void testFindMissingSpecial(){
        ViewInteraction appCompatEditText = onView(withId(R.id.my_editText));
        appCompatEditText.perform(replaceText("ABCabc123"));

        ViewInteraction materialButton = onView(withId(R.id.my_button));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.my_textView));
        textView.check(matches(withText("You shall not pass!")));
    }

//    @Test
//    public void testMeetAll(){
//        ViewInteraction appCompatEditText = onView(withId(R.id.my_editText));
//        appCompatEditText.perform(replaceText("ABC@#abc123"));
//
//        ViewInteraction materialButton = onView(withId(R.id.my_button));
//        materialButton.perform(click());
//        appCompatEditText.check(matches(withText("Your password meets the requirements")));
//    }
}
