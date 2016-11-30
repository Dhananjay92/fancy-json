package nl.smuldr.fancyjson.post.overview;

import android.content.Context;
import android.content.Intent;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;

import nl.smuldr.fancyjson.shared.LoadResult;
import nl.smuldr.fancyjson.shared.model.Post;
import nl.smuldr.fancyjson.shared.model.TestData;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

public class PostListPresenterTest {

    private final PostsLoader mockLoader = mock(PostsLoader.class);
    private final PostListPresenter.View mockView = mock(PostListPresenter.View.class);

    private PostListPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new PostListPresenter(mockLoader);
        presenter.subscribe(mockView);
    }

    @Test
    public void onLoadFinished_success() throws Exception {
        presenter.onLoadFinished(mockLoader, new LoadResult<>(TestData.POSTS));

        Mockito.verify(mockView).showPosts(TestData.POSTS);
    }

    @Test
    public void onLoadFinished_failure() throws Exception {
        presenter.onLoadFinished(mockLoader, new LoadResult<List<Post>>(new IOException("test")));

        Mockito.verifyZeroInteractions(mockView);
    }

    @Test
    public void onPostClick() throws Exception {
        final Context mockContext = mock(Context.class);

        presenter.onPostClick(mockContext, TestData.POSTS.get(0));

        Mockito.verify(mockContext).startActivity(any(Intent.class));
    }
}
