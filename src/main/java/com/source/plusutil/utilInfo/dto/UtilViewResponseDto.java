package com.source.plusutil.utilInfo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UtilViewResponseDto {
    long utilNo;
    long viewCount;
    long likeCount;

    @Override
    public String toString() {
        return "UtilViewResponseDto{" +
                "utilNo=" + utilNo +
                ", viewCount=" + viewCount +
                ", likeCount=" + likeCount +
                '}';
    }

    public long getUtilNo() {
        return utilNo;
    }

    public void setUtilNo(long utilNo) {
        this.utilNo = utilNo;
    }

    public long getViewCount() {
        return viewCount;
    }

    public void setViewCount(long viewCount) {
        this.viewCount = viewCount;
    }

    public long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(long likeCount) {
        this.likeCount = likeCount;
    }
}
