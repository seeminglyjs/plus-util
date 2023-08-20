package com.source.plusutil.mypage.dto

import com.source.plusutil.utils.entity.PrimaryKeyEntity
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import java.util.*
import javax.persistence.*


@Entity
@Table(
        name="tb_my_page_info",
        uniqueConstraints=
        [UniqueConstraint(columnNames=["user_no","nick_name"])]
)
@NoArgsConstructor
@AllArgsConstructor
class MyPageDto(
        @Column(name = "user_no", nullable = false, unique = true) val userNo: Int,
        nickName: String = UUID.randomUUID().toString().replace("-", ""),
        @Column(name = "description") var description: String? = "안녕하세요. 방문해 주셔서 감사드립니다.",
        @Column(name = "view_cnt", nullable = false) var viewCnt: Long = 0,
        @Column(name = "like_cnt", nullable = false) var likeCnt: Long = 0
)  : PrimaryKeyEntity(){

        @Column(name = "nick_name", nullable = false , unique = true)
        var nickName: String = nickName
                protected set //상속한 객체만 수정가능 allopen 키워드 사용으로 private으로 하면 오류남
        override fun toString(): String {
                return "MyPageDto(nickName='$nickName', userNo=$userNo, description=$description, viewCnt=$viewCnt, likeCnt=$likeCnt)"
        }
}