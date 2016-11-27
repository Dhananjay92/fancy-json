package nl.smuldr.fancyjson.shared.storage;


import java.io.IOException;

import javax.inject.Inject;

import nl.smuldr.fancyjson.shared.model.User;
import nl.smuldr.fancyjson.shared.network.PlaceholderClient;

public final class UserRepository {

    private final LocalStorage localStorage;
    private final PlaceholderClient client;

    @Inject
    public UserRepository(final LocalStorage localStorage, final PlaceholderClient client) {
        this.localStorage = localStorage;
        this.client = client;
    }

    public User getUserDetails(long userId) throws IOException {
        try {
            return localStorage.findUserDetails(userId);
        } catch (final IOException e) {
            final User user = client.getUserDetails(userId);
            localStorage.insertUserDetails(user);
            return user;
        }
    }
}
