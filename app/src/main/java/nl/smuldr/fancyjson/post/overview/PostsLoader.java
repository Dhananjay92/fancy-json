package nl.smuldr.fancyjson.post.overview;


import android.app.Application;
import android.support.v4.content.AsyncTaskLoader;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import nl.smuldr.fancyjson.shared.LoadResult;
import nl.smuldr.fancyjson.shared.model.Post;
import nl.smuldr.fancyjson.shared.storage.PostRepository;


public class PostsLoader extends AsyncTaskLoader<LoadResult<List<Post>>> {

    private final PostRepository repository;

    @Inject
    PostsLoader(final Application application, final PostRepository repository) {
        super(application);
        this.repository = repository;
    }

    @Override
    public LoadResult<List<Post>> loadInBackground() {
        try {
            return new LoadResult<>(repository.getPosts());
        } catch (IOException e) {
            return new LoadResult<>(e);
        }
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        // keep loading in the background
        forceLoad();
    }
}
