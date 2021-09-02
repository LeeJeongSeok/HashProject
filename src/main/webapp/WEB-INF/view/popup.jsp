<%@page contentType="text/html; charset=utf-8" language="java"
%>
<div id="tinyBackground"></div>
<div id="tinyBackground2"></div>
<div id="tinyFrontBackground"></div>
<div class="loadingArea">
	<img id="loadingProgress" class="loadingProgress" src="images/common_progress.png" />
</div>
<div id="oneButtonAlert" class="customAlert oneButtonAlert">
	<div class="alertTextArea">
		<p id="oneButtonAlertText"></p>
	</div>
	<div class="alertButtonRow">
		<button class="blackButton alertButton alertOneConfirmButton" onclick="alertDone()">확인</button>
	</div>
</div>
<div id="twoButtonAlert" class="customAlert twoButtonAlert">
	<div class="alertTextArea">
		<p id="twoButtonAlertText"></p>
	</div>
	<div class="alertButtonRow">
		<button class="blackButton alertButton alertTwoConfirmButton" onclick="alertDone()">확인</button>
		<button class="grayButton alertButton alertTwoCloseButton" onclick="closeAlert()">취소</button>
	</div>
</div>