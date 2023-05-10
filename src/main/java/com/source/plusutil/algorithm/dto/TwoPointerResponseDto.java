package com.source.plusutil.algorithm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TwoPointerResponseDto {
    String twoPointerArr;
    String twoPointerTarget;
    long twoPointerResult;

    @Override
    public String toString() {
        return "TwoPointerResponseDto{" +
                "twoPointerArr='" + twoPointerArr + '\'' +
                ", twoPointerTarget='" + twoPointerTarget + '\'' +
                ", twoPointerResult=" + twoPointerResult +
                '}';
    }

    public String getTwoPointerArr() {
        return twoPointerArr;
    }

    public void setTwoPointerArr(String twoPointerArr) {
        this.twoPointerArr = twoPointerArr;
    }

    public String getTwoPointerTarget() {
        return twoPointerTarget;
    }

    public void setTwoPointerTarget(String twoPointerTarget) {
        this.twoPointerTarget = twoPointerTarget;
    }

    public long getTwoPointerResult() {
        return twoPointerResult;
    }

    public void setTwoPointerResult(long twoPointerResult) {
        this.twoPointerResult = twoPointerResult;
    }
}
