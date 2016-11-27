package nl.smuldr.fancyjson.shared.model;


/**
 * Basic model of a post as it is returned by the backend.
 *
 * @see Post
 */
public final class PartialPost {

    private final long id;
    private final long userId;
    private final String title;
    private final String body;

    public PartialPost(final long id, final long userId, final String title, final String body) {
        this.id = id;
        this.userId = userId;
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

    public long getUserId() {
        return userId;
    }
}
