<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
						게시판 상세보기<small>(디자인이궁금하세요?)</small>
					</h2>

					<form action="modify.board?bId=${vo.boardId}">
						<div class="form-group">
							<label>등록일</label> <input type="text" class="form-control"
								value="<fmt:formatDate value='${vo.regDate}'
                              pattern="yy년 MM월 dd일 a hh시 mm분" />"
								readonly style="color: red">
						</div>
						<div class="form-group">
							<label>글번호</label> <input name="bId" type="text"
								class="form-control" value="${vo.boardId}" readonly>
						</div>
						<div class="form-group">
							<label>글쓴이</label> <input type="text" class="form-control"
								value="${vo.author}" readonly>
						</div>
						<div class="form-group">
							<label>제목</label> <input type="text" class="form-control"
								value="${vo.title}" readonly>
						</div>
						<div class="form-group">
							<label>내용</label>
							<textarea class="form-control" rows="5" readonly>${vo.content}</textarea>
						</div>

						<div class="form-group">
							<button type="button" class="btn btn-success"
								onclick="location.href='/Project01/list.board'">목록</button>
							<button type="submit" class="btn btn-info" >수정</button>
						</div>

					</form>
				</div>
			</div>
		</div>


	</section>

	<jsp:include page="include/footer.jsp" />

</body>
</html>