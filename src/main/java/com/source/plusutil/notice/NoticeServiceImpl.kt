package com.source.plusutil.notice

import com.source.plusutil.enums.UserRolePlusEnum
import com.source.plusutil.notice.dto.*
import com.source.plusutil.utils.auth.AuthObjectUtil
import com.source.plusutil.utils.etc.DateUtil
import com.source.plusutil.utils.etc.PagingUtil
import io.github.oshai.KotlinLogging
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*

private val logger = KotlinLogging.logger {}
@Service
@Slf4j
@RequiredArgsConstructor
class NoticeServiceImpl(private val noticeRepository: NoticeRepository) : NoticeService {

    /*
     * 공지사항 리스트를 가져오는 메소드
     *
     * @param request
     * @param authentication
     * @param currentPage
     */
    override fun getNoticeList(authentication: Authentication?, currentPage: Int?): NoticeListDto? {
        var currentPage = currentPage
        if (currentPage == null) { //만약 페이지 null이면 0으로 초기화
            currentPage = 0
        }
        if (currentPage < 0) {
            currentPage = 0
        } // 0보다 작은 페이지 넘버는 존재할 수 없음
        var noticeWriteRole = ""
        noticeWriteRole = if (AuthObjectUtil.authenticationConfirm(authentication, UserRolePlusEnum.ROLE_ADMIN.toString())) { //권한 체크
            "ok" //관리자일 경우 게시글 쓰기 권한 있음
        } else "none"
        val listSize = 5
        val totalNoticePage = getNoticeTotalPage(currentPage, listSize) //전체 페이지 정보를 가져온다.
        if (currentPage > totalNoticePage!! - 1) { //요청된 페이지가 전체 페이지 보다 클경우
            currentPage = totalNoticePage - 1 //현재 페이지를 마지막 페이지로 변경해버린다. 페이지는 0부터 시작이기 때문 -1
        }
        var pageRequest: PageRequest? = null
        var noticePageList: Page<NoticeDto>? = null
        var pageExist = false
        var totalNoticeCount = 0L
        if (totalNoticePage > 0) { //공지사항이 적어도 1개는 있을경우
            pageRequest = PageRequest.of(currentPage, listSize, Sort.by(Sort.Direction.DESC, "noticeNo")) //현재 페이지와 리스트 크기 공지사항 번호기준 내림차순 정렬
            noticePageList = noticeRepository.findAll(pageRequest) //공지사항 리스트 정보를 가져온다.
            pageExist = true
            for (nd in noticePageList) { //정보 출력
                logger.info("noticePageList info -> " + nd.toString())
            }
            totalNoticeCount = noticePageList.getTotalElements()
        }
        val noticePaging = PagingUtil(totalNoticeCount, currentPage, listSize) //페이징 정보 셋팅
        logger.info("noticePaging -> $noticePaging")
        return NoticeListDto(
                pageExist,
                noticePaging.startPage,
                noticePaging.endPage,
                noticePaging.totalPage,
                noticePageList,
                currentPage,
                noticeWriteRole)
    }

    /*
     * 공지사항 작성하는 메소드
     *
     * @param noticeTitle
     * @param noticeContent
     * @param request
     * @param authentication
     */
    override fun writeNotice(noticeWriteRequestDto: NoticeWriteRequestDto, authentication: Authentication?): NoticeWriteResponseDto {
        logger.info("noticeTitle->")
        return if (AuthObjectUtil.authenticationConfirm(authentication, UserRolePlusEnum.ROLE_ADMIN.toString())) { //권한 체크
            val principal = SecurityContextHolder.getContext().authentication.principal
            val userEmail = (principal as UserDetails).username
            logger.info("$noticeWriteRequestDto / $userEmail")
            noticeRepository!!.save(NoticeDto.writeNotice(Objects.requireNonNull(makeNoticeWriteDto(userEmail, noticeWriteRequestDto))))
            NoticeWriteResponseDto(true)
        } else {
            logger.info("writeNotice authentication Deny")
            NoticeWriteResponseDto()
        }
    }

    override fun makeNoticeWriteDto(userEmail: String?, noticeWriteRequestDto: NoticeWriteRequestDto?): NoticeWriteDto? {
        return NoticeWriteDto(
                noticeWriteRequestDto?.noticeTitle ?: "제목없음", Objects.requireNonNull(noticeWriteRequestDto!!.noticeContent), noticeWriteRequestDto.category, userEmail, DateUtil.getDateString())
    }

    /*
     * 전체 페이지 정보를 카운트 한다.
     *
     * @param currentPage
     * @param listSize
     * @return
     */
    override fun getNoticeTotalPage(currentPage: Int?, listSize: Int?): Int? {
        var totalCount = 0
        val pageRequest = PageRequest.of(currentPage!!, listSize!!)
        val noticePageList = noticeRepository!!.findAll(pageRequest) //공지사항 리스트 정보를 가져온다.
        totalCount = noticePageList.totalPages
        return totalCount
    }

