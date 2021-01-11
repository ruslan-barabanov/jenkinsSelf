package testAutomationSelfEducation.selfEducationTest;

import org.openqa.selenium.*;
import org.testng.Assert;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import testAutomationSelfEducation.BaseTest;
import testAutomationSelfEducation.pages.MyProjectPage;
import testAutomationSelfEducation.pages.NexagePage;
import testAutomationSelfEducation.pages.ProjectsPage;
import testAutomationSelfEducation.util.GenerateRandomString;
import testAutomationSelfEducation.util.ScreenShot;
import testAutomationSelfEducation.util.dataBaseUtil.DatabaseHandler;
import testAutomationSelfEducation.util.testRail.TestRailUtil;
import testAutomationSelfEducation.util.testRail.testrailClient.APIException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.*;


public class SelfEducationTest extends BaseTest {
    protected SelfEducationTest() throws IOException {
    }

    ProjectsPage projectsPage = new ProjectsPage(By.xpath(""), "button add");
    MyProjectPage myProjectPage = new MyProjectPage(By.xpath(""), "project Name");
    NexagePage nexagePage = new NexagePage(By.xpath(""), "Nexage");
    GenerateRandomString randomName = new GenerateRandomString();
    DatabaseHandler databaseHandler = new DatabaseHandler();
    String nameProject = randomName.randomString();
    String myNameProject = "My Best Project " + nameProject;
    protected static final String testForMe = "testForMe";
    protected static final String localhost = "localhost";
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

    @Test(priority = 4)
    public void getListNexage() throws SQLException, ClassNotFoundException, IOException {
        nexagePage.getNexage().click();
        Set<String> webElementsList = new HashSet<>();
        List<WebElement> tests = nexagePage.getNexageTestsUi();
        for (WebElement element : tests) {
            webElementsList.add(element.getText());
        }
        List<String> sortedStringsData = new ArrayList<>(databaseHandler.getNexageTests());
        List<String> sortedStrings = new ArrayList<>(webElementsList);

        Collections.sort(sortedStrings);
        Collections.sort(sortedStringsData);
        System.out.println(sortedStrings.equals(sortedStringsData));
    }

    @Test(priority = 2)
    public void addNewProject() throws IOException, URISyntaxException {

        projectsPage.getAddButton().click();
        getBrowser().getDriver().switchTo().defaultContent();

        myProjectPage.getInputMyProjectName().sendKeys(myNameProject + Keys.ENTER);
        getBrowser().getDriver().navigate().refresh();
        getBrowser().getDriver().switchTo().defaultContent();

        String actualName = myProjectPage.getNameMyProject(myNameProject);
        Assert.assertEquals(myNameProject, actualName);
        myProjectPage.clickNameMyProject(myNameProject);

        String id = fluentApi.sendPostTestId(actualName, testForMe);
        System.out.println(id);
        fluentApi.sendLogs(id);
        myProjectPage.clickMyTest(testForMe);
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
            TestRailUtil.addFelltestrailResult();
        }
        if (result.getStatus() == ITestResult.SUCCESS) {
            System.out.println("test passed");
            TestRailUtil.addPassedtestrailResult();
        }
    }
}
