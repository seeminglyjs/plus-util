package com.source.plusutil.main

import com.source.plusutil.github.dto.GithubLanguagesListDto

class MainDataDto(val code: String, val githubCodeSum : Long, val githubLanguagesList : MutableList<GithubLanguagesListDto>) {

}