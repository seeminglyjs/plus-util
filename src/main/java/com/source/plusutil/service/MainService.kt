package com.source.plusutil.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.source.plusutil.config.PropertiesConfig
import com.source.plusutil.dto.github.GithubLanguagesListDto
import com.source.plusutil.dto.main.MainDataDto
import com.source.plusutil.utils.http.GitHubHttpUtil
import io.github.oshai.KotlinLogging
import org.json.simple.JSONObject
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest
import kotlin.math.roundToInt


private val logger = KotlinLogging.logger {}
@Service
class MainService (private val config : PropertiesConfig){

    fun callMainPageMethod(request: HttpServletRequest): MainDataDto {
        val jsonObject: JSONObject = getGithubListRepositoryLanguagesCall()
        val jsonIterator = jsonObject.iterator()
        val list: MutableList<GithubLanguagesListDto> = ArrayList()
        var githubCodeSum: Long = 0;
        while (jsonIterator.hasNext()) {
            val info = jsonIterator.next()
            val githubLanguagesListDto = GithubLanguagesListDto(info.key.toString(), info.value.toString());
            githubCodeSum += info.value.toString().toLong();
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
        val mainDataDto = MainDataDto(code, githubCodeSum, list)
        return mainDataDto;
    }

    private fun getGithubListRepositoryLanguagesCall() : JSONObject{
        val gitHubHttpUtil = GitHubHttpUtil()
        return gitHubHttpUtil.getGithubListRepositoryLanguages(config.githubSecretToken)
    }
}