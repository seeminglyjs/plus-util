package com.source.plusutil.mypage.dto

import com.source.plusutil.user.dto.UserInfoDto
import com.source.plusutil.utils.entity.PrimaryKeyEntity
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import org.hibernate.annotations.ColumnDefault
import java.util.*
import javax.persistence.*


@Entity(name ="tb_my_page_info")
@Table(
        name="tb_my_page_info",
        uniqueConstraints=
        [UniqueConstraint(columnNames=["user_no","nick_name"])]
)
@NoArgsConstructor
@AllArgsConstructor
class MyPageDto(
        @Column(name = "nick_name", nullable = false, unique = true) var nickName: String = UUID.randomUUID().toString().replace("-", ""),
        @Column(name = "description") var description: String? = "안녕하세요. 방문해 주셔서 감사드립니다.",
        @Column(name = "view_cnt", nullable = false) var viewCnt: Long = 0,
        @Column(name = "like_cnt", nullable = false) var likeCnt: Long = 0,
        @Column(name = "phone_show_yn",  nullable = false, length = 1) @ColumnDefault("\'n\'") var phoneShow: String = "n", // 사용자 핸드폰 번호 공개 유무
        @Column(name = "name_show_yn", nullable = false,  length = 1) @ColumnDefault("\'n\'") var nameShow: String = "n", // 사용자 이름 공개 유무

        /**
         * 조인 정보 tb_user_info user_no
         * 유니크한 값이며, insert 만 가능 updatable 안됨
         * FK 제약 조건은 따르지 않고 적용하지 않는다.
         * 테스트가 어렵고 테이블 확장에 제약이 따르기 때문에 안쓰기로함
         */
        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_no", referencedColumnName = "user_no", unique = true, insertable = true, updatable = false, nullable = false, foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
        var userInfo : UserInfoDto

)  : PrimaryKeyEntity(){

        override fun toString(): String {
                return "MyPageDto(description=$description, viewCnt=$viewCnt, likeCnt=$likeCnt, userInfo=$userInfo, nickName='$nickName')"
        }


}