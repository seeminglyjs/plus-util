package com.source.plusutil.utilString.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StringSimilarityRequestDto {
    String firstContent;
    String secondContent;

    @Override
    public String toString() {
        return "StringSimilarityRequestDto{" +
                "firstContent='" + firstContent + '\'' +
                ", secondContent='" + secondContent + '\'' +
                '}';
    }

    public String getFirstContent() {
        return firstContent;
    }

    public void setFirstContent(String firstContent) {
        this.firstContent = firstContent;
    }

    public String getSecondContent() {
        return secondContent;
    }

    public void setSecondContent(String secondContent) {
        this.secondContent = secondContent;
    }
}
