package utils.parameters;

import lombok.Getter;

public enum APIMethod {
    GET_TOKEN ("/token/get"),
    GET_TESTS_JSON ("/test/get/json"),
    PUT_TEST ("/test/put"),
    PUT_LOG ("/test/put/log"),
    PUT_ATTACHMENT ("/test/put/attachment");

    @Getter private String methodName;

    APIMethod(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public String toString() {
        return methodName;
    }
}
