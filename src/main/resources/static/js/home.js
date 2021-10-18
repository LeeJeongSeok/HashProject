$(document).on("click", ".homeHeart", function(e) {
	e.stopPropagation();
	
	if(isLogin != "Y") {
		showLoginPopup();
		return;
	}
	
	var index = -1;
	var selfElement = $(this);
	var size = array.length;
	for(var i=0; i<size; i++) {
		if(selfElement.hasClass("homeHeart_" + i)) {
			index = i;
			break;
		}
	}
	if(index != -1) {
		var item = array[index];
		var _styleNo = item.no;
		
		var _isLike = "";
		var isLikeTemp = item.isLike;
		if(isLikeTemp == "Y") {
			_isLike = "";
		} else {
			_isLike = "Y";
		}
		updateStyleLike(index, _styleNo, _isLike);
	}
});
$(document).on("click", ".homeHeartCount", function(e) {
	e.stopPropagation();
	
	if(isLogin != "Y") {
		showLoginPopup();
		return;
	}
	
	var index = -1;
	var selfElement = $(this);
	var size = array.length;
	for(var i=0; i<size; i++) {
		if(selfElement.hasClass("homeHeartCount_" + i)) {
			index = i;
			break;
		}
	}
	if(index != -1) {
		var item = array[index];
		var _styleNo = item.no;
		
		var _isLike = "";
		var isLikeTemp = item.isLike;
		if(isLikeTemp == "Y") {
			_isLike = "";
		} else {
			_isLike = "Y";
		}
		updateStyleLike(index, _styleNo, _isLike);
	}
});

$(document).on("click", ".homePicture", function(e) {
	e.stopPropagation();
	
	var index = -1;
	var selfElement = $(this);
	var size = array.length;
	for(var i=0; i<size; i++) {
		if(selfElement.hasClass("homePicture_" + i)) {
			index = i;
			break;
		}
	}
	if(index != -1) {
		var item = array[index];
		showDetailPopup(item);
	}
});

function updateStyleLike(index, _styleNo, _isLike) {
	showLoadingBar();
	var param = {
		kind:"updateStyleLike",
		styleNo:_styleNo,
		isLike:_isLike
	};
	jQuery.ajax({
		type:"POST",
		url:("/updateStyleLike"),
		contentType: "application/json; charset=utf-8",
		dataType:"JSON",
		data:JSON.stringify(param),
		timeout:20000,
		success:function(data) {
			hideLoadingBar();
			if(data.isConfirm == "Y") {
				if(index == -1) {
					var likeCount = data.likeCount;
					nowDetailItem.isLike = _isLike;
					nowDetailItem.likeCount = likeCount;
					
					if(_isLike == "Y") {
						$("#detailHeart").attr("src", "images/ico_heart_on.png");
					} else {
						$("#detailHeart").attr("src", "images/ico_heart_off.png");
					}
					$("#detailHeartCount").text(setComma(likeCount));
					
					var realIndex = -1;
					for(var i=0; i<arraySize; i++) {
						var rowItem = array[i];
						if(rowItem.no == _styleNo) {
							realIndex = i;
							break;
						}
					}
					if(realIndex != -1) {
						var item = array[realIndex];
						item.isLike = _isLike;
						item.likeCount = likeCount;
						
						$(".homeHeartCount_"+realIndex).text(setComma(likeCount));
						
						if(_isLike == "Y") {
							$(".homeHeart_"+realIndex).attr("src", "images/ico_heart_on.png");
						} else {
							$(".homeHeart_"+realIndex).attr("src", "images/ico_heart_off.png");
						}
					}
				} else {
					var item = array[index];
					item.isLike = _isLike;
					var likeCount = data.likeCount;
					item.likeCount = likeCount;
					$(".homeHeartCount_"+index).text(setComma(likeCount));
					
					if(_isLike == "Y") {
						$(".homeHeart_"+index).attr("src", "images/ico_heart_on.png");
					} else {
						$(".homeHeart_"+index).attr("src", "images/ico_heart_off.png");
					}
				}
			}
		}, complete:function(data) {
		}, error:function(xhr, status, error) {
		}
	});
}

$("#detailHeartCount").click(function() {
	if(isLogin != "Y") {
		showLoginPopup();
		return;
	}
	
	var _styleNo = nowDetailItem.no;
	var _isLike = "";
	var isLikeTemp = nowDetailItem.isLike;
	if(isLikeTemp == "Y") {
		_isLike = "";
	} else {
		_isLike = "Y";
	}
	updateStyleLike(-1, _styleNo, _isLike);
});

