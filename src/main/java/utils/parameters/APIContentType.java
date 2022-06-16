package utils.parameters;

import lombok.Getter;

public enum APIContentType {
    IMAGE ("image/png"),
    TEXT ("text/html");

    @Getter
    private String type;

    APIContentType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
