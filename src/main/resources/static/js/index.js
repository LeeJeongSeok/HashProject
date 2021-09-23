function showInformationBox() {
	$(".emptyTouchArea").css("display", "block");
	$("#homeProfileArea").css("height", "120px");
}
function hideInformationBox() {
	$(".emptyTouchArea").css("display", "none");
	$("#homeProfileArea").css("height", "0px");
}


$(".emptyTouchArea").click(function() {
	hideInformationBox();
});


$(".myProfileButton").click(function() {
	showInformationBox();
});
$("#myInformationButton").click(function() {
	showLoadingBar();
	var param = {
		kind:"getMyInformation"
	};
	jQuery.ajax({
		type:"POST",
		url:("/getMyInformation"),
		contentType: "application/json; charset=utf-8",
		dataType:"JSON",
		data:JSON.stringify(param),
		timeout:20000,
		success:function(data) {
			hideLoadingBar();
			if(data.isConfirm == "Y") {
				showMyInformationPopup(data);
			} else {
				showAlert("내 정보 조회에 실패했습니다. 다시 시도해 주세요.", null);
			}
		}, complete:function(data) {
		}, error:function(xhr, status, error) {
			console.log(xhr, status, error);
		}
	});
});
$("#myStyleDetailRoomButton").click(function() {
	location.replace("/index?page=detail");
});

$("#logoutButton").click(function() {
	location.replace("/logout");
});

$(".addStyleButton").click(function() {
	showTinyBackground();
	$("#addStylePopup").css("display", "block");

	styleAddPictureArray = new Array();
	$("#addStyleMainDescriptionInput").val("");
	$(".styleSubSelect").val("");
	$(".pictureSubAddItem").css("display", "block");
	$(".styleSubPicture").css("display", "none");
	$(".styleSubPicture").attr("src", "");
	$(".styleSubTitle").val("");
	$(".styleSubContent").val("");
	
	$(".styleSubCircleCheck").removeClass("circleCheckPress");
	
	drawStyleAddMainPicture();
});

$(".styleSubCircleCheck").click(function() {
	var selfElement = $(this);
	if(selfElement.hasClass("circleCheckPress")) {
		selfElement.removeClass("circleCheckPress");
	} else {
		selfElement.removeClass("circleCheckPress").addClass("circleCheckPress");
	}
});

function checkSaveAddStyle() {
	var _content = $("#addStyleMainDescriptionInput").val();
	
	var _pictureSize = styleAddPictureArray.length;
	if(_pictureSize < 3) {
		showAlert("전신샷을 최소 3장이상 업로드해 주세요.");
		return;
	}
	var _strPictureUrlArray = JSON.stringify(styleAddPictureArray);
	var _pictureUrl = styleAddPictureArray[0].pictureUrl;
	
	var productArray = new Array();

	var itemTop = new Object();
	itemTop.type = "top";
	itemTop.pictureUrl = $("#styleAddPictureUrlTop").val();
	itemTop.categoryName = $(".styleSubSelectTop").val();
	itemTop.title = $(".styleSubTitleTop").val();
	itemTop.description = $(".styleSubContentTop").val();
	itemTop.isUse = $(".styleSubCircleCheckTop").hasClass("circleCheckPress") ? "Y" : "";
	productArray.push(itemTop);
	var itemBottom = new Object();
	itemBottom.type = "bottom";
	itemBottom.pictureUrl = $("#styleAddPictureUrlBottom").val();
	itemBottom.categoryName = $(".styleSubSelectBottom").val();
	itemBottom.title = $(".styleSubTitleBottom").val();
	itemBottom.description = $(".styleSubContentBottom").val();
	itemBottom.isUse = $(".styleSubCircleCheckBottom").hasClass("circleCheckPress") ? "Y" : "";
	productArray.push(itemBottom);
	var itemAcc = new Object();
	itemAcc.type = "acc";
	itemAcc.pictureUrl = $("#styleAddPictureUrlAcc").val();
	itemAcc.categoryName = $(".styleSubSelectAcc").val();
	itemAcc.title = $(".styleSubTitleAcc").val();
	itemAcc.description = $(".styleSubContentAcc").val();
	itemAcc.isUse = $(".styleSubCircleCheckAcc").hasClass("circleCheckPress") ? "Y" : "";
	productArray.push(itemAcc);
	var itemHat = new Object();
	itemHat.type = "hat";
	itemHat.pictureUrl = $("#styleAddPictureUrlHat").val();
	itemHat.categoryName = $(".styleSubSelectHat").val();
	itemHat.title = $(".styleSubTitleHat").val();
	itemHat.description = $(".styleSubContentHat").val();
	itemHat.isUse = $(".styleSubCircleCheckHat").hasClass("circleCheckPress") ? "Y" : "";
	productArray.push(itemHat);
	var itemShoes = new Object();
	itemShoes.type = "shoes";
	itemShoes.pictureUrl = $("#styleAddPictureUrlShoes").val();
	itemShoes.categoryName = $(".styleSubSelectShoes").val();
	itemShoes.title = $(".styleSubTitleShoes").val();
	itemShoes.description = $(".styleSubContentShoes").val();
	itemShoes.isUse = $(".styleSubCircleCheckShoes").hasClass("circleCheckPress") ? "Y" : "";
	productArray.push(itemShoes);
	var itemBag = new Object();
	itemBag.type = "bag";
	itemBag.pictureUrl = $("#styleAddPictureUrlBag").val();
	itemBag.categoryName = $(".styleSubSelectBag").val();
	itemBag.title = $(".styleSubTitleBag").val();
	itemBag.description = $(".styleSubContentBag").val();
	itemBag.isUse = $(".styleSubCircleCheckBag").hasClass("circleCheckPress") ? "Y" : "";
	productArray.push(itemBag);
	
	var _strProductArray = JSON.stringify(productArray);
	
	showLoadingBar();
	var param = {
		kind:"writeStyle",
		content:_content,
		pictureUrl:_pictureUrl,
		strPictureUrlArray:_strPictureUrlArray,
		strProductArray:_strProductArray
	};
	jQuery.ajax({
		type:"POST",
		url:("/writeStyle"),
		contentType: "application/json; charset=utf-8",
		dataType:"JSON",
		data:JSON.stringify(param),
		timeout:20000,
		success:function(data) {
			hideLoadingBar();
			if(data.isConfirm == "Y") {
				showAlert("내 스타일이 등록되었습니다.", function() {
					location.replace("index");
				});
			} else {
				showAlert("내 스타일 등록에 실패했습니다. 다시 시도해 주세요.", null);
			}
		}, complete:function(data) {
		}, error:function(xhr, status, error) {
		}
	});
}


