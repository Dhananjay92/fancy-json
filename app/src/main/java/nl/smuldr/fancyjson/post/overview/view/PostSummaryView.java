package nl.smuldr.fancyjson.post.overview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Locale;

import nl.smuldr.fancyjson.R;
import nl.smuldr.fancyjson.shared.model.Post;


public class PostSummaryView extends LinearLayout {

    private TextView title;
    private TextView email;

    public PostSummaryView(final Context context) {
        this(context, null);
    }

    public PostSummaryView(final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PostSummaryView(final Context context, @Nullable final AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        title = (TextView) findViewById(R.id.title);
        email = (TextView) findViewById(R.id.email);
    }

    public void setData(final Post post) {
        title.setText(post.getTitle());
        email.setText(post.getUser().getEmail().toLowerCase(Locale.US));
    }
}
