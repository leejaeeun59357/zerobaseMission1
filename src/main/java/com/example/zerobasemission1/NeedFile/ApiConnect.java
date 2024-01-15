package com.example.zerobasemission1.NeedFile;

import com.example.zerobasemission1.DTO.WifiDto;
import com.google.gson.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class ApiConnect {

    public static WifiDto takeWifi(int start, int end) throws Exception {

        // URL 설정
        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");  // URL
        urlBuilder.append("/" + URLEncoder.encode("79614864656e65323838686d514867","UTF-8")); // 인증키
        urlBuilder.append("/" + URLEncoder.encode("json","UTF-8"));  // 요청파일 타입은 json
        urlBuilder.append("/"+ URLEncoder.encode("TbPublicWifiInfo","UTF-8"));
        urlBuilder.append("/"+ URLEncoder.encode(Integer.toString(start),"UTF-8"));
        urlBuilder.append("/"+ URLEncoder.encode(Integer.toString(end),"UTF-8") + "/");

        // URL 객체 생성
        URL url = new URL(urlBuilder.toString());

        // 요청하고자 하는 URL과 통신하기 위한 Connection 객체 생성
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Request 형식 설정
        connection.setRequestMethod("GET");

        // 통신을 위한 Content-type SET
        connection.setRequestProperty("Content-Type", "application/json");

        // 전달받은 데이터를 BufferedReader 로 저장
        BufferedReader bufferedReader;

        // 200과 300 사이의 값이면 정상작동
        if (connection.getResponseCode() >= 200 & connection.getResponseCode() <= 300) {
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        } else {
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }

        // 저장된 데이터를 한줄 씩 result에 저장
        StringBuffer result = new StringBuffer();

        String line;

        while ((line = bufferedReader.readLine()) != null) {
            result.append(line);
        }


        // 객체 연결 해제
        bufferedReader.close();
        connection.disconnect();

        // Gson 으로 json 데이터 -> 자바 object
        Gson gson = new Gson();
        WifiDto wifiDto = gson.fromJson(result.toString(), WifiDto.class);

        return wifiDto;

    }

}