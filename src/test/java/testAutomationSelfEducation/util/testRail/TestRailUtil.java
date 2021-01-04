package testAutomationSelfEducation.util.testRail;


import aquality.selenium.browser.AqualityServices;
import org.json.simple.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import testAutomationSelfEducation.util.testRail.testrailClient.APIClient;
import testAutomationSelfEducation.util.testRail.testrailClient.APIException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestRailUtil {

    public static void addPassedtestrailResult() throws IOException, APIException {

        APIClient client = new APIClient("https://tr.a1qa.com/");
        client.setUser("r.barabanov");
        client.setPassword("CX6cHJofI6");

        Map<String, List<Map<String, Object>>> map = new HashMap<>();
        List<Map<String, Object>> res = new ArrayList<>();
        Map<String, Object> resObjs = new HashMap<>();

        resObjs.put("test_id", "58923230");
        resObjs.put("status_id", 1);
        resObjs.put("comment", "Test Passed");
        res.add(resObjs);
        map.put("results", res);
        File file = ((TakesScreenshot) AqualityServices.getBrowser().getDriver()).getScreenshotAs(OutputType.FILE);
        String screenshotBase64 = ((TakesScreenshot) AqualityServices.getBrowser().getDriver()).getScreenshotAs(OutputType.BASE64);
        client.sendPost("add_results/51095", map);
    }

    public static void addFelltestrailResult() throws IOException, APIException {

        APIClient client = new APIClient("https://tr.a1qa.com/");
        client.setUser("r.barabanov");
        client.setPassword("CX6cHJofI6");

        Map<String, List<Map<String, Object>>> map = new HashMap<>();
        List<Map<String, Object>> res = new ArrayList<>();
        Map<String, Object> resObjs = new HashMap<>();

        resObjs.put("test_id", "58923230");
        resObjs.put("status_id", 5);
        resObjs.put("comment", "Test Failed");
        res.add(resObjs);
        map.put("results", res);
        File file = ((TakesScreenshot) AqualityServices.getBrowser().getDriver()).getScreenshotAs(OutputType.FILE);
        String screenshotBase64 = ((TakesScreenshot) AqualityServices.getBrowser().getDriver()).getScreenshotAs(OutputType.BASE64);
        client.sendPost("add_results/51095", map);
    }
}