package com.source.plusutil.mypage.enums

/**
 * 닉네임 검증 단계 enum 객체
 * 
 */
enum class NickNameCheckStatus(val status : String) {
    BEFORE("before"), //검증전
    SUCCESS("success"), // 검증성공
    DUPLICATE("duplicate"), // 중복
    FAIL("fail") //검증실패
}