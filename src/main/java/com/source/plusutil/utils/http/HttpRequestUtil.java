package com.source.plusutil.utils.http;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
public class HttpRequestUtil {

    public String httpApiGetRequestReturnString (String requestUrl) {
        StringBuilder sb = new StringBuilder();
        String line = "";
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(5000); //서버에 연결되는 Timeout 시간 설정
            con.setReadTimeout(5000); // InputStream 읽어 오는 Timeout 시간 설정
            con.setRequestMethod("GET");
            con.setDefaultUseCaches(false);
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setRequestProperty("content-type", "application/x-www-form-urlencoded");

            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                log.info("requestUrl Http 200 ->" + requestUrl);
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(con.getInputStream(), "utf-8"));

                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                br.close();
                log.info("responseInfo -> " + sb.toString());
            } else {
                log.info("requestUrl Http->  " + con.getResponseMessage());
                return "Fail";
            }
        } catch (Exception e) {
            log.info("exception->" + System.lineSeparator(), e);
            return "Fail";
        }
        return sb.toString();
    }

    public String naverPapagoPostRequest(String requestUrl, String clientId, String secret){
        StringBuilder sb = new StringBuilder();
        String line = "";
        String result = "";
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(5000); //서버에 연결되는 Timeout 시간 설정
            con.setReadTimeout(5000); // InputStream 읽어 오는 Timeout 시간 설정
            con.setRequestMethod("POST");
            con.setDefaultUseCaches(false);
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", secret);
            log.info(requestUrl);
            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                log.info("requestUrl Http 200 ->" + requestUrl);
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(con.getInputStream(), "utf-8"));

                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = (JSONObject) jsonParser.parse(sb.toString());
                JSONObject jsonMessage = (JSONObject) jsonObject.get("message");
                JSONObject jsonResult =  (JSONObject) jsonMessage.get("result");
                result = (String) jsonResult.get("translatedText");
                br.close();
                log.info("responseInfo -> " + sb.toString());
            } else {
                log.info("responseInfo -> " + sb.toString());
                return "Fail";
            }
        } catch (Exception e) {
            log.info("exception->" + System.lineSeparator(), e);
            return "Fail";
        }
        return result;
    }
}
