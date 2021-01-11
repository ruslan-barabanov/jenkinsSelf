package testAutomationSelfEducation.util.testRail;

import testAutomationSelfEducation.util.testRail.testrailClient.APIClient;
import testAutomationSelfEducation.util.testRail.testrailClient.APIException;

import java.io.IOException;
import java.util.*;

public class TestRailUtil {

    public static void addPassedtestrailResult() throws IOException, APIException {
        Properties properties = new Properties();
        APIClient client = new APIClient("https://tr.a1qa.com/");
        properties.load(ClassLoader.getSystemResourceAsStream("selfEducation.properties"));

        String setUser = properties.getProperty("testRailSetUser.path");
        String setPassword = properties.getProperty("testRailSetPassword.path");

        client.setUser(setUser);
        client.setPassword(setPassword);

        Map<String, List<Map<String, Object>>> map = new HashMap<>();
        List<Map<String, Object>> res = new ArrayList<>();
        Map<String, Object> resObjs = new HashMap<>();

        resObjs.put("test_id", "58923230");
        resObjs.put("status_id", 1);
        resObjs.put("comment", "Test Passed");
        res.add(resObjs);
        map.put("results", res);

        client.sendPost("add_results/51095", map);
    }

    public static void addFelltestrailResult() throws IOException, APIException {
        Properties properties = new Properties();
        APIClient client = new APIClient("https://tr.a1qa.com/");
        properties.load(ClassLoader.getSystemResourceAsStream("selfEducation.properties"));

        String setUser = properties.getProperty("testRailSetUser.path");
        String setPassword = properties.getProperty("testRailSetPassword.path");

        client.setUser(setUser);
        client.setPassword(setPassword);

        Map<String, List<Map<String, Object>>> map = new HashMap<>();
        List<Map<String, Object>> res = new ArrayList<>();
        Map<String, Object> resObjs = new HashMap<>();

        resObjs.put("test_id", "58923230");
        resObjs.put("status_id", 5);
        resObjs.put("comment", "Test Failed");
        res.add(resObjs);
        map.put("results", res);

        client.sendPost("add_results/51095", map);
    }
}