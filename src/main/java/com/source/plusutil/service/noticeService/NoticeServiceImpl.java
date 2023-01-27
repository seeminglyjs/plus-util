package com.source.plusutil.service.noticeService;

import com.source.plusutil.dto.etc.DateDto;
import com.source.plusutil.dto.notice.NoticeDto;
import com.source.plusutil.dto.notice.NoticeWriteDto;
import com.source.plusutil.enums.UserRolePlusEnum;
import com.source.plusutil.repository.noticeRepository.NoticeRepository;
import com.source.plusutil.utils.auth.AuthObjectUtil;
import com.source.plusutil.utils.etc.DateUtil;
import com.source.plusutil.utils.etc.PagingUtil;
import com.source.plusutil.utils.html.HtmlUtil;
import com.source.plusutil.utils.protect.XSSUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;

    /**
     * 공지사항 리스트를 가져오는 메소드
     *
     * @param request
     * @param authentication
     * @param currentPage
     */
    @Override
    public void getNoticeList(HttpServletRequest request, Authentication authentication, Integer currentPage) {
        if (currentPage == null) {//만약 페이지 null이면 0으로 초기화
            currentPage = 0;
        }

        if (currentPage < 0) {
            currentPage = 0;
        } // 0보다 작은 페이지 넘버는 존재할 수 없음

        if (AuthObjectUtil.authenticationConfirm(authentication, UserRolePlusEnum.ROLE_ADMIN.toString())) { //권한 체크
            request.setAttribute("noticeWriteRole", "ok"); //관리자일 경우 게시글 쓰기 권한 있음
        }
        int listSize = 10;

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

			request.setAttribute("pageExist", pageExist);
            request.setAttribute("startPage", noticePaging.getStartPage());
            request.setAttribute("endPage", noticePaging.getEndPage());
            request.setAttribute("totalPage", noticePaging.getTotalPage());
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
        @Override
        public void writeNotice (String noticeTitle, String noticeContent, String category, HttpServletRequest
        request, Authentication authentication){
            if (AuthObjectUtil.authenticationConfirm(authentication, UserRolePlusEnum.ROLE_ADMIN.toString())) { //권한 체크
                Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                String userEmail = ((UserDetails) principal).getUsername();

                NoticeWriteDto noticeWriteDto = new NoticeWriteDto(
                        XSSUtils.stripXSS(noticeTitle)
                        , XSSUtils.stripXSS(HtmlUtil.containLineSeparatorDataPlusBr(noticeContent))
                        , category
                        , userEmail
                        , DateUtil.getDateString());
                log.info("noticeWriteDto -> " + noticeWriteDto.toString());
                noticeRepository.save(NoticeDto.writeNotice(noticeWriteDto));
            } else {
                log.info("writeNotice authentication Deny");
                return;
            }
        }

        /**
         * 전체 페이지 정보를 카운트 한다.
         *
         * @param currentPage
         * @param listSize
         * @return
         */
        @Override
        public Integer getNoticeTotalPage (Integer currentPage, Integer listSize){
            Integer totalCount = 0;
            PageRequest pageRequest = PageRequest.of(currentPage, listSize);
            Page<NoticeDto> noticePageList = noticeRepository.findAll(pageRequest); //공지사항 리스트 정보를 가져온다.
            Integer totalNoticePage = noticePageList.getTotalPages(); //전체 페이지 정보를 가져온다.
            totalCount = totalNoticePage;
            return totalCount;
        }

        /**
         * 조회 번호에 따라 공지사항의 상세 정보를 가져온다.
         * @param authentication
         *
         */
        @Override
        public void getNoticeDetailInfo (HttpServletRequest request, Authentication authentication, Integer noticeNo){
            log.info("getNoticeDetailInfo noticeNo -> [" + noticeNo + "]");
            if (AuthObjectUtil.authenticationConfirm(authentication, UserRolePlusEnum.ROLE_ADMIN.toString())) { //권한 체크
                request.setAttribute("updateRoleCheck", true); //조회한 유저가 관리자면 게시글 수정 권한이 있음
            } else {
                request.setAttribute("updateRoleCheck", false);
            }
            if (noticeNo != null) {//조회한 공지사항 번호가 null 값이 아닐 경우
                Optional<NoticeDto> noticeDetailInfo = noticeRepository.findById(noticeNo);
                if (noticeDetailInfo.isPresent()) {//조회번호에 따른 공지사항 정보가 존재하면
                    log.info("NoticeDetailInfo -> " + noticeDetailInfo.get().toString());
                    NoticeDto noticeDetail = noticeDetailInfo.get();
                    noticeDetail.setTitle(HtmlUtil.escapeDataPlusSpan(noticeDetail.getTitle()));
                    noticeDetail.setContent(HtmlUtil.escapeDataPlusSpan(noticeDetail.getContent()));
                    DateDto dateDto = getDateInfo(noticeDetail);
                    request.setAttribute("noticeDetail", noticeDetail); //객체 저장
                    request.setAttribute("dateDto", dateDto); //날짜 객체 저장
                }
            }
        }

        /**
         * 상세 정보 조회시 날짜 포맷을 정돈 하는 메소드
         *
         * @param noticeDetail
         * @return
         */
        private DateDto getDateInfo (NoticeDto noticeDetail){
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

        /**
         * 전달받은 공지사항번호 기준으로 해당 공지사항을 삭제처리한다.
         *
         */
        @Override
        public void deleteNoticeInfo (HttpServletRequest request, Authentication authentication, Integer noticeNo){
            log.info("deleteNoticeInfo noticeNo -> [" + noticeNo + "]");
            if (!AuthObjectUtil.authenticationConfirm(authentication, UserRolePlusEnum.ROLE_ADMIN.toString())) { //권한 체크
                return; //권한 없으면 리턴
            } else {
                if (noticeNo == null) {
                    log.info("noticeNo [uull parameter] Return");
                    return;
                }
                noticeRepository.deleteById(noticeNo); //공지사항 번호 기준으로 해당 게시글 삭제
            }
        }

        /**
         * 공지사항 수정건에 대한 반영
         *
         */
        @Override
        public void updateNoticeInfo (HttpServletRequest request, Authentication authentication, Integer
        noticeNo, String noticeTitle, String noticeContent){
            log.info("updateNoticeInfo noticeNo -> [" + noticeNo + "]" + "noticeTitle ->[" + noticeTitle + "]" + "noticeContent -> [" + noticeContent + "]");
            if (!AuthObjectUtil.authenticationConfirm(authentication, UserRolePlusEnum.ROLE_ADMIN.toString())) { //권한 체크
                return; //권한 없으면 리턴
            } else {
                if (noticeNo == null || noticeTitle == null || noticeContent == null) {
                    log.info("updateNoticeInfo [uull parameter] Return");
                    return;
                }
                Optional<NoticeDto> noticeDetailInfo = noticeRepository.findById(noticeNo); //변경할 공지사항 정보를 우선적으로 가져온다.
                if(noticeDetailInfo.isPresent()){
                    NoticeDto noticeDto = noticeDetailInfo.get();
                    noticeDto.setTitle(XSSUtils.stripXSS(noticeTitle)); //제목 재설정
                    noticeDto.setContent(XSSUtils.stripXSS(HtmlUtil.containLineSeparatorDataPlusBr(noticeContent))); // 내용 재설정
                    noticeDto.setUpDateDate(DateUtil.getDateString()); //변경날짜 재설정
                    noticeRepository.save(noticeDto); //변경정보 저장
                }
            }
        }
    }
