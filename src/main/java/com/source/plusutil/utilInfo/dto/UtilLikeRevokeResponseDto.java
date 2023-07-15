package com.source.plusutil.utilInfo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UtilLikeRevokeResponseDto {
    long utilNo;
    long likeCount;
    boolean like;
    String ip;
    public long getUtilNo() {
        return utilNo;
    }

    public void setUtilNo(long utilNo) {
        this.utilNo = utilNo;
    }

    public long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(long likeCount) {
        this.likeCount = likeCount;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        like = like;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}

