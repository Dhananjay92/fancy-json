package nl.smuldr.fancyjson.post.details;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import nl.smuldr.fancyjson.post.details.loader.PostDetailsLoader;
import nl.smuldr.fancyjson.shared.LoadResult;
import nl.smuldr.fancyjson.shared.model.Post;
import nl.smuldr.fancyjson.shared.model.TestData;

import static org.mockito.Mockito.mock;

public class PostDetailsPresenterTest {

    private final PostDetailsLoader mockLoader = mock(PostDetailsLoader.class);
    private final PostDetailsPresenter.View mockView = mock(PostDetailsPresenter.View.class);

    private PostDetailsPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new PostDetailsPresenter(mockLoader);
        presenter.subscribe(mockView, 42);
    }

    @Test
    public void onLoadFinished_success() throws Exception {
        final Post loadResult = TestData.POSTS.get(0);
        presenter.onLoadFinished(mockLoader, new LoadResult<>(loadResult));

        Mockito.verify(mockView).showPost(loadResult);
    }

    @Test
    public void onLoadFinished_failure() throws Exception {
        presenter.onLoadFinished(mockLoader, new LoadResult<Post>(new IOException("test")));

        Mockito.verifyZeroInteractions(mockView);
    }
}