function showDetailPopup(item) {
	nowDetailItem = item;
	
	closePopup();
	showTinyBackground();
	$("#detailPopup").css("display", "block");
	
	$("#detailNoInput").val(item.no);

	var productElement = $(".detailContentRow");
	productElement.empty();
	var _productArray = JSON.parse(item.strProductArray);
	var _productSize = _productArray.length;
	for(var j=0; j<_productSize; j++) {
		var _productObject = _productArray[j];
		var _rowTitle = _productObject.title;
		var _displayProductPictureUrl = "";
		var _productPictureUrl = _productObject.pictureUrl;
		if(String(_productPictureUrl).length > 0) {
			_displayProductPictureUrl = _productPictureUrl;
		}
		
		var _isUse = _productObject.isUse;
		if(_isUse == "Y") {
			var rowText = "<div class=\"contentMiniRow\"><img src=\""+_displayProductPictureUrl+"\" /><p>"+_rowTitle+"</p></div>";
			productElement.append(rowText);
		}
	}
	
	var _displayUserPictureUrl = "";
	var _rowUserPictureUrl = item.userPictureUrl;
	if(String(_rowUserPictureUrl).length > 0) {
		_displayUserPictureUrl = _rowUserPictureUrl;
	} else {
		_displayUserPictureUrl = "images/ico_person.png";
	}
	
	$("#detailUserPicture").attr("src", _displayUserPictureUrl);
	$("#detailUserName").text(item.userName);
	$("#detailUserDescription").val(item.userDescription);
	
	var _isLike = item.isLike;
	var _displayLikeImage = "images/ico_heart_off.png";
	if(_isLike == "Y") {
		_displayLikeImage = "images/ico_heart_on.png";
	}
	var _likeCount = item.likeCount;
	$("#detailHeart").attr("src", _displayLikeImage);
	$("#detailHeartCount").text(_likeCount);
	
	replyArray = new Array();
	$(".replyArea").empty();
	
	setTimeout(function() {
		var pictureUrlArray = JSON.parse(item.strPictureUrlArray);
		var size = pictureUrlArray.length;
		
		var data = [];
		for(var i=0; i<size; i++) {
			var rowPictureUrl = pictureUrlArray[i].pictureUrl;
			data.push({img: rowPictureUrl});
		}

		var fotorama = $('#fotorama');
		fotorama.removeData();
		fotorama.fotorama({
		    data: data,
		    loop:true,
		    width:'100%',
		    height:'700',
		    autoplay:true,
		    keyboard:true,
		    fit:'cover',
		    nav:'thumbs',
		    arrows:true
		});
		
		doGetReplyArray();
	}, 300);
}

$("#writeReplyButton").click(function() {
	doWriteReply();
});
function doWriteReply() {
	var _content = $("#writeReplyInput").val();
	if(String(_content).length == 0) {
		showAlert("댓글을 작성해 주세요.", function() {
			$("#writeReplyInput").focus();
		});
		return;
	}
	
	var _no = $("#detailNoInput").val();
	showLoadingBar();
	var param = {
		kind:"writeReply",
		styleNo:_no,
		content:_content
	};
	jQuery.ajax({
		type:"POST",
		url:("/writeReply"),
		contentType: "application/json; charset=utf-8",
		dataType:"JSON",
		data:JSON.stringify(param),
		timeout:20000,
		success:function(data) {
			hideLoadingBar();
			if(data.isConfirm == "Y") {
				$("#writeReplyInput").val("");
				doGetReplyArray();
			}
		}, complete:function(data) {
		}, error:function(xhr, status, error) {
		}
	});
}

function doGetReplyArray() {
	var _no = $("#detailNoInput").val();
	showLoadingBar();
	var param = {
		kind:"getReplyArray",
		styleNo:_no
	};
	jQuery.ajax({
		type:"POST",
		url:("/getReplyArray"),
		contentType: "application/json; charset=utf-8",
		dataType:"JSON",
		data:JSON.stringify(param),
		timeout:20000,
		success:function(data) {
			hideLoadingBar();
			if(data.isConfirm == "Y") {
				replyArray = data.replyArray;
				drawReplyArray();
			}
		}, complete:function(data) {
		}, error:function(xhr, status, error) {
		}
	});
}

function drawReplyArray() {
	var element = $(".replyArea");
	element.empty();
	
	var rowText = "";
	var size = replyArray.length;
	for(var i=0; i<size; i++) {
		var item = replyArray[i];
		var userName = item.userName;
		var content = item.content;
		var userPictureUrl = item.userPictureUrl;
		if(String(userPictureUrl).length > 0) {
			var temp = userPictureUrl;
			userPictureUrl = temp;
		}
		
		var insertTime = item.insertTime;
		
		var strOtherPeople = " deleteReplyButtonOtherPeople";
		if(item.isMe == "Y") {
			strOtherPeople = "";
		}
		rowText += ("<div class=\"replyRow\"><p class=\"content\"><span>"+userName+"</span>"+content+"</p><img class=\"picture\" src=\""+userPictureUrl+"\" /><h6>"+insertTime+"</h6><img class=\"deleteButton deleteReplyButton deleteReplyButton_"+i+strOtherPeople+"\" src=\"images/btn_delete.png\"/></div>");
	}
	element.append(rowText);
}
$(document).on("click", ".deleteReplyButton", function(e) {
	e.stopPropagation();
	
	var index = -1;
	var selfElement = $(this);
	var size = replyArray.length;
	for(var i=0; i<size; i++) {
		if(selfElement.hasClass("deleteReplyButton_" + i)) {
			index = i;
			break;
		}
	}
	if(index != -1) {
		var item = replyArray[index];
		showConfirm("댓글을 삭제하시겠습니까?", function() {
			deleteReply(item.no);
		});
	}
});

function deleteReply(_no) {
	showLoadingBar();
	var param = {
		kind:"deleteReply",
		replyNo:_no
	};
	jQuery.ajax({
		type:"POST",
		url:("/deleteReply"),
		contentType: "application/json; charset=utf-8",
		dataType:"JSON",
		data:JSON.stringify(param),
		timeout:20000,
		success:function(data) {
			hideLoadingBar();
			if(data.isConfirm == "Y") {
				doGetReplyArray();
			}
		}, complete:function(data) {
		}, error:function(xhr, status, error) {
		}
	});
}