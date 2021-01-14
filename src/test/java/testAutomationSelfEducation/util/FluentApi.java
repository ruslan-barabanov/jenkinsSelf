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
import java.util.Properties;

public class FluentApi {

    public FluentApi() {
    }
    Properties properties = new Properties();

    public String sendPostGetToken() throws IOException {
        final Collection<NameValuePair> params = new ArrayList<>();

        properties.load(ClassLoader.getSystemResourceAsStream("selfEducation.properties"));

        String requestPost = properties.getProperty("requestPost.path");

        final Content postResultForm = Request.Post(requestPost)
                .bodyForm(params, Charset.defaultCharset())
                .execute().returnContent();
        String token = postResultForm.asString();
        return token;
    }

    public String sendPostTestId(String myProjecktName, String testForMe) throws IOException, URISyntaxException {

        properties.load(ClassLoader.getSystemResourceAsStream("selfEducation.properties"));
        String sid = properties.getProperty("sid.path");
        String methodName = properties.getProperty("methodName.path");
        String env = properties.getProperty("env.path");

        URIBuilder ub = new URIBuilder();
        ub.setScheme("http").setHost("localhost").
                setPort(8080).setPath("/api/test/put").
                setParameter("SID", sid).
                setParameter("projectName", myProjecktName).
                setParameter("testName", testForMe).
                setParameter("methodName", methodName).
                setParameter("env", env);
        final Content postResultForm = Request.Post(ub.build())
                .execute().returnContent();
        String token = postResultForm.asString();
        return token;
    }

    public void sendLogs(String id) throws IOException {
        final CloseableHttpClient httpclient = HttpClients.createDefault();
        Properties properties = new Properties();
        properties.load(ClassLoader.getSystemResourceAsStream("selfEducation.properties"));

        String httpPostPath = properties.getProperty("httpPostPath.path");

        final HttpPost httpPost = new HttpPost(httpPostPath);
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
    public void makeAndSendScreen(String id, String screenshotBase64) throws IOException {

        final CloseableHttpClient httpclient = HttpClients.createDefault();
        Properties properties = new Properties();
        properties.load(ClassLoader.getSystemResourceAsStream("selfEducation.properties"));

        String httpAttachmentPost = properties.getProperty("httpAttachmentPost.path");
        final HttpPost httpPost = new HttpPost(httpAttachmentPost);
        final List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("testId", id));
        params.add(new BasicNameValuePair("content", screenshotBase64));
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
