package com.team.hash.hashproject.api;

import org.json.JSONObject;

public class AppInformationManager {
	private static AppInformationManager mAppManager = null;
	private AppInformationDAO mAppDAO = null;
	
	private AppInformationManager() {
		init();
	}
	
	public static AppInformationManager getInstance() {
		if (mAppManager == null) {
			synchronized (AppInformationManager.class) {
				if (mAppManager == null)
					mAppManager = new AppInformationManager();
			}
		}
		return mAppManager;
	}
	
	private void init() {
		mAppDAO = AppInformationDAOFactory.getInstance().getDAO();
	}
	
	public JSONObject login(String email, String password) {
		return mAppDAO.login(email, password);
	}
	public JSONObject join(String email, String password, String name, String sex, String height, String weight, String pictureUrl, String description, String strTagArray, String instagramId) {
		return mAppDAO.join(email, password, name, sex, height, weight, pictureUrl, description, strTagArray, instagramId);
	}
	public JSONObject updateUser(int userNo, String email, String password, String name, String sex, String height, String weight, String pictureUrl, String description, String strTagArray, String instagramId) {
		return mAppDAO.updateUser(userNo, email, password, name, sex, height, weight, pictureUrl, description, strTagArray, instagramId);
	}
	public JSONObject getMyInformation(int userNo) {
		return mAppDAO.getMyInformation(userNo);
	}

	public JSONObject getMyStyleArray(int userNo) {
		return mAppDAO.getMyStyleArray(userNo);
	}
	public JSONObject getStyleArray(int userNo, String categoryName, String keyword, int page, int viewCount, String orderColumn, String orderDirection) {
		return mAppDAO.getStyleArray(userNo, categoryName, keyword, page, viewCount, orderColumn, orderDirection);
	}

	public JSONObject writeStyle(int userNo, String content, String pictureUrl, String strPictureUrlArray, String strProductArray) {
		return mAppDAO.writeStyle(userNo, content, pictureUrl, strPictureUrlArray, strProductArray);
	}
	public JSONObject updateStyle(int userNo, int styleNo, String content, String pictureUrl, String strPictureUrlArray, String strProductArray) {
		return mAppDAO.updateStyle(userNo, styleNo, content, pictureUrl, strPictureUrlArray, strProductArray);
	}
	public JSONObject deleteStyle(int userNo, int styleNo) {
		return mAppDAO.deleteStyle(userNo, styleNo);
	}
	
	public JSONObject updateStyleLike(int userNo, int styleNo, String isLike) {
		return mAppDAO.updateStyleLike(userNo, styleNo, isLike);
	}
	
	public JSONObject getReplyArray(int userNo, int styleNo) {
		return mAppDAO.getReplyArray(userNo, styleNo);
	}
	public JSONObject writeReply(int userNo, int styleNo, String content) {
		return mAppDAO.writeReply(userNo, styleNo, content);
	}
	public JSONObject deleteReply(int userNo, int replyNo) {
		return mAppDAO.deleteReply(userNo, replyNo);
	}
}
