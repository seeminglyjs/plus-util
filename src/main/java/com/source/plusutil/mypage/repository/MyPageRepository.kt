package com.source.plusutil.mypage.repository

import com.source.plusutil.mypage.dto.MyPageDto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MyPageRepository : JpaRepository<MyPageDto, Long>{
    fun findByUserId(userId: Int): MyPageDto?
}