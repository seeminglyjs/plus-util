package com.source.plusutil.controller.noticeController

import com.source.plusutil.dto.notice.NoticeDetailDto
import com.source.plusutil.dto.notice.NoticeListDto
import com.source.plusutil.enums.UserRolePlusEnum
import com.source.plusutil.enums.regex.RegexExpressionEnum
import com.source.plusutil.service.noticeService.NoticeServiceImpl
import com.source.plusutil.utils.auth.AuthObjectUtil
import com.source.plusutil.utils.html.HtmlUtil
import lombok.RequiredArgsConstructor
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/plus/notice")
@RequiredArgsConstructor
class NoticeController(private val noticeService: NoticeServiceImpl) {

    @GetMapping("/list")
    @ResponseBody
    fun noticeMain(authentication: Authentication?, currentPage: Int?): NoticeListDto? {
        return noticeService.getNoticeList(authentication, currentPage)
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
    fun noticeWrite(request: HttpServletRequest, authentication: Authentication?, currentPage: Int?): String {
        return if (AuthObjectUtil.authenticationConfirm(authentication, UserRolePlusEnum.ROLE_ADMIN.toString())) { //권한 체크
            request.setAttribute("regexAllPermit", RegexExpressionEnum.ALL_PERMIT.regex)
            "/notice/noticeWrite"
        } else {
            noticeService.getNoticeList(authentication, currentPage)
            "/notice/noticeMain"
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
    fun noticeWriteAction(
            noticeTitle: String?, noticeContent: String?, category: String?, request: HttpServletRequest?, authentication: Authentication?, currentPage: Int?): String {
        return if (AuthObjectUtil.authenticationConfirm(authentication, UserRolePlusEnum.ROLE_ADMIN.toString())) { //권한 체크
            noticeService.writeNotice(noticeTitle, noticeContent, category, request, authentication)
            noticeService.getNoticeList(authentication, currentPage)
            "/notice/noticeMain"
        } else {
            noticeService.getNoticeList(authentication, currentPage)
            "/notice/noticeMain"
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
    fun noticeDetail(authentication: Authentication?, noticeNo: Int?): NoticeDetailDto? {
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
    fun deleteNotice(
            request: HttpServletRequest?, authentication: Authentication?, noticeNo: Int?, currentPage: Int?
    ): String {
        return if (AuthObjectUtil.authenticationConfirm(authentication, UserRolePlusEnum.ROLE_ADMIN.toString())) { //권한 체크
            noticeService.deleteNoticeInfo(request, authentication, noticeNo)
            noticeService.getNoticeList(authentication, currentPage)
            "/notice/noticeMain"
        } else {
            noticeService.getNoticeList(authentication, currentPage)
            "/notice/noticeMain"
        }
    }

    @GetMapping("/update/main")
    fun updateNotice(
            request: HttpServletRequest, authentication: Authentication?, noticeNo: Int?, noticeTitle: String?, noticeContent: String?, category: String?
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
            request: HttpServletRequest?, authentication: Authentication?, noticeNo: Int?, noticeTitle: String?, noticeContent: String?, category: String?
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