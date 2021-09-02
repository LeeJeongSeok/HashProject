package com.team.hash.hashproject.repository;

import org.json.JSONObject;

public interface AppInformationDAO {
    public JSONObject login(String email, String password);
    public JSONObject join(String email, String password, String name, String sex, String height, String weight, String pictureUrl, String description, String strTagArray, String instagramId);
    public JSONObject updateUser(int userNo, String email, String password, String name, String sex, String height, String weight, String pictureUrl, String description, String strTagArray, String instagramId);
    public JSONObject getMyInformation(int userNo);

    public JSONObject getMyStyleArray(int userNo);
    public JSONObject getStyleArray(int userNo, String categoryName, String keyword, int page, int viewCount, String orderColumn, String orderDirection);

    public JSONObject writeStyle(int userNo, String content, String pictureUrl, String strPictureUrlArray, String strProductArray);
    public JSONObject updateStyle(int userNo, int styleNo, String pictureUrl, String content, String strPictureUrlArray, String strProductArray);
    public JSONObject deleteStyle(int userNo, int styleNo);

    public JSONObject updateStyleLike(int userNo, int styleNo, String isLike);

    public JSONObject getReplyArray(int userNo, int styleNo);
    public JSONObject writeReply(int userNo, int styleNo, String content);
    public JSONObject deleteReply(int userNo, int replyNo);
}
