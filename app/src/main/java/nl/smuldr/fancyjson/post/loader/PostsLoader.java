package nl.smuldr.fancyjson.post.loader;


import android.app.Application;
import android.support.v4.content.AsyncTaskLoader;

import java.io.IOException;

import javax.inject.Inject;

import nl.smuldr.fancyjson.shared.storage.PostRepository;


public class PostsLoader extends AsyncTaskLoader<LoadPostsResult> {

    private final PostRepository repository;

    @Inject
    PostsLoader(final Application application, final PostRepository repository) {
        super(application);
        this.repository = repository;
    }

    @Override
    public LoadPostsResult loadInBackground() {
        try {
            return new LoadPostsResult(repository.getPosts());
        } catch (IOException e) {
            return new LoadPostsResult(e);
        }
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        // keep loading in the background
        forceLoad();
    }
}
