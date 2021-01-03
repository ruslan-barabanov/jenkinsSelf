package testAutomationSelfEducation.util.testRail;



import testAutomationSelfEducation.util.testRail.testrailClient.APIClient;
import testAutomationSelfEducation.util.testRail.testrailClient.APIException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestRailUtil {

    public static void addPassedtestrailResult(int testId) throws IOException, APIException {

        APIClient client = new APIClient("https://tr.a1qa.com/");
        client.setUser("r.barabanov");
        client.setPassword("CX6cHJofI6");

        Map<String, List<Map<String, Object>>> map = new HashMap<>();
        List<Map<String, Object>> res = new ArrayList<>();
        Map<String, Object> resObjs = new HashMap<>();

        resObjs.put("test_id", testId);
        resObjs.put("status_id", 1);
        resObjs.put("comment", "Test Passed");
        res.add(resObjs);
        map.put("results", res);
        client.sendPost("add_results/50755", map);

    }

    public static void addFelltestrailResult(int testId, String screen) throws IOException, APIException {

        APIClient client = new APIClient("https://tr.a1qa.com/");
        client.setUser("r.barabanov");
        client.setPassword("CX6cHJofI6");

        Map<String, List<Map<String, Object>>> map = new HashMap<>();
        List<Map<String, Object>> res = new ArrayList<>();
        Map<String, Object> resObjs = new HashMap<>();

        resObjs.put("test_id", "58923230");
        resObjs.put("status_id", 5);
        res.add(resObjs);
        map.put("results", res);

        client.sendPost("add_attachment_to_result/51095", "C:\\Users\\Ruslan\\Desktop\\скрины по заданиям\\idea url json.png");
        client.sendPost("add_results/51095", map);
    }
}