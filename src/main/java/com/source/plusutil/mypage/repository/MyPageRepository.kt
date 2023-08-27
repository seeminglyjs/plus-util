package com.source.plusutil.mypage.repository

import com.source.plusutil.mypage.dto.MyPageDto
import com.source.plusutil.mypage.dto.MyPageModifyDto
import com.source.plusutil.user.dto.UserInfoDto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MyPageRepository : JpaRepository<MyPageDto?, Long?> {
    fun findByUserInfo(userInfoDto: UserInfoDto): MyPageDto?
    fun findById(id: UUID): MyPageDto
    fun findByNickName(nickName : String): MyPageDto?

}