$(".pictureSubAddItem").click(function() {
	var selfElement = $(this);
	if(selfElement.hasClass("pictureSubAddItemTop")) {
		$("#fileTypeInputForAddStyle").val("Top");
	} else if(selfElement.hasClass("pictureSubAddItemBottom")) {
		$("#fileTypeInputForAddStyle").val("Bottom");
	} else if(selfElement.hasClass("pictureSubAddItemAcc")) {
		$("#fileTypeInputForAddStyle").val("Acc");
	} else if(selfElement.hasClass("pictureSubAddItemHat")) {
		$("#fileTypeInputForAddStyle").val("Hat");
	} else if(selfElement.hasClass("pictureSubAddItemShoes")) {
		$("#fileTypeInputForAddStyle").val("Shoes");
	} else if(selfElement.hasClass("pictureSubAddItemBag")) {
		$("#fileTypeInputForAddStyle").val("Bag");
	}
	$("#fileUploadInputForAddStyle").trigger("click");
});
$(".styleSubPicture").click(function() {
	var selfElement = $(this);
	if(selfElement.hasClass("styleSubPictureTop")) {
		$("#fileTypeInputForAddStyle").val("Top");
	} else if(selfElement.hasClass("styleSubPictureBottom")) {
		$("#fileTypeInputForAddStyle").val("Bottom");
	} else if(selfElement.hasClass("styleSubPictureAcc")) {
		$("#fileTypeInputForAddStyle").val("Acc");
	} else if(selfElement.hasClass("styleSubPictureHat")) {
		$("#fileTypeInputForAddStyle").val("Hat");
	} else if(selfElement.hasClass("styleSubPictureShoes")) {
		$("#fileTypeInputForAddStyle").val("Shoes");
	} else if(selfElement.hasClass("styleSubPictureBag")) {
		$("#fileTypeInputForAddStyle").val("Bag");
	}
	$("#fileUploadInputForAddStyle").trigger("click");
});
function uploadFileForAddStyle(input) {
	var maxFileSize = 10 * 1024 * 1024;
	var file = input.files[0];
	var fileSize = file.size;
	if(fileSize > maxFileSize) {
		showAlert("10MB 미만의 파일만 첨부 가능합니다.");
		return;
	}
	if(input.files && file) {
		var reader = new FileReader();
      	reader.readAsDataURL(file);

		var formData = new FormData();
		formData.append('file', file);
		showLoadingBar();
		jQuery.ajax({
			url:"uploadFile",
			type:"POST",
			dataType:"JSON",
			data:formData,
			// beforeSend:function(xhr){
	        //     xhr.setRequestHeader("kind", "uploadFile");
	        // },
			processData:false,
			contentType:false,
			success: function(data, textStatus, jqXHR) {
				hideLoadingBar();
				if(data.isConfirm == "Y") {
					var fileUrl = data.fileUrl;
					var fileName = data.fileName;
					
					var tag = $("#fileTypeInputForAddStyle").val();
					if(tag == "main") {
						var indexTemp = $("#fileIndexInputForAddStyle").val();
						if(indexTemp == "-1") {
							var item = new Object();
							item.pictureUrl = fileUrl;
							styleAddPictureArray.push(item);
						} else {
							var index = parseInt(indexTemp);
							var item = styleAddPictureArray[index];
							item.pictureUrl = fileUrl;
						}
						drawStyleAddMainPicture();
					} else {
						$(".pictureSubAddItem"+tag).css("display", "none");
						$(".styleSubPicture"+tag).css("display", "block");
						$(".styleSubPicture"+tag).attr("src", "file/"+fileUrl);
						$("#styleAddPictureUrl"+tag).val(fileUrl);
						$(".styleSubCircleCheck"+tag).removeClass("circleCheckPress").addClass("circleCheckPress");
					}
				} else {
					showAlert("파일 업로드에 실패했습니다.", null);
				}
			}, error: function(jqXHR, textStatus, errorThrown) {
				hideLoadingBar();
				showAlert("파일 업로드에 실패했습니다.", null);
			}
		});
	}
}

