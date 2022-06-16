package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import logger.Log;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public final class JsonReader {

    private JsonReader() {}

    public static <T> List<T> getModelsList(Class<T[]> tClass, String json) {
        Log.info("Get list of objects from json: " + json);
        try {
            return Arrays.asList(new ObjectMapper().readValue(json, tClass));
        } catch (IOException e) {
            throw new RuntimeException("JSON doesn't contain these models");
        }
    }
}