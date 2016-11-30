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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (id != comment.id) return false;
        if (postId != comment.postId) return false;
        if (!name.equals(comment.name)) return false;
        if (!email.equals(comment.email)) return false;
        return body.equals(comment.body);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (postId ^ (postId >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + body.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", postId=" + postId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
