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
import org.testng.annotations.Test;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.*;

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


    public String sendPostTestId(String myProjecktName) throws IOException, URISyntaxException {

        URIBuilder ub = new URIBuilder();
        ub.setScheme("http").setHost("localhost").setPort(8080).setPath("/api/test/put").setParameter("SID", "2222").setParameter("projectName", myProjecktName).
                setParameter("testName", "testForMe").setParameter("methodName", "method").setParameter("env", "localhost");
        final Content postResultForm = Request.Post(ub.build())
                .execute().returnContent();
        String token = postResultForm.asString();
        return token;

    }


    public String sendLogTestId() throws IOException, URISyntaxException {

        URIBuilder ub = new URIBuilder();
        ub.setScheme("http").setHost("localhost").setPort(8080).setPath("/api/test/put").setParameter("testId", "363").setParameter("content", "HelloLog");
        final Content postResultForm = Request.Post(ub.build())
                .execute().returnContent();
        String token = postResultForm.asString();
        return token;

    }

    public void sendScreen(String id, String screen) throws URISyntaxException, IOException {
        URIBuilder ub = new URIBuilder();
        ub.setScheme("http").setHost("localhost").setPort(8080).setPath("/api/test/put/attachment").setParameter("testId", id).setParameter("content", screen).
                setParameter("contentType", "image/png");
        final Content postResultForm = Request.Post(ub.build())
                .execute().returnContent();
        String token = postResultForm.asString();
    }

    public void sendLogs() throws IOException {
        final CloseableHttpClient httpclient = HttpClients.createDefault();

        final HttpPost httpPost = new HttpPost("http://localhost:8080/api/test/put/log");
        final List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("testId", "363"));
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
