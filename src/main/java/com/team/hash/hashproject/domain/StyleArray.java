package com.team.hash.hashproject.domain;

import org.json.JSONException;
import org.json.JSONObject;

public class StyleArray {

    private String categoryName = "";
    private String keyword = "";
    private int page = 0;
    private int viewCount = 0;
    private String orderColumn = "";
    private String orderDirection = "";

    public JSONObject getJSONObject() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("categoryName", categoryName);
        jsonObject.put("keyword", keyword);
        jsonObject.put("page", page);
        jsonObject.put("viewCount", viewCount);
        jsonObject.put("orderColumn", orderColumn);
        jsonObject.put("orderDirection", orderDirection);

        return jsonObject;
    }
}
