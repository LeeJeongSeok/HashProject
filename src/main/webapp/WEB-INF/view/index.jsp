<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"
import="com.team.hash.hashproject.util.StringUtil"
import="com.team.hash.hashproject.util.DateUtil"
%>


<%
String _categoryName = StringUtil.replaceNullForServlet(request.getParameter("categoryName"));
String _displayNavigationLineName = "";
if(!StringUtil.isEmpty(_categoryName)) {
	_displayNavigationLineName = " navigationLine_"+_categoryName;
	System.out.println(_displayNavigationLineName);
}

int _no = StringUtil.getStringNumber((String)session.getAttribute("no"));
String _userName = StringUtil.replaceNullForServlet((String)session.getAttribute("name"));
String _page = StringUtil.replaceNull(request.getParameter("page"));
String _pageUrl = "home.jsp";
boolean _isDetail = false;
if("detail".equals(_page)) {
	_pageUrl = "detail.jsp";
	_isDetail = true;
}

String _nowDate = DateUtil.getTodayDate("yyyyMMdd_HHmmss");
%>

<!DOCTYPE HTML>
<html lang="ko">
<head>

<title>#</title>

<meta charset="utf-8" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
<link rel="stylesheet" type="text/css" media="all" href="/css/jquery.ui.min.css" />
<link rel="stylesheet" type="text/css" href="/css/font.css?v=6" />
<link rel="stylesheet" type="text/css" href="/css/reset.css?v=6" />
<link rel="stylesheet" type="text/css" media="all" href="/css/module.css?v=<%= _nowDate%>" />
<link rel="stylesheet" type="text/css" media="all" href="/css/popup.css?v=<%= _nowDate%>" />
<link rel="stylesheet" type="text/css" media="all" href="/css/index.css?v=<%= _nowDate%>" />
<link rel="shortcut icon" href="/images/favicon.ico">

<script type="text/javascript" src="/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="/js/module.js?v=<%= _nowDate%>"></script>

<link rel="stylesheet" type="text/css" media="all" href="/css/fotorama.css">
<script type="text/javascript" src="/js/fotorama.js"></script>

<style>
.ui-datepicker{font-size:12px;width:180px}
.ui-datepicker select{-moz-appearance:none;-webkit-appearance:none;appearance:none;text-align-last:center}
.ui-datepicker select.ui-datepicker-year{width:40px;font-size:10px}
.ui-datepicker select.ui-datepicker-month{width:30px;font-size:10px;margin-left:10px}
</style>

<script>
var isLogin = "<%= _no > 0 ? "Y":""%>";
var styleAddPictureArray = new Array();
var nowDetailItem = null;
var replyArray = new Array();
</script>
</head>

<body>
<div class="entireArea">
	<jsp:include page="<%=_pageUrl %>">
		<jsp:param name="nowDate" value="<%= _nowDate %>"></jsp:param>
   	</jsp:include>
   	
   	<img class="addStyleButton" src="/images/btn_add_style.png" />
   	<div class="navigationArea<%= _isDetail ? " navigationForDetail":""%>">
		<div class="navigationCenterArea">
			<div class="navigationSearchArea">
				<p class="navigationTopLogo">#</p>
				<% if(_no > 0) { %>
					<div class="myProfileButton">
						<img src="/images/ico_person.png" />
					</div>
				<% } else { %>
					<button class="blackButton loginButton" onclick="showLoginPopup()">????????? / ??????</button>
				<% } %>
				
				<% if(!_isDetail) { %>
					<div class="topSearchInputArea">
						<input id="topSearchInput" placeholder="??????" maxlength="10" onkeydown="if(event.keyCode==13) {doSearch(); return false;}"/>
						<img class="topSearchButton" src="/images/btn_top_search.png" />
					</div>
				<%} else {%>
					<p class="detailTitle"><%= _userName %> ?????? ??????</p>
				<%} %>
			</div>
			<div class="navigationItemArea">
				<a class="navigationItem navigationItemOn" href="index?categoryName=top">TOP</a>
				<a class="navigationItem" href="index?categoryName=bottom">BOTTOM</a>
				<a class="navigationItem" href="index?categoryName=acc">ACC</a>
				<a class="navigationItem" href="index?categoryName=hat">HAT</a>
				<a class="navigationItem" href="index?categoryName=shoes">SHOES</a>
				<a class="navigationItem" href="index?categoryName=bag">BAG</a>
				<div class="navigationLine<%= _displayNavigationLineName %>"></div>
			</div>
		</div>
	</div>
	<div class="emptyTouchArea">
		<div id="homeProfileArea" class="profileArea">
			<p id="myInformationButton">??? ?????? ??????</p>
			<p id="myStyleDetailRoomButton">??? ??????</p>
			<p id="logoutButton">????????????</p>
		</div>
	</div>
