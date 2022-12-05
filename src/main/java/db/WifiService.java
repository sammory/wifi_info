package db;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class WifiService { //DbTest

    //savewifi 출력
    public List<Wifi> list() {

        List<Wifi> wifiList = new ArrayList<>();

        String url = "jdbc:mariadb://localhost:3306/db1";
        String dbUserId = "wifi_user";
        String dbPassword = "wifi1234";



        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        String memberTypeValue = "1";

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);


            String sql = " select num, x_data, y_data, save_data " +
                    " from wifi_save " +
                    " where num = ? ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, memberTypeValue);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {

                String num = rs.getString("num");
                String xData = rs.getString("x_data");
                String yData = rs.getString("y_data");
                String save_data = rs.getString("save_data");

                Wifi wifi = new Wifi();
                wifi.setNum(num);
                wifi.setX_data(xData);
                wifi.setY_data(yData);
                wifi.setSave_data(save_data);

                wifiList.add(wifi);

                System.out.println(num + ", " + xData + ", " + yData + ", " + save_data);
            }


        } catch (SQLException e) {
            e.printStackTrace();

        } finally {

            try {
                if(rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if(preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if(connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return wifiList;
    }

    // 입력
    public void register(Wifi wifi) {

        String url = "jdbc:mariadb://localhost:3306/db1";
        String dbUserId = "wifi_user";
        String dbPassword = "wifi1234";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);


            String sql = " insert into wifi_save (num, x_data, y_data, save_data)" +
                    "values (?, ?, ?, ?) ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, wifi.getNum());
            preparedStatement.setString(2, wifi.getX_data());
            preparedStatement.setString(3, wifi.getX_data());
            preparedStatement.setString(4, wifi.getSave_data());

            int affected = preparedStatement.executeUpdate();

            if (affected > 0) {
                System.out.println(" 저장 성공 ");
            } else {
                System.out.println(" 저장 실패 ");
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {

            try {
                if(rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if(preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if(connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }



    public void dbUpdate() {
        String url = "jdbc:mariadb://localhost:3306/db1";
        String dbUserId = "wifi_user";
        String dbPassword = "wifi1234";



        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        String memberTypeValue = "1";
        String x_dataValue = "10.7654321";
        String save_dataValue = "01.00";

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);


            String sql = " update wifi_save set" +
                    " x_data = ? " +
                    "where num = ? and save_data = ? " ;

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, x_dataValue);
            preparedStatement.setString(2, memberTypeValue);
            preparedStatement.setString(3, save_dataValue);

            int affected = preparedStatement.executeUpdate();

            if (affected > 0) {
                System.out.println(" 수정 성공 ");
            } else {
                System.out.println(" 수정 실패 ");
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {

            try {
                if(rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if(preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if(connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }


    public void withdraw(Wifi wifi) {
        String url = "jdbc:mariadb://localhost:3306/db1";
        String dbUserId = "wifi_user";
        String dbPassword = "wifi1234";



        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);


            String sql = " delete from wifi_save " +
                    "where num = ? and x_data = ? " ;

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, wifi.getNum());
            preparedStatement.setString(2, wifi.getX_data());

            int affected = preparedStatement.executeUpdate();

            if (affected > 0) {
                System.out.println(" 삭제 성공 ");
            } else {
                System.out.println(" 삭제 실패 ");
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {

            try {
                if(rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if(preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if(connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }



    //api 출력 getwifi
    public List<GetWifi> getlist() {

        List<GetWifi> getList = new ArrayList<>();

        String url = "jdbc:mariadb://localhost:3306/db1";
        String dbUserId = "wifi_user";
        String dbPassword = "wifi1234";



        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        String memberTypeValue = "1";

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);


            String sql = " select *" +
                    "from wifi_info" ;

            preparedStatement = connection.prepareStatement(sql);
            //preparedStatement.setString(1, memberTypeValue);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {

                String mgr_no = rs.getString("mgr_no");
                String wrdofc = rs.getString("wrdofc");
                String main = rs.getString("main");
                String adres1 = rs.getString("adres1");
                String adres2 = rs.getString("adres2");
                String instl_floor = rs.getString("instl_floor");
                String instl_ty = rs.getString("instl_ty");
                String instl_mby = rs.getString("instl_mby");
                String svc_se = rs.getString("svc_se");
                String cmcwr = rs.getString("cmcwr");
                String cnstc_year = rs.getString("cnstc_year");
                String inout_door = rs.getString("inout_door");
                String remars3 = rs.getString("remars3");
                String lat = rs.getString("lat");
                String lnt = rs.getString("lnt");
                String work_dttm = rs.getString("work_dttm");


                GetWifi getwifi = new GetWifi();
                getwifi.setMgr_no(mgr_no);
                getwifi.setWrdofc(wrdofc);
                getwifi.setMain(main);
                getwifi.setAdres1(adres1);
                getwifi.setAdres2(adres2);
                getwifi.setInstl_floor(instl_floor);
                getwifi.setInstl_ty(instl_ty);
                getwifi.setInstl_mby(instl_mby);
                getwifi.setSvc_se(svc_se);
                getwifi.setCmcwr(cmcwr);
                getwifi.setCnstc_year(cnstc_year);
                getwifi.setInout_door(inout_door);
                getwifi.setRemars3(remars3);
                getwifi.setLat(lat);
                getwifi.setLnt(lnt);
                getwifi.setWork_dttm(work_dttm);

                getList.add(getwifi);

                System.out.println(mgr_no + ", " + wrdofc + ", " + main + ", " + adres1 + ", " + adres2 + ", " + instl_floor + ", " + instl_ty + ", " + instl_mby + ", " + svc_se + "," +
                        " " + cmcwr + ", " + cnstc_year + ", " + inout_door + ", " + inout_door + ", " + remars3 + ", " + lat + ", " + lnt + ", " + work_dttm);
            }


        } catch (SQLException e) {
            e.printStackTrace();

        } finally {

            try {
                if(rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if(preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if(connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return getList;
    }


    //api 받아와서 저장 getwifi
    public void insert() throws ParseException, IOException {

        String url = "jdbc:mariadb://localhost:3306/db1";
        String dbUserId = "wifi_user";
        String dbPassword = "wifi1234";


        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;


        ApiExplorer apiExplorer = new ApiExplorer();
        List<GetWifi> list = apiExplorer.parserData();
        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);


        for (int i = 0; i < list.size(); i++) {


            String sql = "insert into wifi_info (" +
                    "mgr_no, wrdofc, main, adres1, adres2, instl_floor, instl_ty, instl_mby, svc_se, cmcwr, cnstc_year, inout_door, remars3, lat, lnt, work_dttm) " +
                    "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, list.get(i).getMgr_no());
            preparedStatement.setString(2, list.get(i).getWrdofc());
            preparedStatement.setString(3, list.get(i).getMain());
            preparedStatement.setString(4, list.get(i).getAdres1());
            preparedStatement.setString(5, list.get(i).getAdres2());
            preparedStatement.setString(6, list.get(i).getInstl_floor());
            preparedStatement.setString(7, list.get(i).getInstl_ty());
            preparedStatement.setString(8, list.get(i).getInstl_mby());
            preparedStatement.setString(9, list.get(i).getSvc_se());
            preparedStatement.setString(10, list.get(i).getCmcwr());
            preparedStatement.setString(11, list.get(i).getCnstc_year());
            preparedStatement.setString(12, list.get(i).getInout_door());
            preparedStatement.setString(13, list.get(i).getRemars3());
            preparedStatement.setString(14, list.get(i).getLat());
            preparedStatement.setString(15, list.get(i).getLnt());
            preparedStatement.setString(16, list.get(i).getWork_dttm());

            System.out.println("@@@@@@@"+(i+1));

            int affected = preparedStatement.executeUpdate();

            if (affected > 0) {
                System.out.println(" 저장 성공 ");
            } else {
                System.out.println(" 저장 실패 ");
            }
        }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {

            try {
                if(rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if(preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if(connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
