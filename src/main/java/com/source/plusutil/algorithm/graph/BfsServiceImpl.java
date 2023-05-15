package com.source.plusutil.algorithm.graph;

import java.util.LinkedList;
import java.util.Queue;

import javax.servlet.http.HttpServletRequest;

import com.source.plusutil.algorithm.dto.BfsRequestDto;
import com.source.plusutil.algorithm.dto.BfsResponseDto;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BfsServiceImpl implements BfsService {

	@Override
	public BfsResponseDto bfsDefault(BfsRequestDto bfsRequestDto) {
		int result = -1; //최단거리 출력
		
		//입력 값들은 15을 넘을 수 없음
		if(!bfsRequestDto.fieldCheck()) return BfsResponseDto.builder().bfsSearchResult(-1).build();

		try {
			log.info("startRow -> ["+bfsRequestDto.getBfsStartCol()+"]"+" startCol ->["+bfsRequestDto.getBfsStartRow()+"]"); //시작하는 위치 행/열
			log.info("endRow -> ["+bfsRequestDto.getBfsEndRow()+"]"+" endCol ->["+bfsRequestDto.getBfsEndCol()+"]"); //도착을 원하는 위치  행/열
			
			//배열의 길이이기 때문에 하나씩 더해줌
			bfsRequestDto.setBfsEndCol(bfsRequestDto.getBfsEndCol() + 1);
			bfsRequestDto.setBfsEndRow(bfsRequestDto.getBfsEndRow() + 1);
			
			if(bfsRequestDto.getBfsEndCol() > bfsRequestDto.getBfsCol()) {bfsRequestDto.setBfsEndCol(bfsRequestDto.getBfsCol());} //끝나는 값이 최대값보다 클수 없음
			if(bfsRequestDto.getBfsEndRow() > bfsRequestDto.getBfsEndRow()) {bfsRequestDto.setBfsEndRow(bfsRequestDto.getBfsRow());}

			int[][] visited = new int [bfsRequestDto.getBfsEndRow()][bfsRequestDto.getBfsCol()]; //방문배열 생성
			
			Queue<int[]> queue = new LinkedList<>();
	        queue.add(new int[] {bfsRequestDto.getBfsStartCol(), bfsRequestDto.getBfsStartRow()}); //첫번째 위치 저장
	        
	        //q가 비지 않을때까지 실행
	        while(!queue.isEmpty()) {
	        	int [] node = queue.poll(); //현재 큐 최앞단 추출
	        	if(node[0] == bfsRequestDto.getBfsEndRow() -1 && node[1] == bfsRequestDto.getBfsEndCol() -1 ) {//현재의 큐의 위치가 끝나는위치와 같으면,
	        		result = visited[node[0]][node[1]]; //방문위치값을 대입
	        	}
	        	
	        	//[행기준] 현재 노드가 도착위치보다 작고, 미방문 위치일 경우에
	        	if(node[0] + 1 < bfsRequestDto.getBfsEndRow() && visited[node[0]+1][node[1]] == 0) {
	        		queue.add(new int [] {node[0]+1, node[1]});// 큐에 다음 위치 저장
	        		visited[node[0]+1][node[1]] = visited[node[0]][node[1]] + 1; //방문위치 더하기
	        		printVisitedArr(visited);
	        	}   	
	        	//[행기준] 현재 노드가 시작위치보다 크고, 미방문 위치일 경우에
	        	if(node[0] > 0  && visited[node[0]-1][node[1]] == 0) {
	        		queue.add(new int [] {node[0]-1, node[1]});// 큐에 다음 위치 저장
	        		visited[node[0]-1][node[1]] = visited[node[0]][node[1]] + 1; //방문위치 더하기
	        		printVisitedArr(visited);
	        	}
	        	//[열기준] 현재 노드가 도착위치보다 작고, 미방문 위치일 경우에
	        	if(node[1] + 1 < bfsRequestDto.getBfsEndCol() && visited[node[0]][node[1]+1] == 0) {
	        		queue.add(new int [] {node[0], node[1]+1});// 큐에 다음 위치 저장
	        		visited[node[0]][node[1]+1] = visited[node[0]][node[1]] + 1; //방문위치 더하기
	        		printVisitedArr(visited);
	        	}
	        	//[열기준] 현재 노드가 시작위치보다 크고, 미방문 위치일 경우에
	        	if(node[1] > 0 && visited[node[0]][node[1]-1] == 0) {
	        		queue.add(new int [] {node[0], node[1]-1});// 큐에 다음 위치 저장
	        		visited[node[0]][node[1]-1] = visited[node[0]][node[1]] + 1; //방문위치 더하기
	        		printVisitedArr(visited);
	        	}	
	        }
			
		}catch (Exception e) {
			log.info("exception",e);
			return BfsResponseDto.builder().bfsSearchResult(-1).build();
		}

		return BfsResponseDto.builder()
				.bfsRow(bfsRequestDto.getBfsRow())
				.bfsCol(bfsRequestDto.getBfsCol())
				.bfsStartRow(bfsRequestDto.getBfsStartRow())
				.bfsStartCol(bfsRequestDto.getBfsStartCol())
				.bfsEndRow(bfsRequestDto.getBfsStartRow())
				.bfsEndCol(bfsRequestDto.getBfsEndCol())
				.bfsSearchResult(result)
				.build();
	}
	
	
	
	/**
	 * bfs 배열 형태의 기본 로직을 구현한 메소드
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
	public int bfsArrDefault(int[][] maps, int startRow, int startCol, int endRow, int endCol, int block) {
		int result = -1;
		log.info("startRow -> ["+startRow+"]"+" startCol ->["+startCol+"]"); //시작하는 위치 행/열
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
		StringBuilder line = new StringBuilder();
		line.append(System.lineSeparator());
		for (int[] ints : visited) {
			for (int anInt : ints) {
				line.append(anInt).append(" ");
			}
			line.append(System.lineSeparator());
		}
    	log.info(line.toString());
	}
	
}
