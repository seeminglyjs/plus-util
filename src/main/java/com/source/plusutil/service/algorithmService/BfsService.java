package com.source.plusutil.service.algorithmService;

import java.util.LinkedList;
import java.util.Queue;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BfsService {
	
	/**
	 * bfs 기본 로직을 구현한 메소드
	 * 
	 * 
	 * @param maps 전체 이중 배열
	 * @param startRow 이중 배열 중 시작 행
	 * @param startCol 이중 배열 중 시작 열
	 * @param endRow 도착지점 행
	 * @param endCol 도착지점 열
	 * @param block 배열에서 막혀있는곳 체크
	 * @return
	 */
	public int bfsDefault(int[][] maps, int startRow, int startCol, int endRow, int endCol, int block) {
		int result = -1;
		log.info("startRow -> ["+startRow+"]"+" startCol ->["+startCol+"]"); //지가하는 위치 행/열
		log.info("endRow -> ["+endRow+"]"+" endCol ->["+endCol+"]"); //도착을 원하는 위치  행/열
		log.info("block -> ["+block+"]"); //이동불가한 위치

		//열 최대값 //행 최대값 설정 
		int colMax = maps.length, rowMax = maps[0].length;
		//배열의 길이이기 때문에 하나씩 더해준다. out of index exception 예방
		endRow +=1;
		endCol +=1;

		if(endRow > rowMax) {endRow = rowMax;} //끝나는 값이 최대값보다 클수 없음
		if(endCol > colMax) {endCol = colMax;}

		//시작지점이 및 끝나는 지점이 block 이면 -1
		if(maps[startCol][startRow] == block || maps[endRow-1][endCol-1] == block) {
			return -1;
		}	

		//방문체크 배열 생성
		int[][] visited = new int[colMax][rowMax];
		
		//시작위치 방문 시작
        visited[startCol][startRow] = 1; 

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {startCol, startRow}); //첫번째 위치 저장
		
        //q가 비지 않을때까지 실행
        while(!queue.isEmpty()) {
        	int [] node = queue.poll(); //현재 큐 최앞단 추출
        	if(node[0] == endCol -1 && node[1] == endRow -1 ) {//현재의 큐의 위치가 끝나는위치와 같으면,
        		result = visited[node[0]][node[1]]; //방문위치값을 대입
        	}
        	
        	//[열기준] 현재 노드가 도착위치보다 작고 맵에서 block 되지 않았으며, 미방문 위치일 경우에
        	if(node[0] + 1 < endCol && maps[node[0]+1][node[1]] != block && visited[node[0]+1][node[1]] == 0) {
        		queue.add(new int [] {node[0]+1, node[1]});// 큐에 다음 위치 저장
        		visited[node[0]+1][node[1]] = visited[node[0]][node[1]] + 1; //방문위치 더하기
        		printVisitedArr(visited);
        	}
        	//[열기준] 현재 노드가 시작위치보다 크고 맵에서 block 되지 않았으며, 미방문 위치일 경우에
        	if(node[0] > 0 && maps[node[0]-1][node[1]] != block && visited[node[0]-1][node[1]] == 0) {
        		queue.add(new int [] {node[0]-1, node[1]});// 큐에 다음 위치 저장
        		visited[node[0]-1][node[1]] = visited[node[0]][node[1]] + 1; //방문위치 더하기
        		printVisitedArr(visited);
        	}
        	//[행기준] 현재 노드가 도착위치보다 작고 맵에서 block 되지 않았으며, 미방문 위치일 경우에
        	if(node[1] + 1 < endRow && maps[node[0]][node[1]+1] != block && visited[node[0]][node[1]+1] == 0) {
        		queue.add(new int [] {node[0], node[1]+1});// 큐에 다음 위치 저장
        		visited[node[0]][node[1]+1] = visited[node[0]][node[1]] + 1; //방문위치 더하기
        		printVisitedArr(visited);
        	}
        	//[행기준] 현재 노드가 시작위치보다 크고 맵에서 block 되지 않았으며, 미방문 위치일 경우에
        	if(node[1] > 0 && maps[node[0]][node[1]-1] != block && visited[node[0]][node[1]-1] == 0) {
        		queue.add(new int [] {node[0], node[1]-1});// 큐에 다음 위치 저장
        		visited[node[0]][node[1]-1] = visited[node[0]][node[1]] + 1; //방문위치 더하기
        		printVisitedArr(visited);
        	}	
        }	
		return result;
	}
	
	/**
	 * bfs 방문 배열을 출력하는 메소드
	 * 
	 * @param visited
	 */
	public void printVisitedArr(int [][] visited) {
    	for(int i = 0; i < visited.length; i++) {
    		String line = "";
    		for(int j = 0; j <visited[i].length; j++) {
    			line += visited[i][j] + " ";		
    		}
    		log.info(line);
    	}
    	log.info("");
	}
	
}
