
$(function(){
	$('#changBtn').on('click',function(){
		if(($('#userPwd').val()!='')&&($('#userRePwd').val()!='')){
			var regExp = /^.*(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
			if(regExp.test($('#userPwd').val())==false){
				alert('영문, 숫자, 특수문자 포함 (8~15)');
			}else{
				if($('#userPwd').val()!=$('#userRePwd').val()){
					alert('재확인 비밀번호가 다릅니다');
				}else{
					$.ajax({
						type : 'post',
						url  : '/portfolioStore/chanPwd.do',
						data : {
							'userId' : $('#userId').val(),
							'userPwd' : $('#userPwd').val()
							},
						dataType : 'text',
						success : function(data){
									alert('비밀번호가 변경되었습니다');
									location.href = '/portfolioStore/loginForm.do';
								}
					});
				}
			}
		}else{
			alert('비밀번호를 입력해주세요');
		}
	});
});
