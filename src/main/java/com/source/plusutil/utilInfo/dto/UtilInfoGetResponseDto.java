package com.source.plusutil.utilInfo.dto;

import com.source.plusutil.utilInfo.dto.entity.UtilInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UtilInfoGetResponseDto {
    List<UtilInfoDto> utilInfoDtoList;
    boolean isEmpty;

    public List<UtilInfoDto> getUtilInfoDtoList() {
        return utilInfoDtoList;
    }

    public void setUtilInfoDtoList(List<UtilInfoDto> utilInfoDtoList) {
        this.utilInfoDtoList = utilInfoDtoList;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    @Override
    public String toString() {
        return "UtilInfoGetResponseDto{" +
                "utilInfoDtoList=" + utilInfoDtoList +
                ", isEmpty=" + isEmpty +
                '}';
    }
}
