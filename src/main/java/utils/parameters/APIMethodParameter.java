package utils.parameters;

import lombok.Getter;

public enum APIMethodParameter {
    VARIANT ("variant"),
    PROJECT_ID ("projectId"),
    SID ("SID"),
    PROJECT_NAME ("projectName"),
    TEST_NAME ("testName"),
    METHOD_NAME ("methodName"),
    ENV ("env"),
    TEST_ID ("testId"),
    CONTENT ("content"),
    CONTENT_TYPE ("contentType");

    @Getter private String parameterName;

    APIMethodParameter(String parameterName) {
        this.parameterName = parameterName;
    }

    @Override
    public String toString() {
        return parameterName;
    }
}