package com.source.plusutil.service

import com.source.plusutil.config.PropertiesConfig
import com.source.plusutil.dto.github.GithubLanguagesListDto
import com.source.plusutil.utils.http.GitHubHttpUtil
import org.json.simple.JSONObject
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest
import io.github.oshai.KotlinLogging
import kotlin.math.roundToInt

private val logger = KotlinLogging.logger {}
@Service
class MainService (private val config : PropertiesConfig){

    fun callMainPageMethod(request : HttpServletRequest){
        val jsonObject : JSONObject = getGithubListRepositoryLanguagesCall()
        val jsonIterator = jsonObject.iterator()
        val list: MutableList<GithubLanguagesListDto> = ArrayList()
        var githubCodeSum : Long = 0;
        while(jsonIterator.hasNext()){
            val info = jsonIterator.next()
            val githubLanguagesListDto = GithubLanguagesListDto(info.key.toString(), info.value.toString());
            githubCodeSum += info.value.toString().toLong();
            list.add(githubLanguagesListDto);
        }
        for(listDto : GithubLanguagesListDto in list){
            listDto.gValue = ((listDto.gValue.toDouble() / githubCodeSum.toDouble() * 10000.0).roundToInt() / 100.0).toString()
        }
        request.setAttribute("githubLanguagesList",list)
        request.setAttribute("githubCodeSum",githubCodeSum)
        logger.info("codeSum -> $githubCodeSum")
    }

    private fun getGithubListRepositoryLanguagesCall() : JSONObject{
        val gitHubHttpUtil = GitHubHttpUtil()
        return gitHubHttpUtil.getGithubListRepositoryLanguages(config.githubSecretToken)
    }
}