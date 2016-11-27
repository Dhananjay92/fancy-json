package nl.smuldr.fancyjson.shared.network;


import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import nl.smuldr.fancyjson.shared.model.Post;
import nl.smuldr.fancyjson.shared.model.User;
import retrofit2.Response;
import retrofit2.Retrofit;
import timber.log.Timber;


public class PlaceholderClient {

    private final PlaceholderBackend service;

    @Inject
    public PlaceholderClient(final Retrofit retrofit) {
        this.service = retrofit.create(PlaceholderBackend.class);
    }

    public List<Post> getPosts() throws IOException {
        final Response<List<Post>> response = service.getPosts().execute();
        if (response.isSuccessful()) {
            return response.body();
        } else {
            Timber.w("Failed to get posts: %s", response.errorBody().string());
            throw new IOException(response.errorBody().string());
        }
    }

    public Post getPostDetails(long postId) throws IOException {
        final Response<Post> response = service.getPostDetails(postId).execute();
        if (response.isSuccessful()) {
            return response.body();
        } else {
            Timber.w("Failed to get post details: %s", response.errorBody().string());
            throw new IOException(response.errorBody().string());
        }
    }

    public User getUserDetails(long userId) throws IOException {
        final Response<User> response = service.getUserDetails(userId).execute();
        if (response.isSuccessful()) {
            return response.body();
        } else {
            Timber.w("Failed to get user details: %s", response.errorBody().string());
            throw new IOException(response.errorBody().string());
        }
    }
}
