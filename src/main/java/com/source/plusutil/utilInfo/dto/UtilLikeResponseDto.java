package com.source.plusutil.utilInfo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UtilLikeResponseDto {

    long utilNo;
    long viewCount;
    long likeCount;

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
