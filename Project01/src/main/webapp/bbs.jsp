<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/custom.css">
        <title>BBS Test</title>
        
        <!--게시판만 적용되는 css-->            
        <style>

            .table-striped > tbody > tr {
                background-color: rgba(255, 255, 255)
            }
            .row h2 {
                color: aliceblue;
                
            }
            .pagination-sm {
                margin: 0;
            }
            
        </style>
    </head>

    <body>
       <jsp:include page="include/header.jsp"/>
    <section>
        
        <div class="container">
            <div class="row">
                
                <h2>게시판 목록</h2>
                <table class="table table-striped" style="text-align: center; border: 2px solid #737373">
                    <thead>
                        <tr>
                            <th style="background-color: #9DCAFF; text-align: center;">번호</th>
                            <th style="background-color: #9DCAFF; text-align: center;">제목</th>
                            <th style="background-color: #9DCAFF; text-align: center;">작성자</th>
                            <th style="background-color: #9DCAFF; text-align: center;">작성일</th>
                        </tr>
                    </thead>
                    

               <tbody>
                    	<c:forEach var="b" items="${boardList}">
	                        <tr>
	                            <td>${b.boardId}</td>
	                            <td>
	                            	<a href="/Project01/content.board?bId=${b.boardId}&page=${param.page}&countPerPage=${param.countPerPage}">${b.title}</a>
		                            &nbsp;&nbsp;
		                            <c:if test="${b.newMark}">
	                            	<img alt="newMark" src="/Project01/images/icon_new.gif">
	                            </c:if>
	                            </td>
	                            <td>${b.author}</td>
	                            <td>
	                            	<fmt:formatDate value="${b.regDate}" pattern="yyyy-MM-dd hh:mm"/>
	                            </td>
	                        </tr>
                        </c:forEach>
                    </tbody>
			</table>
			<%-- 페이징 처리하는 구간 --%>
                <div class="text-center">
                    <ul class="pagination pagination-sm">
                    
                    <%-- 이전 버튼 --%>
							<c:if test="${pc.prev}">
								<li class="page-item"><a class="page-link"
									href="/Project0/list.board?page=${pc.beginPage-1}&countPerPage=${pc.paging.countPerPage}"
									style="background-color: #643691; margin-top: 0; height: 40px; color: white; border: 0px solid #f78f24; opacity: 0.8">이전</a>
								</li>
							</c:if>

							<%-- 페이지 버튼 --%>
							<c:forEach var="pageNum" begin="${pc.beginPage}"
								end="${pc.endPage}">
								<li class="page-item"><a
									href="/Project01/list.board?page=${pageNum}&countPerPage=${pc.paging.countPerPage}"
									class="page-link"
									style="margin-top: 0; height: 40px; color: pink; border: 1px solid #643691; ${pageNum == pc.paging.page ? 'background-color: orange;' : ''}">${pageNum}</a>
								</li>
							</c:forEach>


							<%-- 다음 버튼 --%>
						<c:if test="${pc.next} ">
							<li class="page-item"><a class="page-link" href="/Project01/list.board?page=${pc.endPage+1 }&countPerPage=${pc.paging.countPerPage}"
								style="background-color: #643691; margin-top: 0; height: 40px; color: white; border: 0px solid #f78f24; opacity: 0.8">다음</a>
							</li>
                     </c:if>
                    </ul>
                    <button class="btn btn-info pull-right" onclick="location.href='/Proejct01/write.board';">글쓰기</button>
                </div>
                
            </div>
        </div>
        
        <div>
        	<form action="/Project01/search.board" align="center">
        		<input type="text" name="search" placeholder="제목을 입력하세요." style="border-radius: 5px; width: 300px; height: 25px">
        		<input type="submit" value="검색" style="border-radius: 5px; width: 50px; height: 25px">
        	</form>
        </div>
    </section>
    
        
    <jsp:include page="include/footer.jsp"/>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="js/bootstrap.js"></script>

    </body>

</html>
