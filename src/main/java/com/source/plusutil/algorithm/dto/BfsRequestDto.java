package com.source.plusutil.algorithm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BfsRequestDto {
    int bfsRow;
    int bfsCol;
    int bfsStartRow;
    int bfsStartCol;
    int bfsEndRow;
    int bfsEndCol;

    public boolean fieldCheck(){
        if(bfsCol > 15 || bfsRow > 15 | bfsStartCol > 14 | bfsStartRow > 14 | bfsEndCol > 15 | bfsEndRow > 15) {
            return false;
        }else if(bfsCol < bfsEndCol || bfsEndCol < bfsStartCol) {
            return false;
        }else if(bfsRow < bfsEndRow || bfsEndRow < bfsStartRow) {
            return false;
        }else{
            return true;
        }
    }

    @Override
    public String toString() {
        return "BfsRequestDto{" +
                "bfsRow=" + bfsRow +
                ", bfsCol=" + bfsCol +
                ", bfsStartRow=" + bfsStartRow +
                ", bfsStartCol=" + bfsStartCol +
                ", bfsEndRow=" + bfsEndRow +
                ", bfsEndCol=" + bfsEndCol +
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
}
