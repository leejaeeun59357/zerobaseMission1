<%@ page import="com.example.zerobasemission1.NeedFile.Services" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Services service = new Services();
    request.setAttribute("totalCount",service.insertWifis());
%>

<html>
<head>
    <title>와이파이 정보 구하기</title>
    <style>
        #center {
            text-align: center;
        }
    </style>
</head>
<body>
<div id="center">
    <h1>${totalCount}개의 WIFI 정보를 정상적으로 저장하였습니다.</h1>
    <a href="index.jsp">홈 으로 가기</a>
</div>
</body>
</html>