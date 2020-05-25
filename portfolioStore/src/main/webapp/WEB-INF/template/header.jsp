<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="headerDiv">
	<div class="labelDiv">
		<label>Portfolio Store</label>
	</div>

	<div class="loginDiv">
		
		<!-- 세션에 아이디 있을 시 storeMain 화면 -->
		<c:if test="${userId ne null}">
			<label >${userName }님 <label>&emsp;
			<input type="button" value="로그아웃" onclick="location.href='/portfolioStore/kakaoLogout.do'">
		</c:if>
	</div>

	<div class="menuDiv">
		<ul>
			<li>
				<a>홈</a>
				<a>랭킹</a>
				<a>카테고리</a>
			</li>
		</ul>
	</div>
</div>