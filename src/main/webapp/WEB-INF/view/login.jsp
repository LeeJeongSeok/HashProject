<%@page contentType="text/html; charset=utf-8" language="java"
import="com.team.hash.hashproject.util.DateUtil"
%>

<%
String _nowDateTemp = DateUtil.getTodayDate("yyyyMMdd_HHmmss");
%>
<link rel="stylesheet" type="text/css" media="all" href="css/login.css?v=<%= _nowDateTemp%>">
<div id="loginPopup" class="popupArea loginPopup">
	<h1>#</h1>
	<input id="loginEmailInput" type="text" class="loginIdInput" maxlength="100" placeholder="Email을 입력해 주세요." onkeydown="if(event.keyCode==13) {checkLogin(); return true;}"/>
	<input id="loginPasswordInput" type="password" class="loginPasswordInput" maxlength="100" placeholder="비밀번호를 입력해 주세요." onkeydown="if(event.keyCode==13) {checkLogin(); return true;}" />
	<button class="blackButton loginButton" onclick="checkLogin()">로그인</button>
	
	<a class="joinText">아이디가 없으신가요?</a>
	<img class="popupCloseButton" src="images/btn_close.png" />
</div>

<div id="joinPopup" class="popupArea joinPopup">
	<h1 id="joinPopupTitle">회원가입</h1>
	<div class="joinRowArea joinRowLeftArea">
		<div class="pictureRow">
			<div class="pictureBox">
				<img id="joinPicture" class="picture" src="" />
			</div>
			<img src="images/btn_capture_camera.png" class="joinPictureCameraButton" />
		</div>
		
		<p class="rowLabel">자기소개</p>
		<div class="joinRow joinBigRow">
			<textarea id="joinDescriptionInput" maxlength="300"></textarea>
		</div>
		
		<p class="rowLabel">인스타그램 아이디</p>
		<div class="joinInstaRow">
			<input id="joinInstagramInput" placeholder="'내 인스타그램을 링크하기'에 사용됩니다."/>
		</div>
	</div>
	
	
	<div class="joinRowArea joinRowRightArea">
		<div class="joinRow">
			<p class="label">이메일</p>
			<p class="star">*</p>
			<input id="joinEmailInput" class="joinInput fullInput" maxlength="100" placeholder="Email을 입력해 주세요." />
		</div>
		<div class="joinRow">
			<p class="label">비밀번호</p>
			<p class="star">*</p>
			<input id="joinPasswordInput" class="joinInput fullInput" type="password" maxlength="30" placeholder="비밀번호를 입력해 주세요." onkeydown="if(event.keyCode==13) {checkLogin(); return true;}"/>
		</div>
		<div class="joinRow">
			<p class="label">비밀번호 확인</p>
			<p class="star">*</p>
			<input id="joinRetryPasswordInput" class="joinInput fullInput" type="password" maxlength="30" placeholder="비밀번호를 한번 더 입력해 주세요." onkeydown="if(event.keyCode==13) {checkLogin(); return true;}" />
		</div>
		<div class="dummyRow"></div>
		
		<div class="joinRow">
			<p class="label">이름</p>
			<p class="star">*</p>
			<input id="joinNameInput" class="joinInput fullInput" />
		</div>
		<div class="joinRow">
			<p class="label">성별</p>
			<div class="checkBackgroundArea">
				<div class="checkArea joinCheckArea joinCheckAreaMan">
					<div class="check"></div>
					<p>남자</p>
				</div>
				<div class="checkArea joinCheckArea joinCheckAreaWoman">
					<div class="check"></div>
					<p>여자</p>
				</div>
			</div>
		</div>
		<div class="joinRow">
			<p class="label">키</p>
			<input id="joinHeightInput" class="joinInput halfInput" maxlength="4" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"/>
			<p class="unit">cm</p>
		</div>
		<div class="joinRow">
			<p class="label"><span></span>몸무게</p>
			<input id="joinWeightInput" class="joinInput halfInput" maxlength="4" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"/>
			<p class="unit">kg</p>
		</div>
	</div>
	
	<button class="grayButton joinPopupButton joinCancelButton" onclick="closePopup()">취소</button>
	<button class="blackButton joinPopupButton joinConfirmButton" onclick="checkJoin()">가입</button>
	<img class="popupCloseButton" src="images/btn_close.png" />
</div>

<input id="fileUploadInputForJoin" type="file" class="fileUploadInput" onchange="uploadFileForJoin(this)" accept="image/*" />
<input id="fileUrlInputForJoin" type="hidden" class="fileUploadInput" value=""/>
<input id="joinOpenType" type="hidden" class="fileUploadInput" value=""/>
<input id="myInformationNoInput" type="hidden" class="fileUploadInput" value=""/>
<script type="text/javascript" src="js/login.js?v=<%= _nowDateTemp%>"></script>