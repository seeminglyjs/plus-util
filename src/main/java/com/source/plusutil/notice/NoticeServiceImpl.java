package com.source.plusutil.notice;

import com.source.plusutil.dto.etc.DateDto;
import com.source.plusutil.enums.UserRolePlusEnum;
import com.source.plusutil.notice.dto.*;
import com.source.plusutil.utils.auth.AuthObjectUtil;
import com.source.plusutil.utils.etc.DateUtil;
import com.source.plusutil.utils.etc.PagingUtil;
import com.source.plusutil.utils.html.HtmlUtil;
import com.source.plusutil.utils.protect.XSSUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;

    /*
     * 공지사항 리스트를 가져오는 메소드
     *
     * @param request
     * @param authentication
     * @param currentPage
     */
    @Override
    public NoticeListDto getNoticeList(Authentication authentication, Integer currentPage) {
        if (currentPage == null) {//만약 페이지 null이면 0으로 초기화
            currentPage = 0;
        }

        if (currentPage < 0) {
            currentPage = 0;
        } // 0보다 작은 페이지 넘버는 존재할 수 없음

        String noticeWriteRole = "";

        if (AuthObjectUtil.authenticationConfirm(authentication, UserRolePlusEnum.ROLE_ADMIN.toString())) { //권한 체크
            noticeWriteRole = "ok"; //관리자일 경우 게시글 쓰기 권한 있음
        } else noticeWriteRole = "none";
        int listSize = 5;

        Integer totalNoticePage = getNoticeTotalPage(currentPage, listSize); //전체 페이지 정보를 가져온다.
        if (currentPage > totalNoticePage - 1) {//요청된 페이지가 전체 페이지 보다 클경우
            currentPage = totalNoticePage - 1; //현재 페이지를 마지막 페이지로 변경해버린다. 페이지는 0부터 시작이기 때문 -1
        }
        PageRequest pageRequest = null;
        Page<NoticeDto> noticePageList = null;
        boolean pageExist = false;
        long totalNoticeCount = 0L;
        if (totalNoticePage > 0) { //공지사항이 적어도 1개는 있을경우
            pageRequest = PageRequest.of(currentPage, listSize, Sort.by(Sort.Direction.DESC, "noticeNo")); //현재 페이지와 리스트 크기 공지사항 번호기준 내림차순 정렬
            noticePageList = noticeRepository.findAll(pageRequest); //공지사항 리스트 정보를 가져온다.
            pageExist = true;
            for (NoticeDto nd : noticePageList) {//정보 출력
                log.info("noticePageList info -> " + nd.toString());
            }
            totalNoticeCount = noticePageList.getTotalElements();
        }

        PagingUtil noticePaging = new PagingUtil(totalNoticeCount, currentPage, listSize); //페이징 정보 셋팅

        log.info("noticePaging -> " + noticePaging.toString());

        return new NoticeListDto(
                pageExist,
                noticePaging.getStartPage(),
                noticePaging.getEndPage(),
                noticePaging.getTotalPage(),
                Objects.requireNonNull(noticePageList, "noticePageList is null exception"),
                currentPage,
                noticeWriteRole);
    }

    /*
     * 공지사항 작성하는 메소드
     *
     * @param noticeTitle
     * @param noticeContent
     * @param request
     * @param authentication
     */
    @NotNull
    @Override
    public NoticeWriteResponseDto writeNotice(@NotNull NoticeWriteRequestDto noticeWriteRequestDto, Authentication authentication) {
        log.info("noticeTitle->");
        if (AuthObjectUtil.authenticationConfirm(authentication, UserRolePlusEnum.ROLE_ADMIN.toString())) { //권한 체크
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String userEmail = ((UserDetails) principal).getUsername();
            log.info(noticeWriteRequestDto.toString() + " / " + userEmail);
            noticeRepository.save(NoticeDto.writeNotice(Objects.requireNonNull(makeNoticeWriteDto(userEmail, noticeWriteRequestDto))));
            return new NoticeWriteResponseDto(true);
        } else {
            log.info("writeNotice authentication Deny");
            return new NoticeWriteResponseDto();
        }
    }

    @Override
    public NoticeWriteDto makeNoticeWriteDto(String userEmail, NoticeWriteRequestDto noticeWriteRequestDto) {
        return new NoticeWriteDto(
                Objects.requireNonNull(noticeWriteRequestDto).getNoticeTitle()
                , Objects.requireNonNull(noticeWriteRequestDto.getNoticeContent())
                , noticeWriteRequestDto.getCategory()
                , userEmail
                , DateUtil.getDateString());
    }

    /*
     * 전체 페이지 정보를 카운트 한다.
     *
     * @param currentPage
     * @param listSize
     * @return
     */
    @Override
    public Integer getNoticeTotalPage(Integer currentPage, Integer listSize) {
        int totalCount = 0;
        PageRequest pageRequest = PageRequest.of(currentPage, listSize);
        Page<NoticeDto> noticePageList = noticeRepository.findAll(pageRequest); //공지사항 리스트 정보를 가져온다.
        totalCount = noticePageList.getTotalPages();
        return totalCount;
    }

    /*
     * 조회 번호에 따라 공지사항의 상세 정보를 가져온다.
     * @param authentication
     *
     */
    @Override
    public NoticeDetailDto getNoticeDetailInfo(Authentication authentication, Long noticeNo) {
        boolean updateRoleCheck = false;
        log.info("getNoticeDetailInfo noticeNo -> [" + noticeNo + "]");
        if (AuthObjectUtil.authenticationConfirm(authentication, UserRolePlusEnum.ROLE_ADMIN.toString())) { //권한 체크
            updateRoleCheck = true; //조회한 유저가 관리자면 게시글 수정 권한이 있음
        }
        if (noticeNo != null) {//조회한 공지사항 번호가 null 값이 아닐 경우
            Optional<NoticeDto> noticeDetailInfo = noticeRepository.findById(noticeNo);
            return makeNoticeDtoWhenDetail(noticeDetailInfo, updateRoleCheck);
        }
        return null;
    }

    @Override
    public NoticeDetailDto makeNoticeDtoWhenDetail(Optional<NoticeDto> noticeDetailInfo, boolean updateRoleCheck) {
        if (Objects.requireNonNull(noticeDetailInfo).isPresent()) {//조회번호에 따른 공지사항 정보가 존재하면
            NoticeDto noticeDetail = noticeDetailInfo.get();
            noticeDetail.setTitle(noticeDetail.getTitle());
            noticeDetail.setContent(noticeDetail.getContent());
            DateDto dateDto = getDateInfo(noticeDetail);
            return new NoticeDetailDto(noticeDetail, dateDto, updateRoleCheck);
        } else {
            return null;
        }
    }

    /*
     * 상세 정보 조회시 날짜 포맷을 정돈 하는 메소드
     *
     * @param noticeDetail
     * @return
     */
    private DateDto getDateInfo(NoticeDto noticeDetail) {
        DateDto dateDto = new DateDto();
        String updateDate = null;
        String writeDate = null;
        try {
            updateDate = noticeDetail.getUpDateDate();
        } catch (Exception e) {
            log.info("updateDate is Empty ====");
        }
        if (updateDate != null && !updateDate.equals("")) {//수정 정보 존재함
            dateDto.setDay(DateUtil.getDateStrCommaLen14(updateDate));
            dateDto.setTime(DateUtil.getTimeStrCommaLen14(updateDate));
        } else {
            writeDate = noticeDetail.getWriteDate();
            dateDto.setDay(DateUtil.getDateStrCommaLen14(writeDate));
            dateDto.setTime(DateUtil.getTimeStrCommaLen14(writeDate));
        }
        log.info("getDateInfo ->" + dateDto.toString());
        return dateDto;
    }

    /*
     * 전달받은 공지사항번호 기준으로 해당 공지사항을 삭제처리한다.
     *
     */
    @Override
    public NoticeDeleteResponseDto deleteNoticeInfo(@NotNull NoticeDeleteRequestDto noticeDeleteRequestDto) {
        log.info("deleteNoticeInfo noticeNo -> [" + noticeDeleteRequestDto.getNoticeNo() + "]");
        if (!AuthObjectUtil.authenticationConfirm(SecurityContextHolder.getContext().getAuthentication(), UserRolePlusEnum.ROLE_ADMIN.toString())) { //권한 체크
            return new NoticeDeleteResponseDto(); //권한 없으면 리턴
        } else {
            noticeRepository.deleteById(noticeDeleteRequestDto.getNoticeNo()); //공지사항 번호 기준으로 해당 게시글 삭제
        }
        return new NoticeDeleteResponseDto(true, noticeDeleteRequestDto.getCurrentPage());
    }

    /*
     * 공지사항 수정건에 대한 반영
     */
    @Override
    public NoticeUpdateResponseDto updateNoticeInfo(@NotNull NoticeUpdateRequestDto noticeRequestDto) {
        log.info(noticeRequestDto.toString());
        if (!AuthObjectUtil.authenticationConfirm(SecurityContextHolder.getContext().getAuthentication(), UserRolePlusEnum.ROLE_ADMIN.toString())) { //권한 체크
            return new NoticeUpdateResponseDto(false, noticeRequestDto.getNoticeNo()); //권한 없으면 리턴
        } else {
            Optional<NoticeDto> noticeDetailInfo = noticeRepository.findById(noticeRequestDto.getNoticeNo()); //변경할 공지사항 정보를 우선적으로 가져온다.
            NoticeDto noticeDto = makeNoticeDtoWhenUpdate(noticeDetailInfo, noticeRequestDto);
            if (noticeDto != null) noticeRepository.save(noticeDto); //변경정보 저장
            else return new NoticeUpdateResponseDto(false, noticeRequestDto.getNoticeNo()); //정보없음
        }
        return new NoticeUpdateResponseDto(true, noticeRequestDto.getNoticeNo());
    }

    public NoticeDto makeNoticeDtoWhenUpdate(Optional<NoticeDto> noticeDetailInfo, NoticeUpdateRequestDto noticeRequestDto) {
        if (Objects.requireNonNull(noticeDetailInfo).isPresent()) {
            NoticeDto noticeDto = noticeDetailInfo.get();
            noticeDto.setTitle(noticeRequestDto.getNoticeTitle()); //제목 재설정
            noticeDto.setContent(noticeRequestDto.getNoticeContent()); // 내용 재설정
            noticeDto.setCategory(noticeRequestDto.getCategory());
            noticeDto.setUpDateDate(DateUtil.getDateString()); //변경날짜 재설정
            return noticeDto;
        } else {
            return null;
        }
    }
}
