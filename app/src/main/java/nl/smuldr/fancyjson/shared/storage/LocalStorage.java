package nl.smuldr.fancyjson.shared.storage;


import android.util.LongSparseArray;

import java.io.IOException;

import javax.inject.Inject;

import nl.smuldr.fancyjson.shared.model.User;

public final class LocalStorage {

    private final LongSparseArray<User> cache = new LongSparseArray<>();

    @Inject
    public LocalStorage() {
        // annotated constructor for Dagger
    }

    public User findUserDetails(long userId) throws IOException {
        final User user = cache.get(userId);
        if (user != null) {
            return user;
        } else {
            throw new IOException("Not found");
        }
    }

    public void insertUserDetails(final User user) throws IOException {
        cache.put(user.getId(), user);
    }
}
