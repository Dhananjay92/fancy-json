package nl.smuldr.fancyjson.shared.model;


/**
 * Complete model of a post, contains the full user details.
 *
 * @see PartialPost
 */
public final class Post {

    private final long id;
    private final User user;
    private final String title;
    private final String body;

    public static Post createFromPartial(final PartialPost partialPost, final User user) {
        return new Post(partialPost.getId(), user, partialPost.getTitle(), partialPost.getBody());
    }

    public Post(final long id, final User user, final String title, final String body) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public User getUser() {
        return user;
    }
}
