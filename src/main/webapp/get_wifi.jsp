<%@ page import="db.WifiService" %>
<%@ page import="java.util.List" %>
<%@ page import="db.Wifi" %>
<%@ page import="db.GetWifi" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Insert title here</title>

    <style>

      #customers {
        font-family: Arial, Helvetica, sans-serif;
        border-collapse: collapse;
        width: 100%;
      }

      #customers td, #customers th {
        border: 1px solid #ddd;
        padding: 8px;

      }

      #customers tr:nth-child(even){background-color: #f2f2f2;}

      #customers tr:hover {background-color: #ddd;}

      #customers th {
        padding-top: 12px;
        padding-bottom: 12px;
        text-align: center;
        background-color: #04AA6D;
        color: white;

      }
      table, td, th {
        border: 1px solid black;
      }

      table {
        border-collapse: collapse;
        width: 100%;
      }

    </style>
</head>

<body>
<h1>와이파이 정보 구하기</h1>
  <p>
    <a href="http://localhost:8080/list.jsp">홈</a> |

    <a href="http://localhost:8080/save_wifi.jsp">위치 히스토리 목록</a> |

    <a href="http://localhost:8080/total_wifi.jsp">Open API 와이파이 정보 가져오기</a>

  </p>

<form method="post" action='my_location.jsp'>

    <span class="blind">LAT:</span>
    <input type="text" name="id" placeholder="0.0">,

    <span class="blind">LNT:</span>
    <input type="text" name="id" placeholder="0.0">

    <input type='submit' value="내 위치 가져오기">
    <input type='button' value="근처 WIFI 정보 보기" onclick='return submit2(this.form);'><br><br>
</form>

<%
  WifiService wifiService = new WifiService();
  List<GetWifi> getList = wifiService.getlist();
%>

<table id="customers">
    <thead>
      <tr>
        <th >거리(Km)</th>
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
    </thead>
    <tbody>
      <tr>

        <%
        for(GetWifi getWifi : getList) {
        %>

          <tr>
          <td><%=getWifi.getMgr_no()%></td>
          <td><%=getWifi.getWrdofc()%></td>
          <td><%=getWifi.getMain()%></td>
          <td><%=getWifi.getAdres1()%></td>
          <td><%=getWifi.getAdres2()%></td>
          <td><%=getWifi.getInstl_floor()%></td>
          <td><%=getWifi.getInstl_ty()%></td>
          <td><%=getWifi.getInstl_mby()%></td>
          <td><%=getWifi.getSvc_se()%></td>
          <td><%=getWifi.getCmcwr()%></td>
          <td><%=getWifi.getCnstc_year()%></td>
          <td><%=getWifi.getInout_door()%></td>
          <td><%=getWifi.getRemars3()%></td>
          <td><%=getWifi.getLat()%></td>
          <td><%=getWifi.getLnt()%></td>
          <td><%=getWifi.getWork_dttm()%></td>
          <td></td>
          </tr>

        <%
        }
        %>


      </tr>
    </tbody>
</table>
    <script>
      function submit2(frm) {
        frm.action='get_wifi.jsp';
        frm.submit();
        return true;
      }
    </script>
</body>
</html>