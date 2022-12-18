package com.source.plusutil.service.noticeService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.source.plusutil.dto.notice.NoticeDto;
import com.source.plusutil.dto.notice.NoticeWriteDto;
import com.source.plusutil.enums.UserRolePlusEnum;
import com.source.plusutil.repository.noticeRepository.NoticeRepository;
import com.source.plusutil.service.authService.AuthenticationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class NoticeService {

	private final AuthenticationService authenticationService;
	
	private final NoticeRepository noticeRepository;
	
	
	public void getNoticeList(HttpServletRequest request) {
		
	}

	/**
	 * 공지사항 작성하는 메소드
	 * 
	 * @param noticeTitle
	 * @param noticeContent
	 * @param request
	 * @param authentication
	 */
	public void writeNotice(String noticeTitle, String noticeContent, HttpServletRequest request, Authentication authentication) {	
		if(authenticationService.authenticationConfirm(authentication, UserRolePlusEnum.ROLE_ADMIN.toString())) { //권한 체크
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			Date writeTime = new Date();
			String writeTimeStr = sdf.format(writeTime);
			log.info("writeNotice TIME -> " + writeTimeStr);
			
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String userEmail = ((UserDetails) principal).getUsername();
			
			NoticeWriteDto noticeWriteDto = new NoticeWriteDto(
						noticeTitle
						,noticeContent
						,userEmail
						,writeTimeStr);
			log.info("noticeWriteDto -> " + noticeWriteDto.toString());
			noticeRepository.save(NoticeDto.writeNotice(noticeWriteDto));
		}else {
			log.info("writeNotice authentication Deny");
			return;
		}
	}

}
