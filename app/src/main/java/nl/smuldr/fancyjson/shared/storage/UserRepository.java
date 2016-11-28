package nl.smuldr.fancyjson.shared.storage;


import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import nl.smuldr.fancyjson.shared.model.User;
import nl.smuldr.fancyjson.shared.network.PlaceholderClient;


@Singleton
public final class UserRepository {

    private final UserStorage userStorage;
    private final PlaceholderClient client;

    @Inject
    public UserRepository(final UserStorage userStorage, final PlaceholderClient client) {
        this.userStorage = userStorage;
        this.client = client;
    }

    public User getUserDetails(long userId) throws IOException {
        try {
            return userStorage.findUserDetails(userId);
        } catch (final IOException e) {
            final User user = client.getUserDetails(userId);
            userStorage.insertUserDetails(user);
            return user;
        }
    }
}
