package nl.smuldr.fancyjson.shared.storage;


import android.text.format.DateUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import nl.smuldr.fancyjson.shared.model.Post;
import nl.smuldr.fancyjson.shared.network.PlaceholderClient;


public class PostRepository {

    private final PlaceholderClient client;
    private List<Post> cachedPosts = null;
    private long cacheTimestamp = 0L;

    @Inject
    public PostRepository(final PlaceholderClient client) {
        this.client = client;
    }

    public List<Post> getPosts() throws IOException {
        if (cachedPosts == null || isCacheOutdated()) {
            // nothing in the cache, load fresh data from network
            cachedPosts = client.getPosts();
            cacheTimestamp = System.currentTimeMillis();
        }
        return cachedPosts;
    }

    public Post getPostDetails(long postId) throws IOException {
        // search the cache
        if (cachedPosts != null && !isCacheOutdated()) {
            for (final Post post : cachedPosts) {
                if (post.getId() == postId) {
                    return post;
                }
            }
        }
        // fall back on the network
        final Post post = client.getPostDetails(postId);
        if (cachedPosts == null) {
            cachedPosts = Collections.singletonList(post);
        } else {
            cachedPosts.add(post);
        }
        return post;
    }

    /**
     * @return true if the cache contains stale data
     */
    private boolean isCacheOutdated() {
        return System.currentTimeMillis() - cacheTimestamp > 5 * DateUtils.MINUTE_IN_MILLIS;
    }
}
