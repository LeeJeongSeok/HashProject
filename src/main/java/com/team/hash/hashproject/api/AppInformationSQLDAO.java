package com.team.hash.hashproject.api;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.team.hash.hashproject.db.DBConstant;
import com.team.hash.hashproject.db.DBUtil;
import com.team.hash.hashproject.domain.ReplyItem;
import com.team.hash.hashproject.domain.StyleItem;
import com.team.hash.hashproject.domain.UserItem;
import com.team.hash.hashproject.util.DateUtil;
import com.team.hash.hashproject.util.PrintUtil;
import com.team.hash.hashproject.util.StringUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AppInformationSQLDAO implements AppInformationDAO {

	// 실질적으로 mapper 안에 들어가야할 로직들임
	@Override
	public JSONObject login(String email, String password) {
		Connection connection = null;
		Statement stmt = null;
		ResultSet result = null;

		boolean isConfirm = false;
		int no = 0;
		String name = "";
		
		String sql = "";
		try {
			Class.forName(DBConstant.DRIVER_NAME);
			connection = DBUtil.getConnection();
			stmt = connection.createStatement();

			sql = "SELECT no, name FROM user_t WHERE email='"+email+"' AND password='"+password+"' LIMIT 1";
			result = stmt.executeQuery(sql);
//			System.out.println("result : " + result.getInt("no"));
			while(result.next()) {
				no = result.getInt("no");
				name = result.getString("name");
				break;
			}
			result.close();

			if(no > 0) {
				isConfirm = true;

				String lastLoginTime = DateUtil.getTodayDate("yyyy-MM-dd HH:mm:ss");
				sql = "UPDATE user_t SET lastLoginTime='"+lastLoginTime+"' WHERE no="+no;
				stmt.execute(sql);
			}
		} catch(SQLException | ClassNotFoundException e) {
			PrintUtil.print("e) " + sql + ", e: " + e.toString());
		} finally {
			if(result != null) {
				try {
					result.close();
				} catch (SQLException e) {
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
				}
			}
		}

		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("isConfirm", isConfirm ? "Y" : "N");
			jsonObject.put("no", no);
			jsonObject.put("name", StringUtil.replaceNullForDB(name));

			System.out.println("jsonobject from db : " + jsonObject.toString());
		} catch(JSONException e) {
		}
		return jsonObject;
	}
	
	@Override
	public JSONObject join(String email, String password, String name, String sex, String height, String weight, String pictureUrl, String description, String strTagArray, String instagramId) {
		Connection connection = null;
		Statement stmt = null;
		ResultSet result = null;

		boolean isExistUser = false;
		boolean isConfirm = false;
		
		String sql = "";
		
		try {
			Class.forName(DBConstant.DRIVER_NAME);
			connection = DBUtil.getConnection();	
			stmt = connection.createStatement();

			
			sql = "SELECT no FROM user_t WHERE email='"+email+"' LIMIT 1";
			result = stmt.executeQuery(sql);
			while(result.next()) {
				int no = result.getInt("no");
				if(no > 0) {
					isExistUser = true;
				}
				break;
			}
			result.close();
			
			if(!isExistUser) {
				String insertTime = DateUtil.getTodayDate("yyyy-MM-dd HH:mm:ss");

				String strKeyArray = "email, password, name, sex, height, weight, pictureUrl, description, strTagArray, instagramId, joinTime, lastLoginTime";
				String strValueArray = "'"+email+"','"+password+"','"+name+"','"+sex+"','"+height+"','"+weight+"','"+pictureUrl+"','"+description+"','"+strTagArray+"','"+instagramId+"','"+insertTime+"','"+insertTime+"'";

				sql = "INSERT INTO user_t("+strKeyArray+") VALUES("+strValueArray+")";
				int rowCount = stmt.executeUpdate(sql);
				if(rowCount > 0) {
					isConfirm = true;
				}
			}
		} catch(SQLException | ClassNotFoundException e) {
			PrintUtil.print("e) " + sql);
		} finally {
			if(result != null) {
				try {
					result.close();
				} catch (SQLException e) {
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
				}
			}
		}

		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("isConfirm", isConfirm ? "Y" : "N");
			jsonObject.put("isExistUser", isExistUser ? "Y" : "N");
		} catch(JSONException e) {
		}

		return jsonObject;
	}
	
	
	@Override
	public JSONObject updateUser(int userNo, String email, String password, String name, String sex, String height, String weight, String pictureUrl, String description, String strTagArray, String instagramId) {
		Connection connection = null;
		Statement stmt = null;
		ResultSet result = null;

		boolean isConfirm = false;
		
		String sql = "";
		
		try {
			Class.forName(DBConstant.DRIVER_NAME);
			connection = DBUtil.getConnection();	
			stmt = connection.createStatement();

			if(userNo > 0) {
				String passwordClause = "";
				if(!StringUtil.isEmpty(password)) {
					passwordClause = "password='"+password+"', ";
				}
				
				sql = "UPDATE user_t SET "+passwordClause+"email='"+email+"', name='"+name+"', sex='"+sex+"', height='"+height+"', weight='"+weight+"', pictureUrl='"+pictureUrl+"', description='"+description+"', strTagArray='"+strTagArray+"', instagramId='"+instagramId+"' WHERE no="+userNo;
				int rowCount = stmt.executeUpdate(sql);
				if(rowCount > 0) {
					isConfirm = true;
				}
			}
		} catch(SQLException | ClassNotFoundException e) {
			PrintUtil.print("e) " + sql);
		} finally {
			if(result != null) {
				try {
					result.close();
				} catch (SQLException e) {
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
				}
			}
		}

		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("isConfirm", isConfirm ? "Y" : "N");
		} catch(JSONException e) {
		}

		return jsonObject;
	}
	
	@Override
	public JSONObject getMyInformation(int userNo) {
		Connection connection = null;
		Statement stmt = null;
		ResultSet result = null;

		boolean isConfirm = false;
		String email = "";
		String name = "";
		String sex = "";
		String height = "";
		String weight = "";
		String pictureUrl = "";
		String description = "";
		String instagramId = "";
		
		String sql = "";
		try {
			Class.forName(DBConstant.DRIVER_NAME);
			connection = DBUtil.getConnection();
			stmt = connection.createStatement();

			sql = "SELECT email, name, sex, height, weight, pictureUrl, description, instagramId FROM user_t WHERE no="+userNo+" LIMIT 1";
			result = stmt.executeQuery(sql);
			while(result.next()) {
				email = result.getString("email");
				name = result.getString("name");
				sex = result.getString("sex");
				height = result.getString("height");
				weight = result.getString("weight");
				pictureUrl = result.getString("pictureUrl");
				description = result.getString("description");
				instagramId = result.getString("instagramId");
				isConfirm = true;
				break;
			}
			result.close();
		} catch(SQLException | ClassNotFoundException e) {
			PrintUtil.print("e) " + sql + ", e: " + e.toString());
		} finally {
			if(result != null) {
				try {
					result.close();
				} catch (SQLException e) {
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
				}
			}
		}

		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("isConfirm", isConfirm ? "Y" : "N");
			jsonObject.put("email", StringUtil.replaceNullForDB(email));
			jsonObject.put("name", StringUtil.replaceNullForDB(name));
			jsonObject.put("sex", StringUtil.replaceNullForDB(sex));
			jsonObject.put("height", StringUtil.replaceNullForDB(height));
			jsonObject.put("weight", StringUtil.replaceNullForDB(weight));
			jsonObject.put("pictureUrl", StringUtil.replaceNullForDB(pictureUrl));
			jsonObject.put("description", StringUtil.replaceNullForDB(description));
			jsonObject.put("instagramId", StringUtil.replaceNullForDB(instagramId));
			
		} catch(JSONException e) {
		}
		return jsonObject;
	}
	
	@Override
	public JSONObject getMyStyleArray(int userNo) {
		Connection connection = null;
		Statement stmt = null;
		ResultSet result = null;

		String userName = "";
		String userPictureUrl = "";
		String userInstagramId = "";
		String userDescription = "";
		
		String strStyleNoArray = "";
		
		boolean isConfirm = false;
		ArrayList<StyleItem> styleList = new ArrayList<StyleItem>();
		int totalCount = 0;
		
		HashMap<Integer, Boolean> myLikeMap = new HashMap<Integer, Boolean>();
		HashMap<Integer, Integer> likeCountMap = new HashMap<Integer, Integer>();
		
		String sql = "";
		try {
			Class.forName(DBConstant.DRIVER_NAME);
			connection = DBUtil.getConnection();
			stmt = connection.createStatement();
			
			sql = "SELECT name, pictureUrl, instagramId, description FROM user_t WHERE no="+userNo+" LIMIT 1";
			result = stmt.executeQuery(sql);
			while(result.next()) {
				userName = result.getString("name");
				userPictureUrl = result.getString("pictureUrl");
				userInstagramId = result.getString("instagramId");
				userDescription = result.getString("description");
				break;
			}
			result.close();
			
			sql = "SELECT COUNT(no) AS totalCount FROM style_t WHERE userNo="+userNo+" LIMIT 1";
			result = stmt.executeQuery(sql);
			while(result.next()) {
				totalCount = result.getInt("totalCount");
				break;
			}
			result.close();
			
			sql = "SELECT no, content, pictureUrl, strPictureUrlArray, strProductArray, viewCount, insertTime FROM style_t WHERE userNo="+userNo;
			result = stmt.executeQuery(sql);
			while(result.next()) {
				StyleItem item = new StyleItem();
				int rowNo = result.getInt("no");
				item.no = rowNo;
				strStyleNoArray += (rowNo + ",");
				item.content = result.getString("content");
				item.pictureUrl = result.getString("pictureUrl");
				item.strPictureUrlArray = result.getString("strPictureUrlArray");
				item.strProductArray = result.getString("strProductArray");
				item.viewCount = result.getInt("viewCount");
				item.insertTime = result.getString("insertTime");
				styleList.add(item);
			}
			result.close();

			if(!StringUtil.isEmpty(strStyleNoArray) && strStyleNoArray.endsWith(",")) {
				String temp = strStyleNoArray.substring(0, strStyleNoArray.length() - 1);
				strStyleNoArray = temp;
			
				if(userNo > 0) {
					sql = "SELECT styleNo FROM like_t WHERE styleNo IN("+strStyleNoArray+") AND userNo="+userNo+" AND isLike='Y'";
					result = stmt.executeQuery(sql);
					while(result.next()) {
						int rowNo = result.getInt("styleNo");
						myLikeMap.put(rowNo, true);
					}
					result.close();
				}
				
				sql = "SELECT styleNo, COUNT(no) AS totalCount FROM like_t WHERE styleNo IN("+strStyleNoArray+") AND isLike='Y' GROUP BY styleNo";
				result = stmt.executeQuery(sql);
				while(result.next()) {
					int rowNo = result.getInt("styleNo");
					int rowTotalCount = result.getInt("totalCount");
					likeCountMap.put(rowNo, rowTotalCount);
				}
				result.close();
			}
			
			isConfirm = true;
		} catch(SQLException | ClassNotFoundException e) {
			PrintUtil.print("e) " + sql + ", e: " + e.toString());
		} finally {
			if(result != null) {
				try {
					result.close();
				} catch (SQLException e) {
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
				}
			}
		}

		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("isConfirm", isConfirm ? "Y" : "N");

			JSONArray styleArray = new JSONArray();
			int styleSize = styleList.size();
			for(int i=0; i<styleSize; i++) {
				StyleItem item = styleList.get(i);
				int rowNo = item.no;
				if(myLikeMap.containsKey(rowNo)) {
					item.isLike = "Y";
				}
				if(likeCountMap.containsKey(rowNo)) {
					item.likeCount = likeCountMap.get(rowNo);
				}
				
				item.userName = userName;
				item.userPictureUrl = userPictureUrl;
				item.userInstagramId = userInstagramId;
				item.userDescription = userDescription;
				styleArray.put(item.getJSONObject());
			}
			jsonObject.put("styleArray", styleArray);
			
			jsonObject.put("totalCount", totalCount);
		} catch(JSONException e) {
		}
		return jsonObject;
	}
	
	@Override
	public JSONObject getStyleArray(int userNo, String categoryName, String keyword, int page, int viewCount, String orderColumn, String orderDirection) {
		Connection connection = null;
		Statement stmt = null;
		ResultSet result = null;

		boolean isConfirm = false;
		int totalCount = 0;
		
		HashMap<Integer, Boolean> myLikeMap = new HashMap<Integer, Boolean>();
		HashMap<Integer, Integer> likeCountMap = new HashMap<Integer, Integer>();
		HashMap<Integer, UserItem> userMap = new HashMap<Integer, UserItem>();
		ArrayList<StyleItem> styleList = new ArrayList<StyleItem>();
		String strUserNoArray = "";
		String strStyleNoArray = "";
		
		String sql = "";
		try {
			Class.forName(DBConstant.DRIVER_NAME);
			connection = DBUtil.getConnection();
			stmt = connection.createStatement();
			
			
			String whereClause = "";
			sql = "SELECT no, content, pictureUrl, strPictureUrlArray, strProductArray, viewCount, insertTime, userNo FROM style_t";
			String countSql = "SELECT count(no) AS totalCount FROM style_t";

			boolean isExistWhere = false;
			whereClause = "";

			if(!StringUtil.isEmpty(categoryName)) {
				if("top".equals(categoryName)) {
					whereClause += " AND strTop LIKE '%\"isUse\":\"Y\"%'";
					isExistWhere = true;
				} else if("bottom".equals(categoryName)) {
					whereClause += " AND strBottom LIKE '%\"isUse\":\"Y\"%'";
					isExistWhere = true;
				} else if("acc".equals(categoryName)) {
					whereClause += " AND strAcc LIKE '%\"isUse\":\"Y\"%'";
					isExistWhere = true;
				} else if("hat".equals(categoryName)) {
					whereClause += " AND strHat LIKE '%\"isUse\":\"Y\"%'";
					isExistWhere = true;
				} else if("shoes".equals(categoryName)) {
					whereClause += " AND strShoes LIKE '%\"isUse\":\"Y\"%'";
					isExistWhere = true;
				} else if("bag".equals(categoryName)) {
					whereClause += " AND strBag LIKE '%\"isUse\":\"Y\"%'";
					isExistWhere = true;
				}
			}
			if(!StringUtil.isEmpty(keyword)) {
				whereClause += " AND strProductArray LIKE '%"+keyword+"%'";
				isExistWhere = true;
			}

			if(isExistWhere) {
				if(whereClause.startsWith(" AND")) {
					String temp = whereClause.substring(4, whereClause.length());
					whereClause = temp;
				}

				String temp = " WHERE" + whereClause;
				whereClause = temp;
			}

			String limitClause = " LIMIT " + (page * viewCount) + ", " + viewCount;

			countSql += (whereClause + " LIMIT 1");
			result = stmt.executeQuery(countSql);
			while(result.next()) {
				totalCount = result.getInt("totalCount");
				break;
			}
			result.close();

			sql += (whereClause + " ORDER BY "+orderColumn+" "+orderDirection + limitClause);
			result = stmt.executeQuery(sql);
			while(result.next()) {
				StyleItem item = new StyleItem();
				int rowNo = result.getInt("no");
				item.no = rowNo;
				strStyleNoArray += (rowNo + ",");
				item.content = result.getString("content");
				item.pictureUrl = result.getString("pictureUrl");
				item.strPictureUrlArray = result.getString("strPictureUrlArray");
				item.strProductArray = result.getString("strProductArray");
				item.viewCount = result.getInt("viewCount");
				item.insertTime = result.getString("insertTime");
				int rowUserNo = result.getInt("userNo");
				if(!userMap.containsKey(rowUserNo)) {
					strUserNoArray += (rowUserNo + ",");
					userMap.put(rowUserNo, null);
				}
				item.userNo = rowUserNo;
				styleList.add(item);
			}
			result.close();

			if(!StringUtil.isEmpty(strUserNoArray) && strUserNoArray.endsWith(",")) {
				String temp = strUserNoArray.substring(0, strUserNoArray.length() - 1);
				strUserNoArray = temp;

				sql = "SELECT no, name, pictureUrl, instagramId, description FROM user_t WHERE no IN("+strUserNoArray+")";
				result = stmt.executeQuery(sql);
				while(result.next()) {
					UserItem rowUserItem = new UserItem();
					int rowNo = result.getInt("no");
					rowUserItem.no = rowNo;
					rowUserItem.name = result.getString("name");
					rowUserItem.pictureUrl = result.getString("pictureUrl");
					rowUserItem.instagramId = result.getString("instagramId");
					rowUserItem.description = result.getString("description");
					userMap.put(rowNo, rowUserItem);
				}
				result.close();
			}
			
			if(!StringUtil.isEmpty(strStyleNoArray) && strStyleNoArray.endsWith(",")) {
				String temp = strStyleNoArray.substring(0, strStyleNoArray.length() - 1);
				strStyleNoArray = temp;
			
				if(userNo > 0) {
					sql = "SELECT styleNo FROM like_t WHERE styleNo IN("+strStyleNoArray+") AND userNo="+userNo+" AND isLike='Y'";
					result = stmt.executeQuery(sql);
					while(result.next()) {
						int rowNo = result.getInt("styleNo");
						myLikeMap.put(rowNo, true);
					}
					result.close();
				}
				
				sql = "SELECT styleNo, COUNT(no) AS totalCount FROM like_t WHERE styleNo IN("+strStyleNoArray+") AND isLike='Y' GROUP BY styleNo";
				result = stmt.executeQuery(sql);
				while(result.next()) {
					int rowNo = result.getInt("styleNo");
					int rowTotalCount = result.getInt("totalCount");
					likeCountMap.put(rowNo, rowTotalCount);
				}
				result.close();
			}

			isConfirm = true;
		} catch(SQLException | ClassNotFoundException e) {
			PrintUtil.print("e) " + sql + ", e: " + e.toString());
		} finally {
			if(result != null) {
				try {
					result.close();
				} catch (SQLException e) {
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
				}
			}
		}

		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("isConfirm", isConfirm ? "Y" : "N");
			jsonObject.put("totalCount", totalCount);

			JSONArray styleArray = new JSONArray();
			int styleSize = styleList.size();
			for(int i=0; i<styleSize; i++) {
				StyleItem item = styleList.get(i);
				int rowNo = item.no;
				if(myLikeMap.containsKey(rowNo)) {
					item.isLike = "Y";
				}
				if(likeCountMap.containsKey(rowNo)) {
					item.likeCount = likeCountMap.get(rowNo);
				}
				
				int rowUserNo = item.userNo;
				if(userMap.containsKey(rowUserNo)) {
					UserItem rowUserItem = userMap.get(rowUserNo);
					if(rowUserItem != null) {
						item.userName = rowUserItem.name;
						item.userPictureUrl = rowUserItem.pictureUrl;
						item.userInstagramId = rowUserItem.instagramId;
						item.userDescription = rowUserItem.description;
					}
				}
				styleArray.put(item.getJSONObject());
			}
			jsonObject.put("styleArray", styleArray);
		} catch(JSONException e) {
		}
		return jsonObject;
	}
	
	@Override
	public JSONObject writeStyle(int userNo, String content, String pictureUrl, String strPictureUrlArray, String strProductArray) {
		String strTop = "";
		String strBottom = "";
		String strAcc = "";
		String strHat = "";
		String strShoes = "";
		String strBag = "";
		if(!StringUtil.isEmpty(strProductArray) && strProductArray.startsWith("[")) {
			try {
				JSONArray jsonArray = new JSONArray(strProductArray);
				int length = jsonArray.length();
				for(int i=0; i<length; i++) {
					JSONObject rowObject = jsonArray.getJSONObject(i);
					if(!rowObject.isNull("type")) {
						String rowType = rowObject.getString("type");
						if("top".equals(rowType)) {
							strTop = rowObject.toString();
						} else if("bottom".equals(rowType)) {
							strBottom = rowObject.toString();
						} else if("acc".equals(rowType)) {
							strAcc = rowObject.toString();
						} else if("hat".equals(rowType)) {
							strHat = rowObject.toString();
						} else if("shoes".equals(rowType)) {
							strShoes = rowObject.toString();
						} else if("bag".equals(rowType)) {
							strBag = rowObject.toString();
						}
					}
				}
			} catch(JSONException e) {
			}
		}
		
		
		Connection connection = null;
		Statement stmt = null;
		ResultSet result = null;

		boolean isConfirm = false;

		String sql = "";
		try {
			Class.forName(DBConstant.DRIVER_NAME);
			connection = DBUtil.getConnection();	
			stmt = connection.createStatement();

			if(userNo > 0) {
				String insertTime = DateUtil.getTodayDate("yyyy-MM-dd HH:mm:ss");
				
				String strKeyArray = "userNo, content, pictureUrl, strPictureUrlArray, strProductArray, insertTime, strTop, strBottom, strAcc, strHat, strShoes, strBag";
				String strValueArray = userNo+",'"+content+"','"+pictureUrl+"','"+strPictureUrlArray+"','"+strProductArray+"','"+insertTime+"','"+strTop+"','"+strBottom+"','"+strAcc+"','"+strHat+"','"+strShoes+"','"+strBag+"'";

				sql = "INSERT INTO style_t("+strKeyArray+") VALUES("+strValueArray+")";
				int rowCount = stmt.executeUpdate(sql);
				if(rowCount > 0) {
					isConfirm = true;
				}
			}
		} catch(SQLException | ClassNotFoundException e) {
			PrintUtil.print("e) " + sql + ", e: " + e.toString());
		} finally {
			if(result != null) {
				try {
					result.close();
				} catch (SQLException e) {
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
				}
			}
		}

		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("isConfirm", isConfirm ? "Y" : "N");
		} catch(JSONException e) {
		}
		return jsonObject;
	}
	
	@Override
	public JSONObject updateStyle(int userNo, int styleNo, String content, String pictureUrl, String strPictureUrlArray, String strProductArray) {
		String strTop = "";
		String strBottom = "";
		String strAcc = "";
		String strHat = "";
		String strShoes = "";
		String strBag = "";
		if(!StringUtil.isEmpty(strProductArray) && strProductArray.startsWith("[")) {
			try {
				JSONArray jsonArray = new JSONArray(strProductArray);
				int length = jsonArray.length();
				for(int i=0; i<length; i++) {
					JSONObject rowObject = jsonArray.getJSONObject(i);
					if(!rowObject.isNull("type")) {
						String rowType = rowObject.getString("type");
						if("top".equals(rowType)) {
							strTop = rowObject.toString();
						} else if("bottom".equals(rowType)) {
							strBottom = rowObject.toString();
						} else if("acc".equals(rowType)) {
							strAcc = rowObject.toString();
						} else if("hat".equals(rowType)) {
							strHat = rowObject.toString();
						} else if("shoes".equals(rowType)) {
							strShoes = rowObject.toString();
						} else if("bag".equals(rowType)) {
							strBag = rowObject.toString();
						}
					}
				}
			} catch(JSONException e) {
			}
		}
		
		Connection connection = null;
		Statement stmt = null;
		ResultSet result = null;

		boolean isConfirm = false;

		String sql = "";
		try {
			Class.forName(DBConstant.DRIVER_NAME);
			connection = DBUtil.getConnection();	
			stmt = connection.createStatement();
			if(userNo > 0) {
				sql = "UPDATE style_t SET content='"+content+"', pictureUrl='"+pictureUrl+"', strPictureUrlArray='"+strPictureUrlArray+"', strProductArray='"+strProductArray+"', strTop='"+strTop+"', strBottom='"+strBottom+"', strAcc='"+strAcc+"', strHat='"+strHat+"', strShoes='"+strShoes+"', strBag='"+strBag+"' WHERE userNo="+userNo+" AND styleNo="+styleNo;
				int rowCount = stmt.executeUpdate(sql);
				if(rowCount > 0) {
					isConfirm = true;
				}
			}
		} catch(SQLException | ClassNotFoundException e) {
			PrintUtil.print("e) " + sql + ", e: " + e.toString());
		} finally {
			if(result != null) {
				try {
					result.close();
				} catch (SQLException e) {
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
				}
			}
		}

		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("isConfirm", isConfirm ? "Y" : "N");
		} catch(JSONException e) {
		}
		return jsonObject;
	}
	
	
	@Override
	public JSONObject deleteStyle(int userNo, int styleNo) {
		Connection connection = null;
		Statement stmt = null;
		ResultSet result = null;

		boolean isConfirm = false;

		String sql = "";
		try {
			Class.forName(DBConstant.DRIVER_NAME);
			connection = DBUtil.getConnection();	
			stmt = connection.createStatement();

			sql = "DELETE FROM style_t WHERE userNo="+userNo+" AND no="+styleNo;
			int rowCount = stmt.executeUpdate(sql);
			if(rowCount > 0) {
				isConfirm = true;
			}
		} catch(SQLException | ClassNotFoundException e) {
			PrintUtil.print("e) " + sql + ", e: " + e.toString());
		} finally {
			if(result != null) {
				try {
					result.close();
				} catch (SQLException e) {
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
				}
			}
		}

		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("isConfirm", isConfirm ? "Y" : "N");
		} catch(JSONException e) {
		}
		return jsonObject;
	}
	
	@Override
	public JSONObject updateStyleLike(int userNo, int styleNo, String isLike) {
		Connection connection = null;
		Statement stmt = null;
		ResultSet result = null;

		boolean isExist = false;
		int likeCount = 0;
		boolean isConfirm = false;

		String sql = "";
		try {
			Class.forName(DBConstant.DRIVER_NAME);
			connection = DBUtil.getConnection();	
			stmt = connection.createStatement();

			sql = "SELECT no FROM like_t WHERE userNo="+userNo+" AND styleNo="+styleNo+" LIMIT 1";
			result = stmt.executeQuery(sql);
			while(result.next()) {
				int tempNo = result.getInt("no");
				if(tempNo > 0) {
					isExist = true;
				}
				break;
			}
			result.close();
			
			if(isExist) {
				sql = "UPDATE like_t SET isLike='"+isLike+"' WHERE userNo="+userNo+" AND styleNo="+styleNo;
			} else {
				String insertTime = DateUtil.getTodayDate("yyyy-MM-dd HH:mm:ss");
				sql = "INSERT INTO like_t(styleNo, userNo, isLike, insertTime) VALUES("+styleNo+","+userNo+",'"+isLike+"','"+insertTime+"')";
			}
			int rowCount = stmt.executeUpdate(sql);
			if(rowCount > 0) {
				isConfirm = true;
			}
			
			sql = "SELECT COUNT(no) AS likeCount FROM like_t WHERE styleNo="+styleNo+" AND isLike='Y' LIMIT 1";
			result = stmt.executeQuery(sql);
			while(result.next()) {
				likeCount = result.getInt("likeCount");
				break;
			}
			result.close();
		} catch(SQLException | ClassNotFoundException e) {
			PrintUtil.print("e) " + sql + ", e: " + e.toString());
		} finally {
			if(result != null) {
				try {
					result.close();
				} catch (SQLException e) {
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
				}
			}
		}

		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("isConfirm", isConfirm ? "Y" : "N");
			jsonObject.put("isLike", isLike);
			jsonObject.put("likeCount", likeCount);
		} catch(JSONException e) {
		}
		return jsonObject;
	}
	
	@Override
	public JSONObject getReplyArray(int userNo, int styleNo) {
		Connection connection = null;
		Statement stmt = null;
		ResultSet result = null;

		boolean isConfirm = false;
		ArrayList<ReplyItem> replyList = new ArrayList<ReplyItem>();
		int totalCount = 0;
		
		HashMap<Integer, UserItem> userMap = new HashMap<Integer, UserItem>();
		String strUserNoArray = "";

		String sql = "";
		try {
			Class.forName(DBConstant.DRIVER_NAME);
			connection = DBUtil.getConnection();
			stmt = connection.createStatement();
			
			sql = "SELECT COUNT(no) AS totalCount FROM reply_t WHERE styleNo="+styleNo+" LIMIT 1";
			result = stmt.executeQuery(sql);
			while(result.next()) {
				totalCount = result.getInt("totalCount");
				break;
			}
			result.close();
			
			sql = "SELECT no, userNo, content, insertTime FROM reply_t WHERE styleNo="+styleNo;
			result = stmt.executeQuery(sql);
			while(result.next()) {
				ReplyItem item = new ReplyItem();
				item.no = result.getInt("no");
				item.content = result.getString("content");
				item.insertTime = result.getString("insertTime");
				int rowUserNo = result.getInt("userNo");
				item.userNo = rowUserNo;
				if(rowUserNo == userNo) {
					item.isMe = "Y";
				}
				if(!userMap.containsKey(rowUserNo)) {
					strUserNoArray += (rowUserNo + ",");
					userMap.put(rowUserNo, null);
				}
				replyList.add(item);
			}
			result.close();

			if(!StringUtil.isEmpty(strUserNoArray) && strUserNoArray.endsWith(",")) {
				String temp = strUserNoArray.substring(0, strUserNoArray.length() - 1);
				strUserNoArray = temp;

				sql = "SELECT no, name, pictureUrl FROM user_t WHERE no IN("+strUserNoArray+")";
				result = stmt.executeQuery(sql);
				while(result.next()) {
					UserItem rowUserItem = new UserItem();
					int rowNo = result.getInt("no");
					rowUserItem.no = rowNo;
					rowUserItem.name = result.getString("name");
					rowUserItem.pictureUrl = result.getString("pictureUrl");
					userMap.put(rowNo, rowUserItem);
				}
				result.close();
			}
			
			isConfirm = true;
		} catch(SQLException | ClassNotFoundException e) {
			PrintUtil.print("e) " + sql + ", e: " + e.toString());
		} finally {
			if(result != null) {
				try {
					result.close();
				} catch (SQLException e) {
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
				}
			}
		}

		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("isConfirm", isConfirm ? "Y" : "N");

			JSONArray replyArray = new JSONArray();
			int replySize = replyList.size();
			for(int i=0; i<replySize; i++) {
				ReplyItem item = replyList.get(i);
				int rowUserNo = item.userNo;
				if(userMap.containsKey(rowUserNo)) {
					UserItem rowUserItem = userMap.get(rowUserNo);
					if(rowUserItem != null) {
						item.userName = rowUserItem.name;
						item.userPictureUrl = rowUserItem.pictureUrl;
					}
				}
				replyArray.put(item.getJSONObject());
			}
			jsonObject.put("replyArray", replyArray);
			jsonObject.put("totalCount", totalCount);
		} catch(JSONException e) {
		}
		return jsonObject;
	}
	
	@Override
	public JSONObject writeReply(int userNo, int styleNo, String content) {
		Connection connection = null;
		Statement stmt = null;
		ResultSet result = null;

		boolean isConfirm = false;

		String sql = "";
		try {
			Class.forName(DBConstant.DRIVER_NAME);
			connection = DBUtil.getConnection();	
			stmt = connection.createStatement();

			if(userNo > 0) {
				String insertTime = DateUtil.getTodayDate("yyyy-MM-dd HH:mm:ss");
				
				String strKeyArray = "userNo, styleNo, content, insertTime";
				String strValueArray = userNo+","+styleNo+",'"+content+"','"+insertTime+"'";

				sql = "INSERT INTO reply_t("+strKeyArray+") VALUES("+strValueArray+")";
				int rowCount = stmt.executeUpdate(sql);
				if(rowCount > 0) {
					isConfirm = true;
				}
			}
		} catch(SQLException | ClassNotFoundException e) {
			PrintUtil.print("e) " + sql + ", e: " + e.toString());
		} finally {
			if(result != null) {
				try {
					result.close();
				} catch (SQLException e) {
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
				}
			}
		}

		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("isConfirm", isConfirm ? "Y" : "N");
		} catch(JSONException e) {
		}
		return jsonObject;
	}
	
	@Override
	public JSONObject deleteReply(int userNo, int replyNo) {
		Connection connection = null;
		Statement stmt = null;
		ResultSet result = null;

		boolean isConfirm = false;

		String sql = "";
		try {
			Class.forName(DBConstant.DRIVER_NAME);
			connection = DBUtil.getConnection();	
			stmt = connection.createStatement();

			sql = "DELETE FROM reply_t WHERE userNo="+userNo+" AND no="+replyNo;
			int rowCount = stmt.executeUpdate(sql);
			if(rowCount > 0) {
				isConfirm = true;
			}
		} catch(SQLException | ClassNotFoundException e) {
			PrintUtil.print("e) " + sql + ", e: " + e.toString());
		} finally {
			if(result != null) {
				try {
					result.close();
				} catch (SQLException e) {
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
				}
			}
		}

		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("isConfirm", isConfirm ? "Y" : "N");
		} catch(JSONException e) {
		}
		return jsonObject;
	}
}
