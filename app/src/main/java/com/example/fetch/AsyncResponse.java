package com.example.fetch;

import org.json.JSONArray;
import org.json.JSONObject;

public interface AsyncResponse {
    void processFinish(JSONArray output);
}
