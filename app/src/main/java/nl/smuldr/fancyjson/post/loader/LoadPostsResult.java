package nl.smuldr.fancyjson.post.loader;

import java.util.List;

import nl.smuldr.fancyjson.shared.model.Post;


public final class LoadPostsResult {

    private final List<Post> data;
    private final Exception exception;

    LoadPostsResult(final List<Post> data) {
        this.data = data;
        this.exception = null;
    }

    LoadPostsResult(final Exception exception) {
        this.data = null;
        this.exception = exception;
    }

    public List<Post> getData() {
        return data;
    }

    public Exception getException() {
        return exception;
    }
}
