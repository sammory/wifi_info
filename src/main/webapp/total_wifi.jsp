<%@ page import="db.WifiService" %>
<%@ page import="db.ApiExplorer" %>
<%@ page import="db.GetWifi" %>
<%@ page import="java.util.List" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>



<%
    request.setCharacterEncoding("utf-8");

    WifiService wifiService = new WifiService(); //자바로 저장할땐 글자 안꺠지는데 jsp로 실행하면 꺠짐
    wifiService.insert();

%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <style>
        h1 { text-align: center; }
        div { text-align: center; }
    </style>
</head>
<body>

<h1>
    <%
    ApiExplorer apiExplorer = new ApiExplorer();
    %>
    <%= apiExplorer.totalData()%>
    개의 WIFI 정보를 정상적으로 저장하였습니다.
</h1>
    <div class="atag">
    <a href="http://localhost:8080/list.jsp">홈으로 가기</a>
    </div>
</body>
</html>
