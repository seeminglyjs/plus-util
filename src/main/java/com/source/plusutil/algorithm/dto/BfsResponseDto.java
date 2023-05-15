package com.source.plusutil.algorithm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BfsResponseDto {
    int bfsRow;
    int bfsCol;
    int bfsStartRow;
    int bfsStartCol;
    int bfsEndRow;
    int bfsEndCol;
    int bfsSearchResult;

    @Override
    public String toString() {
        return "BfsResponseDto{" +
                "bfsRow=" + bfsRow +
                ", bfsCol=" + bfsCol +
                ", bfsStartRow=" + bfsStartRow +
                ", bfsStartCol=" + bfsStartCol +
                ", bfsEndRow=" + bfsEndRow +
                ", bfsEndCol=" + bfsEndCol +
                ", bfsSearchResult=" + bfsSearchResult +
                '}';
    }

    public int getBfsRow() {
        return bfsRow;
    }

    public void setBfsRow(int bfsRow) {
        this.bfsRow = bfsRow;
    }

    public int getBfsCol() {
        return bfsCol;
    }

    public void setBfsCol(int bfsCol) {
        this.bfsCol = bfsCol;
    }

    public int getBfsStartRow() {
        return bfsStartRow;
    }

    public void setBfsStartRow(int bfsStartRow) {
        this.bfsStartRow = bfsStartRow;
    }

    public int getBfsStartCol() {
        return bfsStartCol;
    }

    public void setBfsStartCol(int bfsStartCol) {
        this.bfsStartCol = bfsStartCol;
    }

    public int getBfsEndRow() {
        return bfsEndRow;
    }

    public void setBfsEndRow(int bfsEndRow) {
        this.bfsEndRow = bfsEndRow;
    }

    public int getBfsEndCol() {
        return bfsEndCol;
    }

    public void setBfsEndCol(int bfsEndCol) {
        this.bfsEndCol = bfsEndCol;
    }

    public int getBfsSearchResult() {
        return bfsSearchResult;
    }

    public void setBfsSearchResult(int bfsSearchResult) {
        this.bfsSearchResult = bfsSearchResult;
    }
}
