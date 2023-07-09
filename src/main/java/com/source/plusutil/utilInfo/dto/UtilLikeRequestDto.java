package com.source.plusutil.utilInfo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UtilLikeRequestDto {
    long utilNo;

    public long getUtilNo() {
        return utilNo;
    }

    public void setUtilNo(long utilNo) {
        this.utilNo = utilNo;
    }
}
