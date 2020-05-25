
$(function(){
	//	id찾기, pwd찾기 구분
	var state;
	$('.findDiv').hide();
	
	$('.findBtn').on('click',function(){
		$('.btnDiv').hide();
		$('.findDiv').show();
		
		if($(this).attr('id')=='idBtn'){
			state='id';
			var html = '<input type="text" id="userName" name="userName" placeholder="이름 입력">';
			$('#text1L').append('이  름');
		}
		if($(this).attr('id')=='pwdBtn'){
			state='pwd';
			var html = '<input type="text" id="userId" name="userId" placeholder="아이디 입력">';
			$('#text1L').append('아이디');
		}
		var html2 = '<input type="text" id="userPhone" name="userPhone" placeholder="ex) 010-1234-1234">';
		$('.text1').append(html);
		$('.text2').append(html2);	
		$('#text2L').append('휴대폰');
	});
	
	//	userName 길이
	function getTextLength(str) {
        var len = 0;
        for (var i = 0; i < str.length; i++) {
            if (escape(str.charAt(i)).length == 6) {
                len++;
            }
            len++;
        }
        return len;
    }
	//	ajax
	function ajaxFn(){
		$.ajax({
			type : 'post',
			url  : '/portfolioStore/findInfo.do',
			data : {
				'userName' : $('#userName').val(),
				'userId' : $('#userId').val(),
				'userPhone' : $('#userPhone').val()
				},
			dataType : 'text',
			success : function(data){
				if(data == ''){
					alert('일치하는 정보가 없습니다');	
				}else{
					if(data == 'pwd'){
						location.href = '/portfolioStore/chanPwdForm.do?userId='+$('#userId').val();
					}else{
						alert('회원님의 아이디는 : '+ data + '입니다');
						location.href = '/portfolioStore/loginForm.do';
					}
				}
			}
		});
	}
	
	//	유효성 검사
	$('#findInfo').on('click',function(){
		if(state=='id'){
			if(($('#userName').val()!='')&&($('#userPhone').val()!='')){
				var nameLen = getTextLength($('#userName').val());
				if((nameLen<2)||(nameLen>6)){
					alert('2~6자 사이 한글만 입력해주세요');
				}else{
					var regExp = /(01[0|1|6|9|7])[-](\d{3}|\d{4})[-](\d{4}$)/g;
					if(regExp.test($('#userPhone').val())==false){
						alert('입력 형식에 맞춰주세요 ex) 010-1234-1234');
					}else{
						ajaxFn();
					}
				}	
			}else{
				alert('이름, 휴대폰을 입력해주세요');
			}
		}
		if(state=='pwd'){
			if(($('#userId').val()!='')&&($('#userPhone').val()!='')){
				var regExp = /^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$/;
				if(regExp.test($('#userId').val())==false) {
					alert('아이디는 영문자로 시작 (5~12)');
				}else{
					var regExp = /(01[0|1|6|9|7])[-](\d{3}|\d{4})[-](\d{4}$)/g;
					if(regExp.test($('#userPhone').val())==false){
						alert('입력 형식에 맞춰주세요 ex) 010-1234-1234');
					}else{
						ajaxFn();
					}
				}
			}else{
				alert('아이디, 휴대폰을 입력해주세요');
			}

		}
	});

});