</div>
<%@include file="popup.jsp" %>
<%@include file="login.jsp" %>
<div id="addStylePopup" class="popupArea addStylePopupArea">
	<div class="styleMainArea">
		<h1>* ????????? (3~5???)</h1>
		<div class="styleMainPictureArea">
		</div>
		<textarea id="addStyleMainDescriptionInput" placeholder="??????, ????????????"></textarea>
	</div>
	<div class="styleSubArea">
		<div class="styleSubItem">
			<h1>TOP</h1>
			<div class="circleCheck styleSubCircleCheck styleSubCircleCheckTop"></div>
			<div class="pictureSubItem">
				<p class="pictureSubAddItem pictureSubAddItemTop">+</p>
				<img class="styleSubPicture styleSubPictureTop" src="" />
			</div>
			<div class="subContentArea">
				<div class="commonSelectArea">
					<img src="/images/ico_select_arrow.png" />
					<select class="commonSelect styleSubSelect styleSubSelectTop">
						<option value="">-</option>
						<option value="?????????">?????????</option>
						<option value="?????? ???/??????">?????? ???/??????</option>
						<option value="?????????">?????????</option>
						<option value="?????????">?????????</option>
						<option value="??????/??????/?????????">??????/??????/?????????</option>
						<option value="?????????">?????????</option>
						<option value="??????/??????/????????????">??????/??????/????????????</option>
					</select>
				</div>
				<input class="styleSubTitle styleSubTitleTop" placeholder="?????????" />
				<textarea class="styleSubContent styleSubContentTop" placeholder="??????, ????????????"></textarea>
			</div>
		</div>
		<div class="styleSubItem">
			<h1>BOTTOM</h1>
			<div class="circleCheck styleSubCircleCheck styleSubCircleCheckBottom"></div>
			<div class="pictureSubItem">
				<p class="pictureSubAddItem pictureSubAddItemBottom">+</p>
				<img class="styleSubPicture styleSubPictureBottom" src="" />
			</div>
			<div class="subContentArea">
				<div class="commonSelectArea">
					<img src="/images/ico_select_arrow.png" />
					<select class="commonSelect styleSubSelect styleSubSelectBottom">
						<option value="">-</option>
						<option value="?????????">?????????</option>
						<option value="?????????">?????????</option>
						<option value="skirt">skirt</option>
					</select>
				</div>
				<input class="styleSubTitle styleSubTitleBottom" placeholder="?????????" />
				<textarea class="styleSubContent styleSubContentBottom" placeholder="??????, ????????????"></textarea>
			</div>
		</div>
		<div class="styleSubItem">
			<h1>ACC</h1>
			<div class="circleCheck styleSubCircleCheck styleSubCircleCheckAcc"></div>
			<div class="pictureSubItem">
				<p class="pictureSubAddItem pictureSubAddItemAcc">+</p>
				<img class="styleSubPicture styleSubPictureAcc" src="" />
			</div>
			<div class="subContentArea">
				<div class="commonSelectArea">
					<img src="/images/ico_select_arrow.png" />
					<select class="commonSelect styleSubSelect styleSubSelectAcc">
						<option value="">-</option>
					</select>
				</div>
				<input class="styleSubTitle styleSubTitleAcc" placeholder="?????????" />
				<textarea class="styleSubContent styleSubContentAcc" placeholder="??????, ????????????"></textarea>
			</div>
		</div>
		<div class="styleSubItem">
			<h1>HAT</h1>
			<div class="circleCheck styleSubCircleCheck styleSubCircleCheckHat"></div>
			<div class="pictureSubItem">
				<p class="pictureSubAddItem pictureSubAddItemHat">+</p>
				<img class="styleSubPicture styleSubPictureHat" src="" />
			</div>
			<div class="subContentArea">
				<div class="commonSelectArea">
					<img src="/images/ico_select_arrow.png" />
					<select class="commonSelect styleSubSelect styleSubSelectHat">
						<option value="">-</option>
					</select>
				</div>
				<input class="styleSubTitle styleSubTitleHat" placeholder="?????????" />
				<textarea class="styleSubContent styleSubContentHat" placeholder="??????, ????????????"></textarea>
			</div>
		</div>
		<div class="styleSubItem">
			<h1>SHOES</h1>
			<div class="circleCheck styleSubCircleCheck styleSubCircleCheckShoes"></div>
			<div class="pictureSubItem">
				<p class="pictureSubAddItem pictureSubAddItemShoes">+</p>
				<img class="styleSubPicture styleSubPictureShoes" src="" />
			</div>
			<div class="subContentArea">
				<div class="commonSelectArea">
					<img src="/images/ico_select_arrow.png" />
					<select class="commonSelect styleSubSelect styleSubSelectShoes">
						<option value="">-</option>
						<option value="?????????">?????????</option>
						<option value="????????????">????????????</option>
						<option value="??????">??????</option>
					</select>
				</div>
				<input class="styleSubTitle styleSubTitleShoes" placeholder="?????????" />
				<textarea class="styleSubContent styleSubContentShoes" placeholder="??????, ????????????"></textarea>
			</div>
		</div>
		<div class="styleSubItem">
			<h1>BAG</h1>
			<div class="circleCheck styleSubCircleCheck styleSubCircleCheckBag"></div>
			<div class="pictureSubItem">
				<p class="pictureSubAddItem pictureSubAddItemBag">+</p>
				<img class="styleSubPicture styleSubPictureBag" src="" />
			</div>
			<div class="subContentArea">
				<div class="commonSelectArea">
					<img src="/images/ico_select_arrow.png" />
					<select class="commonSelect styleSubSelect styleSubSelectBag">
						<option value="">-</option>
					</select>
				</div>
				<input class="styleSubTitle styleSubTitleBag" placeholder="?????????" />
				<textarea class="styleSubContent styleSubContentBag" placeholder="??????, ????????????"></textarea>
			</div>
		</div>
	</div>

	<button class="grayButton addStylePopupButton addStylePopupCancelButton" onclick="closePopup()">??????</button>
	<button class="blackButton addStylePopupButton addStylePopupConfirmButton" onclick="checkSaveAddStyle()">??????</button>
	<img class="popupCloseButton" src="/images/btn_close.png" />
