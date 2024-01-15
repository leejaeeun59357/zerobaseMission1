<%@ page import="com.example.zerobasemission1.NeedFile.Services" %>
<%@ page import="com.example.zerobasemission1.DTO.HistoryDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: com
  Date: 2024-01-02
  Time: 오후 7:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    Services services = new Services();
    List<HistoryDTO> historyList = services.SelectAllHistory();
%>

<head>
    <title>와이파이 정보 구하기</title>
    <link href="Css/basicCss.css" rel="stylesheet" type="text/css">

</head>
<body>
<h1 style="font-weight: bolder">위치 히스토리 목록</h1>

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

<table>
    <tr>  <!-- 제목행 -->
        <th>ID</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>조회일자</th>
        <th>비고</th>
    </tr>
    <%
        if (historyList.size() == 0) {
    %>
    <tr>
        <td colspan="5">
            데이터가 존재하지 않습니다.
        </td>
    </tr>
    <%
        } else {
            for(HistoryDTO item : historyList) {
    %>

    <tr>
        <td>
            <%=item.getHistoryID()%>
        </td>
        <td>
            <%=item.getHistoryLAT()%>
        </td>
        <td>
            <%=item.getHistoryLNT()%>
        </td>
        <td>
            <%=item.getHistoryDate()%>
        </td>
        <td>
            <button onclick="deleteHistory(<%=item.getHistoryID()%>);">삭제</button>
        </td>
    </tr>
<%
        }
    }
%>

</table>

<script>

    function deleteHistory(historyID) {
        location.href = "history-delete.jsp?id=" + historyID;
    }



</script>
</body>
</html>
