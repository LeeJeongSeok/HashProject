function showLoginPopup() {
	closePopup();
	showTinyBackground();
	$("#loginPopup").css("display", "block");
	$("#loginEmailInput").val("");
	$("#loginPasswordInput").val("");
	$("#loginEmailInput").focus();
}

function showJoinPopup() {
	closePopup();
	showTinyBackground();
	$("#joinPopup").css("display", "block");
	
	$("#joinOpenType").val("join");
	$("#joinPopupTitle").text("회원가입");
	$(".joinConfirmButton").text("가입");
	
	$("#joinPicture").attr("src", "images/ico_person.png");
	$("#joinDescriptionInput").val("");
	$("#joinInstagramInput").val("");
	$("#joinEmailInput").val("");
	$("#joinEmailInput").attr("readonly", false);
	
	$("#joinPasswordInput").val("");
	$("#joinRetryPasswordInput").val("");
	$("#joinPasswordInput").attr("placeholder", "비밀번호를 입력해 주세요.");
	$("#joinRetryPasswordInput").attr("placeholder", "비밀번호를 한번 더 입력해 주세요.");
	$("#joinNameInput").val("");
	
	$(".joinCheckArea").find(".check").removeClass("checkPress");
	$(".joinCheckAreaMan").find(".check").addClass("checkPress");
	$("#joinHeightInput").val("");
	$("#joinWeightInput").val("");
	$("#joinEmailInput").focus();
}

function showMyInformationPopup(item) {
	closePopup();
	showTinyBackground();
	$("#joinPopup").css("display", "block");
	
	$("#joinOpenType").val("edit");
	$("#joinPopupTitle").text("내 정보 수정");
	$(".joinConfirmButton").text("저장");
	
	var pictureUrl = item.pictureUrl;
	if(String(pictureUrl).length > 0) {
		$("#joinPicture").attr("src", pictureUrl);
	} else {
		$("#joinPicture").attr("src", "images/ico_person.png");
	}
	
	
	$("#fileUrlInputForJoin").val(pictureUrl);
	$("#joinDescriptionInput").val(item.description);
	$("#joinInstagramInput").val(item.instagramId);
	$("#joinEmailInput").val(item.email);
	$("#joinEmailInput").attr("readonly", true);
	
	$("#joinPasswordInput").val("");
	$("#joinRetryPasswordInput").val("");
	$("#joinPasswordInput").attr("placeholder", "변경시에만 입력");
	$("#joinRetryPasswordInput").attr("placeholder", "변경시에만 입력");
	
	$("#joinNameInput").val(item.name);
	
	$(".joinCheckArea").find(".check").removeClass("checkPress");
	var sex = item.sex;
	if(sex == "W") {
		$(".joinCheckAreaWoman").find(".check").addClass("checkPress");
	} else {
		$(".joinCheckAreaMan").find(".check").addClass("checkPress");
	}
	
	$("#joinHeightInput").val(item.height);
	$("#joinWeightInput").val(item.weight);
	$("#joinNameInput").focus();
}

function checkLogin() {
	var emailElement = $("#loginEmailInput");
	var passwordElement = $("#loginPasswordInput");

	var _email = emailElement.val();
	var _password = passwordElement.val();
	if(String(_email).length == 0) {
		showAlert("아이디를 입력해 주세요.", function() {
			emailElement.focus();
		});
		return;
	}

	if(String(_password).length == 0) {
		showAlert("비밀번호를 입력해 주세요.", function() {
			passwordElement.focus();
		});
		return;
	}
	if(String(_password).length < 4) {
		showAlert("비밀번호를 4자리이상 입력해 주세요.", function() {
			passwordElement.focus();
		});
		return;
	}

	var param = {
		kind:"login",
		email:_email,
		password:_password
	};
	jQuery.ajax({
		type:"POST",
		url:("/login"),
		contentType: "application/json; charset=utf-8",
		dataType:"JSON",
		data:JSON.stringify(param),
		timeout:20000,
		success:function(data) {
			// 씨발 어이가없네;; 그냥 데이터를 string으로 변환하면 끝나는거네;
			// var json = "'"+JSON.stringify(data)+"'";
			// console.log(json);

			if(data.isConfirm == "Y") {
				location.replace("/index");
			} else {
				showAlert("아이디 또는 비밀번호가 일치하지 않습니다.", function() {
					passwordElement.focus();
				});
			}
		}, complete:function(data) {
		}, error:function(xhr, status, error) {
		}
	});
}


