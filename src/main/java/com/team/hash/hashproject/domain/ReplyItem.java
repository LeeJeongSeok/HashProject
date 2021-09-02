package com.team.hash.hashproject.domain;

import com.team.hash.hashproject.util.DateUtil;
import com.team.hash.hashproject.util.StringUtil;
import org.json.JSONException;
import org.json.JSONObject;

public class ReplyItem {

    public int no = 0;
    public String content = "";
    public String insertTime = "";

    public int userNo = 0;
    public String userName = "";
    public String userPictureUrl = "";

    public String isMe = "" ;

    public JSONObject getJSONObject() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("no", no);
        jsonObject.put("content", StringUtil.replaceNullForDB(content));
        jsonObject.put("insertTime", DateUtil.getDisplayDateTime(insertTime));
        jsonObject.put("userName", StringUtil.replaceNullForDB(userName));
        jsonObject.put("userPictureUrl", StringUtil.replaceNullForDB(userPictureUrl));
        jsonObject.put("isMe", StringUtil.replaceNullForDB(isMe));
        return jsonObject;
    }


}
