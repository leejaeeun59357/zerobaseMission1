<%@ page import="com.example.zerobasemission1.NeedFile.Services" %>
<%@ page import="com.example.zerobasemission1.DTO.BookmarkGroupDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%
    request.setCharacterEncoding("UTF-8");
%>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<body>

<%
    Services service = new Services();

    String bookmarkNumber = request.getParameter("bookmarkNumber");
    String mgrNo = request.getParameter("mgrNo");
    String WifiName = service.SelectOneWifiInfo(mgrNo).getXSwifiMainNm();

    BookmarkGroupDTO bookmarkGroup = service.SelectOneBookmarkGroup(bookmarkNumber);


    if (bookmarkNumber.equals("none")) {
        response.sendRedirect(request.getHeader("Referer"));
        return;
    }

    service.AddBookmark(WifiName,bookmarkGroup.getBookmarkName());

%>
</body>

<script>
    alert("북마크에 데이터를 추가하였습니다.");
    location.href = "bookmark-list.jsp";
</script>
</html>