function drawStyleAddMainPicture() {
	var element = $(".styleMainPictureArea");
	element.empty();
	
	var size = styleAddPictureArray.length;
	for(var i=0; i<size; i++) {
		var item = styleAddPictureArray[i];
		var pictureUrl = "file/"+item.pictureUrl;
		var rowText = ("<div class=\"pictureMainItem pictureMainItem_"+i+" pictureMainItemExist\"><p class=\"pictureMainAddItem pictureMainAddItem_"+i+"\">+</p><img class=\"styleMainPicture styleMainPicture_"+i+"\" src=\""+pictureUrl+"\" /><img class=\"styleMainPictureDeleteButton styleMainPictureDeleteButton_"+i+"\" src=\"images/btn_delete.png\" /></div>");
		element.append(rowText);
	}
	
	if(size < 5) {
		var addIndex = size;
		var addText = ("<div class=\"pictureMainItem pictureMainItem_"+addIndex+"\"><p class=\"pictureMainAddItem pictureMainAddItem_"+addIndex+"\">+</p><img class=\"styleMainPicture styleMainPicture_"+addIndex+"\" src=\"\" /><img class=\"styleMainPictureDeleteButton styleMainPictureDeleteButton_"+addIndex+"\" src=\"images/btn_delete.png\" /></div>");
		element.append(addText);
	}
}
$(document).on("click", ".pictureMainAddItem", function() {
	var index = -1;
	var selfElement = $(this);
	var size = styleAddPictureArray.length;
	if(size > 4) {
		showAlert("최대 5개까지만 선택 가능합니다.");
		return;
	}
	
	for(var i=0; i<size; i++) {
		if(selfElement.hasClass("pictureMainAddItem_" + i)) {
			index = i;
			break;
		}
	}
	if(index == -1) {
		$("#fileTypeInputForAddStyle").val("main");
		$("#fileIndexInputForAddStyle").val("-1");
		$("#fileUploadInputForAddStyle").trigger("click");
	}
});
$(document).on("click", ".styleMainPicture", function() {
	var index = -1;
	var selfElement = $(this);
	var size = styleAddPictureArray.length;
	for(var i=0; i<size; i++) {
		if(selfElement.hasClass("styleMainPicture_" + i)) {
			index = i;
			break;
		}
	}
	if(index != -1) {
		$("#fileTypeInputForAddStyle").val("main");
		$("#fileIndexInputForAddStyle").val(index);
		$("#fileUploadInputForAddStyle").trigger("click");
	}
});
$(document).on("click", ".styleMainPictureDeleteButton", function() {
	var index = -1;
	var selfElement = $(this);
	var size = styleAddPictureArray.length;
	for(var i=0; i<size; i++) {
		if(selfElement.hasClass("styleMainPictureDeleteButton_" + i)) {
			index = i;
			break;
		}
	}
	if(index != -1) {
		styleAddPictureArray.splice(index, 1);
		drawStyleAddMainPicture();
	}
});

$("#topSearchButton").click(function() {
	doSearch();
});

function doSearch() {
	var keyword = $("#topSearchInput").val();
	location.replace("index.jsp?keyword="+keyword);
}

$(".navigationTopLogo").click(function() {
	location.replace("index");
});
