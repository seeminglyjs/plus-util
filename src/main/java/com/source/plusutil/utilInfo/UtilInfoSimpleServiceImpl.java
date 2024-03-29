package com.source.plusutil.utilInfo;


import com.source.plusutil.utilInfo.dto.*;
import com.source.plusutil.utilInfo.dto.entity.UtilInfoDto;
import com.source.plusutil.utilInfo.dto.entity.UtilLikesDto;
import com.source.plusutil.utilInfo.dto.entity.UtilViewsDto;
import com.source.plusutil.utilInfo.repository.UtilInfoQueryDSLRepository;
import com.source.plusutil.utilInfo.repository.UtilInfoRepository;
import com.source.plusutil.utilInfo.repository.UtilLikeRepository;
import com.source.plusutil.utilInfo.repository.UtilViewRepository;
import com.source.plusutil.utils.etc.DateUtil;
import com.source.plusutil.utils.http.HttpParamCheckUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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

    private final UtilLikeRepository utilLikeRepository;

    private final UtilInfoQueryDSLRepository utilInfoQueryDSLRepository;

    private final EntityManagerFactory entityManagerFactory;

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
        Optional<UtilInfoDto> utilInfoDtoOp = utilInfoRepository.findByUtilName(utilInfoInsertRequestDto.getUtilName());
        if (utilInfoDtoOp.isPresent()) { //유틸정보 수정 요청
            return utilInfoRepository.save(UtilInfoDto.builder()
                    .utilNo(utilInfoDtoOp.get().getUtilNo())
                    .utilName(utilInfoInsertRequestDto.getUtilName())
                    .utilDescription(utilInfoInsertRequestDto.getUtilDescription())
                    .utilEnrollDate(DateUtil.getDateStryyyyMMdd())
                    .utilViews(0)
                    .utilLikes(0)
                    .urlPath(utilInfoInsertRequestDto.getUrlPath())
                    .category(utilInfoInsertRequestDto.getCategory())
                    .subject(utilInfoInsertRequestDto.getSubject())
                    .build());

        } else {//유틸정보 신규 등록
            utilInfoDtoOp = utilInfoRepository.findByUrlPath(utilInfoInsertRequestDto.getUrlPath());
            if (utilInfoDtoOp.isPresent()) {
                log.info("===== 이미 등록된 정보가 있습니다. ======");
                log.info("===== 등록된 유틸정보 -> [" + utilInfoDtoOp.get() + "] =====");
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
            utilViewRepository.save(UtilViewsDto.builder()
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
        EntityManager entityManager = entityManagerFactory.createEntityManager(); //사용할 객체 entityManager 선언
        try{
            UtilInfoDto updateUtilInfoDto = entityManager.find(UtilInfoDto.class, utilInfoDto.getUtilNo());
            if(updateUtilInfoDto == null){
                Optional<UtilInfoDto> updateUtilInfoDtoOp = utilInfoRepository.findById(utilInfoDto.getUtilNo()); //없으면 DB 찾음
                if(updateUtilInfoDtoOp.isEmpty()){
                    log.info("===== [updateUtilInfoDtoOp].isEmpty() =====");
                    return UtilViewResponseDto.builder() //조회수 증가 완료된 객체 전달
                            .utilNo(utilInfoDto.getUtilNo())
                            .likeCount(utilInfoDto.getUtilLikes())
                            .viewCount(utilInfoDto.getUtilViews()) //실패했으니 그대로 전달
                            .build();
                }else{
                    updateUtilInfoDto = updateUtilInfoDtoOp.get();
                }
            }
            updateUtilInfoDto.setUtilViews(newViews);
            utilInfoRepository.save(updateUtilInfoDto);//객체 업데이트
        }finally {
            entityManager.close(); //사용 후 entityManager 종료
        }
        return UtilViewResponseDto.builder() //조회수 증가 완료된 객체 전달
                .utilNo(utilInfoDto.getUtilNo())
                .likeCount(utilInfoDto.getUtilLikes())
                .viewCount(newViews)
                .build();
    }

    @Override
    public List<UtilInfoDto> getUtilInfoList(String utilName) {
        return utilInfoRepository.findAll();
    }

    @Override
    public Optional<UtilInfoDto> getUtilInfoDetail(long utilNo) {
        return utilInfoRepository.findById(utilNo);
    }

    @Override
    public List<UtilInfoDto> getUtilTopList() {
        return utilInfoQueryDSLRepository.getTopFiveUtilInfo();
    }

    @Override
    public UtilInfoDto getUtilInfoDetailByUrlPath(String urlPath) {
        Optional<UtilInfoDto> UtilInfoDtoOp = utilInfoRepository.findByUrlPath(urlPath);
        return UtilInfoDtoOp.orElse(null);
    }

    @Override
    public UtilLikeResponseDto addUtilLike(HttpServletRequest request, UtilLikeRequestDto utilLikeRequestDto) {
        Map<String, String> myIpMap = HttpParamCheckUtil.localeCheck(request);

        //이미 저장된 건이 있는지 확인한다.
        Optional<UtilLikesDto> getUtilLikesDtoOp = utilLikeRepository.findByUtilNoAndLikeIp(utilLikeRequestDto.getUtilNo(), myIpMap.get("ip"));
        Optional<UtilInfoDto> utilInfoDtoOp = utilInfoRepository.findById(utilLikeRequestDto.getUtilNo());

        if (getUtilLikesDtoOp.isEmpty()) {
            //우선 조회 히스트 테이블에 저장한다.
            utilLikeRepository.save(UtilLikesDto.builder()
                    .utilNo(utilLikeRequestDto.getUtilNo())
                    .likeIp(myIpMap.get("ip"))
                    .build());

            if (utilInfoDtoOp.isPresent()) {//유틸 정보 가지고 있음
                return getUtilLikeResponseDto(utilInfoDtoOp.get(),1);
            } else {//utilInfo에 없는 데이터 요청시에 발생
                throw new RuntimeException();
            }
        } else {
            if (utilInfoDtoOp.isPresent()) {//유틸 정보 가지고 있음
                return UtilLikeResponseDto.builder()
                        .utilNo(utilLikeRequestDto.getUtilNo())
                        .like(true)
                        .likeCount(utilInfoDtoOp.get().getUtilLikes())
                        .viewCount(utilInfoDtoOp.get().getUtilViews())
                        .build();
            } else {
                throw new RuntimeException();
            }
        }
    }

    @Override
    public UtilLikeResponseDto getUtilLikeResponseDto(UtilInfoDto utilInfoDto, int count) {
        long newLikes = utilInfoDto.getUtilLikes() + count; //기존 조회수에 +1을 한다.
        EntityManager entityManager =  entityManagerFactory.createEntityManager();
        try{
            UtilInfoDto updateUtilInfoDto = entityManager.find(UtilInfoDto.class, utilInfoDto.getUtilNo()); //캐시 먼저 찾음
            if(updateUtilInfoDto == null){
                Optional<UtilInfoDto> updateUtilInfoDtoOp = utilInfoRepository.findById(utilInfoDto.getUtilNo()); //없으면 DB 찾음
                if(updateUtilInfoDtoOp.isEmpty()){
                    return UtilLikeResponseDto.builder() //조회수 증가 완료된 객체 전달
                            .utilNo(utilInfoDto.getUtilNo())
                            .likeCount(utilInfoDto.getUtilLikes())
                            .viewCount(utilInfoDto.getUtilLikes())
                            .like(false)
                            .build();
                }else{
                    updateUtilInfoDto = updateUtilInfoDtoOp.get();
                }
            }
            updateUtilInfoDto.setUtilLikes(newLikes);
            utilInfoRepository.save(updateUtilInfoDto);
        }finally {
            entityManager.close();
        }
        return UtilLikeResponseDto.builder() //조회수 증가 완료된 객체 전달
                .utilNo(utilInfoDto.getUtilNo())
                .likeCount(utilInfoDto.getUtilLikes())
                .viewCount(newLikes)
                .like(true)
                .build();
    }

    @Override
    public UtilLikesDto getLikeUtilInfo(HttpServletRequest request, long utilNo) {
        Map<String, String> myIpMap = HttpParamCheckUtil.localeCheck(request);
        Optional<UtilLikesDto> utilLikesDtoOp =  utilLikeRepository.findByUtilNoAndLikeIp(utilNo, myIpMap.get("ip"));
        return utilLikesDtoOp.orElse(null);
    }

    @Override
    public UtilLikeRevokeResponseDto revokeLikeUtilInfo(HttpServletRequest request, UtilLikeRevokeRequestDto utilLikeRevokeRequestDto) {
        Map<String, String> myIpMap = HttpParamCheckUtil.localeCheck(request);
        if(myIpMap.get("ip").equals("unknown")){//ip 확인이 되지 않을 경우
            return UtilLikeRevokeResponseDto.builder()
                    .utilNo(utilLikeRevokeRequestDto.getUtilNo())
                    .like(true)
                    .likeCount(-1)
                    .build();
        }else{
          utilLikeRepository.deleteByUtilNoAndLikeIp(utilLikeRevokeRequestDto.getUtilNo(),myIpMap.get("ip"));
          Optional<UtilInfoDto> utilInfoDtoOp = utilInfoRepository.findById(utilLikeRevokeRequestDto.getUtilNo());
          if(utilInfoDtoOp.isPresent()){
              long nowLikeCount = utilInfoDtoOp.get().getUtilLikes();
              UtilLikeResponseDto utilLikeResponseDto = getUtilLikeResponseDto(utilInfoDtoOp.get(),-1);
              if(utilLikeResponseDto.getLikeCount() >= nowLikeCount){ //like 차감이 정상적으로 되지 않았다면
                  return UtilLikeRevokeResponseDto.builder()
                          .utilNo(utilLikeRevokeRequestDto.getUtilNo())
                          .like(true)
                          .likeCount(utilLikeResponseDto.getLikeCount())
                          .build();
              }else{//정상 케이스
                  return UtilLikeRevokeResponseDto.builder()
                          .utilNo(utilLikeRevokeRequestDto.getUtilNo())
                          .like(false)
                          .likeCount(utilLikeResponseDto.getLikeCount())
                          .ip(myIpMap.get("ip"))
                          .build();
              }
          }else{//유틸정보가 존재하지 않을 경우[등록 필요]
              log.info("========================Util Info Empty========================");
              log.info("========================Util Info 등록필요========================");
              return UtilLikeRevokeResponseDto.builder()
                      .utilNo(utilLikeRevokeRequestDto.getUtilNo())
                      .like(true)
                      .likeCount(-1)
                      .build();
          }
        }
    }
}
