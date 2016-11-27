package nl.smuldr.fancyjson.shared.model;


public final class Post {

    private final long id;
    private final long userId;
    private final String title;
    private final String body;

    public Post(final long id, final long userId, final String title, final String body) {
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
}
