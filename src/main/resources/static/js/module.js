var functionCallBack = null;
function showLoadingBar() {
	showTinyFrontBackground();
	var loadingAreaElement = $(".loadingArea");
	if(loadingAreaElement.css("display") != "none") {
		return;
	}

	loadingAreaElement.css("display", "block");

	var element = $("#loadingProgress");
	element.css("-webkit-animation", "spin 1.5s infinite linear");
	element.css("-moz-animation", "spin 1.5s infinite linear");
	element.css("animation", "spin 1.5s infinite linear");
}

function hideLoadingBar() {
	var loadingAreaElement = $(".loadingArea");
	if(loadingAreaElement.css("display") == "none") {
		return;
	}

	loadingAreaElement.css("display", "none");

	var element = $("#loadingProgress");
	element.css("-webkit-animation", "");
	element.css("-moz-animation", "");
	element.css("animation", "");
	hideTinyFrontBackground();
}

function showTinyBackground() {
	var element = $("#tinyBackground");
	element.css("display", "block");
	
	element.on('scroll touchmove mousewheel', function(event) {
		event.preventDefault();
		event.stopPropagation();
		return false;
	});
}
function showTinyBackground2() {
	var element = $("#tinyBackground2");
	element.css("display", "block");
	
	element.on('scroll touchmove mousewheel', function(event) {
		event.preventDefault();
		event.stopPropagation();
		return false;
	});
}

function showTinyFrontBackground() {
	var element = $("#tinyFrontBackground");
	element.css("display", "block");
}

function hideTinyBackground() {
	var element = $("#tinyBackground");
	element.css("display", "none");
	
	element.off('scroll touchmove mousewheel');
}
function hideTinyBackground2() {
	var element = $("#tinyBackground2");
	element.css("display", "none");
	
	element.off('scroll touchmove mousewheel');
}
function hideTinyFrontBackground() {
	var element = $("#tinyFrontBackground");
	element.css("display", "none");
}

function checkUrlForm(strUrl) {
    var expUrl = /^http[s]?\:\/\//i;
    return expUrl.test(strUrl);
}

function setComma(num) {
	return num.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
}


function onlyNumber(event){
	event = event || window.event;
	var keyID = (event.which) ? event.which : event.keyCode;
	if ((keyID >= 48 && keyID <= 57) || (keyID >= 96 && keyID <= 105) || keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 || keyID == 9 || keyID == 10) {
		return;
	}
	else {
		return false;
	}
}
function removeChar(event) {
	event = event || window.event;
	var keyID = (event.which) ? event.which : event.keyCode;
	if (keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 || keyID == 9 || keyID == 10) {
		return;
	}
	else {
		event.target.value = event.target.value.replace(/[^0-9]/g, "");
	}
}

function onlyNumberForPlusMinus(event) {
	event = event || window.event;
	var keyID = (event.which) ? event.which : event.keyCode;
	if ((keyID >= 48 && keyID <= 57) || (keyID >= 96 && keyID <= 105) || keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 || keyID == 9 || keyID == 10 || keyID == 189) {
		return;
	}
	else {
		return false;
	}
}
function removeCharForPlusMinus(event) {
	event = event || window.event;
	var keyID = (event.which) ? event.which : event.keyCode;
	if (keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 || keyID == 9 || keyID == 10 || keyID == 189) {
		return;
	} else {
		var value = event.target.value;
		if(value == '-0') {
			event.target.value = "0";
		} else {
			event.target.value = event.target.value.replace(/[^(-?)0-9]/g, "");
		}
	}
}

function setCookie (name, value, expires) {
	document.cookie = name + "=" + escape (value) +"; path=/; expires=" + expires.toGMTString();
}

function getCookie(Name) {
	var search = Name + "="
		if (document.cookie.length > 0) {
			offset = document.cookie.indexOf(search)
	    	if (offset != -1) {
	      		offset += search.length
	      		end = document.cookie.indexOf(";", offset)
	      		if (end == -1) {
	      			end = document.cookie.length
	      		}
	    			
	      		return unescape(document.cookie.substring(offset, end))
	    	}
	  	}
	return "";
}

function closePopup() {
	$(".popupArea").css("display", "none");
	hideTinyBackground();
	hideTinyBackground2();
}
function closePopup2() {
	$(".popupArea2").css("display", "none");
	hideTinyBackground2();
}

