package com.source.plusutil.user.join.service

import com.source.plusutil.config.PropertiesConfig
import com.source.plusutil.user.UserInfoRepository
import com.source.plusutil.user.join.dto.JoinResultDto
import com.source.plusutil.user.dto.UserInfoDto
import com.source.plusutil.user.dto.UserJoinDto
import com.source.plusutil.utils.encrypt.AesUtil
import io.github.oshai.KotlinLogging
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest
import javax.transaction.Transactional
import javax.validation.Valid

private val logger = KotlinLogging.logger {}
@RequiredArgsConstructor
@Transactional
@Service
@Slf4j
class JoinService(val config: PropertiesConfig,
                  val passwordEncoder: PasswordEncoder,
                  val userInfoRepository: UserInfoRepository
) {
    /*
	 * 회원가입 유저 정보 저장 메소드
	 * 
	 * @param userEmail
	 * @param userPassword
	 * @param request
	 */
    fun joinAction(userJoinDto: @Valid UserJoinDto?, request: HttpServletRequest?): JoinResultDto {
        val joinResultDto = JoinResultDto()
        var joinSuccess = false //회원가입 성공여부 체크 변수
        logger.info("joinAction UserJoinDto ->[$userJoinDto]")
        if (joinDataCheck(userJoinDto?.userEmail) && joinDataCheck(userJoinDto?.userPassword)) { //정해진 데이터가 모두 정상인 경우
            logger.info("userEmail -> [" + (userJoinDto?.userEmail ?: "-1") + "]")
            logger.info("userPassword -> [" + (userJoinDto?.userPassword ?: "-1") + "]")
            if((userJoinDto?.userEmail ?: "-1") == "-1" || (userJoinDto?.userPassword ?: "-1") == "-1"){
                joinResultDto.check = false
                joinResultDto.message ="정확한 정보가 입력되지 않았습니다."
                return joinResultDto;
            }
            val aesDecEmailMap : MutableMap<String, String> =  AesUtil.aes256Decrypt(config.aes256key, config.aes256iv, userJoinDto?.userEmail)
            val aesDecPasswordMap : MutableMap<String, String> =  AesUtil.aes256Decrypt(config.aes256key, config.aes256iv, userJoinDto?.userPassword)
            if(aesDecEmailMap["result"] == "n" || aesDecPasswordMap["result"] == "n"){
                joinResultDto.message = "정확한 정보가 입력되지 않았습니다. 확인 요청"
                return joinResultDto
            }else{
                userJoinDto?.userEmail = aesDecEmailMap["decryptContent"];
                userJoinDto?.userPassword = aesDecPasswordMap["decryptContent"];
                logger.info("userEmail aes256Decrypt -> [" + (userJoinDto?.userEmail ?: "-1") + "]")
                logger.info("userPassword aes256Decrypt -> [" + (userJoinDto?.userPassword ?: "-1") + "]")
                if (validateDuplicateUser(userJoinDto?.userEmail ?: "-1")) { //유저 로그인 계정 중복 여부 체크
                    try {
                        userInfoRepository.save(UserInfoDto.makeUser(userJoinDto, passwordEncoder)) //만들어진 유저정보 db 저장하기
                        joinSuccess = true //에외없이 처리되면 회원가입 성공
                    } catch (e: Exception) {
                        logger.info("[joinDataVaildCheck false] =====")
                        logger.info("[Exception info]", e)
                    }
                } else {
                    joinResultDto.message = "이미 존재하는 아이디입니다."
                }
            }
        }
        joinResultDto.check = joinSuccess
        //회원가입 성공여부 객체 저장
        return joinResultDto
    }

    /*
	 * 회원가입 유저정보의 중복 여부를 체크하는 메소드
	 * 
	 * @param userEmail
	 * @return
	 */
    private fun validateDuplicateUser(userEmail: String): Boolean {
        if(userEmail == "-1") return false //잘못된 아이디 정보를 대입함
        val userInfoDtoOptinoal = userInfoRepository.findByUserEmail(userEmail)
        val userInfo = userInfoDtoOptinoal.orElse(null) //null 여부 체크
        return if (userInfo == null) {
            logger.info("validateDuplicateUser UserInfoDto -> [ null ] ")
            logger.info("[joinAction available] ===== ")
            true
        } else {
            logger.info("validateDuplicateUser exist -> [$userInfo] ")
            false
        }
    }

    /*
	 * 회원가입 정보가 유효한지 여부를 체크하는 메소드
	 * 
	 * @param data
	 * @return
	 */
    fun joinDataCheck(data: String?): Boolean {
        //요청데이터가 null이거난 없을 경우 false
        return data != null && data != config.noData
    }

    /*
	 * 회원가입 실패시에 동작할 메소드
	 * 
	 * @param request
	 */
    fun joinFail(request: HttpServletRequest?): JoinResultDto {
        logger.info("joinFail Method start =====")
        return JoinResultDto()
    }
}