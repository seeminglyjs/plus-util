package com.source.plusutil.utilInfo;

import com.querydsl.core.BooleanBuilder;
import com.source.plusutil.utilInfo.dto.UtilInfoDeleteRequestDto;
import com.source.plusutil.utilInfo.dto.UtilInfoDto;
import com.source.plusutil.utilInfo.dto.UtilInfoInsertRequestDto;
import com.source.plusutil.utilInfo.dto.UtilInfoUpdateRequestDto;
import com.source.plusutil.utils.etc.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UtilInfoServiceImpl implements UtilInfoService {

    private final UtilInfoRepository utilInfoRepository;


    @Override
    public Page<UtilInfoDto> getUtilList(int limit) {
        if (limit == 0) { // 0일 경우 전체 조회
            return utilInfoRepository.findAll(PageRequest.of(0, Integer.MAX_VALUE,Sort.by(Sort.Direction.DESC, "utilNo")));
        }else{
            return utilInfoRepository.findAll(PageRequest.of(0,limit,Sort.by(Sort.Direction.DESC, "utilNo")));
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
        return utilInfoRepository.save(UtilInfoDto.builder()
                .utilName(utilInfoInsertRequestDto.getUtilName())
                .utilDescription(utilInfoInsertRequestDto.getUtilDescription())
                .utilEnrollDate(DateUtil.getDateStryyyyMMdd())
                .utilViews(0)
                .utilLikes(0)
                .build());
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


}
