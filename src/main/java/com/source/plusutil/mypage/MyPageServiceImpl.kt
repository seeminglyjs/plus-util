package com.source.plusutil.mypage

import com.source.plusutil.mypage.dto.MyPageDto
import com.source.plusutil.mypage.dto.MyPageModifyRequestDto
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
@Slf4j
@RequiredArgsConstructor
@Transactional
class MyPageServiceImpl (
        private val myPageRepository: MyPageRepository,
        private val userRepository: UserInfoRepository,
        private val entityManagerFactory : EntityManagerFactory) : MyPageService{

    override fun getMyPage(userNo: Int): MyPageDto? {
        val myPageDto : MyPageDto? = myPageRepository.findByUserNo(userNo)
        return if(myPageDto != null){
            makeResponseGetMyPageDto(myPageDto)
        }else{
            val userInfoDtoOp : Optional<UserInfoDto> = userRepository.findById(userNo);
            if(userInfoDtoOp.isPresent){
                myPageRepository.save(MyPageDto(userNo = userNo))
            }else{
                null; //유저 아이디 정보 없음 잘못된 요청
            }
        }
    }

    fun makeResponseGetMyPageDto(myPageDto : MyPageDto) : MyPageDto{
        val newMyPageView = myPageDto.viewCnt + 1
        val entityManger : EntityManager = entityManagerFactory.createEntityManager()
        try {
            var persistMyPageDto : MyPageDto? = entityManger.find (MyPageDto::class.java, myPageDto.id)
            persistMyPageDto = persistMyPageDto ?: myPageRepository.findById(myPageDto.id);
            persistMyPageDto.viewCnt = newMyPageView
            return myPageRepository.save(persistMyPageDto)
        }finally {
            entityManger.close()
        }
    }
    override fun checkNickNameDuplicate(nickNameDuplicateCheckDto: NickNameDuplicateCheckDto): NickNameDuplicateCheckDto? {
        val myPageDto : MyPageDto? = myPageRepository.findByNickName(nickNameDuplicateCheckDto.nickName)
        return if(myPageDto == null){
            nickNameDuplicateCheckDto.checkStatus = NickNameCheckStatus.SUCCESS
            nickNameDuplicateCheckDto
        }else{
            nickNameDuplicateCheckDto.checkStatus = NickNameCheckStatus.DUPLICATE
            nickNameDuplicateCheckDto
        }
    }

    override fun modifyPage(myPageModifyRequestDto: MyPageModifyRequestDto): MyPageDto? {
        TODO("Not yet implemented")
    }
}