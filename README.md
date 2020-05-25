# gitTest
로그인, 비밀번호 암호화, 회원가입 구현 

# 사용 방법
DB => id : java, pw : 1234
      
table =>    
create table test(   
userId varchar(30),   
userPwd varchar(60) not null,   
userName varchar(30) not null,   
userPhone varchar(30) not null,   
userEmail varchar(30) not null,   
PRIMARY KEY(userId)   
);
