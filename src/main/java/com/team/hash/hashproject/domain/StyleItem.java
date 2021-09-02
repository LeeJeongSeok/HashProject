package com.team.hash.hashproject.domain;

import com.team.hash.hashproject.util.DateUtil;
import com.team.hash.hashproject.util.StringUtil;
import org.json.JSONException;
import org.json.JSONObject;

public class StyleItem {
    public int no = 0;
    public String content = "";
    public String pictureUrl = "";
    public String strPictureUrlArray = "";
    public String strProductArray = "";
    public int viewCount = 0;
    public String insertTime = "";

    public int userNo = 0;
    public String userName = "";
    public String userPictureUrl = "";
    public String userInstagramId = "";
    public String userDescription = "";
    public String isLike = "";
    public int likeCount = 0;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getStrPictureUrlArray() {
        return strPictureUrlArray;
    }

    public void setStrPictureUrlArray(String strPictureUrlArray) {
        this.strPictureUrlArray = strPictureUrlArray;
    }

    public String getStrProductArray() {
        return strProductArray;
    }

    public void setStrProductArray(String strProductArray) {
        this.strProductArray = strProductArray;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPictureUrl() {
        return userPictureUrl;
    }

    public void setUserPictureUrl(String userPictureUrl) {
        this.userPictureUrl = userPictureUrl;
    }

    public String getUserInstagramId() {
        return userInstagramId;
    }

    public void setUserInstagramId(String userInstagramId) {
        this.userInstagramId = userInstagramId;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    public String getIsLike() {
        return isLike;
    }

    public void setIsLike(String isLike) {
        this.isLike = isLike;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public JSONObject getJSONObject() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("no", no);
        jsonObject.put("content", StringUtil.replaceNullForDB(content));
        jsonObject.put("pictureUrl", StringUtil.replaceNullForDB(pictureUrl));
        jsonObject.put("strPictureUrlArray", StringUtil.replaceNullForDB(strPictureUrlArray));
        jsonObject.put("strProductArray", StringUtil.replaceNullForDB(strProductArray));
        jsonObject.put("viewCount", viewCount);
        jsonObject.put("userNo", userNo);
        jsonObject.put("userName", StringUtil.replaceNullForDB(userName));
        jsonObject.put("userPictureUrl", StringUtil.replaceNullForDB(userPictureUrl));
        jsonObject.put("userInstagramId", StringUtil.replaceNullForDB(userInstagramId));
        jsonObject.put("userDescription", StringUtil.replaceNullForDB(userDescription));
        jsonObject.put("insertTime", DateUtil.getDisplayDateTime(insertTime));
        jsonObject.put("isLike", StringUtil.replaceNullForDB(isLike));
        jsonObject.put("likeCount", likeCount);
        return jsonObject;
    }
}