function checkJoin() {
	var joinOpenType = $("#joinOpenType").val();
	
	var _pictureUrl = $("#fileUrlInputForJoin").val();
	var _description = $("#joinDescriptionInput").val();
	var _instagramId = $("#joinInstagramInput").val();
	
	
	var _email = $("#joinEmailInput").val();
	var _password = $("#joinPasswordInput").val();
	var _retryPassword = $("#joinRetryPasswordInput").val();
	if(String(_email).length == 0) {
		showAlert("이메일을 입력해 주세요.", function() {
			$("#joinEmailInput").focus();
		});
		return;
	}
	
	if(joinOpenType == "edit") {
		if(String(_password).length > 4 && String(_retryPassword) != String(_password)) {
			showAlert("비밀번호가 일치하지 않습니다. 비밀번호를 확인해 주세요.", function() {
				$("#joinRetryPasswordInput").focus();
			});
			return;
		}
	} else {
		if(String(_password).length < 4) {
			showAlert("비밀번호를 4자리 이상 입력해 주세요.", function() {
				$("#joinPasswordInput").focus();
			});
			return;
		}
		
		if(String(_retryPassword) != String(_password)) {
			showAlert("비밀번호가 일치하지 않습니다. 비밀번호를 확인해 주세요.", function() {
				$("#joinRetryPasswordInput").focus();
			});
			return;
		}
	}
	

	var _name = $("#joinNameInput").val();
	if(String(_name).length == 0) {
		showAlert("이름을 입력해 주세요.", function() {
			$("#joinNameInput").focus();
		});
		return;
	}
	
	var _sex = "M";
	if($(".joinCheckAreaWoman").find(".check").hasClass("checkPress")) {
		_sex = "W";
	}
	
	var _height = $("#joinHeightInput").val();
	var _weight = $("#joinWeightInput").val();
	
	if(joinOpenType == "edit") {
		updateUser(_email, _password, _name, _sex, _height, _weight, _pictureUrl, _description, _instagramId);
	} else {
		join(_email, _password, _name, _sex, _height, _weight, _pictureUrl, _description, _instagramId);
	}
}

function join(_email, _password, _name, _sex, _height, _weight, _pictureUrl, _description, _instagramId) {
	showLoadingBar();
	var param = {
		kind:"join",
		email:_email,
		password:_password,
		name:_name,
		sex:_sex,
		height:_height,
		weight:_weight,
		pictureUrl:_pictureUrl,
		description:_description,
		instagramId:_instagramId
	};
	jQuery.ajax({
		type:"POST",
		url:("/join"),
		contentType: "application/json; charset=utf-8",
		dataType:"JSON",
		data:JSON.stringify(param),
		timeout:20000,
		success:function(data) {
			hideLoadingBar();
			if(data.isConfirm == "Y") {
				showAlert("회원가입이 완료되었습니다. 로그인 해주세요.", function() {
					closePopup();
					showLoginPopup();
					$("#loginEmailInput").val(_email);
					$("#loginPasswordInput").focus();
				});
			} else {
				if(data.isExistUser == "Y") {
					showAlert("중복된 회원이 존재하여, 등록에 실패했습니다.", null);
				} else {
					showAlert("회원 등록에 실패했습니다.", null);
				}
			}
		}, complete:function(data) {
		}, error:function(xhr, status, error) {
		}
	});
}

function updateUser(_email, _password, _name, _sex, _height, _weight, _pictureUrl, _description, _instagramId) {
	showLoadingBar();
	var param = {
		kind:"updateUser",
		email:_email,
		password:_password,
		name:_name,
		sex:_sex,
		height:_height,
		weight:_weight,
		pictureUrl:_pictureUrl,
		description:_description,
		instagramId:_instagramId
	};
	jQuery.ajax({
		type:"POST",
		url:("/updateUser"),
		contentType: "application/json; charset=utf-8",
		dataType:"JSON",
		data:JSON.stringify(param),
		timeout:20000,
		success:function(data) {
			hideLoadingBar();
			if(data.isConfirm == "Y") {
				showAlert("내 정보가 수정되었습니다.", function() {
					location.reload();
				});
			} else {
				showAlert("내 정보 수정에 실패했습니다. 다시 시도해 주세요.", null);
			}
		}, complete:function(data) {
		}, error:function(xhr, status, error) {
		}
	});
}

$(".joinText").click(function() {
	showJoinPopup();
});


$(".joinPictureCameraButton").click(function() {
	$("#fileUploadInputForJoin").trigger("click");
});
function uploadFileForJoin(input) {
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
					
					$("#fileUrlInputForJoin").val(fileUrl);
					$("#joinPicture").attr("src", fileUrl);
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

$(".joinCheckArea").click(function() {
	$(".joinCheckArea").find(".check").removeClass("checkPress");
	var selfElement = $(this);
	selfElement.find(".check").addClass("checkPress");
});