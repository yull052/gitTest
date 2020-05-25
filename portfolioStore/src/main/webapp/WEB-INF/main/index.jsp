<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Portfolio Store</title>
<link rel="stylesheet" type="text/css" href="css/index.css">
</head>
<body>
	
	<div class="mainContainerDiv">
		<div class="titleDiv">
			<label id="titleL"  style="cursor: pointer">
				PortfolioStore
			</label>
		</div>
		<!-- 세션에 아이디 없을 시 로그인 버튼 -->
		<c:if test="${userId eq null}">
			<div class="loginDiv">
					<input type="button" id="loginBtn" value="로그인">
			</div>
		</c:if>
		<!-- 세션에 아이디 있을 시 스토어메인으로 -->
		<c:if test="${userId ne null}">
			<c:redirect url="/storeMain.do"></c:redirect>
		</c:if>
	</div>
</body>
</html>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="js/index.js"></script>