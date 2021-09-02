package com.team.hash.hashproject.domain;

import com.team.hash.hashproject.util.StringUtil;
import org.json.JSONException;
import org.json.JSONObject;

public class UserItem {
    public int no = 0;
    public String name = "";
    public String pictureUrl = "";
    public String instagramId = "";
    public String description = "";

    public JSONObject getJSONObject() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("no", no);
        jsonObject.put("name", StringUtil.replaceNullForDB(name));
        jsonObject.put("pictureUrl", StringUtil.replaceNullForDB(pictureUrl));
        jsonObject.put("instagramId", StringUtil.replaceNullForDB(instagramId));
        jsonObject.put("description", StringUtil.replaceNullForDB(description));
        return jsonObject;
    }
}
