<%@page contentType="text/html; charset=utf-8" language="java"
import="com.team.hash.hashproject.util.StringUtil"
import="com.team.hash.hashproject.util.DateUtil"
import="com.team.hash.hashproject.api.AppInformationManager"
import="org.json.JSONObject"
import="org.json.JSONArray"
%>

<%
String _categoryName = StringUtil.replaceNullForServlet(request.getParameter("categoryName"));
String _keyword = StringUtil.replaceNullForServlet(request.getParameter("keyword"));
if(StringUtil.isEmpty(_categoryName)) {
	_categoryName = "top";
}

int _no = StringUtil.getStringNumber((String)session.getAttribute("no"));
String _nowDate = DateUtil.getTodayDate("yyyyMMdd_HHmmss");
JSONObject _jsonObject = AppInformationManager.getInstance().getStyleArray(_no, _categoryName, _keyword, 0, 100, "no", "DESC");
JSONArray _jsonArray = _jsonObject.getJSONArray("styleArray");

JSONObject _recommandObject = AppInformationManager.getInstance().getStyleArray(_no, _categoryName, _keyword, 0, 100, "no", "ASC");
JSONArray _recommandObjectArray = _recommandObject.getJSONArray("styleArray");

int _jsonArraySize = 0;
if(_jsonArray != null) {
	_jsonArraySize = _jsonArray.length();
}
int _totalCount = _jsonObject.getInt("totalCount");
%>

<script>
var totalCount = <%= _totalCount%>;
var array = <%= _jsonArray %>;
var recommandArray = <%= _recommandObjectArray %>;
var arraySize = <%= _jsonArraySize %>;
var nowViewCount = 100;
</script>

<link rel="stylesheet" type="text/css" media="all" href="/css/home.css?v=<%= _nowDate%>">
<div class="homeArea">
	<div class="centerArea">
		<% for(int i=0; i<_jsonArraySize; i++) {
			JSONObject _rowObject = _jsonArray.getJSONObject(i);
			JSONObject _recommandRowObject = _recommandObjectArray.getJSONObject(i);
			String _displayPictureUrl = "";
			String _rowPictureUrl = _rowObject.getString("pictureUrl");
			if(!StringUtil.isEmpty(_rowPictureUrl)) {
				_displayPictureUrl = _rowPictureUrl;
			}
			
			String _displayUserPictureUrl = "";
			String _rowUserPictureUrl = _rowObject.getString("userPictureUrl");
			if(!StringUtil.isEmpty(_rowUserPictureUrl)) {
				System.out.println("_displayUserPictureUrl : " + _displayPictureUrl);
				_displayUserPictureUrl = _rowUserPictureUrl;
			} else {
				_displayUserPictureUrl = "images/ico_person.png";
			}
			String _userName = _rowObject.getString("userName");
			
			String _insertTime = _rowObject.getString("insertTime").substring(0, 10);
			
			String _isLike = _rowObject.getString("isLike");
			String _displayLikeImage = "images/ico_heart_off.png";
			if("Y".equals(_isLike)) {
				_displayLikeImage = "images/ico_heart_on.png";
			}
			int _likeCount = _rowObject.getInt("likeCount");
			
			String _strProductArray = _rowObject.getString("strProductArray");
			JSONArray _productArray = new JSONArray(_strProductArray);

			String _strRecommandProductArray2 = _recommandRowObject.getString("strProductArray");
			int _productSize = _productArray.length();
		%>
			<div class="homeItem">
				<img class="picture homePicture homePicture_<%= i %>" src="<%= _displayPictureUrl %>" />
				<div class="userRow">
					<div class="userPicture<%= StringUtil.isEmpty(_rowUserPictureUrl) ? " userPictureEmpty" : "" %>">
						<img src="<%= _displayUserPictureUrl%>"/>
					</div>

					<p class="userName"><%= _userName %></p>
				</div>
				<div class="contentRow">
					<% for(int j=0; j<_productSize; j++) {
						JSONObject _productObject = _productArray.getJSONObject(j);
						String _rowTitle = _productObject.getString("title");
						String _displayProductPictureUrl = "";
						String _productPictureUrl = _productObject.getString("pictureUrl");
						if(!StringUtil.isEmpty(_productPictureUrl)) {
							_displayProductPictureUrl = _productPictureUrl;
						}

						String _isUse = _productObject.getString("isUse");
						if("Y".equals(_isUse)) { %>
							<div class="contentMiniRow">
								<img src="<%= _displayProductPictureUrl %>" />
								<p><%= _rowTitle %></p>
							</div>
						<%}
					}%>
				</div>
				<p class="writeTime"><%= _insertTime %></p>
				<p class="heartCount homeHeartCount homeHeartCount_<%= i%>"><%= StringUtil.getPriceFormat(_likeCount) %></p>
				<img class="heart homeHeart homeHeart_<%= i %>" src="<%= _displayLikeImage %>" />
			</div>
		<% } %>
	</div>
</div>

<script type="text/javascript" src="/js/home.js?v=<%= _nowDate%>"></script>