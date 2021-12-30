<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>
		# data1 : <%=session.getAttribute("data1") %> <br>
		# data2 : <%=application.getAttribute("data2") %>
	</p>
	
	<hr>
	
	<%--
		* EL에서 데이터를 참조할 때 영역을 명시하지 않는다면
		page -> request -> session -> application
		순서대로 검색하여 찾아내 표현합니다.
	--%>
	
	<p>
		# data1 :  ${data1} <br>
		# data2 :  ${applicationScope.data2}
		<%--scope를 쓰면 위치명시함 fm은 사용하지만 생략가능 단,데이터 이름이 겹칠때는 사용함  --%>
	</p>
		
</body>
</html>