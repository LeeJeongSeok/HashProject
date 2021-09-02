package com.team.hash.hashproject.api;

import org.json.JSONObject;

public interface AppInformationDAO {
	JSONObject login(String email, String password);
	JSONObject join(String email, String password, String name, String sex, String height, String weight, String pictureUrl, String description, String strTagArray, String instagramId);
	JSONObject updateUser(int userNo, String email, String password, String name, String sex, String height, String weight, String pictureUrl, String description, String strTagArray, String instagramId);
	JSONObject getMyInformation(int userNo);

	JSONObject getMyStyleArray(int userNo);
	JSONObject getStyleArray(int userNo, String categoryName, String keyword, int page, int viewCount, String orderColumn, String orderDirection);
	
	JSONObject writeStyle(int userNo, String content, String pictureUrl, String strPictureUrlArray, String strProductArray);
	JSONObject updateStyle(int userNo, int styleNo, String pictureUrl, String content, String strPictureUrlArray, String strProductArray);
	JSONObject deleteStyle(int userNo, int styleNo);
	
	JSONObject updateStyleLike(int userNo, int styleNo, String isLike);
	
	JSONObject getReplyArray(int userNo, int styleNo);
	JSONObject writeReply(int userNo, int styleNo, String content);
	JSONObject deleteReply(int userNo, int replyNo);
}