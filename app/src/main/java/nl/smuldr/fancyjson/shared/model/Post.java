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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (id != post.id) return false;
        if (!user.equals(post.user)) return false;
        if (!title.equals(post.title)) return false;
        return body.equals(post.body);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + user.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + body.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", user=" + user +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
