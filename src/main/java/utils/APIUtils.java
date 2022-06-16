package utils;

import aquality.selenium.core.utilities.JsonSettingsFile;
import logger.Log;
import models.TestRecord;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.openqa.selenium.logging.LogEntries;
import utils.parameters.APIContentType;
import utils.parameters.APIMethod;
import utils.parameters.APIMethodParameter;

import java.io.IOException;
import java.util.ArrayList;

public final class APIUtils {

    private final static String apiUrl = new JsonSettingsFile("settings.json").getValue("/api_url").toString();

    private APIUtils() {}

    private static String sendPOST(HttpPost post) {
        Log.info("Send POST: " + post.getRequestUri());
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {
            return EntityUtils.toString(response.getEntity());
        } catch (IOException | ParseException e) {
            Log.error(post.getRequestUri()+ " ERROR: " + e);
            throw new RuntimeException(e);
        }
    }

    public static String getToken() {
        Log.info("Get token");
        HttpPost post = new HttpPost(apiUrl + APIMethod.GET_TOKEN);
        post.setEntity(new UrlEncodedFormEntity(new ArrayList<>() {{
            add(new BasicNameValuePair(APIMethodParameter.VARIANT.toString(), new JsonSettingsFile("settings.json").getValue("/variant").toString()));
        }}));
        return sendPOST(post);
    }

    public static String getTestsJson(String projectID) {
        Log.info("Get tests json. ProjectID=" + projectID);
        HttpPost post = new HttpPost(apiUrl + APIMethod.GET_TESTS_JSON);
        post.setEntity(new UrlEncodedFormEntity(new ArrayList<>() {{
            add(new BasicNameValuePair(APIMethodParameter.PROJECT_ID.toString(), projectID));
        }}));
        return sendPOST(post);
    }

    public static String putTestRecord(String projectName, TestRecord testRecord) {
        Log.info("Put test record");
        HttpPost post = new HttpPost(apiUrl + APIMethod.PUT_TEST);
        post.setEntity(new UrlEncodedFormEntity(new ArrayList<>() {{
            add(new BasicNameValuePair(APIMethodParameter.SID.toString(), SIDUtility.getSID()));
            add(new BasicNameValuePair(APIMethodParameter.PROJECT_NAME.toString(), projectName));
            add(new BasicNameValuePair(APIMethodParameter.TEST_NAME.toString(), testRecord.getTestName()));
            add(new BasicNameValuePair(APIMethodParameter.METHOD_NAME.toString(), testRecord.getTestMethod()));
            add(new BasicNameValuePair(APIMethodParameter.ENV.toString(), SystemDataUtility.getThisEnvName()));
        }}));
        return sendPOST(post);
    }

    public static void putLog(String testID, LogEntries logs) {
        Log.info("Put logs: " + logs);
        HttpPost post = new HttpPost(apiUrl + APIMethod.PUT_LOG);
        post.setEntity(new UrlEncodedFormEntity(new ArrayList<>() {{
            add(new BasicNameValuePair(APIMethodParameter.TEST_ID.toString(), testID));
            add(new BasicNameValuePair(APIMethodParameter.CONTENT.toString(), logs.toString()));
        }}));
        sendPOST(post);
    }

    public static void putAttachment(String testID, String base64encodedData, APIContentType type) {
        Log.info("Put attachment: " + type);
        HttpPost post = new HttpPost(apiUrl + APIMethod.PUT_ATTACHMENT);
        post.setEntity(new UrlEncodedFormEntity(new ArrayList<>() {{
            add(new BasicNameValuePair(APIMethodParameter.TEST_ID.toString(), testID));
            add(new BasicNameValuePair(APIMethodParameter.CONTENT.toString(), base64encodedData));
            add(new BasicNameValuePair(APIMethodParameter.CONTENT_TYPE.toString(), type.toString()));
        }}));
        sendPOST(post);
    }
}