package com.source.plusutil.service.algorithmService;

import javax.servlet.http.HttpServletRequest;

public interface BfsService {
    public void bfsDefault(int bfsCol, int bfsRow, int bfsStartCol, int bfsStartRow, int bfsEndCol, int bfsEndRow, HttpServletRequest request);
    public int bfsArrDefault(int[][] maps, int startRow, int startCol, int endRow, int endCol, int block);
}
