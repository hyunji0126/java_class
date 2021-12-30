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
                <!--join-form�� �����Ѵ� float���� margin:0 auto-->
                <div class="col-xs-12 col-md-9 join-form">
                    
                    <div class="titlebox">
                        MEMBER INFO                    
                    </div>
                    
                    <p>*ǥ�ô� �ʼ� �Է� ǥ���Դϴ�</p>
                  <form action="/Project01/update.user" method="post">
                      <table class="table">                  
                           <tbody class="m-control">
                               <tr>
                                   <td class="m-title">*ID</td>
                                   <td><input class="form-control input-sm" name="uId" value="${user.id}"></td>
                               </tr>
                               <tr>
                                   <td class="m-title">*�̸�</td>
                                   <td><input class="form-control input-sm" name="name" value="${user.name}"></td>
                               </tr>
                               <tr>
                                   <td class="m-title">*��й�ȣ</td>
                                   <td><input class="form-control input-sm" name="uPw" value="${user.pw}"></td>
                               </tr>
                               <tr>
                                   <td class="m-title">*��й�ȣȮ��</td>
                                   <td><input class="form-control input-sm" name="uPwConfirm" value="${user.������}"></td>
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
                                       <button class="btn btn-info">�ߺ�Ȯ��</button>
                                   </td>
                               </tr>
                               <tr>
                                   <td class="m-title">*�޴���</td>
                                   <td>
                                       <input class="form-control input-sm sel" name="contactNum" value="${user.contactNum}">
                               </tr>
                               <tr>
                                   <td class="m-title">*�ּ�</td>
                                   <td><input class="form-control input-sm add" name="basicAddress" value="${user.basicAddress}"></td>
                               </tr>
                               <tr>
                                   <td class="m-title">*���ּ�</td>
                                   <td><input class="form-control input-sm add" name="detailAddress" value="${user.detailAddress}"></td>
                               </tr>
                           </tbody>
                       </table>
                          <div class="titlefoot">
                              <button class="btn" >����</button>
                              <button class="btn" onclick="location.href='user_mypage.jsp';">���</button>
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