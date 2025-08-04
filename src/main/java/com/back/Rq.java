package com.back;

import java.util.Arrays;
import java.util.Map;

public class Rq {
    private String actionName;
    private Map<String, String> queryParams;

    public Rq(String cmd) {
        String[] cmdBits = cmd.split("\\?", 2);
        actionName = cmdBits[0].trim();

        if (cmdBits.length > 1) {
            String queryString = cmdBits[1].trim();
            queryParams = Map.ofEntries(
                Arrays.stream(queryString.split("&"))
                    .map(e -> e.split("=", 2))
                    .filter(parts -> parts.length == 2)
                    .map(parts -> Map.entry(parts[0].trim(), parts[1].trim()))
                    .toArray(Map.Entry[]::new)
            );
        } else {
            queryParams = Map.of();
        }
    }

    public String getActionName() {
        return actionName;
    }

    public String getParam(String paramName, String defaultValue) {
       try {
            return queryParams.getOrDefault(paramName, defaultValue);
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
