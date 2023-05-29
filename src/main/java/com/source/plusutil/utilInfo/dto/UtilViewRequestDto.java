package com.source.plusutil.utilInfo.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UtilViewRequestDto {
    long utilNo;
    @Override
    public String toString() {
        return "UtilViewRequestDto{" +
                "utilNo=" + utilNo +
                '}';
    }

    public long getUtilNo() {
        return utilNo;
    }

    public void setUtilNo(long utilNo) {
        this.utilNo = utilNo;
    }
}
