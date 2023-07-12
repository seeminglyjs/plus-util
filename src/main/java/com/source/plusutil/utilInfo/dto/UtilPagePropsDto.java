package com.source.plusutil.utilInfo.dto;

import com.source.plusutil.utilInfo.dto.entity.UtilInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UtilPagePropsDto {
    UtilInfoDto data;

    public UtilInfoDto getData() {
        return data;
    }

    public void setData(UtilInfoDto data) {
        this.data = data;
    }
}
