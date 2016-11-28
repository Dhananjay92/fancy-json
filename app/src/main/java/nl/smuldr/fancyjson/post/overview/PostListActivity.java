package nl.smuldr.fancyjson.post.overview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import nl.smuldr.fancyjson.MyApplication;
import nl.smuldr.fancyjson.R;
import nl.smuldr.fancyjson.post.overview.view.DividersItemDecoration;
import nl.smuldr.fancyjson.post.overview.view.PostAdapter;
import nl.smuldr.fancyjson.shared.model.Post;

public final class PostListActivity extends AppCompatActivity implements PostListPresenter.View {

    @Inject
    PostListPresenter presenter;

    private final List<Post> data = new ArrayList<>();

    private RecyclerView recyclerView;
    private PostAdapter adapter;
    private ProgressBar progressBar;

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        ((MyApplication) getApplication()).getMainComponent().inject(this);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        recyclerView = (RecyclerView) findViewById(R.id.posts);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividersItemDecoration(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new PostAdapter(data, presenter);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onPostCreate(@Nullable final Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        presenter.subscribe(this);
        getSupportLoaderManager().initLoader(0, null, presenter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.unsubscribe();
    }

    @Override
    public void showPosts(@NonNull final List<Post> posts) {
        setLoading(false);
        data.addAll(posts);
        adapter.notifyDataSetChanged();
    }

    private void setLoading(boolean loading) {
        progressBar.setVisibility(loading ? View.VISIBLE : View.GONE);
        recyclerView.setVisibility(loading ? View.GONE : View.VISIBLE);
    }
}
