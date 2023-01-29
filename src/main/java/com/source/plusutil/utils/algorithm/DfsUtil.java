package com.source.plusutil.utils.algorithm;

public class DfsUtil {
    private final int[] dx = {0, 1, 0, -1};
    private final int[] dy = {-1, 0, 1, 0};
    private boolean[][] visited;
    private int result = -1;

    public int dfsDefaultResult(int dfsRow, int dfsCol, int dfsStartRow, int dfsStartCol, int dfsEndRow, int dfsEndCol) {
        dfsEndRow += 1;
        dfsEndCol += 1;

        if (dfsEndRow > dfsRow) {
            dfsEndRow = dfsRow;
        } //끝나는 값이 최대값보다 클수 없음
        if (dfsEndCol > dfsCol) {
            dfsEndCol = dfsCol;
        }

        visited = new boolean[dfsEndRow][dfsEndCol];
        defaultDfs(dfsStartRow, dfsStartCol, dfsEndRow, dfsEndCol, 0);
        return result;
    }

    private void defaultDfs(int dfsRow, int dfsCol, int dfsEndRow, int dfsEndCol, int depth) {
        if (dfsRow == dfsEndRow - 1 && dfsCol == dfsEndCol - 1) {
            if (result == -1)
                result = depth;
            else
                result = Math.min(result, depth);
            return;
        }
        if(depth > dfsEndCol + dfsEndRow){return;}
        for (int i = 0; i < 4; i++) {
            int nx = dfsRow + dx[i];
            int ny = dfsCol + dy[i];
            if (nx >= 0 && ny >= 0 && nx < dfsEndRow && ny < dfsEndCol) {
//                System.out.println(nx + "/" +ny + "/" + visited[nx][ny]);
                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    defaultDfs(nx, ny, dfsEndRow, dfsEndCol, depth + 1);
                    visited[nx][ny] = false;
                }
            }
        }
    }

    private void defaultBlockDfs(int dfsRow, int dfsCol, int dfsEndRow, int dfsEndCol, int[][] maps, int depth) {
        if (dfsRow == dfsEndRow - 1 && dfsCol == dfsEndCol - 1) {
            if (result == -1)
                result = depth;
            else
                result = Math.min(result, depth);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = dfsRow + dx[i];
            int ny = dfsCol + dy[i];
            if (nx >= 0 && ny >= 0 && nx < dfsEndRow && ny < dfsEndCol) {
                if (maps[nx][ny] == 1 && !visited[nx][ny]) { //1일 경우 막힘 케이스
                    visited[nx][ny] = true;
                    defaultBlockDfs(nx, ny, dfsEndRow, dfsEndCol, maps, depth + 1);
                    visited[nx][ny] = false;
                }
            }
        }
    }
}
