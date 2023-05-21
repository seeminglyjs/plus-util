package com.source.plusutil.utilInfo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UtilInfoInsertRequestDto {

    private String utilName;
    private String utilDescription;
    private long utilViews;
    private long utilLikes;

    @Override
    public String toString() {
        return "UtilInfoInsertRequestDto{" +
                "utilName='" + utilName + '\'' +
                ", utilDescription='" + utilDescription + '\'' +
                ", utilViews=" + utilViews +
                ", utilLikes=" + utilLikes +
                '}';
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
}
