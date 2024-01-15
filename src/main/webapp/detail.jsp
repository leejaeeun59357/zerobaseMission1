<%@ page import="com.example.zerobasemission1.NeedFile.Services" %>
<%@ page import="com.example.zerobasemission1.DTO.Row" %>
<%@ page import="com.example.zerobasemission1.DTO.BookmarkGroupDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.zerobasemission1.NeedFile.WifiDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    request.setCharacterEncoding("UTF-8");
%>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link href="Css/basicCss.css" rel="stylesheet" type="text/css">

</head>
<body>
<h1 style="font-weight: bolder">와이파이 정보 구하기</h1>

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


<%
    String mgrNo = request.getParameter("mgrNo");
    String dist = request.getParameter("dist");

    Services service = new Services();

    Row wifiInfo = service.SelectOneWifiInfo(mgrNo);

    List<BookmarkGroupDTO> bookmarkGroupDTOS = service.SelectAllBookmarkGroup();
%>


<div>
    <form action="bookmark-add-submit.jsp" method="post" id="bookmark-form">
        <select name="bookmarkNumber">
            <option value="none" selected>북마크 그룹 이름 선택</option>
            <%
                for (BookmarkGroupDTO item : bookmarkGroupDTOS) {
            %>
            <option value="<%=item.getBookmarkNumber()%>">
                <%=item.getBookmarkName()%>
            </option>
            <%
                }
            %>
        </select>

        <input type="submit" value="북마크 추가하기">
        <input type="hidden" name="mgrNo" value="<%=wifiInfo.getXSwifiMgrNo()%>">
    </form>
</div>


<table>
    <tr>
        <th>거리(Km)</th>
        <td><%=dist%>
        </td>
    </tr>
    <tr>
        <th>관리번호</th>
        <td><%=wifiInfo.getXSwifiMgrNo()%>
        </td>
    </tr>
    <tr>
        <th>자치구</th>
        <td><%=wifiInfo.getXSwifiWrdofc()%>
        </td>
    </tr>
    <tr>
        <th>와이파이명</th>
        <td>
            <a href="#" onClick="history.back()">
                <%=wifiInfo.getXSwifiMainNm()%>
            </a>
        </td>
    </tr>
    <tr>
        <th>도로명주소</th>
        <td><%=wifiInfo.getXSwifiAdres1()%>
        </td>
    </tr>
    <tr>
        <th>상세주소</th>
        <td><%=wifiInfo.getXSwifiAdres2()%>
        </td>
    </tr>
    <tr>
        <th>설치위치(층)</th>
        <td><%=wifiInfo.getXSwifiInstlFloor()%>
        </td>
    </tr>
    <tr>
        <th>설치유형</th>
        <td><%=wifiInfo.getXSwifiInstlTy()%>
        </td>
    </tr>
    <tr>
        <th>설치기관</th>
        <td><%=wifiInfo.getXSwifiInstlMby()%>
        </td>
    </tr>
    <tr>
        <th>서비스구분</th>
        <td><%=wifiInfo.getXSwifiSvcSe()%>
        </td>
    </tr>
    <tr>
        <th>망종류</th>
        <td><%=wifiInfo.getXSwifiCmcwr()%>
        </td>
    </tr>
    <tr>
        <th>설치년도</th>
        <td><%=wifiInfo.getXSwifiCnstcYear()%>
        </td>
    </tr>
    <tr>
        <th>실내외구분</th>
        <td><%=wifiInfo.getXSwifiInoutDoor()%>
        </td>
    </tr>
    <tr>
        <th>WIFI접속환경</th>
        <td><%=wifiInfo.getXSwifiRemars3()%>
        </td>
    </tr>
    <tr>
        <th>X좌표</th>
        <td><%=wifiInfo.getLat()%>
        </td>
    </tr>
    <tr>
        <th>Y좌표</th>
        <td><%=wifiInfo.getLnt()%>
        </td>
    </tr>
    <tr>
        <th>작업일자</th>
        <td><%=wifiInfo.getWorkDttm()%>
        </td>
    </tr>
</table>

</body>
</html>
