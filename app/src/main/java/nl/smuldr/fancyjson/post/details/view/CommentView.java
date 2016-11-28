package nl.smuldr.fancyjson.post.details.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Locale;

import nl.smuldr.fancyjson.R;
import nl.smuldr.fancyjson.shared.model.Comment;


public class CommentView extends LinearLayout {

    private TextView body;
    private TextView email;

    public CommentView(final Context context) {
        this(context, null);
    }

    public CommentView(final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommentView(final Context context, @Nullable final AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public CommentView(final Context context, @Nullable final AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        body = (TextView) findViewById(R.id.body);
        email = (TextView) findViewById(R.id.email);
    }

    public void setData(final Comment comment) {
        body.setText(comment.getBody());
        email.setText(comment.getEmail().toLowerCase(Locale.US));
    }
}
