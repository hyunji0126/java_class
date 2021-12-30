<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/custom.css">
    
</head>
<body>
   <jsp:include page="include/header.jsp"/>
<section>
        <div class="container">
            <div class="row join-wrap">
                <!--join-form을 적용한다 float해제 margin:0 auto-->
                <div class="col-xs-12 col-md-9 join-form">
                    
                    <div class="titlebox">
                        MEMBER INFO                    
                    </div>
                    
                    <p>*표시는 필수 입력 표시입니다</p>
                  <form action="/Project01/update.user" method="post">
                      <table class="table">                  
                           <tbody class="m-control">
                               <tr>
                                   <td class="m-title">*ID</td>
                                   <td><input class="form-control input-sm" name="uId" value="${user.id}"></td>
                               </tr>
                               <tr>
                                   <td class="m-title">*이름</td>
                                   <td><input class="form-control input-sm" name="name" value="${user.name}"></td>
                               </tr>
                               <tr>
                                   <td class="m-title">*비밀번호</td>
                                   <td><input class="form-control input-sm" name="uPw" value="${user.pw}"></td>
                               </tr>
                               <tr>
                                   <td class="m-title">*비밀번호확인</td>
                                   <td><input class="form-control input-sm" name="uPwConfirm" value="${user.변수명}"></td>
                               </tr>
                               <tr>
                                   <td class="m-title">*E-mail</td>
                                   <td>
                                       <input class="form-control input-sm" name="email" value="${user.email}">@
                                       <select class="form-control input-sm sel">
                                           <option>naver.com</option>
                                           <option>gmail.com</option>
                                           <option>daum.net</option>
                                       </select>
                                       <button class="btn btn-info">중복확인</button>
                                   </td>
                               </tr>
                               <tr>
                                   <td class="m-title">*휴대폰</td>
                                   <td>
                                       <input class="form-control input-sm sel" name="contactNum" value="${user.contactNum}">
                               </tr>
                               <tr>
                                   <td class="m-title">*주소</td>
                                   <td><input class="form-control input-sm add" name="basicAddress" value="${user.basicAddress}"></td>
                               </tr>
                               <tr>
                                   <td class="m-title">*상세주소</td>
                                   <td><input class="form-control input-sm add" name="detailAddress" value="${user.detailAddress}"></td>
                               </tr>
                           </tbody>
                       </table>
                          <div class="titlefoot">
                              <button class="btn" >수정</button>
                              <button class="btn" onclick="location.href='user_mypage.jsp';">목록</button>
                          </div>
                          

	                    </form>
                </div>


            </div>

        </div>

    </section>
    
    
       <jsp:include page="include/footer.jsp"/>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="js/bootstrap.js"></script>
</body>
</html>