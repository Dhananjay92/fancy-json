package nl.smuldr.fancyjson;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import nl.smuldr.fancyjson.post.overview.PostListActivity;

/**
 * Super light activity that shows a simple splash screen while the application loads.
 */
public final class SplashActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // do not set a content view: use the theme to show an image as window background

        startActivity(new Intent(this, PostListActivity.class));
        finish();
    }
}
