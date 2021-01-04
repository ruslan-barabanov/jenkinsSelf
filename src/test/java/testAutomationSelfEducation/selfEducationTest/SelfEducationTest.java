package testAutomationSelfEducation.selfEducationTest;

import aquality.selenium.browser.AqualityServices;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import testAutomationSelfEducation.BaseTest;
import testAutomationSelfEducation.pages.MyProjectPage;
import testAutomationSelfEducation.pages.ProjectsPage;
import testAutomationSelfEducation.util.GenerateRandomString;
import testAutomationSelfEducation.util.ScreenShot;
import testAutomationSelfEducation.util.testRail.TestRailUtil;
import testAutomationSelfEducation.util.testRail.testrailClient.APIException;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;


public class SelfEducationTest extends BaseTest {
    protected SelfEducationTest() throws IOException {
    }

    ProjectsPage projectsPage = new ProjectsPage(By.xpath("//button[@class='btn btn-xs btn-primary pull-right']"), "button add");
    MyProjectPage myProjectPage = new MyProjectPage(By.xpath("//input[@id='projectName']"), "project Name");
    GenerateRandomString randomName = new GenerateRandomString();
    TestRailUtil testRailUtil = new TestRailUtil();
    String nameProject = randomName.randomString();
    String myNameProject = "My Best Project " + nameProject;
    String testForMe = "testForMe";
    String localhost = "localhost";
    ScreenShot screenShot = new ScreenShot();


    @Test(priority = 1)
    public void newPostRequestGetToken() throws IOException {

        String token = fluentApi.sendPostGetToken();
        System.out.println(token);

        Cookie actualTokenCookie = new Cookie("tokenCookie", token);
        getBrowser().getDriver().manage().addCookie(actualTokenCookie);
        Cookie expectedTokenCookie = getBrowser().getDriver().manage().getCookieNamed("tokenCookie");
        Assert.assertEquals(actualTokenCookie, expectedTokenCookie);
        getBrowser().getDriver().navigate().refresh();

        String actualText = projectsPage.getVersionName().getText();
        System.out.println(actualText);
    }

    @Test
    public void getListNextage() {
        getBrowser().getDriver().findElement(By.xpath("//a[text()='Nexage']")).click();
        List<WebElement> tests = getBrowser().getDriver().findElements(By.xpath("//tr"));
        for (WebElement element : tests) {
            System.out.println(element.getText());
        }
    }

    @Test(priority = 2)
    public void addNewProject() throws IOException, URISyntaxException {

        projectsPage.getAddButton().click();
        getBrowser().getDriver().switchTo().defaultContent();

        myProjectPage.getInputMyProjectName().sendKeys(myNameProject + Keys.ENTER);
        WebDriverWait wait = new WebDriverWait(getBrowser().getDriver(), 10);
        getBrowser().getDriver().navigate().refresh();
        getBrowser().getDriver().switchTo().defaultContent();

        String actualName = myProjectPage.getNameMyProject(myNameProject);
        Assert.assertEquals(myNameProject, actualName);
        myProjectPage.clickNameMyProject(myNameProject);

        String id = fluentApi.sendPostTestId(actualName, testForMe);
        System.out.println(id);
        fluentApi.sendLogs(id);
        myProjectPage.clickMyTest(testForMe);
        File file = ((TakesScreenshot) getBrowser().getDriver()).getScreenshotAs(OutputType.FILE);
        String screenshotBase64 = ((TakesScreenshot) getBrowser().getDriver()).getScreenshotAs(OutputType.BASE64);
        screenShot.makeAndSendScreen(id, screenshotBase64);
    }

    @Test(priority = 3)
    public void addTestInMyProject() {
        myProjectPage.clickNameMyProject(myNameProject);
        myProjectPage.clickMyTest(testForMe);
        String actualProjectName = myProjectPage.getNameMyProject(myNameProject);
        Assert.assertEquals(actualProjectName, myNameProject);
        String actualTestName = myProjectPage.getNameMyTest(testForMe);
        Assert.assertEquals(actualTestName, testForMe);
        String actualEnvironmentName = myProjectPage.getEnvironmentName(localhost);
        Assert.assertEquals(actualEnvironmentName, localhost);
    }

    @AfterMethod
    public void checkResult2(ITestResult result) throws IOException, APIException {
        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("test fell");
            File file = ((TakesScreenshot) getBrowser().getDriver()).getScreenshotAs(OutputType.FILE);
            String screenshotBase64 = ((TakesScreenshot)getBrowser().getDriver()).getScreenshotAs(OutputType.BASE64);
            testRailUtil.addFelltestrailResult(screenshotBase64);

        }
        if (result.getStatus() == ITestResult.SUCCESS) {
            System.out.println("test passed");
            File file = ((TakesScreenshot) getBrowser().getDriver()).getScreenshotAs(OutputType.FILE);
            String screenshotBase64 = ((TakesScreenshot) AqualityServices.getBrowser().getDriver()).getScreenshotAs(OutputType.BASE64);
            testRailUtil.addPassedtestrailResult(screenshotBase64);

        }
    }
}
