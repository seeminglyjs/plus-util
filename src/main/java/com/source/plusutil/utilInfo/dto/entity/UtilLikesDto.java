package com.source.plusutil.utilInfo.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="tb_util_likes")
public class UtilLikesDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="util_like_history_no") //명시적으로 적어두는게 좋다.
    private long utilLikeHistoryNo;
    @Column(name="util_no", nullable=false) //명시적으로 적어두는게 좋다.
    private long utilNo;
    @Column(name="like_ip", nullable=false, length = 50) //명시적으로 적어두는게 좋다.
    private String likeIp;

    @Override
    public String toString() {
        return "UtilLikesDto{" +
                "utilLikeHistoryNo=" + utilLikeHistoryNo +
                ", utilNo=" + utilNo +
                ", likeIp='" + likeIp + '\'' +
                '}';
    }

    public long getUtilLikeHistoryNo() {
        return utilLikeHistoryNo;
    }

    public void setUtilLikeHistoryNo(long utilLikeHistoryNo) {
        this.utilLikeHistoryNo = utilLikeHistoryNo;
    }

    public long getUtilNo() {
        return utilNo;
    }

    public void setUtilNo(long utilNo) {
        this.utilNo = utilNo;
    }

    public String getLikeIp() {
        return likeIp;
    }

    public void setLikeIp(String likeIp) {
        this.likeIp = likeIp;
    }
}
