<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
Enumeration<String> attributes = request.getSession().getAttributeNames();
while (attributes.hasMoreElements()) {
    String attribute = (String) attributes.nextElement();
    System.err.println(attribute+" : "+request.getSession().getAttribute(attribute));
}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">

<title>BBS Test</title>

</head>
<body>
	<jsp:include page="include/header.jsp" />
	<section>
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-md-10 col-sm-12 join-form">
					<h2>
						게시판 수정<small>(디자인이궁금하세요?)</small>
					</h2>

					<form action="/Project01/update.board" method="post">
						<div class="form-group">
							<label>글번호</label> <input type="text" name="boardId"
								value="${modi.boardId}" class="form-control" READONLY>
						</div>
						<div class="form-group">
							<label>글쓴이</label> <input type="text" name="author"
								value="${modi.author}" class="form-control" placeholder="자유"
								READONLY>
						</div>
						<div class="form-group">
							<label>제목</label> <input type="text" name="title"
								value="${modi.title}" class="form-control" placeholder="자유">
						</div>
						<div class="form-group">
							<label>내용</label>
							<textarea class="form-control" name="content" rows="5"></textarea>
						</div>

						<!--구현로직: 버튼은 온클릭을 사용하던 자바스크립트를 이용해야 합니다-->
						<div class="form-group">
							<button type="button" class="btn btn-success"
								onclick="location.href='list.board';">목록</button>
							<button type="submit" class="btn btn-info">수정요청</button>
							<button type="button" class="btn btn-default"
								onclick="location.href='/Project01/delete.board?bId=${modi.boardId}';">삭제</button>
						</div>

					</form>
				</div>
			</div>
		</div>


	</section>
	<jsp:include page="include/footer.jsp" />


</body>
</html>