package nl.smuldr.fancyjson.post.details;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;

import javax.inject.Inject;

import nl.smuldr.fancyjson.post.details.loader.PostDetailsLoader;
import nl.smuldr.fancyjson.shared.LoadResult;
import nl.smuldr.fancyjson.shared.model.Post;
import timber.log.Timber;

final class PostDetailsPresenter implements LoaderCallbacks<LoadResult<Post>> {

    static final int LOADER_ID = 420;

    private final PostDetailsLoader postDetailsLoader;
    private View view;

    @Inject
    PostDetailsPresenter(final PostDetailsLoader postDetailsLoader) {
        this.postDetailsLoader = postDetailsLoader;
    }

    void subscribe(final View view, long postId) {
        this.view = view;
        postDetailsLoader.setPostId(postId);
    }

    void unsubscribe() {
        this.view = null;
    }

    @Override
    public Loader<LoadResult<Post>> onCreateLoader(int id, @Nullable final Bundle args) {
        return postDetailsLoader;
    }

    @Override
    public void onLoadFinished(final Loader<LoadResult<Post>> loader, final LoadResult<Post> result) {
        if (view == null) {
            return;
        }
        if (result.getData() != null) {
            view.showPost(result.getData());
        } else {
            Timber.w(result.getException(), "Failed to load post details");
        }
    }

    @Override
    public void onLoaderReset(final Loader<LoadResult<Post>> loader) {
        // nothing to do
    }

    interface View {
        void showPost(@NonNull Post data);
    }
}
