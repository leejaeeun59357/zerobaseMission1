<%--
  Created by IntelliJ IDEA.
  User: com
  Date: 2024-01-02
  Time: 오후 9:11
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
<br/>

<form method="post" action="bookmark-group-add-submit.jsp">
    <table>
        <tr>
            <th>북마크 이름</th>
            <td>
                <input type="text" name="bookmarkGroupName" id="bookmarkGroupName">
            </td>
        </tr>

        <tr>
            <th>순서</th>
            <td>
                <input type="text" name="seq">
            </td>
        </tr>

        <tr>
            <td colspan='2'>
                <input type="submit" value="추가">
            </td>
        </tr>

    </table>
</form>

</body>
</html>
