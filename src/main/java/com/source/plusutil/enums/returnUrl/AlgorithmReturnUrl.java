package com.source.plusutil.enums.returnUrl;

public enum AlgorithmReturnUrl {
    BASIC_MAIN("/algorithm/basic/basicMain")
    ,GRAPH_MAIN("/algorithm/graph/graphMain")
    ,BFS_MAIN("/algorithm/bfs/bfsMain")
    ,DFS_MAIN("/algorithm/dfs/dfsMain")
    ,TWO_POINTER_MAIN("/algorithm/basic/twoPointerMain")

    ;
    String url;
    AlgorithmReturnUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }
}
