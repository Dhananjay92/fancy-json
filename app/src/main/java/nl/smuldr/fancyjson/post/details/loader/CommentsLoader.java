package nl.smuldr.fancyjson.post.details.loader;


import android.app.Application;
import android.support.v4.content.AsyncTaskLoader;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import nl.smuldr.fancyjson.shared.LoadResult;
import nl.smuldr.fancyjson.shared.model.Comment;
import nl.smuldr.fancyjson.shared.storage.PostRepository;


public class CommentsLoader extends AsyncTaskLoader<LoadResult<List<Comment>>> {

    private final PostRepository repository;
    private long postId = 0L;

    @Inject
    CommentsLoader(final Application application, final PostRepository repository) {
        super(application);
        this.repository = repository;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    @Override
    public LoadResult<List<Comment>> loadInBackground() {
        if (postId == 0) {
            throw new IllegalArgumentException("Did not set postId argument");
        }
        try {
            return new LoadResult<>(repository.getComments(postId));
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
