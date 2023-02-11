package com.source.plusutil.controller.joinController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.source.plusutil.dto.userDto.UserJoinDto;
import com.source.plusutil.service.userService.JoinService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@Slf4j
@RequestMapping("/plus")
public class JoinController {

	@Autowired
	JoinService joinService; //회원가입 로직 구현 서비스
	
	
    @GetMapping("/join")
    public String joinView() { //회원가입 메인 페이지
        return "/join/joinMain";
    }

    @PostMapping("/join/action")
    public String joinAction(
			@Valid UserJoinDto userJoinDto //회원가입객체
			,BindingResult bindingResult //@Vaild 예외처리 위한 객체
    		,HttpServletRequest request
    		) {
        if (bindingResult.hasErrors()) {//@Vaild 실패하면 true로 떨어진다.
            log.info("[UserJoinDto Info Vaild Fail] =====");
            log.info("All Error Info ->["+bindingResult.getAllErrors().toString()+"]");
            List<ObjectError> list = bindingResult.getAllErrors();
            //에러메시지만 Stream 으로 출력하기
            list.stream().forEach((c) ->
            	log.info("Error Message->["+c.getDefaultMessage()+"]")
            );
            joinService.joinFail(request); //회원가입 실패시 동작할 메소드
        }else {//@Valid에서 객체의 이상유무를 통과했을 경우 회원가입진행
        	//회원가입 진행
        	joinService.joinAction(userJoinDto,request);
        }    	
    	return "/join/joinResult"; //회원가입 성공여부를 알리는 페이지
    }    
}
