package com.source.plusutil.service.noticeService;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.source.plusutil.dto.notice.NoticeDto;
import com.source.plusutil.dto.notice.NoticeWriteDto;
import com.source.plusutil.enums.UserRolePlusEnum;
import com.source.plusutil.repository.noticeRepository.NoticeRepository;
import com.source.plusutil.service.authService.AuthenticationService;
import com.source.plusutil.utils.etc.PagingUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class NoticeService {

	private final AuthenticationService authenticationService;
	
	private final NoticeRepository noticeRepository;
	
	
	
	public void getNoticeList(HttpServletRequest request, Authentication authentication, Integer currentPage) {
		if(currentPage == null) { 			//만약 페이지 null이면 0으로 초기화
			currentPage = 0;
		}
		
		if(currentPage < 0) {currentPage = 0;} // 0보다 작은 페이지 넘버는 존재할 수 없음
		
		if(authenticationService.authenticationConfirm(authentication, UserRolePlusEnum.ROLE_ADMIN.toString())) { //권한 체크
			request.setAttribute("noticeWriteRole", "ok"); //관리자일 경우 게시글 쓰기 권한 있음
		}
		Integer listSize = 5;
		
		PageRequest pageRequest = PageRequest.of(currentPage, listSize); //10개 까지 가져오도록 페이징 설정
		
		Page<NoticeDto> noticePageList = noticeRepository.findAll(pageRequest);
		Integer totalNoticePage = noticePageList.getTotalPages();
		
		for(NoticeDto nd : noticePageList) {
			log.info("noticePageList info -> " + nd.toString());
		}
		
		Long totalNoticeCount = noticePageList.getTotalElements();
				
		PagingUtil noticePaging = new PagingUtil(totalNoticeCount, currentPage, listSize);
		log.info("noticePaging -> " + noticePaging.toString());
		
		request.setAttribute("startPage", noticePaging.getStartPage());
		request.setAttribute("endPage", noticePaging.getEndPage());
		request.setAttribute("totalPage", totalNoticePage);
		request.setAttribute("noticePageList", noticePageList);
		request.setAttribute("currentPage", currentPage);
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
