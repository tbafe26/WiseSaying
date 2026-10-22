package com.back;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Rq {
    private final String actionName;
    private final Map<String, String> paramsMap;

    public Rq(String command) {
        String[] parts = command.split("\\?", 2);

        this.actionName = parts[0];

        String queryString = parts.length > 1 ? parts[1].trim() : "";

        this.paramsMap = Arrays.stream(queryString.split("&"))
                .map(param -> param.split("=", 2))
                .filter(keyValue ->
                        keyValue.length == 2
                                && !keyValue[0].isBlank()
                                && !keyValue[1].isBlank()
                )
                .collect(Collectors.toMap(
                        keyValue -> keyValue[0].trim(),
                        keyValue -> keyValue[1].trim()
                ));
    }

    public String getActionName() {
        return this.actionName;
    }

    public String getParam(String paramName, String defaultValue) {
        return this.paramsMap.getOrDefault(paramName, defaultValue);
    }

    public int getParamAsInt(String paramName, int defaultValue) {
        String value = getParam(paramName, "");

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}