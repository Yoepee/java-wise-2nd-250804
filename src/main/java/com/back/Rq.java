package com.back;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Rq {
    private String actionName;
    private Map<String, String> paramsMap;

    public Rq(String cmd) {
        String[] cmdBits = cmd.split("\\?", 2);
        actionName = cmdBits[0];
        String queryString = cmdBits.length > 1 ? cmdBits[1].trim() : "";

        String[] queryStringBits = queryString.split("&");

        paramsMap = Arrays.stream(queryStringBits)
                .map(e -> e.split("=", 2))
                .filter(e->e.length ==2)
                .collect(Collectors.toMap(e -> e[0].trim(), e->e[1].trim()));
    }

    public String getActionName() {
        return actionName;
    }

    public String getParam(String paramName, String defaultValue) {
       try {
            return paramsMap.getOrDefault(paramName, defaultValue);
        } catch (NullPointerException e) {
            return defaultValue;
        }
    }

    public int getParamAsInt(String paramName, int defaultValue) {
        try {
            return Integer.parseInt(getParam(paramName, String.valueOf(defaultValue)));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
