<%@ page import="com.example.zerobasemission1.NeedFile.Services" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<body>
<%
    String HistoryID = request.getParameter("id");

    Services service = new Services();

    service.DeleteOneHistory(HistoryID);
%>
</body>

<script>
    alert("위치 히스토리 데이터를 삭제하였습니다.");
    location.href = "history.jsp";
</script>

</html>
