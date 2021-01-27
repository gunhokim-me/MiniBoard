<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/common/requireQuery.jsp" %>
<link href="./css/login.css" rel="stylesheet" type="text/css" >
<title>Login</title>
</head>
<body>
	<div class="wrapper fadeInDown">
		<div id="formContent">
			<!-- Tabs Titles -->
			<div><br><br></div>

			<!-- Login Form -->
			<form action="${cp }/login" method="POST">
				<input type="text" id="login" class="fadeIn second" name="userid"	placeholder="아이디" value="test">
				<input type="password" id="password"	class="fadeIn third" name="pass" placeholder="비밀번호" value="test">
				<input type="submit" class="fadeIn fourth" value="Log In">
			</form>

			<!-- Remind Passowrd -->
			<div id="formFooter">
				<a class="underlineHover" href="#">아이디 저장하기</a>
			</div>

		</div>
	</div>
</body>
</html>