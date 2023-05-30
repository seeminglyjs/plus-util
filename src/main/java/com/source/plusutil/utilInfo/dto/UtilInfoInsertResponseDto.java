package com.source.plusutil.utilInfo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UtilInfoInsertResponseDto {

    private boolean auth;
    private String utilName;
    private String utilDescription;
    private long utilViews;
    private long utilLikes;

    @Override
    public String toString() {
        return "UtilInfoInsertResponseDto{" +
                "auth=" + auth +
                ", utilName='" + utilName + '\'' +
                ", utilDescription='" + utilDescription + '\'' +
                ", utilViews=" + utilViews +
                ", utilLikes=" + utilLikes +
                '}';
    }

    public boolean isAuth() {
        return auth;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
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
