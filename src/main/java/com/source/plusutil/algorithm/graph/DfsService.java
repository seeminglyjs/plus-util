package com.source.plusutil.algorithm.graph;

import javax.servlet.http.HttpServletRequest;

public interface DfsService {
    public void dfsDefault(int dfsRow, int dfsCol, int dfsStartRow, int dfsStartCol, int dfsEndRow, int dfsEndCol, HttpServletRequest request);
}