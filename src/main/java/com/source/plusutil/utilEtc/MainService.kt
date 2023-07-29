package com.source.plusutil.utilEtc

import com.source.plusutil.config.PropertiesConfig
import com.source.plusutil.github.dto.GithubLanguagesListDto
import com.source.plusutil.main.MainDataDto
import com.source.plusutil.utils.http.GitHubHttpUtil
import io.github.oshai.KotlinLogging
import org.json.simple.JSONObject
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest
import kotlin.math.roundToInt


private val logger = KotlinLogging.logger {}
@Service
class MainService (private val config : PropertiesConfig){

    @Cacheable("mainDataDto", key = "#request.getRemoteAddr")
    fun callMainPageMethod(request: HttpServletRequest): MainDataDto {
        logger.info("[First Access MainPage]")
        val reposList: MutableList<String> = ArrayList()
        reposList.add("https://api.github.com/repos/seeminglyjs/plus-util/languages"); //백엔드
        reposList.add("https://api.github.com/repos/seeminglyjs/plus-util-front/languages"); //프론트
        val list: MutableList<GithubLanguagesListDto> = ArrayList()
        val codeMap = HashMap<String, Long>(); //언어 와 그 언어의 사용량을 담을 맵
        var githubCodeSum: Long = 0; //전체 사용 코드 합

        for (reposeUrl: String in reposList) { //저장소 돌면서 맵과 합계에 저장
            val jsonObject: JSONObject = getGithubListRepositoryLanguagesCall(reposeUrl)
            val jsonIterator = jsonObject.iterator()
            while (jsonIterator.hasNext()) {
                val info = jsonIterator.next()
                if (codeMap.containsKey(info.key.toString())) codeMap[info.key.toString()] = codeMap[info.key.toString()]!! + info.value.toString().toLong()
                else codeMap[info.key.toString()] = info.value.toString().toLong()
                githubCodeSum += info.value.toString().toLong();
            }
        }
        //사용된 언어들 추출
        val codeKeySet = codeMap.keys;
        val codeKeySetIterator = codeKeySet.iterator()
        while (codeKeySetIterator.hasNext()) {
            val key = codeKeySetIterator.next()
            val githubLanguagesListDto = GithubLanguagesListDto(key, codeMap[key].toString());
            list.add(githubLanguagesListDto);
        }

        for (listDto: GithubLanguagesListDto in list) {
            listDto.gValue = ((listDto.gValue.toDouble() / githubCodeSum.toDouble() * 10000.0).roundToInt() / 100.0).toString()
        }
        logger.info("codeSum -> $githubCodeSum")
        val code: String = if (githubCodeSum > 0) {
            "success"
        } else {
            "fail"
        }
        return MainDataDto(code, githubCodeSum, list);
    }

    private fun getGithubListRepositoryLanguagesCall(reposUrl : String) : JSONObject{
        val gitHubHttpUtil = GitHubHttpUtil()
        return gitHubHttpUtil.getGithubListRepositoryLanguages(config.githubSecretToken, reposUrl)
    }
}