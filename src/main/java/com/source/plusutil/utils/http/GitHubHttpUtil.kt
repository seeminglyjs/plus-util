package com.source.plusutil.utils.http

import org.json.simple.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import io.github.oshai.KotlinLogging
import org.json.simple.parser.JSONParser
import java.net.http.HttpResponse

private val logger = KotlinLogging.logger {}
class GitHubHttpUtil {

    fun getGithubListRepositoryLanguages(token : String) : JSONObject{
        val sb = StringBuilder();
        var resultJson = JSONObject();
        var line = "";
        val requestUrl = "https://api.github.com/repos/seeminglyjs/plus-util/languages";
        val url = URL(requestUrl);

        val con = url.openConnection() as HttpURLConnection;
        con.connectTimeout = 5000 //서버에 연결되는 Timeout 시간 설정
        con.readTimeout = 5000 // InputStream 읽어 오는 Timeout 시간 설정
        con.requestMethod = "GET"
        con.defaultUseCaches = false
        con.doInput = true
        con.doOutput = true
        con.setRequestProperty("Accept", "application/vnd.github+json")
        con.setRequestProperty("X-GitHub-Api-Version", "2022-11-28")
        con.setRequestProperty("Authorization", token)

        if (con.responseCode == HttpURLConnection.HTTP_OK) {
            logger.info("requestUrl Http 200 ->$requestUrl")
            val br = BufferedReader(InputStreamReader(con.inputStream, "utf-8"))
            while (true){
                if(br.ready()) sb.append(br.readLine())
                else break;
            }
            br.close()
            val jsonParser = JSONParser();
            val tempJson = jsonParser.parse(sb.toString());
            resultJson = tempJson as JSONObject;
            logger.info("responseInfo -> $resultJson")
        } else {
            logger.info("requestUrl Http->  " + con.responseMessage)
        }
        return resultJson;
    }

}