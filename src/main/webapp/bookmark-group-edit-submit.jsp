<%@ page import="com.example.zerobasemission1.NeedFile.Services" %><%--
  Created by IntelliJ IDEA.
  User: com
  Date: 2024-01-14
  Time: 오후 9:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<body>

<%
    request.setCharacterEncoding("UTF-8");

    Services service = new Services();

    String bookmarkName = request.getParameter("bookmarkName");
    String bookmarkSeq = request.getParameter("seq");
    String bookmarkNumber = request.getParameter("id");

    service.EditBookmarkGroup(bookmarkNumber, bookmarkName, bookmarkSeq);
%>

</body>

<script>

    alert("북마크 그룹 데이터를 수정하였습니다.");
    location.href = "bookmark-group.jsp"

</script>
</html>
