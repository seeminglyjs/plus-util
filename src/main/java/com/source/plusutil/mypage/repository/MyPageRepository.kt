package com.source.plusutil.mypage.repository

import com.source.plusutil.mypage.dto.MyPageDto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MyPageRepository : JpaRepository<MyPageDto?, Long?> {
    fun findByUserNo(userNo: Int): MyPageDto?
     fun findById(id: UUID): MyPageDto
}