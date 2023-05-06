package com.source.plusutil.utilString.dto;

import lombok.Builder;

@Builder
public class StringInitialResponseDto {
    String stringContent;
    String initialString;

    @Override
    public String toString() {
        return "StringInitialResponseDto{" +
                "stringContent='" + stringContent + '\'' +
                ", initialString='" + initialString + '\'' +
                '}';
    }

    public String getStringContent() {
        return stringContent;
    }

    public void setStringContent(String stringContent) {
        this.stringContent = stringContent;
    }

    public String getInitialString() {
        return initialString;
    }

    public void setInitialString(String initialString) {
        this.initialString = initialString;
    }
}
