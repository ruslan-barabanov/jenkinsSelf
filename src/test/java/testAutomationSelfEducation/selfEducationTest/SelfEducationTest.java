package testAutomationSelfEducation.selfEducationTest;



import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import testAutomationSelfEducation.BaseTest;
import testAutomationSelfEducation.pages.ProjectsPage;
import testAutomationSelfEducation.util.GenerateRandomString;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


public class SelfEducationTest extends BaseTest {
    protected SelfEducationTest() throws IOException {
    }

    ProjectsPage projectsPage = new ProjectsPage(By.xpath("//button[@class='btn btn-xs btn-primary pull-right']"), "button add");
    GenerateRandomString randomName = new GenerateRandomString();
    String nameProject = randomName.randomString();
    String myNameProject = "My Project " + nameProject;


    @Test
    public void newPostRequestGetToken() throws IOException {

        String token = fluentApi.sendPostGetToken();

        System.out.println(token);

        Cookie actualTokenCookie = new Cookie("tokenCookie", token);
        getBrowser().getDriver().manage().addCookie(actualTokenCookie);
        Cookie expectedTokenCookie = getBrowser().getDriver().manage().getCookieNamed("tokenCookie");
        Assert.assertEquals(actualTokenCookie, expectedTokenCookie);
        getBrowser().getDriver().navigate().refresh();
        System.out.println();
        String s = getBrowser().getDriver().findElement(By.xpath("//span[text()='Version: 0']")).getText();
        System.out.println(s);
    }

    @Test
    public void getListNextage() {

    }

    @Test
    public void addNewProject() throws IOException, URISyntaxException {

        projectsPage.getAddButton().click();
        getBrowser().getDriver().switchTo().defaultContent();

        getBrowser().getDriver().findElement(By.xpath("//input[@id='projectName']")).sendKeys(myNameProject + Keys.ENTER);
        WebDriverWait wait = new WebDriverWait(getBrowser().getDriver(), 10);
        getBrowser().getDriver().navigate().refresh();
        getBrowser().getDriver().switchTo().defaultContent();
        String actualName = getBrowser().getDriver().findElement(By.xpath("//a[text()='" + myNameProject + "']")).getText();
        System.out.println(actualName);
        Assert.assertEquals(myNameProject, actualName);

        getBrowser().getDriver().findElement(By.xpath("//a[text()='" + myNameProject + "']")).click();

        String id = fluentApi.sendPostTestId(actualName);
        System.out.println(id);
        getBrowser().getDriver().findElement(By.xpath("//a[text()='testForMe']")).click();
        String screenshotBase64 = ((TakesScreenshot) getBrowser().getDriver()).getScreenshotAs(OutputType.BASE64);
        System.out.println(screenshotBase64);

        final CloseableHttpClient httpclient = HttpClients.createDefault();

        final HttpPost httpPost = new HttpPost("http://localhost:8080/api/test/put/attachment");
        final List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("testId", "363"));
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

    @Test
    public void addTestInMyProject() throws IOException, URISyntaxException {
        getBrowser().getDriver().findElement(By.xpath("//a[text()='My Project loRFf']")).click();
        getBrowser().getDriver().findElement(By.xpath("//a[text()='testForMe']")).click();
        String actualProjectName = getBrowser().getDriver().findElement(By.xpath("//p[text()='My Project loRFf']")).getText();
        Assert.assertEquals(actualProjectName, "My Project loRFf");
        String actualTestName = getBrowser().getDriver().findElement(By.xpath("//p[text()='testForMe']")).getText();
        Assert.assertEquals(actualTestName, "testForMe");
        String actualEnvironmentName = getBrowser().getDriver().findElement(By.xpath("//p[text()='localhost']")).getText();
        Assert.assertEquals(actualEnvironmentName, "localhost");
    }
}
