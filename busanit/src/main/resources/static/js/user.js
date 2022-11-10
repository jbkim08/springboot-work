
/**
 * 
 */
 $(document).ready(function(){
	//User Info Update
	$("#btnUserUpdate").click(function(){
		var data = {
			"username":$("#username").val(),
			"password":$("#password").val(),
			"nickname":$("#nickname").val()
		}
		$.ajax({
			type:"post",
			url:"/member/update",
			contentType:"application/json;charset=utf-8",
			data:JSON.stringify(data)
		})
		.done(function(res){
			if(res == "fail"){
				alert("이미 사용중인 별명입니다. 다른 별명을 입력해주세요.");
				$("nickname").focus()
			}else{
				alert("회원정보를 성공적으로 수정했습니다.");
				//sessionStorage.clear();
				location.href="/"
			}
			/*if(res == "success"){
				alert("회원정보를 성공적으로 수정했습니다.");
				location.href = "/"
			}else{
				alert("이미 사용 중인 별명입니다. 다른 별명을 입력해주세요.");
				$("nickname").focus()	
			}*/
		})
		.fail(function(e){
			alert("error"+e);
			location.href="/member/detail"
		})
	});
	
	//Join
	$("#btnJoin").click(function(){
		if($("#username").val() == ""){
			alert("아이디를 입력해주세요");
			$("#username").focus();
			return false;
		}
		if($("#password").val() == ""){
			alert("비밀번호를 입력해주세요");
			$("#password").focus();
			return false;
		}
		if($("#pass_check").val() == ""){
			alert("비밀번호를 한번더 입력해주세요");
			$("#pass_check").focus();
			return false;
		}
		if($("#password").val() != $("#pass_check").val()){
			alert("비밀번호가 일치하지 않습니다.");
			$("#pass_check").focus();
			return false;
		}
		if($("#nickname").val() == ""){
			alert("별명을 입력해주세요!");
			$("#nickname").focus();
			return false;
		}
		var data = {			
			"username":$("#username").val(),  //id가 id의 값(val())을 "id"에
			"password":$("#password").val(),
         	"nickname":$("#nickname").val(),
         	"role":$("#role").val()
		}
		$.ajax({
			type:"post",
			url:"/member/joinProc",
			contentType:"application/json;charset=utf-8",
			data:JSON.stringify(data)
		})
		.done(function(res){ //res는 return되는 값 fail 또는 success
			if (res == "success") {
				alert("회원가입을 축하합니다!");
				location.href="/member/login"
			} else if (res == "failForId"){
				alert("아이디 중복확인하세요!");
				$("#username").focus();
			} else if (res == "failForNick"){
				alert("중복된 닉네임입니다. 다른 닉네임을 사용해주세요!");
				$("#nickname").focus();
			}
		})
		$("#frm").submit();
	})
	//아이디중복체크
	$("#btnIdCheck").click(function(){
		if($("#username").val() == ""){
			alert("아이디를 입력하세요");
			return;
		}
		$.ajax({
			type:"post",
			url:"idDupCheck", //method
			data:{"username" : $("#username").val()},
			success : function(resp){
				if(resp.trim() == "false"){
					alert("사용가능한 아이디입니다.");
					$(opener.document).find("#username").val($("#username").val());
					self.close();
				}else{
					alert("사용 불가능한 아이디입니다!");
					$("#username").val("");
					$("#username").focus();
				}
			},
			error:function(e){
				alert("ERROR : "+ e);
			}
		})
	});
});