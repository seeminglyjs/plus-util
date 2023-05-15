package com.source.plusutil.algorithm.graph;

import com.source.plusutil.utils.algorithm.DfsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@Slf4j
public class DfsServiceImpl implements DfsService {

    @Override
    public void dfsDefault(int dfsRow, int dfsCol, int dfsStartRow, int dfsStartCol, int dfsEndRow, int dfsEndCol, HttpServletRequest request) {
        int result = -1;
        //입력 값들은 15을 넘을 수 없음
        try {
            if (dfsRow > 15 || dfsCol > 15 | dfsStartRow > 14 | dfsStartCol > 14 | dfsEndRow > 15 | dfsEndCol > 15) {
                return;
            } else if (dfsRow < dfsEndRow || dfsEndRow < dfsStartRow) {
                return;
            } else if (dfsCol < dfsEndCol || dfsEndCol < dfsStartCol) {
                return;
            }
        } catch (Exception e) {
            log.info("exception", e);
        }
        DfsUtil dfsUtil = new DfsUtil();
        result = dfsUtil.dfsDefaultResult(dfsRow, dfsCol, dfsStartRow, dfsStartCol, dfsEndRow, dfsEndCol);
        request.setAttribute("dfsSearchResult", result);
        request.setAttribute("dfsRow", dfsRow);
        request.setAttribute("dfsCol", dfsCol);
        request.setAttribute("dfsStartRow", dfsStartRow);
        request.setAttribute("dfsStartCol", dfsStartCol);
        request.setAttribute("dfsEndRow", dfsEndRow);
        request.setAttribute("dfsEndCol", dfsEndCol);
    }
}
