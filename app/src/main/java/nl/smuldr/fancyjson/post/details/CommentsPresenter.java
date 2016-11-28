package nl.smuldr.fancyjson.post.details;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;

import java.util.List;

import javax.inject.Inject;

import nl.smuldr.fancyjson.post.details.loader.CommentsLoader;
import nl.smuldr.fancyjson.shared.LoadResult;
import nl.smuldr.fancyjson.shared.model.Comment;
import timber.log.Timber;

final class CommentsPresenter implements LoaderCallbacks<LoadResult<List<Comment>>> {

    static final int LOADER_ID = 23;

    private final CommentsLoader loader;
    private View view;

    @Inject
    CommentsPresenter(final CommentsLoader loader) {
        this.loader = loader;
    }

    void subscribe(final View view, long postId) {
        this.view = view;
        loader.setPostId(postId);
    }

    void unsubscribe() {
        this.view = null;
    }

    @Override
    public Loader<LoadResult<List<Comment>>> onCreateLoader(int id, @Nullable final Bundle args) {
        return loader;
    }

    @Override
    public void onLoadFinished(final Loader<LoadResult<List<Comment>>> loader, final LoadResult<List<Comment>> result) {
        if (view == null) {
            return;
        }
        if (result.getData() != null) {
            view.showComments(result.getData());
        } else {
            Timber.w(result.getException(), "Failed to load comments");
        }
    }

    @Override
    public void onLoaderReset(final Loader<LoadResult<List<Comment>>> loader) {
        // nothing to do
    }

    interface View {
        void showComments(@NonNull List<Comment> data);
    }
}
