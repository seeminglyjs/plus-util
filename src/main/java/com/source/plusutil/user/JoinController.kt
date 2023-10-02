package com.source.plusutil.user

import com.source.plusutil.user.dto.JoinResultDto
import com.source.plusutil.user.dto.UserJoinDto
import io.github.oshai.KotlinLogging
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.validation.BindingResult
import org.springframework.validation.ObjectError
import org.springframework.web.bind.annotation.*
import java.util.function.Consumer
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

private val logger = KotlinLogging.logger {}
@RestController
@Slf4j
@RequestMapping("/plus")
@RequiredArgsConstructor
class JoinController (val joinService: JoinService){

    @Deprecated("", ReplaceWith("\"/join/joinMain\""))
    @GetMapping("/join")
    fun joinView(): String { //회원가입 메인 페이지
        return "/join/joinMain"
    }

    @PostMapping("/join/action")
    @ResponseBody
    fun joinAction(
        @RequestBody userJoinDto: @Valid UserJoinDto? //회원가입객체
            , bindingResult: BindingResult //@Vaild 예외처리 위한 객체
            , request: HttpServletRequest?
    ): JoinResultDto {
        return if (bindingResult.hasErrors()) { //@Vaild 실패하면 true로 떨어진다.
            logger.info("[UserJoinDto Info Vaild Fail] =====")
            logger.info("All Error Info ->[" + bindingResult.allErrors.toString() + "]")
            val list = bindingResult.allErrors
            //에러메시지만 Stream 으로 출력하기
            list.forEach(Consumer { c: ObjectError -> logger.info("Error Message->[" + c.defaultMessage + "]") }
            )
            joinService.joinFail(request) //회원가입 실패시 동작할 메소드
        } else { //@Valid에서 객체의 이상유무를 통과했을 경우 회원가입진행
            //회원가입 진행
            joinService.joinAction(userJoinDto, request)
        }
    }
}