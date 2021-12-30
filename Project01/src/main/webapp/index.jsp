<%@page import="kr.co.jsp.board.model.BoardDAO"%>
<%@page import="kr.co.jsp.board.model.BoardVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.co.jsp.user.model.UserVO"%>
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
        
           
        <!--메인만 적용되는 css영역-->   
        <style>
            body {
                background-image: url(images/main.jpg);
                background-repeat: no-repeat;
                background-size: cover
            }
        </style>
    </head>

    <body>
   <jsp:include page="include/header.jsp"/>

    
    <section>
    
    <div class="container" id="mainCon">
        <div class="jumbotron" id="jbDiv">
            <div class="container" id="introArea">
                <h1>게시판 사이트</h1>
                <p>김규환 김연지 김현지</p>
                <p>글을 써도 되고, 사진을 넣어도 되는 영역 입니다.</p>

            </div>

        </div>
       
        <div class="col-md-6 col-xs-12" style="padding: 0px 0px;">
            <h2 style="color:black">공지사항</h2>
            <table class="table table-striped" style="text-align: center; border: 2px solid #737373">
                <thead>
                    <tr>
                        <th style="background-color: #9DCAFF; text-align: center;">번호</th>
                        <th style="background-color: #9DCAFF; text-align: center;">제목</th>
                        <th style="background-color: #9DCAFF; text-align: center;">작성자</th>
                        <th style="background-color: #9DCAFF; text-align: center;">작성일</th>
                    </tr>
                </thead>
                
                <%List<BoardVO> mainList = BoardDAO.getInstance().searchBoard("공지", "title");;%>
                <%for(BoardVO content : mainList) { %>
                    <tr>
                        <td><%=content.getBoardId()%></td>
                        <td>
                        	<a href="/Project01/content.board?bId=<%=content.getBoardId()%>"><%=content.getTitle() %></a>
                        	<%long now = System.currentTimeMillis(); 
                        		long regTime = content.getRegDate().getTime();
                        		if(now - regTime < 60 * 60 * 24 * 1000) { %>
									<img alt="newMark" src="/Project/images/new.gif">
                        	<% } %>
                        </td>
                        <td><%=content.getAuthor()%></td>
                        <td><%=content.getRegDate() %></td>
                    </tr>
                <% } %>
                </tbody>
            </table>
        </div>
        
    </div>
    </section>
    <jsp:include page="include/footer.jsp"/>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="js/bootstrap.js"></script>
    
    </body>
    
    

</html>
