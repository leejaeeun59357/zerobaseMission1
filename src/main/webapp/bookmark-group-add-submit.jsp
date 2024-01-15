<%@ page import="com.example.zerobasemission1.NeedFile.Services" %><%--
  Created by IntelliJ IDEA.
  User: com
  Date: 2024-01-14
  Time: 오후 5:15
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

    String bookmarkGroupName = request.getParameter("bookmarkGroupName");
    String seq = request.getParameter("seq");

    service.AddBookmarkGroup(bookmarkGroupName, seq);
%>

</body>
<script>
    alert("북마크 그룹 데이터를 추가하였습니다.");
    location.href="bookmark-group.jsp";
</script>
</html>
