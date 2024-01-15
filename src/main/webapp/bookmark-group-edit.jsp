<%@ page import="com.example.zerobasemission1.DTO.BookmarkGroupDTO" %>
<%@ page import="com.example.zerobasemission1.NeedFile.Services" %><%--
  Created by IntelliJ IDEA.
  User: com
  Date: 2024-01-02
  Time: 오후 8:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>와이파이 정보 구하기</title>
    <link href="Css/basicCss.css" rel="stylesheet" type="text/css">

</head>
<body>
<h1 style="font-weight: bolder">북마크 그룹 수정</h1>

<div>
    <a href="http://localhost:8080">홈</a>
    <label>|</label>
    <a href="http://localhost:8080/history.jsp">위치 히스토리 목록</a>
    <label>|</label>
    <a href="http://localhost:8080/load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
    <label>|</label>
    <a href="http://localhost:8080/bookmark-list.jsp">북마크 보기</a>
    <label>|</label>
    <a href="http://localhost:8080/bookmark-group.jsp">북마크 그룹 관리</a>
</div>
<br/>
<%
    Services service = new Services();
    String bookmarkNumber = request.getParameter("id");

    BookmarkGroupDTO bookmarkGroupDTO = service.SelectOneBookmarkGroup(bookmarkNumber);
%>

<form method="post" action="bookmark-group-edit-submit.jsp">
    <table>
        <tr>
            <th>북마크 이름</th>
            <td>
                <input type="text" name="bookmarkName" value="<%=bookmarkGroupDTO.getBookmarkName()%>">
            </td>
        </tr>
        <tr>
            <th>순서</th>
            <td>
                <input type="text" name="seq" value="<%=bookmarkGroupDTO.getBookmarkSeq()%>">
            </td>
        </tr>

        <tr>
            <td colspan='2'>
                <a href="bookmark-group.jsp">돌아가기</a>
                <label>|</label>
                <input type="submit" value="수정">
                <input type="hidden" name="id" value="<%=bookmarkGroupDTO.getBookmarkNumber()%>">
            </td>
        </tr>
    </table>

</form>

</body>
</html>
