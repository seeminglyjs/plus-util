package com.source.plusutil.mypage

import com.source.plusutil.mypage.dto.MyPageDto
import com.source.plusutil.mypage.dto.MyPageInfoDto
import com.source.plusutil.mypage.dto.MyPageModifyDto
import com.source.plusutil.mypage.dto.NickNameDuplicateCheckDto
import com.source.plusutil.mypage.enums.NickNameCheckStatus
import com.source.plusutil.mypage.repository.MyPageRepository
import com.source.plusutil.user.UserInfoRepository
import com.source.plusutil.user.dto.UserInfoDto
import io.github.oshai.KotlinLogging
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Service
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.transaction.Transactional

private val logger = KotlinLogging.logger {}

@Service
@RequiredArgsConstructor
@Transactional
class MyPageServiceImpl(
        private val myPageRepository: MyPageRepository,
        private val userRepository: UserInfoRepository,
        private val entityManagerFactory: EntityManagerFactory) : MyPageService {

    /**
     * myPage 정보를 가져와서 보여주는 메소드
     */
    override fun getMyPage(userNo: Int): MyPageInfoDto? {
        val entityManger: EntityManager = entityManagerFactory.createEntityManager()
        try {
            val userInfoDtoOp: Optional<UserInfoDto> = userRepository.findById(userNo);
            val myPageDto: MyPageDto? = myPageRepository.findByUserInfo(userInfoDtoOp.get())
            logger.info("MyPage Get Result -> ${myPageDto.toString()}")
            return if (myPageDto != null) {
                entityManger.persist(myPageDto)//객체 존제 영속화
                makeResponseGetMyPageDto(myPageDto, entityManger)
            } else {
                if (userInfoDtoOp.isPresent) {
                    logger.info("====== Make MyPage ======")
                    val savedMyPageDto = myPageRepository.save(MyPageDto(userInfo = userInfoDtoOp.get()))
                    MyPageInfoDto(
                            userNo = savedMyPageDto.userInfo.userNo,
                            nickName = savedMyPageDto.nickName,
                            description = savedMyPageDto.description,
                            viewCnt = savedMyPageDto.viewCnt,
                            likeCnt = savedMyPageDto.likeCnt,
                            userName = savedMyPageDto.userInfo.userName,
                            userPhone = savedMyPageDto.userInfo.userPhone,
                            phoneShow = savedMyPageDto.phoneShow,
                            nameShow = savedMyPageDto.nameShow,
                    )
                } else {
                    logger.info("====== UserInfo user_no is undefined return null ======")
                    MyPageInfoDto(); //유저 아이디 정보 없음 잘못된 요청
                }
            }
        } finally {//EntityManager 살아 있으면 종료시킨다.
            if (entityManger.isOpen) entityManger.close()
        }
    }

    /**
     * myPage 정보 조회된 정보를 조회수 1증가시켜서 객체화 해준다.
     */
    fun makeResponseGetMyPageDto(myPageDto: MyPageDto, entityManger: EntityManager): MyPageInfoDto {
        val newMyPageView = myPageDto.viewCnt + 1
        try {
            logger.info("====== MyPage View + 1 ======")
            var persistMyPageDto: MyPageDto? = entityManger.find(MyPageDto::class.java, myPageDto.id) //영속성 우선 탐색
            persistMyPageDto = persistMyPageDto ?: myPageRepository.findById(myPageDto.id)
            persistMyPageDto.viewCnt = newMyPageView
            val savedMyPageDto = myPageRepository.save(persistMyPageDto)
            return MyPageInfoDto(
                    userNo = savedMyPageDto.userInfo.userNo,
                    nickName = savedMyPageDto.nickName,
                    description = savedMyPageDto.description,
                    viewCnt = savedMyPageDto.viewCnt,
                    likeCnt = savedMyPageDto.likeCnt,
                    userName = savedMyPageDto.userInfo.userName,
                    userPhone = savedMyPageDto.userInfo.userPhone,
                    phoneShow = savedMyPageDto.phoneShow,
                    nameShow = savedMyPageDto.nameShow,
            )
        } finally {
            entityManger.close()
        }
    }

    /**
     * 요청받은 유저의 닉네임 정보가 중복되어 있는지 체크한다.
     */
    override fun checkNickNameDuplicate(nickNameDuplicateCheckDto: NickNameDuplicateCheckDto): NickNameDuplicateCheckDto? {
        val myPageDto: MyPageDto? = myPageRepository.findByNickName(nickNameDuplicateCheckDto.nickName)
        return if (myPageDto == null) {
            nickNameDuplicateCheckDto.checkStatus = NickNameCheckStatus.SUCCESS
            nickNameDuplicateCheckDto
        } else {
            nickNameDuplicateCheckDto.checkStatus = NickNameCheckStatus.DUPLICATE
            nickNameDuplicateCheckDto
        }
    }

    /**
     * 유저의 마이페이지 수정 정보를 불러오는 메소드이다.
     */
    override fun modifyPage(myPageModifyDto: MyPageModifyDto): MyPageInfoDto? {
        val userInfoDtoOp: Optional<UserInfoDto> = userRepository.findById(myPageModifyDto.userNo);
        val myPageDto: MyPageDto? = myPageRepository.findByUserInfo(userInfoDtoOp.get())
        return if (myPageDto != null) {
            return MyPageInfoDto(
                    userNo = myPageDto.userInfo.userNo,
                    nickName = myPageDto.nickName,
                    description = myPageDto.description,
                    viewCnt = myPageDto.viewCnt,
                    likeCnt = myPageDto.likeCnt,
                    userName = myPageDto.userInfo.userName,
                    userPhone = myPageDto.userInfo.userPhone,
                    phoneShow = myPageDto.phoneShow,
                    nameShow = myPageDto.nameShow,
            )
        } else {
            MyPageInfoDto()
        }
    }

}