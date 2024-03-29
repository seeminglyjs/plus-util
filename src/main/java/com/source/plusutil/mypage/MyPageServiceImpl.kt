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
    override fun getMyPage(userNo: Int, viewPlus: String): MyPageInfoDto? {
        val entityManger: EntityManager = entityManagerFactory.createEntityManager()
        try {
            val userInfoDtoOp: Optional<UserInfoDto> = userRepository.findById(userNo);
            val myPageDto: MyPageDto? = myPageRepository.findByUserInfo(userInfoDtoOp.get())
            logger.info("[getMyPage] MyPage Get Result -> ${myPageDto.toString()}")
            return if (myPageDto != null) {
                entityManger.persist(myPageDto)//객체 존제 영속화
                makeResponseGetMyPageDto(myPageDto, entityManger, when{viewPlus == "yes" -> true else -> false },false)
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
     * likeFlag 가 true 라면 좋아요도 올려준다.
     */
    fun makeResponseGetMyPageDto(myPageDto: MyPageDto, entityManger: EntityManager, viewPlus : Boolean, pressLike : Boolean): MyPageInfoDto {
        val newMyPageView = when {viewPlus -> myPageDto.viewCnt + 1  else -> myPageDto.viewCnt}
        val newMyPageLike = when {pressLike -> myPageDto.likeCnt + 1 else -> myPageDto.likeCnt}

        try {
            logger.info("====== MyPage GET View  ======")
            var persistMyPageDto: MyPageDto? = entityManger.find(MyPageDto::class.java, myPageDto.id) //영속성 우선 탐색
            persistMyPageDto = persistMyPageDto ?: myPageRepository.findById(myPageDto.id)
            persistMyPageDto.viewCnt = newMyPageView
            persistMyPageDto.likeCnt = newMyPageLike
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
            val willBeChangedUserInfoDto : UserInfoDto = userInfoDtoOp.get()
            willBeChangedUserInfoDto.userName = when{myPageModifyDto.userName != null -> myPageModifyDto.userName else -> myPageDto.userInfo.userName}
            willBeChangedUserInfoDto.userPhone = when{myPageModifyDto.userPhone != null -> myPageModifyDto.userPhone else -> myPageDto.userInfo.userPhone}

            myPageDto.userInfo = willBeChangedUserInfoDto
            myPageDto.nickName = when{myPageModifyDto.nickName != null -> myPageModifyDto.nickName else -> myPageDto.nickName}
            myPageDto.description = when{myPageModifyDto.description != null -> myPageModifyDto.description else -> myPageDto.description}
            myPageDto.phoneShow = when{myPageModifyDto.phoneShow != null -> myPageModifyDto.phoneShow else -> myPageDto.phoneShow}
            myPageDto.nameShow = when{myPageModifyDto.nameShow != null -> myPageModifyDto.nameShow else -> myPageDto.nameShow}

            val changedMyPageDto: MyPageDto = myPageRepository.save(myPageDto)

            return MyPageInfoDto(
                    userNo = changedMyPageDto.userInfo.userNo,
                    nickName = changedMyPageDto.nickName,
                    description = changedMyPageDto.description,
                    viewCnt = changedMyPageDto.viewCnt,
                    likeCnt = changedMyPageDto.likeCnt,
                    userName = changedMyPageDto.userInfo.userName,
                    userPhone = changedMyPageDto.userInfo.userPhone,
                    phoneShow = changedMyPageDto.phoneShow,
                    nameShow = changedMyPageDto.nameShow,
            )
        } else {
            MyPageInfoDto()
        }
    }

    override fun likePlus(userNo: Int): MyPageInfoDto? {
        val entityManger: EntityManager = entityManagerFactory.createEntityManager()
        try {
            val userInfoDtoOp: Optional<UserInfoDto> = userRepository.findById(userNo);
            val myPageDto: MyPageDto? = myPageRepository.findByUserInfo(userInfoDtoOp.get())
            logger.info("[likePlus] MyPage Get Result -> ${myPageDto.toString()}")
            return if (myPageDto != null) {
                entityManger.persist(myPageDto)//객체 존제 영속화
                makeResponseGetMyPageDto(myPageDto, entityManger,  false, true)
            } else {
                    logger.info("====== UserInfo user_no is undefined return null ======")
                    MyPageInfoDto(); //유저 아이디 정보 없음 잘못된 요청
                }
            }finally {//EntityManager 살아 있으면 종료시킨다.
            if (entityManger.isOpen) entityManger.close()
        }
    }

}