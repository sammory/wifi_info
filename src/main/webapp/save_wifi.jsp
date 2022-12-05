<%@ page import="db.WifiService" %>
<%@ page import="java.util.List" %>
<%@ page import="db.Wifi" %>
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
        #customers table, td, th {
          border: 1px solid black;
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
    List<Wifi> wifiList = wifiService.list();
%>

<table id="customers">
      <thead>
          <tr>
            <th>ID</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>조회일자</th>
            <th>비고</th>
          </tr>
      </thead>
      <tbody>
        <tr>

          <%
            for(Wifi wifi : wifiList) {
          %>

            <tr>
                <td><%=wifi.getNum()%></td>
                <td><%=wifi.getX_data()%></td>
                <td><%=wifi.getY_data()%></td>
                <td><%=wifi.getSave_data()%></td>
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