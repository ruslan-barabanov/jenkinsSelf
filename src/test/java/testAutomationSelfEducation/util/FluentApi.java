package testAutomationSelfEducation.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FluentApi {

    public FluentApi() {
    }

    public String sendPostGetToken() throws IOException {
        final Collection<NameValuePair> params = new ArrayList<>();

        final Content postResultForm = Request.Post("http://localhost:8080/api/token/get?variant=4")
                .bodyForm(params, Charset.defaultCharset())
                .execute().returnContent();
        String token = postResultForm.asString();
        return token;
    }

    public String sendPostTestId(String myProjecktName, String testForMe) throws IOException, URISyntaxException {

        URIBuilder ub = new URIBuilder();
        ub.setScheme("http").setHost("localhost").setPort(8080).setPath("/api/test/put").setParameter("SID", "2222").setParameter("projectName", myProjecktName).
                setParameter("testName", testForMe).setParameter("methodName", "method").setParameter("env", "localhost");
        final Content postResultForm = Request.Post(ub.build())
                .execute().returnContent();
        String token = postResultForm.asString();
        return token;
    }

    public void sendLogs(String id) throws IOException {
        final CloseableHttpClient httpclient = HttpClients.createDefault();

        final HttpPost httpPost = new HttpPost("http://localhost:8080/api/test/put/log");
        final List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("testId", id));
        params.add(new BasicNameValuePair("content", "HelloLogTest"));
        httpPost.setEntity(new UrlEncodedFormEntity(params));
        try (
                CloseableHttpResponse response2 = httpclient.execute(httpPost)
        ) {
            final HttpEntity entity2 = response2.getEntity();
            System.out.println(EntityUtils.toString(entity2));
        }
        httpclient.close();
    }

    public void sendScreens() throws IOException {
        final CloseableHttpClient httpclient = HttpClients.createDefault();

        final HttpPost httpPost = new HttpPost("http://localhost:8080/api/test/put/attachment");
        final List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("testId", "363"));
        params.add(new BasicNameValuePair("content", "HelloLogTest"));
        params.add(new BasicNameValuePair("contentType", "image/png"));
        httpPost.setEntity(new UrlEncodedFormEntity(params));
        try (
                CloseableHttpResponse response2 = httpclient.execute(httpPost)
        ) {
            final HttpEntity entity2 = response2.getEntity();
            System.out.println(EntityUtils.toString(entity2));
        }
        httpclient.close();
    }
}
