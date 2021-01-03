package testAutomationSelfEducation.selfEducationTest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Post {
    public Post() throws IOException {
    }
    final URL url = new URL("http://jsonplaceholder.typicode.com/posts?_limit=10");
    final HttpURLConnection con = (HttpURLConnection) url.openConnection();


}
