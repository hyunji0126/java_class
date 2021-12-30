<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set var="age" value="${param.age }" />
	
	<p>
		나이 : ${age}세 <br>
		<c:if test="${age >= 20}">
			<h2>당신은 성인입니다</h2>
		</c:if>
		<c:if test="${age < 20}">
			<h2>당신은 미성년자입니다</h2>
		</c:if>
	</p>
</body>
</html>