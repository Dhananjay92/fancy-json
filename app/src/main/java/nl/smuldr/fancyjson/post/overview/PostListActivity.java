package nl.smuldr.fancyjson.post.overview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import javax.inject.Inject;

import nl.smuldr.fancyjson.MyApplication;
import nl.smuldr.fancyjson.R;
import nl.smuldr.fancyjson.post.overview.ui.DividersItemDecoration;
import nl.smuldr.fancyjson.post.overview.ui.PostAdapter;
import nl.smuldr.fancyjson.shared.model.Post;

public final class PostListActivity extends AppCompatActivity implements PostListPresenter.View {

    @Inject
    PostListPresenter presenter;

    private RecyclerView recyclerView;
    private PostAdapter adapter;
    private List<Post> data;
    private ProgressBar progressBar;

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_list);

        ((MyApplication) getApplication()).getMainComponent().inject(this);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        recyclerView = (RecyclerView) findViewById(R.id.posts);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividersItemDecoration(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
    public void showPosts(final List<Post> posts) {
        setLoading(false);
        data = posts;
        adapter = new PostAdapter(posts);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void clear() {
        if (data != null && adapter != null) {
            data.clear();
            adapter.notifyDataSetChanged();
        }
    }

    private void setLoading(boolean loading) {
        progressBar.setVisibility(loading ? View.VISIBLE : View.GONE);
        recyclerView.setVisibility(loading ? View.GONE : View.VISIBLE);
    }
}
