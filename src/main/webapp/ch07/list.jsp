<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>


<!--  
Jakarta EE 에서 JSTL을 사용하려면
	1. Jakarta Standard Tag Library API
		jakarta.servlet.jsp.jstl-api-3.0.0.jar (45KB)
	2. org.glassfish.web Jakarta Standard Tag Library Implementation:
		jakarta.servlet.jsp.jstl-3.0.1.jar (3625 KB) -->

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>City List</title>
	<style>
		th, td { padding: 3px; }
	</style>
</head>
<body style="margin: 50px;">
	<h1>City List</h1>
	<hr>
	<table border="1">
		<tr><th>아이디</th><th>도시명</th><th>국가코드</th><th>지역명</th><th>인구수</th><tr>
		
	</table>
</body>
</html>