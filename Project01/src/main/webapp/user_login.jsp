<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/custom.css">
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="js/bootstrap.js"></script>
    <title>BBS Test</title>
    
    <!--login�� ����Ǵ� css-->   
    <style>
        .table-striped>tbody>tr {
            background-color: #f9f9f9
        }

        .join-form {
            margin: 100px auto;
            padding: 20px;
            width: 50%;
            float: none;
            background-color: white;
        }
    </style>
</head>

<body>
   <jsp:include page="include/header.jsp"/>
    <section>
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-9 col-sm-12 join-form">
                    <h2>�α���<small>(�������)</small></h2>
                    
                    
                    <form action="/Project01/login.user" method="post">
                        <div class="form-group">
                            <label for="id">���̵�</label>
                            <input type="text" name="uId" class="form-control" id="id" placeholder="���̵�">
                        </div>
                        <div class="form-group">
                            <label for="password">��й�ȣ</label>
                            <input type="password" name="uPw" class="form-control" id="password" placeholder="��й�ȣ ">
                        </div>
                        
                        <div class="form-group">
                            <button type="button" class="btn btn-lg btn-success btn-block" onclick="location.href='user_join.jsp';">ȸ������</button>
                        </div>

                        <div class="form-group">
                            <button type="submit" class="btn btn-lg btn-info btn-block" >�α���</button>
                        </div>

                    </form>
                </div>
            </div>
        </div>


    </section>

    <jsp:include page="include/footer.jsp"/>

</body>

</html>