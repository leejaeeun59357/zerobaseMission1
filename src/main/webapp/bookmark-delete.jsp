<%@ page import="com.example.zerobasemission1.DTO.BookmarkDTO" %>
<%@ page import="com.example.zerobasemission1.NeedFile.Services" %><%--
  Created by IntelliJ IDEA.
  User: com
  Date: 2024-01-14
  Time: 오후 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link href="Css/basicCss.css" rel="stylesheet" type="text/css">

</head>
<body>

<h1 style="font-weight: bolder">북마크 삭제</h1>

<div>
    <a href="index.jsp">홈</a>
    <label>|</label>
    <a href="history.jsp">위치 히스토리 목록</a>
    <label>|</label>
    <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
    <label>|</label>
    <a href="bookmark-list.jsp">북마크 보기</a>
    <label>|</label>
    <a href="bookmark-group.jsp">북마크 그룹 관리</a>
</div>

<h3>북마크를 삭제하시겠습니까?</h3>
<br/>
<%
    Services service = new Services();

    String bookmarkNumber = request.getParameter("id");

    BookmarkDTO bookmark = service.SelectOneBookmark(bookmarkNumber);
%>

<form method="post" action="bookmark-delete-submit.jsp">
    <table>
        <tr>
            <th>북마크 이름</th>
            <td><%=bookmark.getBookmarkName()%></td>
        </tr>
        <tr>
            <th>와이파이명</th>
            <td><%=bookmark.getWifiName()%></td>
        </tr>
        <tr>
            <th>등록일자</th>
            <td><%=bookmark.getBookmarkRegistDate()%></td>
        </tr>
        <tr>
            <td colspan="2">
                <a href="bookmark-list.jsp">돌아가기</a>
                <input type="submit" name="delete" value="삭제">
                <input type="hidden" name="bookmarkNumber" value="<%=bookmark.getBookmarkNumber()%>">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
