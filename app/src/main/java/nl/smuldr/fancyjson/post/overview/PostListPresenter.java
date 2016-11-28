package nl.smuldr.fancyjson.post.overview;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import java.util.List;

import javax.inject.Inject;

import nl.smuldr.fancyjson.shared.LoadResult;
import nl.smuldr.fancyjson.shared.model.Post;
import timber.log.Timber;

public final class PostListPresenter implements LoaderManager.LoaderCallbacks<LoadResult<List<Post>>> {

    private final PostsLoader postsLoader;
    private View view;

    @Inject
    PostListPresenter(final PostsLoader postsLoader) {
        this.postsLoader = postsLoader;
    }

    void subscribe(final View view) {
        this.view = view;
    }

    void unsubscribe() {
        view = null;
    }

    @Override
    public Loader<LoadResult<List<Post>>> onCreateLoader(int id, final Bundle args) {
        return postsLoader;
    }

    @Override
    public void onLoadFinished(final Loader<LoadResult<List<Post>>> loader, final LoadResult<List<Post>> result) {
        if (view == null) {
            return;
        }
        if (result.getData() != null) {
            Timber.d("Loaded %d posts", result.getData().size());
            view.showPosts(result.getData());
        } else {
            Timber.w(result.getException(), "Failed to load posts");
        }
    }

    @Override
    public void onLoaderReset(final Loader<LoadResult<List<Post>>> loader) {
        // nothing to do
    }

    interface View {
        void showPosts(@NonNull List<Post> posts);
    }
}
