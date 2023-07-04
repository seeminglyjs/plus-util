package com.source.plusutil.utilInfo;


import com.source.plusutil.utilInfo.dto.*;
import com.source.plusutil.utilInfo.repository.UtilInfoQueryDSLRepository;
import com.source.plusutil.utilInfo.repository.UtilInfoRepository;
import com.source.plusutil.utilInfo.repository.UtilViewRepository;
import com.source.plusutil.utils.etc.DateUtil;
import com.source.plusutil.utils.http.HttpParamCheckUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(rollbackOn = {Exception.class})
public class UtilInfoSimpleServiceImpl implements UtilInfoSimpleService {

    private final UtilInfoRepository utilInfoRepository;

    private final UtilViewRepository utilViewRepository;

    private final UtilInfoQueryDSLRepository utilInfoQueryDSLRepository;

    @Override
    public Page<UtilInfoDto> getUtilList(int limit) {
        if (limit == 0) { // 0일 경우 전체 조회
            return utilInfoRepository.findAll(PageRequest.of(0, Integer.MAX_VALUE, Sort.by(Sort.Direction.DESC, "utilNo")));
        } else {
            return utilInfoRepository.findAll(PageRequest.of(0, limit, Sort.by(Sort.Direction.DESC, "utilNo")));
        }
    }

    @Override
    public UtilInfoDto getUtilInfoById(long id) {
        return null;
    }

    @Override
    public UtilInfoDto getUtilInfoByName(String name) {
        return null;
    }

    @Override
    public UtilInfoDto addUtilInfo(UtilInfoInsertRequestDto utilInfoInsertRequestDto) {
        UtilInfoDto utilInfoDto = utilInfoRepository.findByUtilName(utilInfoInsertRequestDto.getUtilName());
        if(utilInfoInsertRequestDto.getUtilNo() != -1){ //유틸정보 수정 요청
            return utilInfoRepository.save(UtilInfoDto.builder()
                    .utilNo(utilInfoDto.getUtilNo())
                    .utilName(utilInfoInsertRequestDto.getUtilName())
                    .utilDescription(utilInfoInsertRequestDto.getUtilDescription())
                    .utilEnrollDate(DateUtil.getDateStryyyyMMdd())
                    .utilViews(0)
                    .utilLikes(0)
                    .urlPath(utilInfoInsertRequestDto.getUrlPath())
                    .category(utilInfoInsertRequestDto.getCategory())
                    .subject(utilInfoInsertRequestDto.getSubject())
                    .build());

        }else{//유틸정보 신규 등록
            if (utilInfoDto != null && !utilInfoDto.utilNameIsEmpty()) {
                log.info("===== 이미 등록된 정보가 있습니다. ======");
                log.info("===== 등록된 유틸정보 -> [" + utilInfoDto + "] =====");
                return null;
            }
            utilInfoDto = utilInfoRepository.findByUrlPath(utilInfoInsertRequestDto.getUrlPath());
            if (utilInfoDto != null && !utilInfoDto.utilNameIsEmpty()) {
                log.info("===== 이미 등록된 정보가 있습니다. ======");
                log.info("===== 등록된 유틸정보 -> [" + utilInfoDto + "] =====");
                return null;
            }
            return utilInfoRepository.save(UtilInfoDto.builder()
                    .utilName(utilInfoInsertRequestDto.getUtilName())
                    .utilDescription(utilInfoInsertRequestDto.getUtilDescription())
                    .utilEnrollDate(DateUtil.getDateStryyyyMMdd())
                    .utilViews(0)
                    .utilLikes(0)
                    .urlPath(utilInfoInsertRequestDto.getUrlPath())
                    .category(utilInfoInsertRequestDto.getCategory())
                    .subject(utilInfoInsertRequestDto.getSubject())
                    .build());    
        }
    }

    @Override
    public List<UtilInfoInsertRequestDto> addUtilInfoList(List<UtilInfoInsertRequestDto> utilInfoInsertRequestDtoList) {
        return null;
    }

    @Override
    public UtilInfoDto modifyUtilInfo(UtilInfoUpdateRequestDto utilInfoUpdateRequest) {
        return null;
    }

