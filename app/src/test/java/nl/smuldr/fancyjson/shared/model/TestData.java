package nl.smuldr.fancyjson.shared.model;


import java.util.Arrays;
import java.util.List;

import nl.smuldr.fancyjson.shared.model.Comment;
import nl.smuldr.fancyjson.shared.model.PartialPost;
import nl.smuldr.fancyjson.shared.model.Post;
import nl.smuldr.fancyjson.shared.model.User;

public final class TestData {

    // corresponds with mock JSON response in resources/mockserver/ folder
    public static final List<PartialPost> PARTIAL_POSTS = Arrays.asList(
            new PartialPost(1, 1, "foo", "bar"),
            new PartialPost(2, 2, "baz", "qux"),
            new PartialPost(3, 1, "quux", "quuz")
    );

    // corresponds with mock JSON response in resources/mockserver/ folder
    public static final List<Comment> COMMENTS = Arrays.asList(
            new Comment(1, 1, "Alice", "Alice@gardner.biz", "foo"),
            new Comment(2, 1, "Bob", "bob@sydney.com", "bar"),
            new Comment(3, 1, "Carol", "carol@garfield.biz", "baz")
    );

    // corresponds with mock JSON response in resources/mockserver/ folder
    public static final List<User> USERS = Arrays.asList(
            new User(1, "Leanne Graham", "Bret", "Sincere@april.biz"),
            new User(2, "Erwin Howell", "Antonette", "Shanna@melissa.tv"),
            new User(3, "Clementine Bauch", "Samantha", "Nathan@yesenia.net")
    );

    // corresponds with mock JSON response in resources/mockserver/ folder
    public static final List<Post> POSTS = Arrays.asList(
            new Post(1, USERS.get(0), "foo", "bar"),
            new Post(2, USERS.get(1), "baz", "qux"),
            new Post(3, USERS.get(0), "quux", "quuz")
    );

    private TestData() {
        // do not instantiate
    }
}
