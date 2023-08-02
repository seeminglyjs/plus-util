package com.source.plusutil.mypage.dto

import java.util.*
import javax.persistence.*

@Entity(name = "tb_my_page_info")
class MyPageDto(
        @Column(name = "user_id", nullable = false)
        val userId: Long,

        @Column(name = "nick_name", nullable = false)
        var nickName: String = UUID.randomUUID().toString().replace("-", ""),

        @Column(name = "description")
        var description: String? = null,

        @Column(name = "view_cnt", nullable = false)
        var viewCnt: Long = 0,

        @Column(name = "like_cnt", nullable = false)
        var likeCnt: Long = 0
) {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "my_id")
        var myId: Long? = null
    constructor() : this(0,"","",0,0)
}