package com.source.plusutil.mypage

import com.source.plusutil.mypage.dto.MyPageDto
import com.source.plusutil.mypage.dto.MyPageRequestDto
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

    override fun getMyPage(myPageRequestDto: MyPageRequestDto): MyPageDto? {
        val myPageDto : MyPageDto? = myPageRepository.findByUserNo(myPageRequestDto.userNo)
        return if(myPageDto != null){
            makeResponseGetMyPageDto(myPageDto)
        }else{
            val userInfoDtoOp : Optional<UserInfoDto> = userRepository.findById(myPageRequestDto.userNo);
            if(userInfoDtoOp.isPresent){
                myPageRepository.save(MyPageDto(userNo = myPageRequestDto.userNo))
            }else{
                null; //유저 아이디 정보 없음 잘못된 요청
            }
        }
    }

    fun makeResponseGetMyPageDto(myPageDto : MyPageDto) : MyPageDto{
        val newMyPageView = myPageDto.viewCnt + 1;
        val entityManger : EntityManager = entityManagerFactory.createEntityManager();
        try {
            var persistMyPageDto : MyPageDto? = entityManger.find (MyPageDto::class.java, myPageDto.id)
            persistMyPageDto = persistMyPageDto ?: myPageRepository.findById(myPageDto.id);
            persistMyPageDto.viewCnt = newMyPageView
            return myPageRepository.save(persistMyPageDto)
        }finally {
            entityManger.close()
        }
    }
}