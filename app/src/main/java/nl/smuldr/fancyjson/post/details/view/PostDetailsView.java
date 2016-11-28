package nl.smuldr.fancyjson.post.details.view;


import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Locale;

import nl.smuldr.fancyjson.R;
import nl.smuldr.fancyjson.shared.model.Post;

public class PostDetailsView extends LinearLayout {

    private TextView body;
    private TextView email;
    private TextView title;

    public PostDetailsView(final Context context) {
        this(context, null);
    }

    public PostDetailsView(final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PostDetailsView(final Context context, @Nullable final AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public PostDetailsView(final Context context, @Nullable final AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        body = (TextView) findViewById(R.id.body);
        title = (TextView) findViewById(R.id.title);
        email = (TextView) findViewById(R.id.email);
    }

    public void setData(final Post post) {
        body.setText(post.getBody());
        title.setText(post.getTitle());
        email.setText(post.getUser().getEmail().toLowerCase(Locale.US));
    }
}
