package com.source.plusutil.algorithm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TwoPointerRequestDto {
    String twoPointerArr;
    String twoPointerTarget;
    @Override
    public String toString() {
        return "TwoPointerRequestDto{" +
                "twoPointerArr='" + twoPointerArr + '\'' +
                ", twoPointerTarget='" + twoPointerTarget + '\'' +
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
}
