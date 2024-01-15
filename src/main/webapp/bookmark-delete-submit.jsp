<%@ page import="com.example.zerobasemission1.NeedFile.Services" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");

    String bookmarkNumber = request.getParameter("bookmarkNumber");

    Services service = new Services();

    service.DeleteOneBookmark(bookmarkNumber);
%>
</body>
<script>

    alert("북마크 데이터를 삭제하였습니다.");
    location.href = "bookmark-list.jsp";

</script>
</html>
