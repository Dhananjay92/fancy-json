package nl.smuldr.fancyjson.post.overview;


import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import java.util.List;

import javax.inject.Inject;

import nl.smuldr.fancyjson.post.overview.loader.LoadPostsResult;
import nl.smuldr.fancyjson.post.overview.loader.PostsLoader;
import nl.smuldr.fancyjson.shared.model.Post;
import timber.log.Timber;

final class PostListPresenter implements LoaderManager.LoaderCallbacks<LoadPostsResult> {

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
    public Loader<LoadPostsResult> onCreateLoader(int id, final Bundle args) {
        return postsLoader;
    }

    @Override
    public void onLoadFinished(final Loader<LoadPostsResult> loader, final LoadPostsResult result) {
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
    public void onLoaderReset(final Loader<LoadPostsResult> loader) {
        if (view != null) {
            view.clear();
        }
    }

    interface View {
        void clear();
        void showPosts(List<Post> posts);
    }
}
