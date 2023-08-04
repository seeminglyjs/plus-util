package com.source.plusutil.mypage

import com.source.plusutil.mypage.dto.MyPageDto
import com.source.plusutil.mypage.dto.MyPageRequestDto
import com.source.plusutil.mypage.dto.MyPageResponseDto
import com.source.plusutil.mypage.repository.MyPageRepository
import com.source.plusutil.user.UserInfoRepository
import com.source.plusutil.user.dto.UserInfoDto
import io.github.oshai.KotlinLogging
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

private val logger = KotlinLogging.logger {}
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
class MyPageServiceImpl (
        private val myPageRepository: MyPageRepository,
        private val userRepository: UserInfoRepository) : MyPageService{

    override fun getMyPage(myPageRequestDto: MyPageRequestDto): MyPageResponseDto? {
        val myPageDto : MyPageDto? = myPageRepository.findByUserId(myPageRequestDto.userId)
        if(myPageDto != null){
            return MyPageResponseDto(myPageRequestDto.userId)
        }else{
            val userInfoDtoOp : Optional<UserInfoDto> = userRepository.findById(myPageRequestDto.userId);
            if(userInfoDtoOp.isPresent){
                return null
            }else{

            }
            return null;
        }

    }
}