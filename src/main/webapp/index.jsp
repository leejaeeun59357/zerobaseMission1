<%@ page import="com.example.zerobasemission1.NeedFile.Services" %>
<%@ page import="com.example.zerobasemission1.DTO.ShowWifiInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.zerobasemission1.NeedFile.WifiDAO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    request.setCharacterEncoding("UTF-8");
    WifiDAO wifiDAO = new WifiDAO();
    wifiDAO.CreateBookmarkRelatedTable();
%>

<%
    Services service = new Services();
    service.CreateHistory();

    String lat = request.getParameter("lat");
    String lnt = request.getParameter("lnt");

    double latDouble = 0.0;
    double lntDouble = 0.0;

    if(lat != null && lat != "" ) {
        latDouble = Double.parseDouble(lat);
    }

    if(lnt !=null && lnt != "") {
        lntDouble = Double.parseDouble(lnt);
    }

%>

<!DOCTYPE html>
<html>

<head>
    <title>와이파이 정보 구하기</title>
    <link href="Css/basicCss.css" rel="stylesheet" type="text/css">
</head>

<body>
<%--@declare id="myloc"--%><h1 style="font-weight: bolder">와이파이 정보 구하기 </h1>

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

<form method="get" action="index.jsp" id="form-list">
    LAT : <input type="text" id="lat" name="lat" placeholder="0.0" value="0.0">
    LNT : <input type="text" id="lnt" name="lnt" placeholder="0.0" value="0.0">
<input type="button" value="내 위치 가져오기" onclick="getLocation()" >
<input type="submit" value="근처 WIFI 정보 보기">
</form>
<br/>

<table>
    <tr>  <!-- 제목행 -->
        <th>거리(Km)</th>
        <th>관리번호</th>
        <th>자치구</th>
        <th>와이파이명</th>
        <th>도로명주소</th>
        <th>상세주소</th>
        <th>설치위치(층)</th>
        <th>설치유형</th>
        <th>설치기관</th>
        <th>서비스구분</th>
        <th>망종류</th>
        <th>설치년도</th>
        <th>실내외구분</th>
        <th>WIFI접속환경</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>작업일자</th>
    </tr>

    <%
        if (latDouble == 0.0 && lntDouble == 0.0) {
    %>
    <tr>
        <td colspan="17">위치 정보를 입력한 후에 조회해 주세요.</td>
    </tr>
    <%
        } else {
            service.AddHistory(lat, lnt);

            List<ShowWifiInfo> wifiInfos = service.SelectAllWifiInfo(latDouble,lntDouble);

            for(ShowWifiInfo wifi : wifiInfos) {
    %>
    <tr>
        <td><%=wifi.getDistance()%></td>
        <td><%=wifi.getxSwifiMgrNo()%></td>
        <td><%=wifi.getxSwifiWrdofc()%></td>
        <td>
            <a href="detail.jsp?mgrNo=<%=wifi.getxSwifiMgrNo()%>&dist=<%=wifi.getDistance()%>">
                <%=wifi.getxSwifiMainNm()%>
            </a>
        </td>
        <td><%=wifi.getxSwifiAdres1()%></td>
        <td><%=wifi.getxSwifiAdres2()%></td>
        <td><%=wifi.getxSwifiInstlFloor()%></td>
        <td><%=wifi.getxSwifiInstlTy()%></td>
        <td><%=wifi.getxSwifiInstlMby()%></td>
        <td><%=wifi.getxSwifiSvcSe()%></td>
        <td><%=wifi.getxSwifiCmcwr()%></td>
        <td><%=wifi.getxSwifiCnstcYear()%></td>
        <td><%=wifi.getxSwifiInoutDoor()%></td>
        <td><%=wifi.getxSwifiRemars3()%></td>
        <td><%=wifi.getLat()%></td>
        <td><%=wifi.getLnt()%></td>
        <td><%=wifi.getWorkDttm()%></td>
    </tr>

<%
        }
    }
%>

</table>
</body>
<script>
    const params = new URLSearchParams(window.location.search)
    const lnt = params.get("lnt")
    const lat = params.get("lat")

    if(lnt) {
        const lntElement = document.getElementById("lnt")
        lntElement.setAttribute("value",lnt)
    }
    if(lat) {
        const latElement = document.getElementById("lat")
        latElement.setAttribute("value",lat)
    }


    function getLocation() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(showPosition);
        } else {
            alert("이 브라우저는 위치 정보를 지원하지 않습니다.");
        }
    }

    function showPosition(position) {
        const lat = position.coords.latitude;
        const lnt = position.coords.longitude;

        document.getElementById("lat").value = lat;
        document.getElementById("lnt").value = lnt;
    }

</script>
</html>