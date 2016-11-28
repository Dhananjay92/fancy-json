package nl.smuldr.fancyjson.post.details;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import nl.smuldr.fancyjson.MyApplication;
import nl.smuldr.fancyjson.R;
import nl.smuldr.fancyjson.post.details.view.PostDetailsAdapter;
import nl.smuldr.fancyjson.post.overview.view.DividersItemDecoration;
import nl.smuldr.fancyjson.shared.model.Comment;
import nl.smuldr.fancyjson.shared.model.Post;


public final class PostDetailsActivity extends AppCompatActivity implements PostDetailsPresenter.View, CommentsPresenter.View {

    private  static final String KEY_POST_ID = "postId";

    @Inject
    PostDetailsPresenter postDetailsPresenter;
    @Inject
    CommentsPresenter commentsPresenter;

    private final List<Object> data = new ArrayList<>();
    private final List<Comment> comments = new ArrayList<>();
    private long postId;

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private PostDetailsAdapter adapter;

    public static Intent createIntent(final Context context, long postId) {
        final Intent intent = new Intent(context, PostDetailsActivity.class);
        intent.putExtra(KEY_POST_ID, postId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ((MyApplication) getApplication()).getMainComponent().inject(this);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        recyclerView = (RecyclerView) findViewById(R.id.details);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividersItemDecoration(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new PostDetailsAdapter(data);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onPostCreate(@Nullable final Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (savedInstanceState != null) {
            postId = savedInstanceState.getLong(KEY_POST_ID);
        } else {
            postId = getIntent().getLongExtra(KEY_POST_ID, -1);
        }
        startLoading();
    }

    @Override
    @SuppressLint("PrivateResource")
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(KEY_POST_ID, postId);
    }

    @Override
    protected void onStop() {
        super.onStop();
        postDetailsPresenter.unsubscribe();
        commentsPresenter.unsubscribe();
    }

    @Override
    public void showPost(@NonNull final Post post) {
        data.add(0, post);

        // also add the comments to the data set if they are not displayed yet
        if (!comments.isEmpty()) {
            data.addAll(comments);
            setLoading(false);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showComments(@NonNull final List<Comment> comments) {
        this.comments.addAll(comments);

        // only add the comments to the data set if the header was already displayed
        if (!data.isEmpty()) {
            data.addAll(comments);
            adapter.notifyDataSetChanged();
            setLoading(false);
        }
    }

    private void startLoading() {
        postDetailsPresenter.subscribe(this, postId);
        getSupportLoaderManager().initLoader(PostDetailsPresenter.LOADER_ID, null, postDetailsPresenter);

        commentsPresenter.subscribe(this, postId);
        getSupportLoaderManager().initLoader(CommentsPresenter.LOADER_ID, null, commentsPresenter);
    }

    private void setLoading(boolean loading) {
        progressBar.setVisibility(loading ? View.VISIBLE : View.GONE);
        recyclerView.setVisibility(loading ? View.GONE : View.VISIBLE);
    }
}
