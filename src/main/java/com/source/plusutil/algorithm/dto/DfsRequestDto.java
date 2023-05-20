package com.source.plusutil.algorithm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DfsRequestDto {
    int dfsRow;
    int dfsCol;
    int dfsStartRow;
    int dfsStartCol;
    int dfsEndRow;
    int dfsEndCol;

    @Override
    public String toString() {
        return "DfsRequestDto{" +
                "dfsRow=" + dfsRow +
                ", dfsCol=" + dfsCol +
                ", dfsStartRow=" + dfsStartRow +
                ", dfsStartCol=" + dfsStartCol +
                ", dfsEndRow=" + dfsEndRow +
                ", dfsEndCol=" + dfsEndCol +
                '}';
    }

    public int getDfsRow() {
        return dfsRow;
    }

    public void setDfsRow(int dfsRow) {
        this.dfsRow = dfsRow;
    }

    public int getDfsCol() {
        return dfsCol;
    }

    public void setDfsCol(int dfsCol) {
        this.dfsCol = dfsCol;
    }

    public int getDfsStartRow() {
        return dfsStartRow;
    }

    public void setDfsStartRow(int dfsStartRow) {
        this.dfsStartRow = dfsStartRow;
    }

    public int getDfsStartCol() {
        return dfsStartCol;
    }

    public void setDfsStartCol(int dfsStartCol) {
        this.dfsStartCol = dfsStartCol;
    }

    public int getDfsEndRow() {
        return dfsEndRow;
    }

    public void setDfsEndRow(int dfsEndRow) {
        this.dfsEndRow = dfsEndRow;
    }

    public int getDfsEndCol() {
        return dfsEndCol;
    }

    public void setDfsEndCol(int dfsEndCol) {
        this.dfsEndCol = dfsEndCol;
    }
}
