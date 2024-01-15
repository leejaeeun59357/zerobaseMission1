<%@ page import="com.example.zerobasemission1.NeedFile.Services" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    Services service = new Services();

    String bookmarkNumber = request.getParameter("id");

    service.DeleteOneBookmarkGroup(bookmarkNumber);
%>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<body>
</body>

<script>

alert("북마크 그룹 데이터를 삭제하였습니다.");
location.href="bookmark-group.jsp";


</script>

</html>