    /*
     * 조회 번호에 따라 공지사항의 상세 정보를 가져온다.
     * @param authentication
     *
     */
    override fun getNoticeDetailInfo(authentication: Authentication?, noticeNo: Long?): NoticeDetailDto? {
        var updateRoleCheck = false
        logger.info("getNoticeDetailInfo noticeNo -> [$noticeNo]")
        if (AuthObjectUtil.authenticationConfirm(authentication, UserRolePlusEnum.ROLE_ADMIN.toString())) { //권한 체크
            updateRoleCheck = true //조회한 유저가 관리자면 게시글 수정 권한이 있음
        }
        if (noticeNo != null) { //조회한 공지사항 번호가 null 값이 아닐 경우
            val noticeDetailInfo = noticeRepository!!.findById(noticeNo)
            return makeNoticeDtoWhenDetail(noticeDetailInfo, updateRoleCheck)
        }
        return null
    }

    override fun makeNoticeDtoWhenDetail(noticeDetailInfo: Optional<NoticeDto?>?, updateRoleCheck: Boolean): NoticeDetailDto? {
        return if (Objects.requireNonNull(noticeDetailInfo)?.isPresent == true) { //조회번호에 따른 공지사항 정보가 존재하면
            val noticeDetail = noticeDetailInfo!!.get()
            noticeDetail.title = noticeDetail.title
            noticeDetail.content = noticeDetail.content
            val dateDto = getDateInfo(noticeDetail)
            NoticeDetailDto(noticeDetail, dateDto, updateRoleCheck)
        } else {
            null
        }
    }

    /*
     * 상세 정보 조회시 날짜 포맷을 정돈 하는 메소드
     *
     * @param noticeDetail
     * @return
     */
    private fun getDateInfo(noticeDetail: NoticeDto): DateDto {
        val dateDto = DateDto()
        var updateDate: String? = null
        var writeDate: String? = null
        try {
            updateDate = noticeDetail.upDateDate
        } catch (e: Exception) {
            logger.info("updateDate is Empty ====")
        }
        if (updateDate != null && updateDate != "") { //수정 정보 존재함
            dateDto.day = DateUtil.getDateStrCommaLen14(updateDate)
            dateDto.time = DateUtil.getTimeStrCommaLen14(updateDate)
        } else {
            writeDate = noticeDetail.writeDate
            dateDto.day = DateUtil.getDateStrCommaLen14(writeDate)
            dateDto.time = DateUtil.getTimeStrCommaLen14(writeDate)
        }
        logger.info("getDateInfo ->$dateDto")
        return dateDto
    }

    /*
     * 전달받은 공지사항번호 기준으로 해당 공지사항을 삭제처리한다.
     *
     */
    override fun deleteNoticeInfo(noticeDeleteRequestDto: NoticeDeleteRequestDto): NoticeDeleteResponseDto? {
        logger.info("deleteNoticeInfo noticeNo -> [" + noticeDeleteRequestDto.noticeNo + "]")
        if (!AuthObjectUtil.authenticationConfirm(SecurityContextHolder.getContext().authentication, UserRolePlusEnum.ROLE_ADMIN.toString())) { //권한 체크
            return NoticeDeleteResponseDto() //권한 없으면 리턴
        } else {
            noticeRepository!!.deleteById(noticeDeleteRequestDto.noticeNo) //공지사항 번호 기준으로 해당 게시글 삭제
        }
        return NoticeDeleteResponseDto(true, noticeDeleteRequestDto.currentPage)
    }

    /*
     * 공지사항 수정건에 대한 반영
     */
    override fun updateNoticeInfo(noticeRequestDto: NoticeUpdateRequestDto): NoticeUpdateResponseDto? {
        logger.info(noticeRequestDto.toString())
        if (!AuthObjectUtil.authenticationConfirm(SecurityContextHolder.getContext().authentication, UserRolePlusEnum.ROLE_ADMIN.toString())) { //권한 체크
            return NoticeUpdateResponseDto(false, noticeRequestDto.noticeNo) //권한 없으면 리턴
        } else {
            val noticeDetailInfo = noticeRepository!!.findById(noticeRequestDto.noticeNo) //변경할 공지사항 정보를 우선적으로 가져온다.
            val noticeDto = makeNoticeDtoWhenUpdate(noticeDetailInfo, noticeRequestDto)
            if (noticeDto != null) noticeRepository.save(noticeDto) //변경정보 저장
            else return NoticeUpdateResponseDto(false, noticeRequestDto.noticeNo) //정보없음
        }
        return NoticeUpdateResponseDto(true, noticeRequestDto.noticeNo)
    }

    override fun makeNoticeDtoWhenUpdate(noticeDetailInfo: Optional<NoticeDto?>?, noticeRequestDto: NoticeUpdateRequestDto?): NoticeDto? {
        return if (Objects.requireNonNull(noticeDetailInfo)?.isPresent == true) {
            val noticeDto = noticeDetailInfo!!.get()
            noticeDto.title = noticeRequestDto!!.noticeTitle //제목 재설정
            noticeDto.content = noticeRequestDto.noticeContent // 내용 재설정
            noticeDto.category = noticeRequestDto.category
            noticeDto.upDateDate = DateUtil.getDateString() //변경날짜 재설정
            noticeDto
        } else {
            null
        }
    }
}