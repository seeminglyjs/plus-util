package com.source.plusutil.utilString.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StringSimilarityResponseDto {
    String firstContent;
    String secondContent;
    String similarity;

    @Override
    public String toString() {
        return "StringSimilarityResponseDto{" +
                "firstContent='" + firstContent + '\'' +
                ", secondContent='" + secondContent + '\'' +
                ", similarity='" + similarity + '\'' +
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

    public String getSimilarity() {
        return similarity;
    }

    public void setSimilarity(String similarity) {
        this.similarity = similarity;
    }
}
