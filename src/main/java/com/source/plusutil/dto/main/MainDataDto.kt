package com.source.plusutil.dto.main

import com.source.plusutil.dto.github.GithubLanguagesListDto

class MainDataDto(val code: String, val githubCodeSum : Long, val githubLanguagesList : MutableList<GithubLanguagesListDto>) {
}