</div>

<div id="detailPopup" class="popupArea detailPopupArea">
	<div class="fotoramaBackground">
		<div id="fotorama" class="fotorama">
		</div>
	</div>
	<div class="heartArea">
		<img id="detailHeart" src="/images/ico_heart_off.png" />
		<p id="detailHeartCount">0</p>
	</div>
	<div class="rightArea">
		<div class="contentRow detailContentRow"></div>
		<%-- ?????? ???????????? ??????	??????--%>
		<div class="userArea">
			<div class="userRow">
<%--				<div class="userPicture">--%>
<%--					<img id="detailUserPicture" src=""/>--%>
<%--				</div>--%>

<%--				<p id="detailUserName" class="userName"></p>--%>
<%--				<textarea id="detailUserDescription" readonly="readonly"> </textarea>--%>
<%--				<br>--%>
					<p> ?????? ?????? ?????? ???????????? ???????????? ????????? ???????????? ????????? ???????????????!(?????? ????????? ???)</p>
				<div class="contentRow recommandContentRow">

				</div>
			</div>
		</div>
		<%-- ?????? ???????????? ??????	???--%>
		
		<p id="detailReplyCountText" class="rowTitle">??????</p>
		<div class="replyArea">
		</div>
		<div class="writeReplayArea">
			<input id="writeReplyInput" onkeydown="if(event.keyCode==13) {doWriteReply(); return false;}" placeholder="????????? ???????????????." maxlength="100"/>
			<img id="writeReplyButton" src="/images/btn_write_chat.png" />
		</div>
	</div>
	<img class="popupCloseButton" src="/images/btn_close.png" />
</div>

<input id="detailNoInput" type="hidden" class="hiddenInput" />
<input id="styleAddPictureUrlTop" type="hidden" class="hiddenInput" />
<input id="styleAddPictureUrlBottom" type="hidden" class="hiddenInput" />
<input id="styleAddPictureUrlAcc" type="hidden" class="hiddenInput" />
<input id="styleAddPictureUrlHat" type="hidden" class="hiddenInput" />
<input id="styleAddPictureUrlShoes" type="hidden" class="hiddenInput" />
<input id="styleAddPictureUrlBag" type="hidden" class="hiddenInput" />
<input id="fileTypeInputForAddStyle" type="hidden" class="fileUploadInput" value=""/>
<input id="fileIndexInputForAddStyle" type="hidden" class="fileUploadInput" value=""/>
<input id="fileUploadInputForAddStyle" type="file" class="fileUploadInput" onchange="uploadFileForAddStyle(this)" accept="image/*" />
<input id="fileUrlInputForAddStyle" type="hidden" class="fileUploadInput" value=""/>

<script>
$(function() {
	$(".searchDateInput").datepicker();
});
$.datepicker.setDefaults({
	dateFormat: 'yy-mm-dd',
	showMonthAfterYear: true,
	yearSuffix: '/',
	changeYear: true,
	changeMonth: true
});
</script>
<script type="text/javascript" src="/js/index.js?v=<%= _nowDate%>"></script>
<script type="text/javascript" src="/js/moduleAfter.js?v=<%= _nowDate%>"></script>

</body>
</html>