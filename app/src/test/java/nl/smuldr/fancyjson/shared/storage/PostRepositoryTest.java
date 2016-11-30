package nl.smuldr.fancyjson.shared.storage;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import nl.smuldr.fancyjson.shared.model.Comment;
import nl.smuldr.fancyjson.shared.model.Post;
import nl.smuldr.fancyjson.shared.model.TestData;
import nl.smuldr.fancyjson.shared.network.PlaceholderClient;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PostRepositoryTest {

    private final PlaceholderClient mockNetwork = mock(PlaceholderClient.class);
    private final UserRepository mockUserRepo = mock(UserRepository.class);

    @Test
    public void getPosts() throws Exception {
        when(mockNetwork.getPosts()).thenReturn(TestData.PARTIAL_POSTS);
        when(mockUserRepo.getUserDetails(1)).thenReturn(TestData.USERS.get(0));
        when(mockUserRepo.getUserDetails(2)).thenReturn(TestData.USERS.get(1));
        final PostRepository repo = new PostRepository(mockNetwork, mockUserRepo);

        final List<Post> result = repo.getPosts();

        assertThat(result, is(TestData.POSTS));
    }

    @Test
    public void getPost_withCache() throws Exception {
        when(mockNetwork.getPosts()).thenReturn(TestData.PARTIAL_POSTS);
        when(mockUserRepo.getUserDetails(1)).thenReturn(TestData.USERS.get(0));
        when(mockUserRepo.getUserDetails(2)).thenReturn(TestData.USERS.get(1));
        final PostRepository repo = new PostRepository(mockNetwork, mockUserRepo);

        // request posts from network the first time
        repo.getPosts();
        Mockito.verify(mockNetwork).getPosts();

        Mockito.reset(mockNetwork);

        // do not hit network the second time
        repo.getPosts();
        Mockito.verifyZeroInteractions(mockNetwork);
    }

    @Test
    public void getComments() throws Exception {
        when(mockNetwork.getComments(1)).thenReturn(TestData.COMMENTS);
        final PostRepository repo = new PostRepository(mockNetwork, mockUserRepo);

        final List<Comment> result = repo.getComments(1);

        assertThat(result, is(TestData.COMMENTS));
    }
}
