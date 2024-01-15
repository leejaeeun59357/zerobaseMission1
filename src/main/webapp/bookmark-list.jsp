<%@ page import="com.example.zerobasemission1.NeedFile.Services" %>
<%@ page import="com.example.zerobasemission1.DTO.BookmarkDTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link href="Css/basicCss.css" rel="stylesheet" type="text/css">

</head>
<body>

<h1 style="font-weight: bolder">북마크 목록</h1>

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

<div>
    <table>
        <tr>
            <th>ID</th>
            <th>북마크 이름</th>
            <th>와이파이명</th>
            <th>등록일자</th>
            <th>비고</th>
        </tr>
        <%
            Services service = new Services();
            List<BookmarkDTO> bookmarklist = service.SelectAllBookmark();
            if (bookmarklist.size() == 0) {
        %>
        <tr>
            <td colspan="5">
                데이터가 존재하지 않습니다.
            </td>
        </tr>
        <%
            } else {

                for(BookmarkDTO item : bookmarklist) {
        %>
        <tr>
            <td><%=item.getBookmarkNumber()%></td>
            <td><%=item.getBookmarkName()%></td>
            <td><%=item.getWifiName()%></td>
            <td><%=item.getBookmarkRegistDate()%></td>
            <td>
                <a href="bookmark-delete.jsp?id=<%=item.getBookmarkNumber()%>">
                    삭제
                </a>
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
