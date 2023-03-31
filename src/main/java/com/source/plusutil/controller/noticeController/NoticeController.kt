package com.source.plusutil.controller.noticeController

import com.source.plusutil.dto.notice.*
import com.source.plusutil.enums.UserRolePlusEnum
import com.source.plusutil.enums.regex.RegexExpressionEnum
import com.source.plusutil.service.noticeService.NoticeService
import com.source.plusutil.utils.auth.AuthObjectUtil
import com.source.plusutil.utils.html.HtmlUtil
import lombok.RequiredArgsConstructor
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/plus/notice")
@RequiredArgsConstructor
class NoticeController(private val noticeService: NoticeService) {

    @GetMapping("/list")
    @ResponseBody
    fun noticeMain(currentPage: Int?): NoticeListDto? {
        return noticeService.getNoticeList(SecurityContextHolder.getContext().authentication, currentPage)
    }

    /**
     * 공지사항 작성 페이지 호출
     *
     * @param authentication
     * @param currentPage
     * @return
     */
    @PostMapping("/write")
    @ResponseBody
    fun noticeWrite(
           @RequestBody noticeWriteRequestDto: NoticeWriteRequestDto): NoticeWriteResponseDto {
        return if (AuthObjectUtil.authenticationConfirm(SecurityContextHolder.getContext().authentication, UserRolePlusEnum.ROLE_ADMIN.toString())) { //권한 체크
            noticeService.writeNotice(noticeWriteRequestDto, SecurityContextHolder.getContext().authentication)
        } else {
            return NoticeWriteResponseDto();
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
    @ResponseBody
    fun noticeDetail(authentication: Authentication?, noticeNo: Long?): NoticeDetailDto? {
        return noticeService.getNoticeDetailInfo(authentication,noticeNo);
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
    @ResponseBody
    fun deleteNotice(
            noticeNo: Long?, currentPage: Int?
    ): NoticeDeleteResponseDto? {
        return if (AuthObjectUtil.authenticationConfirm(SecurityContextHolder.getContext().authentication, UserRolePlusEnum.ROLE_ADMIN.toString())) { //권한 체크
            noticeService.deleteNoticeInfo(noticeNo, currentPage);
        } else {
            NoticeDeleteResponseDto();
        }
    }

    @GetMapping("/update/main")
    fun updateNotice(
            request: HttpServletRequest, authentication: Authentication?, noticeNo: Long?, noticeTitle: String?, noticeContent: String?, category: String?
    ): String {
        return if (AuthObjectUtil.authenticationConfirm(authentication, UserRolePlusEnum.ROLE_ADMIN.toString())) { //권한 체크
            request.setAttribute("regexAllPermit", RegexExpressionEnum.ALL_PERMIT.regex)
            request.setAttribute("noticeNo", noticeNo)
            request.setAttribute("noticeTitle", HtmlUtil.escapeDataMinusSpan(noticeTitle))
            request.setAttribute("noticeContent", HtmlUtil.escapeDataMinusSpan(noticeContent))
            request.setAttribute("category", category)
            "/notice/noticeUpdate"
        } else {
            noticeService.getNoticeDetailInfo(authentication, noticeNo)
            "/notice/noticeDetail"
        }
    }

    @PostMapping("/update/action")
    fun updateNoticeAction(
            request: HttpServletRequest?, authentication: Authentication?, noticeNo: Long?, noticeTitle: String?, noticeContent: String?, category: String?
    ): String {
        return if (AuthObjectUtil.authenticationConfirm(authentication, UserRolePlusEnum.ROLE_ADMIN.toString())) { //권한 체크
            noticeService.updateNoticeInfo(request, authentication, noticeNo, noticeTitle, noticeContent, category)
            noticeService.getNoticeDetailInfo(authentication, noticeNo)
            "/notice/noticeDetail"
        } else {
            noticeService.getNoticeDetailInfo(authentication, noticeNo)
            "/notice/noticeDetail"
        }
    }
}