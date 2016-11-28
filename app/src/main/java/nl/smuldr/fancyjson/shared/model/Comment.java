package nl.smuldr.fancyjson.shared.model;


public final class Comment {

    private final long id;
    private final long postId;
    private final String name;
    private final String email;
    private final String body;

    public Comment(long id, long postId, final String name, final String email, final String body) {
        this.id = id;
        this.postId = postId;
        this.name = name;
        this.email = email;
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
