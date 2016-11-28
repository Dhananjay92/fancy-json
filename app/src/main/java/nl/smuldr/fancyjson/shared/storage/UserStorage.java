package nl.smuldr.fancyjson.shared.storage;


import android.util.LongSparseArray;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import nl.smuldr.fancyjson.shared.model.User;


@Singleton
final class UserStorage {

    private final LongSparseArray<User> cache = new LongSparseArray<>();

    @Inject
    UserStorage() {
        // annotated constructor for Dagger
    }

    User findUserDetails(long userId) throws IOException {
        final User user = cache.get(userId);
        if (user != null) {
            return user;
        } else {
            throw new IOException("Not found");
        }
    }

    void insertUserDetails(final User user) throws IOException {
        cache.put(user.getId(), user);
    }
}
