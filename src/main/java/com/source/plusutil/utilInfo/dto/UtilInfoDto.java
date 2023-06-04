package com.source.plusutil.utilInfo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="tb_util_info")
public class UtilInfoDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="util_no") //명시적으로 적어두는게 좋다.
    private long utilNo;
    @Column(name="util_name",nullable=false)
    private String utilName;
    @Column(name="util_description", nullable=false)
    private String utilDescription;
    @Column(name="util_views", nullable=false)
    private long utilViews;
    @Column(name="util_likes", nullable=false)
    private long utilLikes;
    @Column(name="util_enroll_date", nullable=false, length = 8)
    private String utilEnrollDate;
    @Column(name="util_modify_date", length = 8)
    private String utilModifyDate;
    @Column(name="url_path", nullable=false)
    private String urlPath;
    @Column(name="category", nullable=false)
    private String category;

    @Override
    public String toString() {
        return "UtilInfoDto{" +
                "utilNo=" + utilNo +
                ", utilName='" + utilName + '\'' +
                ", utilDescription='" + utilDescription + '\'' +
                ", utilViews=" + utilViews +
                ", utilLikes=" + utilLikes +
                ", utilEnrollDate='" + utilEnrollDate + '\'' +
                ", utilModifyDate='" + utilModifyDate + '\'' +
                ", urlPath='" + urlPath + '\'' +
                ", category='" + category + '\'' +
                '}';
    }

    public long getUtilNo() {
        return utilNo;
    }

    public void setUtilNo(long utilNo) {
        this.utilNo = utilNo;
    }

    public String getUtilName() {
        return utilName;
    }

    public void setUtilName(String utilName) {
        this.utilName = utilName;
    }

    public String getUtilDescription() {
        return utilDescription;
    }

    public void setUtilDescription(String utilDescription) {
        this.utilDescription = utilDescription;
    }

    public long getUtilViews() {
        return utilViews;
    }

    public void setUtilViews(long utilViews) {
        this.utilViews = utilViews;
    }

    public long getUtilLikes() {
        return utilLikes;
    }

    public void setUtilLikes(long utilLikes) {
        this.utilLikes = utilLikes;
    }

    public String getUtilEnrollDate() {
        return utilEnrollDate;
    }

    public void setUtilEnrollDate(String utilEnrollDate) {
        this.utilEnrollDate = utilEnrollDate;
    }

    public String getUtilModifyDate() {
        return utilModifyDate;
    }

    public void setUtilModifyDate(String utilModifyDate) {
        this.utilModifyDate = utilModifyDate;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
