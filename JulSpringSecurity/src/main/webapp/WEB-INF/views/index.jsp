<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>인덱스 화면입니다.</h1>
	
	<sec:authorize access="!isAuthenticated()">
		<a href="/login">로그인</a>
	</sec:authorize>
	<sec:authorize access="isAuthenticated()">
	id : <sec:authentication property="principal.users.id"/> 
		<a href="/logout">로그아웃</a>
	</sec:authorize>
	<sec:authorize access="hasRole('ADMIN')">
		<a href="/admin">관리자 페이지</a>
	</sec:authorize>
</body>
</html>