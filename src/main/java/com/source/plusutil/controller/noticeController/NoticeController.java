package com.source.plusutil.controller.noticeController;

import com.source.plusutil.enums.UserRolePlusEnum;
import com.source.plusutil.enums.regex.RegexExpressionEnum;
import com.source.plusutil.service.noticeService.NoticeServiceImpl;
import com.source.plusutil.utils.auth.AuthObjectUtil;
import com.source.plusutil.utils.html.HtmlUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {

	private final NoticeServiceImpl noticeService;

	@GetMapping("/main")
	public String noticeMain(HttpServletRequest request, Authentication authentication, Integer currentPage) {
		noticeService.getNoticeList(request, authentication, currentPage);
		return "/notice/noticeMain";
	}
	
	/**
	 * 공지사항 작성 페이지 호출
	 * 
	 * @param request
	 * @param authentication
	 * @param currentPage
	 * @return
	 */
	@GetMapping("/write")
	public String noticeWrite(HttpServletRequest request, Authentication authentication, Integer currentPage) {
		if(AuthObjectUtil.authenticationConfirm(authentication, UserRolePlusEnum.ROLE_ADMIN.toString())) { //권한 체크
			request.setAttribute("regexAllPermit", RegexExpressionEnum.ALL_PERMIT.getRegex());
			return "/notice/noticeWrite";
		}else {
			noticeService.getNoticeList(request, authentication, currentPage); //리스트 조회
			return "/notice/noticeMain";
		}
	}
	
	/**
	 * 공지사항 작성 호출
	 * 
	 * @param noticeTitle
	 * @param noticeContent
	 * @param request
	 * @param authentication
	 * @param currentPage
	 * @return
	 */
	@PostMapping("/write/action")
	public String noticeWriteAction(
			String noticeTitle
			, String noticeContent
			, String category
			,HttpServletRequest request
			,Authentication authentication
			, Integer currentPage) {
		if(AuthObjectUtil.authenticationConfirm(authentication, UserRolePlusEnum.ROLE_ADMIN.toString())) { //권한 체크
			noticeService.writeNotice(noticeTitle, noticeContent, category, request, authentication);
			noticeService.getNoticeList(request, authentication, currentPage); //리스트 조회
			return "/notice/noticeMain";
		}else {
			noticeService.getNoticeList(request, authentication, currentPage); //리스트 조회
			return "/notice/noticeMain";
		}
	}
	
	/**
	 * 공지사항 상세페이지 이동 호출
	 * 
	 * @param request
	 * @param noticeNo
	 * @return
	 */
	@GetMapping("/detail")
	public String noticeDetail(
			HttpServletRequest request
			,Authentication authentication
			, Integer noticeNo
			) {
		noticeService.getNoticeDetailInfo(request, authentication, noticeNo);
		return "/notice/noticeDetail";
	}
	
	/**
	 * 요청받은 게시글 정보를 삭제한다.
	 * 
	 * @param request
	 * @param authentication
	 * @param noticeNo
	 * @return
	 */
	@PostMapping("/delete")
	public String deleteNotice(	
			HttpServletRequest request
			,Authentication authentication
			, Integer noticeNo
			, Integer currentPage
			) {
		if(AuthObjectUtil.authenticationConfirm(authentication, UserRolePlusEnum.ROLE_ADMIN.toString())) { //권한 체크
			noticeService.deleteNoticeInfo(request, authentication, noticeNo);
			noticeService.getNoticeList(request, authentication, currentPage); //리스트 조회
			return "/notice/noticeMain";
		}else {
			noticeService.getNoticeList(request, authentication, currentPage); //리스트 조회
			return "/notice/noticeMain";
		}
	}
	
	@GetMapping("/update/main")
	public String updateNotice(
			HttpServletRequest request
			,Authentication authentication
			, Integer noticeNo
			, String noticeTitle
			, String noticeContent
			, String category
			) {
		if(AuthObjectUtil.authenticationConfirm(authentication, UserRolePlusEnum.ROLE_ADMIN.toString())) { //권한 체크
			request.setAttribute("regexAllPermit", RegexExpressionEnum.ALL_PERMIT.getRegex());
			request.setAttribute("noticeNo", noticeNo);
			request.setAttribute("noticeTitle", HtmlUtil.escapeDataMinusSpan(noticeTitle));
			request.setAttribute("noticeContent", HtmlUtil.escapeDataMinusSpan(noticeContent));
			request.setAttribute("category", category);
			return "/notice/noticeUpdate";
		}else {
			noticeService.getNoticeDetailInfo(request, authentication, noticeNo);
			return "/notice/noticeDetail";
		}

	}
	
	@PostMapping("/update/action")
	public String updateNoticeAction(
			HttpServletRequest request
			,Authentication authentication
			, Integer noticeNo
			, String noticeTitle
			, String noticeContent
			, String category
			) {
		if(AuthObjectUtil.authenticationConfirm(authentication, UserRolePlusEnum.ROLE_ADMIN.toString())) { //권한 체크
			noticeService.updateNoticeInfo(request, authentication, noticeNo, noticeTitle, noticeContent, category);
			noticeService.getNoticeDetailInfo(request, authentication, noticeNo);
			return "/notice/noticeDetail";
		}else {
			noticeService.getNoticeDetailInfo(request, authentication, noticeNo);
			return "/notice/noticeDetail";
		}
	}
	
}