    @Override
    public List<UtilInfoUpdateRequestDto> modifyUtilInfoList(List<UtilInfoUpdateRequestDto> utilInfoUpdateRequestList) {
        return null;
    }

    @Override
    public long removeUtilInfo(UtilInfoDeleteRequestDto utilInfoDeleteRequestDto) {
        return 0;
    }

    @Override
    public long removeUtilInfoList(List<UtilInfoDeleteRequestDto> utilInfoDeleteRequestDtoList) {
        return 0;
    }

    @Override
    public UtilViewResponseDto addUtilView(HttpServletRequest request, UtilViewRequestDto utilViewRequestDto) throws RuntimeException {
        Map<String, String> myIpMap = HttpParamCheckUtil.localeCheck(request);

        String dateInfo = DateUtil.getDateStryyyyMMdd();//조회시점의 날짜 정보 저장 찰나의 순간 날짜 변경을 예방하기 위해 변수화

        //이미 저장된 건이 있는지 확인한다.
        UtilViewsDto getUtilViewsDto = utilViewRepository.findByUtilNoAndViewIpAndViewDate(utilViewRequestDto.getUtilNo(), myIpMap.get("ip"), dateInfo);

        if (getUtilViewsDto == null) {//오늘 일자 해당 아이피가 유틸 정보를 조회를 하지 않았다면
            //우선 조회 히스트 테이블에 저장한다.
            UtilViewsDto saveUtilViewsDto = utilViewRepository.save(UtilViewsDto.builder()
                    .utilNo(utilViewRequestDto.getUtilNo())
                    .viewIp(myIpMap.get("ip"))
                    .viewDate(dateInfo)
                    .build());

            //조회하는 util 정보를 가져온다.
            Optional<UtilInfoDto> utilInfoDtoOp = utilInfoRepository.findById(utilViewRequestDto.getUtilNo());
            if (utilInfoDtoOp.isPresent()) {//유틸 정보 가지고 있음
                return getUtilViewResponseDto(utilInfoDtoOp.get());
            } else {//utilInfo에 없는 데이터 요청시에 발생
                throw new RuntimeException();
            }
        } else {
            //조회하는 util 정보를 가져온다.
            Optional<UtilInfoDto> utilInfoDtoOp = utilInfoRepository.findById(utilViewRequestDto.getUtilNo());
            if (utilInfoDtoOp.isPresent()) {//유틸 정보 가지고 있음
                return getUtilViewResponseDto(utilInfoDtoOp.get());
            } else {//utilInfo에 없는 데이터 요청시에 발생
                throw new RuntimeException();
            }
        }
    }

    @Override
    public UtilViewResponseDto getUtilViewResponseDto(UtilInfoDto utilInfoDto) {
        long newViews = utilInfoDto.getUtilViews() + 1; //기존 조회수에 +1을 한다.
        utilInfoRepository.save(UtilInfoDto.builder()
                .utilNo(utilInfoDto.getUtilNo())
                .utilName(utilInfoDto.getUtilName())
                .utilDescription(utilInfoDto.getUtilDescription())
                .utilEnrollDate(utilInfoDto.getUtilEnrollDate())
                .utilLikes(utilInfoDto.getUtilLikes())
                .utilViews(newViews)
                .build());

        return UtilViewResponseDto.builder() //조회수 증가 완료된 객체 전달
                .utilNo(utilInfoDto.getUtilNo())
                .likeCount(utilInfoDto.getUtilLikes())
                .viewCount(newViews)
                .build();
    }

    @Override
    public List<UtilInfoDto> getUtilInfoList(String utilName) {
        if (utilName != null && !utilName.equals("")) {
            return utilInfoRepository.findAll();
        } else {
            return utilInfoRepository.findAll();
        }
    }

    @Override
    public Optional<UtilInfoDto> getUtilInfoDetail(long utilNo) {
        return utilInfoRepository.findById(utilNo);
    }

    @Override
    public List<UtilInfoDto> getUtilTopList() {
        return utilInfoQueryDSLRepository.getTopFiveUtilInfo();
    }
}
