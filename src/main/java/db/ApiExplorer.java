package db;


import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


public class ApiExplorer {

        public static List<GetWifi> apiDb() throws IOException, ParseException {

            ArrayList totalList = new ArrayList<>();

//            int[] start = {1, 1001, 2001, 3001, 4001, 5001, 6001, 7001, 8001, 9001
////                    , 10001, 11001, 12001, 13001, 14001, 15001, 16001, 17001};
//            , 10001, 11001, 12001, 13001};
            int start = 1;
            int end = 1000;
            int cnt = 18000/1000;

//            for (int i : start) {
            StringBuilder sb = null;
           // sb.append("");
            for (int i = 1; i <= cnt; i++) {//for시작

                StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
                urlBuilder.append("/" + URLEncoder.encode("635a574f6968796f3334634675464f", "UTF-8")); /*인증키 (sample사용시에는 호출시 제한됩니다.)*/
                urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8"));                           /*요청파일타입 (xml,xmlf,xls,json) */
                urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo", "UTF-8"));               /*서비스명 (대소문자 구분 필수입니다.)*/
                urlBuilder.append("/" + URLEncoder.encode(String.valueOf(start), "UTF-8"));                   /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
                urlBuilder.append("/" + URLEncoder.encode(String.valueOf(end), "UTF-8"));                 /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/


                URL url = new URL(urlBuilder.toString());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Content-type", "application/json");
                System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/

                BufferedReader rd;                                              // 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.


                if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                    rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                } else {
                    rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                }

                sb = new StringBuilder();

                String line;
                while ((line = rd.readLine()) != null) {
                    sb.append(line);
                }
                rd.close();
                conn.disconnect();

                start += 1000;
                end += 1000;



                JSONParser parser = new JSONParser();
                JSONObject jsonParser = (JSONObject) parser.parse(sb.toString());
                JSONObject jsonData = (JSONObject) jsonParser.get("TbPublicWifiInfo");
                JSONArray jsonValue = (JSONArray) jsonData.get("row");
                Object rowDAta = jsonValue;

                totalList.add(rowDAta);





            }//for 끝

//            System.out.println("$$$$$$$$$$$$$$$$$$"+sb.toString());



            return totalList;
        }


        public List<GetWifi> parserData() throws ParseException, IOException {

            ApiExplorer apiExplorer = new ApiExplorer();
            List<GetWifi> dwnList = new ArrayList<>();
            List sbdata = apiExplorer.apiDb();

            JSONParser parser = new JSONParser();
            JSONArray jsonParser2 = (JSONArray) parser.parse(sbdata.toString());
//            JSONArray jsonValue = (JSONArray) jsonParser2.get("jsonParser2");
//            JSONObject jsonData = (JSONObject) jsonParser.get("TbPublicWifiInfo");
//            JSONArray jsonValue = (JSONArray) jsonParser2.get("row");


            for(int i=0; i<jsonParser2.size(); i++){

                JSONArray obj2 = (JSONArray)jsonParser2.get(i);

                for(int j=0; j<obj2.size(); j++){
                    JSONObject obj = (JSONObject) obj2.get(j);


                GetWifi getWifi = new GetWifi();
                getWifi.setMgr_no((String) obj.get("X_SWIFI_MGR_NO"));
                getWifi.setWrdofc((String) obj.get("X_SWIFI_WRDOFC"));
                getWifi.setMain((String) obj.get("X_SWIFI_MAIN_NM"));
                getWifi.setAdres1((String) obj.get("X_SWIFI_ADRES1"));
                getWifi.setAdres2((String) obj.get("X_SWIFI_ADRES2"));
                getWifi.setInstl_floor((String) obj.get("X_SWIFI_INSTL_FLOOR"));
                getWifi.setInstl_ty((String) obj.get("X_SWIFI_INSTL_TY"));
                getWifi.setInstl_mby((String) obj.get("X_SWIFI_INSTL_MBY"));
                getWifi.setSvc_se((String) obj.get("X_SWIFI_SVC_SE"));
                getWifi.setCmcwr((String) obj.get("X_SWIFI_CMCWR"));
                getWifi.setCnstc_year((String) obj.get("X_SWIFI_CNSTC_YEAR"));
                getWifi.setInout_door((String) obj.get("X_SWIFI_INOUT_DOOR"));
                getWifi.setRemars3((String) obj.get("X_SWIFI_REMARS3"));
                getWifi.setLat((String) obj.get("LAT"));
                getWifi.setLnt((String) obj.get("LNT"));
                getWifi.setWork_dttm((String) obj.get("WORK_DTTM"));
                dwnList.add(getWifi);
             }
            }
            return dwnList;
        }

        public String totalData() throws IOException, ParseException {

            ApiExplorer apiExplorer = new ApiExplorer();

            String cnt = String.valueOf(apiExplorer.parserData().size());
            System.out.println(cnt);

//
//            ApiExplorer apiExplorer = new ApiExplorer();
//            String rowDAta = apiExplorer.apiDb().toString();
//
//            JSONParser parser = new JSONParser();
//            JSONObject jsonParser = (JSONObject) parser.parse(rowDAta);
//            JSONObject jsonData = (JSONObject) jsonParser.get("TbPublicWifiInfo");
//
////
////            JSONParser parser = new JSONParser();
////            JSONObject jsonObject = (JSONObject) parser.parse(sbdata);
////            JSONObject jsonData = (JSONObject) jsonObject.get("TbPublicWifiInfo");
//
//            jsonData.get("list_total_count");
//            System.out.println(jsonData.get("list_total_count"));
//
//            return jsonData.get("list_total_count");
            return cnt;
        }




}
