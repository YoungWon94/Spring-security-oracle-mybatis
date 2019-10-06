<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp"%>
<script type="text/javascript">
	function join() {
		location.href = "${path}/user/join.do"
	}
</script>
</head>
<body>
	<h1>로그인 페이지</h1>
	<span style="color: red;">${errMsg}</span>
	<!-- 

요청 url(login_check.do)은 
시큐리티 설정(security-context.xml)에서 지정한 
<form-loin> 에 login-processing-url="/user/login_check.do"와 맞춰줘야함

input name 역시 시큐리티에서 설정한 파라미터로 맞춰 줘야함. userid, passwd, _spring_security_remember_me

  -->
	<form action="${path}/user/login_check.do" method="post">
		<table>
			<tr>
				<td>아이디</td>
				<td><input name="userid"></td>
			</tr>
			<tr>
				<td>비번</td>
				<td><input type="password" name="passwd"> <input
					type="checkbox" name="_spring_security_remember_me">자동 로그인</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="로그인">
					<input type="button" value="회원가입" onclick="join()"></td>
			</tr>
		</table>
	</form>
</body>
</html>