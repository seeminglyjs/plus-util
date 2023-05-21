package com.source.plusutil.utilInfo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_util_views")
public class UtilViewsDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "util_view_history_no") //명시적으로 적어두는게 좋다.
    private long utilViewHistoryNo;
    @Column(name = "util_no", nullable = false) //명시적으로 적어두는게 좋다.
    private long utilNo;
    @Column(name = "view_ip", nullable = false, length = 50) //명시적으로 적어두는게 좋다.
    private String viewIp;
    @Column(name = "view_date", nullable = false, length = 8) //명시적으로 적어두는게 좋다.
    private String viewDate;

    @Override
    public String toString() {
        return "UtilViewsDto{" +
                "utilViewHistoryNo=" + utilViewHistoryNo +
                ", utilNo=" + utilNo +
                ", viewIp='" + viewIp + '\'' +
                ", viewDate='" + viewDate + '\'' +
                '}';
    }

    public long getUtilViewHistoryNo() {
        return utilViewHistoryNo;
    }

    public void setUtilViewHistoryNo(long utilViewHistoryNo) {
        this.utilViewHistoryNo = utilViewHistoryNo;
    }

    public long getUtilNo() {
        return utilNo;
    }

    public void setUtilNo(long utilNo) {
        this.utilNo = utilNo;
    }

    public String getViewIp() {
        return viewIp;
    }

    public void setViewIp(String viewIp) {
        this.viewIp = viewIp;
    }

    public String getViewDate() {
        return viewDate;
    }

    public void setViewDate(String viewDate) {
        this.viewDate = viewDate;
    }
}