function setPagingButton(totalCount, nowPage) {
	var viewCount = $("#viewCountSelect").val();
	if(totalCount == 0) {
		$(".prev").css("display", "none");
		$(".next").css("display", "none");
		
		$(".prevprev").css("display", "none");
		$(".nextnext").css("display", "none");
		for(var i=0; i<5; i++) {
			var element = $(".pagingButton_" + (i + 1));
			element.css("display", "none");
		}
		return;
	}

	var divide = parseInt(nowPage / 5);
	var mod = parseInt(nowPage % 5);
	//var lastFirstNumber = divide * 5;

	var totalPage = parseInt(totalCount / viewCount);
	var totalMod = parseInt(totalCount % viewCount);
	if(totalMod > 0) {
		totalPage += 1;
	}

	var buttonCount = 0;

	if(totalPage < 6) {
		for(var i=0; i<5; i++) {
			var element = $(".pagingButton_" + (i + 1));
			if(i+1 > totalPage) {
				element.css("display", "none");
			} else {
				buttonCount++;
				element.css("display", "block");
			}
			element.removeClass("pagingSelect");
			element.text("" + (i + 1));
		}
	} else {
		for(var i=0; i<5; i++) {
			var element = $(".pagingButton_" + (i + 1));
			var realNumber = (divide * 5) + (i + 1);
			
			if(realNumber > totalPage) {
				element.css("display", "none");
			} else {
				buttonCount++;
				element.css("display", "block");
			}

			element.removeClass("pagingSelect");
			element.text("" + realNumber);
		}
	}

	$(".pagingButton_" + (mod + 1)).addClass("pagingSelect");

	if(totalPage < 2) {
		$(".prev").css("display", "none");
		$(".next").css("display", "none");
		
		$(".prevprev").css("display", "none");
		$(".nextnext").css("display", "none");
	} else if(nowPage == 0) {
		$(".prev").css("display", "none");
		$(".next").css("display", "block");
		
		$(".prevprev").css("display", "none");
		$(".nextnext").css("display", "block");
		buttonCount += 2;
	} else if(nowPage == totalPage - 1) {
		$(".prev").css("display", "block");
		$(".next").css("display", "none");
		
		$(".prevprev").css("display", "block");
		$(".nextnext").css("display", "none");
		buttonCount += 2;
	} else {
		$(".prev").css("display", "block");
		$(".next").css("display", "block");
		
		$(".prevprev").css("display", "block");
		$(".nextnext").css("display", "block");
		buttonCount += 4;
	}
	
//	var areaWidth = buttonCount * 36;
//	var element = $(".pagingButtonArea");
//	element.css("width", areaWidth + "px");
//	element.css("margin-left", "calc((100% - " + areaWidth + "px) / 2)");
//	element.css("margin-left", "-webkit-calc((100% - " + areaWidth + "px) / 2)");
//	element.css("margin-left", "-moz-calc((100% - " + areaWidth + "px) / 2)");
}


function moveNowClickedIndex(element) {
	var viewCount = $("#viewCountSelect").val();
	if(element.hasClass("prev") || element.hasClass("next")) {
		if(element.css("cursor") == "default") {
			return;
		}
		
		var nowPage = 0;
		for(var i=0; i<5; i++) {
			var nowElement = $(".pagingButton_" + (i + 1));
			if(nowElement.hasClass("pagingSelect")) {
				var text = nowElement.text();
				nowPage = parseInt(text);
				break;
			}
		}
		
		var targetPage = 0;
		if(element.hasClass("prev")) {
			targetPage = nowPage - 2;
			if(targetPage < 0) {
				targetPage = 0;
			}
		} else {
			var totalPage = parseInt(totalCount / viewCount);
			var totalMod = parseInt(totalCount % viewCount);
			if(totalMod > 0) {
				totalPage += 1;
			}
			if(nowPage == totalCount) {
				nowPage -= 1;
			}
			targetPage = nowPage;
		}
		movePage(targetPage);
	} else if(element.hasClass("prevprev") || element.hasClass("nextnext")) {
		//var nowPage = 0;
		for(var i=0; i<5; i++) {
			var nowElement = $(".pagingButton_" + (i + 1));
			if(nowElement.hasClass("pagingSelect")) {
				//var text = nowElement.text();
				//nowPage = parseInt(text);
				break;
			}
		}

		var targetPage = 0;
		if(element.hasClass("prevprev")) {
			targetPage = 0;
		} else {
			var totalPage = parseInt(totalCount / viewCount);
			var totalMod = parseInt(totalCount % viewCount);
			if(totalMod == 0) {
				totalPage -= 1;
			}
			targetPage = totalPage;
		}
		movePage(targetPage);
	} else {
		var nowPage = 0;
		for(var i=0; i<5; i++) {
			var className = "pagingButton_" + (i + 1);
			if(element.hasClass(className)) {
				var text = $("." + className).text();
				nowPage = parseInt(text);
				break;
			}
		}
		var targetPage = nowPage - 1;
		if(targetPage < 0) {
			targetPage = 0;
		}
		movePage(targetPage);
	}
}

