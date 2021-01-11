package testAutomationSelfEducation.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ScreenShot {

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