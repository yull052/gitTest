$(function(){
	//	페이지 로드 시 유효성 검사 실행 위함
	jQuery(function(){
	   jQuery('#joinBtn').click();
	});
	
	//	아이디 체크 정규식
	$.validator.addMethod("idRegex",  function( value, element ) {
		return this.optional(element) ||  /^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$/.test(value);
	});
	
	//	비밀번호 체크 정규식
	$.validator.addMethod("pwdRegex",  function( value, element ) {
		return this.optional(element) ||  /^.*(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/.test(value);
	});
	
	//	휴대폰 체크 정규식
	$.validator.addMethod("phoneRegex",  function( value, element ) {
		return this.optional(element) ||  /(01[0|1|6|9|7])[-](\d{3}|\d{4})[-](\d{4}$)/g.test(value);
	});
	
	//	유효성 검사
    $("#joinForm").validate({
        //	규칙
        rules: {
            userId: {
                required : true,
                rangelength : [5, 12],
                idRegex : true,
                remote:{
                	url : "/portfolioStore/dupeId.do",
                	type : "post"
                }
            },
            userPwd: {
                required : true,
                pwdRegex : true,
                rangelength :[8, 15]
            },
            userRePwd: {
                required : true,
                equalTo : userPwd
            },
            userName: {
                required : true,
                rangelength :[2, 6]
            },
            userPhone: {
            	required : true,
            	maxlength : 13,
            	phoneRegex : true
            },
            userEmail: {
                email : true
            }
        },
        //	규칙체크 실패시 출력될 메시지
        messages : {
        	userId: {
                required : "필수로 입력해주세요",
                rangelength : "{0}~{1}자 사이로 입력해주세요",
                idRegex : "영문자로 시작해야합니다",
                remote : "존재하는 아이디입니다"
            },
            userPwd: {
                required : "필수로 입력해주세요",
                rangelength :"{0}~{1}자 사이로 입력해주세요",
                pwdRegex : "영문, 숫자, 특수문자를 포함해주세요"
            },
            userRePwd: {
                required : "필수로 입력해주세요",
                equalTo : "비밀번호가 일치하지 않습니다"
            },
            userName: {
                required : "필수로 입력해주세요",
                rangelength : "{0}~{1}자 사이로 입력해주세요"
            },
            userPhone: {
            	required : "필수로 입력해주세요",
            	maxlength : "잘못된 휴대폰 번호입니다",
            	phoneRegex : "-를 포함해주세요"
            },
            userEmail: {
                email : "메일규칙에 어긋납니다"
            }
        }
    });//validate
})