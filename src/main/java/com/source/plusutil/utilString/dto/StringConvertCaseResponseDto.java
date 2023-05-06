package com.source.plusutil.utilString.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StringConvertCaseResponseDto {
    String stringContent;
    String upperOrLower;
    String convertStringContent;

    @Override
    public String toString() {
        return "StringConvertCaseResponseDto{" +
                "stringContent='" + stringContent + '\'' +
                ", upperOrLower='" + upperOrLower + '\'' +
                ", convertStringContent='" + convertStringContent + '\'' +
                '}';
    }

    public String getStringContent() {
        return stringContent;
    }

    public void setStringContent(String stringContent) {
        this.stringContent = stringContent;
    }

    public String getUpperOrLower() {
        return upperOrLower;
    }

    public void setUpperOrLower(String upperOrLower) {
        this.upperOrLower = upperOrLower;
    }

    public String getConvertStringContent() {
        return convertStringContent;
    }

    public void setConvertStringContent(String convertStringContent) {
        this.convertStringContent = convertStringContent;
    }
}
