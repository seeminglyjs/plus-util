package com.source.plusutil.utilString.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StringInitialRequestDto {
    String stringContent;

    @Override
    public String toString() {
        return "StringInitialRequestDto{" +
                "stringContent='" + stringContent + '\'' +
                '}';
    }

    public String getStringContent() {
        return stringContent;
    }

    public void setStringContent(String stringContent) {
        this.stringContent = stringContent;
    }
}