function getTotalPage(totalCount, viewCount) {
	var totalPage = parseInt(totalCount / viewCount);
	var totalMod = parseInt(totalCount % viewCount);
	if(totalMod > 0) {
		totalPage += 1;
	}
	return totalPage;
}
function setPagingResultMessage(targetPage, totalPage, totalCount) {
	var nowPage = targetPage + 1;
	if(totalPage == 0) {
		nowPage = 0;
	}
	$("#resultMessage").text(totalCount + "건이 조회되었습니다.");
}


function makeTable(tableElement, array, arraySize) {
	for(var i=0; i<50; i++) {
		addRow(tableElement, i);
		if(i < arraySize) {
			var item = array[i];
			setRow(item, i);
		} else {
			$(".commonTr_" + i).css("display", "none");
		}
	}
}
function updateTable(array, arraySize) {
	for(var i=0; i<50; i++) {
		if(i < arraySize) {
			var item = array[i];
			$(".commonTr_" + i).css("display", "table-row");

			setRow(item, i);
		} else {
			$(".commonTr_" + i).css("display", "none");
		}
	}
}


function showUserInformationDropDown() {
	$("#userInformationDropDown").css("display", "block");
//	$(".userInformationDropDown").mouseleave(function() {
//		$("#userInformationDropDown").css("display", "none");
//	});
	$(".userInformationArea").mouseleave(function() {
		$("#userInformationDropDown").css("display", "none");
	});
}


function showAlert(str, callback) {
	$(':focus').blur();
	
	showTinyFrontBackground();
	$("#oneButtonAlert").css("display", "block");
	$("#twoButtonAlert").css("display", "none");
	$("#oneButtonAlertText").text(str);
	if(callback) {
		functionCallBack = callback;
	} else {
		functionCallBack = null;
	}
}

function showConfirm(str, callback) {
	showTinyFrontBackground();
	$("#oneButtonAlert").css("display", "none");
	$("#twoButtonAlert").css("display", "block");
	$("#twoButtonAlertText").text(str);
	if(callback) {
		functionCallBack = callback;
	} else {
		functionCallBack = null;
	}
}
function alertDone() {
	closeAlert();
	if(functionCallBack != null && functionCallBack) {
		functionCallBack();
	}
}
function closeAlert() {
	hideTinyFrontBackground();
	$(".customAlert").css("display", "none");
}
function clearFocus() {
	$(":focus").blur();
}


function enableScroll(element) {
	element.off("scroll touchmove mousewheel");
}
function disableScroll(element) {
	element.on("scroll touchmove mousewheel", function(event) {
		event.preventDefault();
		event.stopPropagation();
		return false;
	});
}

function formatDate(date) {
	var date = new Date(date);
	var month = "" + (date.getMonth() + 1);
	var day = "" + date.getDate();
	var year = date.getFullYear();

	if(String(month).length < 2) {
		month = "0" + month;
	}
	if(String(day).length < 2) {
    	day = "0" + day;
	}
	return year + "-" + month + "-" + day;
}

function initInput(element) {
	var agent = navigator.userAgent.toLowerCase();
	if((navigator.appName == 'Netscape' && navigator.userAgent.search('Trident') != -1) || (agent.indexOf("msie") != -1) ){
		element.replaceWith(element.clone(true));
	} else {
		element.val("");
	}
}

function changePhoneNumber(_phoneNumber){
	var regex = /[^0-9]/g;
	var phoneNumber = _phoneNumber.replace(regex, "");
	
    var result = "";
    var length = phoneNumber.length;
    if(length == 11) {
    	result = phoneNumber.replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');
    } else if(length == 10) {
    	if(length > 2) {
    		var start = phoneNumber.substr(0, 2);
    		if(start == "02") {
    			result = phoneNumber.replace(/(\d{2})(\d{4})(\d{4})/, '$1-$2-$3');
    		} else {
    			result = phoneNumber.replace(/(\d{3})(\d{3})(\d{4})/, '$1-$2-$3');
    		}
    	}
    } else if(length == 9) {
    	result = phoneNumber.replace(/(\d{2})(\d{3})(\d{4})/, '$1-$2-$3');
    } else {
    	result = _phoneNumber;
    }
    return result;
}

function doLogout() {
	location.replace("app.do?kind=logout");
}

function getOnlyNumber(_str) {
	return _str.replace(/[^0-9]/g, "");
}

function changeComma(_id) {
	var element = $("#"+_id);
	var value = element.val();
	if(String(value).length == 0) {
		value = 0;
	}
	element.val(setComma(parseInt(getOnlyNumber(value))));
}

function numberWithComma(id, x) {
	x = x.replace(/[^0-9]/g,'');
	x = x.replace(/,/g,'');
	$("#"+id).val(x.replace(/\B(?=(\d{3})+(?!\d))/g, ","));
}
