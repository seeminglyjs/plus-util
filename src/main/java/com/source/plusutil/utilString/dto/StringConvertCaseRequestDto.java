package com.source.plusutil.utilString.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StringConvertCaseRequestDto {
    String stringContent;
    String upperOrLower;

    @Override
    public String toString() {
        return "StringConvertCaseRequestDto{" +
                "stringContent='" + stringContent + '\'' +
                ", upperOrLower='" + upperOrLower + '\'' +
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
}
