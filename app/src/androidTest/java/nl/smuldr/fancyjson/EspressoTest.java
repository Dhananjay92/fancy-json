package nl.smuldr.fancyjson;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static nl.smuldr.fancyjson.util.TestUtils.withRecyclerView;
import static org.hamcrest.CoreMatchers.containsString;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class EspressoTest {

    @Rule
    public ActivityTestRule<SplashActivity> mActivityRule = new ActivityTestRule<>(SplashActivity.class);

    @Test
    public void coreFunctionality() throws Exception {

        // scroll to some post in the list
        onView(withId(R.id.posts)).perform(RecyclerViewActions.scrollToPosition(30));

        // should display the post user's email in lowercase
        onView(withRecyclerView(R.id.posts).atPositionOnView(30, R.id.email))
                .check(matches(withText("julianne.oconner@kory.org")));

        // should display the post title
        onView(withRecyclerView(R.id.posts).atPositionOnView(30, R.id.title))
                .check(matches(withText("ullam ut quidem id aut vel consequuntur")));

        // click on the post
        onView(withRecyclerView(R.id.posts).atPosition(30))
                .perform(ViewActions.click());

        // should display the post details as the first item
        onView(withRecyclerView(R.id.details).atPositionOnView(0, R.id.email))
                .check(matches(withText("julianne.oconner@kory.org")));
        onView(withRecyclerView(R.id.details).atPositionOnView(0, R.id.title))
                .check(matches(withText("ullam ut quidem id aut vel consequuntur")));
        onView(withRecyclerView(R.id.details).atPositionOnView(0, R.id.body))
                .check(matches(withText(containsString("debitis eius sed quibusdam non quis consectetur vitae"))));

        // should display the comments below
        onView(withId(R.id.details)).perform(RecyclerViewActions.scrollToPosition(4));
        onView(withRecyclerView(R.id.details).atPositionOnView(4, R.id.email))
            .check(matches(withText("durward@cindy.com")));
        onView(withRecyclerView(R.id.details).atPositionOnView(4, R.id.body))
            .check(matches(withText(containsString("quod magni consectetur"))));
    }
}
