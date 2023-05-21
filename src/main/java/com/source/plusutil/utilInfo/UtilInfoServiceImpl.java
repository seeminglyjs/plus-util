package com.source.plusutil.utilInfo;

import com.source.plusutil.utilInfo.dto.UtilInfoDeleteRequestDto;
import com.source.plusutil.utilInfo.dto.UtilInfoDto;
import com.source.plusutil.utilInfo.dto.UtilInfoInsertRequestDto;
import com.source.plusutil.utilInfo.dto.UtilInfoUpdateRequestDto;
import com.source.plusutil.utils.etc.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UtilInfoServiceImpl implements UtilInfoService {

    private final UtilInfoRepository utilInfoRepository;


    @Override
    public List<UtilInfoDto> getUtilList(int limit) {
        if (limit == 0) { // 0일 경우 전체 조회
            return utilInfoRepository.findAll(Sort.by(Sort.Direction.DESC, "util_no"));
        }
        return null;
    }

    @Override
    public UtilInfoDto findUtilInfoById(long id) {
        return null;
    }

    @Override
    public UtilInfoDto findUtilInfoByName(String name) {
        return null;
    }

    @Override
    public UtilInfoDto insertUtilInfo(UtilInfoInsertRequestDto utilInfoInsertRequestDto) {
        return utilInfoRepository.save(UtilInfoDto.builder()
                .utilName(utilInfoInsertRequestDto.getUtilName())
                .utilDescription(utilInfoInsertRequestDto.getUtilDescription())
                .utilEnrollDate(DateUtil.getDateStryyyyMMdd())
                .utilViews(0)
                .utilLikes(0)
                .build());
    }

    @Override
    public List<UtilInfoInsertRequestDto> insertUtilInfoList(List<UtilInfoInsertRequestDto> utilInfoInsertRequestDtoList) {
        return null;
    }

    @Override
    public UtilInfoDto updateUtilInfo(UtilInfoUpdateRequestDto utilInfoUpdateRequest) {
        return null;
    }

    @Override
    public List<UtilInfoUpdateRequestDto> updateUtilInfoList(List<UtilInfoUpdateRequestDto> utilInfoUpdateRequestList) {
        return null;
    }

    @Override
    public long deleteUtilInfo(UtilInfoDeleteRequestDto utilInfoDeleteRequestDto) {
        return 0;
    }

    @Override
    public long deleteUtilInfoList(List<UtilInfoDeleteRequestDto> utilInfoDeleteRequestDtoList) {
        return 0;
    }


}
