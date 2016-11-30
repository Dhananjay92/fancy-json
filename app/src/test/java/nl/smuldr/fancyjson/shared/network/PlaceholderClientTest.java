package nl.smuldr.fancyjson.shared.network;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import nl.smuldr.fancyjson.shared.model.TestData;
import nl.smuldr.fancyjson.shared.model.Comment;
import nl.smuldr.fancyjson.shared.model.PartialPost;
import nl.smuldr.fancyjson.shared.model.User;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PlaceholderClientTest {

    @Test
    public void getPosts_happy() throws Exception {
        final String mockResponseBody = readTextResource("mockserver/posts/posts.json");
        final MockResponse mockResponse = new MockResponse().setBody(mockResponseBody);
        final PlaceholderClient client = createClient(mockResponse);

        final List<PartialPost> result = client.getPosts();

        assertThat(result, is(TestData.PARTIAL_POSTS));
    }

    @Test(expected = IOException.class)
    public void getPosts_notFound() throws Exception {
        final MockResponse mockResponse = new MockResponse().setBody("[]").setResponseCode(404);
        final PlaceholderClient client = createClient(mockResponse);

        // should throw exception
        client.getPosts();
    }

    @Test
    public void getPostDetails_happy() throws Exception {
        final String mockResponseBody = readTextResource("mockserver/posts/post.json");
        final MockResponse mockResponse = new MockResponse().setBody(mockResponseBody);
        final PlaceholderClient client = createClient(mockResponse);

        final PartialPost result = client.getPost(1);

        assertThat(result, is(TestData.PARTIAL_POSTS.get(0)));
    }

    @Test(expected = IOException.class)
    public void getPostDetails_notFound() throws Exception {
        final MockResponse mockResponse = new MockResponse().setBody("{}").setResponseCode(404);
        final PlaceholderClient client = createClient(mockResponse);

        // should throw exception
        client.getPost(1);
    }

    @Test
    public void getUserDetails_happy() throws Exception {
        final String mockResponseBody = readTextResource("mockserver/users/user.json");
        final MockResponse mockResponse = new MockResponse().setBody(mockResponseBody);
        final PlaceholderClient client = createClient(mockResponse);

        final User result = client.getUserDetails(1);

        assertThat(result, is(TestData.USERS.get(0)));
    }

    @Test
    public void getComments_happy() throws Exception {
        final String mockResponseBody = readTextResource("mockserver/posts/comments/comments.json");
        final MockResponse mockResponse = new MockResponse().setBody(mockResponseBody);
        final PlaceholderClient client = createClient(mockResponse);

        final List<Comment> result = client.getComments(1);

        assertThat(result, is(TestData.COMMENTS));
    }

    private PlaceholderClient createClient(final MockResponse mockResponse) throws Exception {
        // prepare mock web server
        final MockWebServer server = new MockWebServer();
        server.enqueue(mockResponse);
        server.start();

        // get the mock base url
        final HttpUrl baseUrl = server.url("/");

        // create client with mock backend
        final Retrofit retrofit = HttpClientUtils.getRetrofit(baseUrl.toString(), HttpClientUtils.getHttpClient());
        return new PlaceholderClient(retrofit);
    }

    private String readTextResource(final String filename) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        InputStream inputStream = null;
        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(filename);
            final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                stringBuilder.append(currentLine);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return stringBuilder.toString();
    }
}
