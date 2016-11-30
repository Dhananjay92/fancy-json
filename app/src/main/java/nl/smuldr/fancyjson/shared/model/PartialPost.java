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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PartialPost that = (PartialPost) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (!title.equals(that.title)) return false;
        return body.equals(that.body);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + title.hashCode();
        result = 31 * result + body.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PartialPost{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
