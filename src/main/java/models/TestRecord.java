package models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import logger.Log;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Getter
@EqualsAndHashCode
@ToString
@JsonAutoDetect
public class TestRecord {

    private final String testName;
    private final String testMethod;
    private String testResult;
    private String startTime;
    private String endTime;
    private String duration;

    public TestRecord(@JsonProperty(value="duration") String duration,
                      @JsonProperty(value="method") String method,
                      @JsonProperty(value="name") String name,
                      @JsonProperty(value="startTime") String startTime,
                      @JsonProperty(value="endTime") String endTime,
                      @JsonProperty(value="status") String status) {
        Log.info("Create test record: " + name);
        this.testName = name;
        this.testMethod = method;
        this.testResult = status.toUpperCase();
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        if (this.endTime == null) this.endTime = "";
    }

    public TestRecord(String name, String method) {
        this.testName = name;
        this.testMethod = method;
    }

    public static boolean isRecordsListContainsList(List<TestRecord> list, List<TestRecord> containedList) {
        Log.info("Check: is test records list contains list");
        for (TestRecord contained: containedList) {
            boolean isMatch = false;
            for (TestRecord element: list) {
                if (contained.equals(element)) {
                    isMatch = true;
                    break;
                }
            }
            if (!isMatch) return false;
        }
        return true;
    }

    public static boolean isListSortedByStartTime(List<TestRecord> testRecords) {
        Log.info("Check: is list sorted by start time");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        Date previousDate = new Date();
        try {
            for (TestRecord testRecord: testRecords) {
                Date currentDate = format.parse(testRecord.getStartTime());
                if (currentDate.after(previousDate)) return false;
                previousDate = currentDate;
            }
        } catch (ParseException e) {
            Log.error("Records isn't sorted");
        }
        return true;
    }
}