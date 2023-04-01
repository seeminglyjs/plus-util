package com.source.plusutil.notice

import com.source.plusutil.enums.UserRolePlusEnum
import com.source.plusutil.enums.regex.RegexExpressionEnum
import com.source.plusutil.notice.dto.*
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

    /*
     * 공지사항 작성 페이지 호출
     *
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

    /*
     * 공지사항 상세페이지 이동 호출
     *
     */
    @GetMapping("/detail")
    @ResponseBody
    fun noticeDetail(noticeNo: Long?): NoticeDetailDto? {
        return noticeService.getNoticeDetailInfo(SecurityContextHolder.getContext().authentication,noticeNo);
    }

    /*
     * 요청받은 게시글 정보를 삭제한다.
     *
     */
    @DeleteMapping("/remove")
    @ResponseBody
    fun deleteNotice(
            @RequestBody noticeDeleteRequestDto: NoticeDeleteRequestDto
    ): NoticeDeleteResponseDto? {
        return if (AuthObjectUtil.authenticationConfirm(SecurityContextHolder.getContext().authentication, UserRolePlusEnum.ROLE_ADMIN.toString())) { //권한 체크
            noticeService.deleteNoticeInfo(noticeDeleteRequestDto);
        } else {
            NoticeDeleteResponseDto();
        }
    }

    @PutMapping("/update")
    @ResponseBody
    fun updateNotice(
           @RequestBody noticeUpdateRequestDto : NoticeUpdateRequestDto
    ): NoticeUpdateResponseDto? {
        return if (AuthObjectUtil.authenticationConfirm(SecurityContextHolder.getContext().authentication, UserRolePlusEnum.ROLE_ADMIN.toString())) { //권한 체크
            noticeService.updateNoticeInfo(noticeUpdateRequestDto)
        } else {
            NoticeUpdateResponseDto(false,noticeUpdateRequestDto.noticeNo)
        }
    }
}