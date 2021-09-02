$("#tinyBackground").click(function() {
	closePopup();
});
$("#tinyBackground2").click(function() {
	closePopup2();
});

$(document).keydown(function(e) {
	var keyCode = e.keyCode;
	if(keyCode == 27) {
		if($("#oneButtonAlert").css("display") == "block") {
			closeAlert();
			return;
		}
		if($("#twoButtonAlert").css("display") == "block") {
			closeAlert();
			return;
		}
		if($("#tinyBackground2").css("display") == "block") {
			closePopup2();
			return;
		}
		closePopup();
	} else if(keyCode == 13) {
		if($(".availablePopupEnter").css("display") == "block") {
			return;
		}
		if($(".pictureSelectPopup").css("display") == "block") {
			return;
		}
		if($("#twoButtonAlert").css("display") == "block") {
			alertDone();
			return;
		}
		if($("#oneButtonAlert").css("display") == "block") {
			alertDone();
			return;
		}
	}
});

$(".popupCloseButton").click(function() {
	closePopup();
});