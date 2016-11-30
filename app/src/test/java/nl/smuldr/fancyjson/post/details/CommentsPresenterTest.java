package nl.smuldr.fancyjson.post.details;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;

import nl.smuldr.fancyjson.post.details.loader.CommentsLoader;
import nl.smuldr.fancyjson.shared.LoadResult;
import nl.smuldr.fancyjson.shared.model.Comment;
import nl.smuldr.fancyjson.shared.model.TestData;

import static org.mockito.Mockito.mock;

public class CommentsPresenterTest {

    private final CommentsLoader mockLoader = mock(CommentsLoader.class);
    private final CommentsPresenter.View mockView = mock(CommentsPresenter.View.class);

    private CommentsPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new CommentsPresenter(mockLoader);
        presenter.subscribe(mockView, 42);
    }

    @Test
    public void onLoadFinished_success() throws Exception {
        List<Comment> loadResult = TestData.COMMENTS;
        presenter.onLoadFinished(mockLoader, new LoadResult<>(loadResult));

        Mockito.verify(mockView).showComments(loadResult);
    }

    @Test
    public void onLoadFinished_failure() throws Exception {
        presenter.onLoadFinished(mockLoader, new LoadResult<List<Comment>>(new IOException("test")));

        Mockito.verifyZeroInteractions(mockView);
    }
}
