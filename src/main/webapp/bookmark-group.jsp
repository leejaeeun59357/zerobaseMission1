<%@ page import="com.example.zerobasemission1.NeedFile.Services" %>
<%@ page import="com.example.zerobasemission1.DTO.BookmarkGroupDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link href="Css/basicCss.css" rel="stylesheet" type="text/css">
</head>
<body>
<h1 style="font-weight: bolder">북마크 그룹
</h1>

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
<div>
    <button onclick="location.href='bookmark-group-add.jsp'">북마크 그룹 이름 추가</button>
</div>
<br/>

<div>
    <table>
        <tr>
            <th>ID</th>
            <th>북마크 이름</th>
            <th>순서</th>
            <th>등록일자</th>
            <th>수정일자</th>
            <th>비고</th>
        </tr>

        <%
            Services service = new Services();

            List<BookmarkGroupDTO> bookmarkGroupList = service.SelectAllBookmarkGroup();

            if (bookmarkGroupList.size() == 0) {
        %>
        <tr>
            <td colspan="6">데이터가 존재하지 않습니다.</td>
        </tr>
        <%
        } else {
            for (BookmarkGroupDTO item : bookmarkGroupList) {
        %>
        <tr>
            <td><%=item.getBookmarkNumber()%></td>
            <td><%=item.getBookmarkName()%></td>
            <td><%=item.getBookmarkSeq()%></td>
            <td><%=item.getBookmarkRegistDate()%></td>
            <td><%=item.getBookmarkEditDate()%></td>
            <td>
                <a href="bookmark-group-edit.jsp?id=<%=item.getBookmarkNumber()%>">수정</a>
                <a href="bookmark-group-delete.jsp?id=<%=item.getBookmarkNumber()%>">삭제</a>
            </td>
        </tr>
<%
    }
    }
%>

    </table>
</div>

</body>
</html>
