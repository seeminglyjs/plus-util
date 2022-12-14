package com.source.plusutil.utils.etc;

public class PagingUtil {
	private int curPage; //현재 페이지의 번호

	private Long totalCount; //총 게시글 수
	private int listCount; //한 페이지당 보여질 게시글의 수
	private int totalPage; //총 페이지 수

	private int pageCount; //한 화면에서 출력될 페이지네이션의 수
	private int startPage; //화면에 보이는 페이지네이션의 시작 번호
	private int endPage; //화면에 보이는 페이지네이션의 끝 번호

	private int startNo; //화면에 보이는 게시글의 시작 번호	
	private int endNo; //화면에 보이는 게시글의 끝 번호

	
	
	//총 게시글 수를 입력하는 생성자
	public PagingUtil(Long totalCount) {
		setTotalCount(totalCount);
		
		makePaging();
	}
	
	//총 게시글 수, 현재 페이지번호를 입력하는 생성자
	public PagingUtil(Long totalCount, int curPage) {
		setTotalCount(totalCount);
		setCurPage(curPage);
		
		makePaging();
	}
	

	//총 게시글 수, 현재 페이지번호, 보여질 게시글 수를 입력하는 생성자
	public PagingUtil(Long totalCount, int curPage, int listCount) {
		setTotalCount(totalCount);
		setCurPage(curPage);
		setListCount(listCount);
		
		makePaging();
	}
	
	//총 게시글 수, 현재 페이지번호, 보여질 게시글 수, 보여질 페이지 수를 입력하는 생성자
	public PagingUtil(Long totalCount, int curPage, int listCount, int pageCount) {
		setTotalCount(totalCount);
		setCurPage(curPage);
		setListCount(listCount);
		setPageCount(pageCount);
		
		makePaging();
	}
	
	//페이징 정보를 생성하는 메소드
	private void makePaging() {
		if( totalCount == 0 )	return; //게시글이 없는 경우 중단
		
		//기본값 설정
		if(curPage <= 0)	setCurPage(0);//첫 페이지를 기본 페이지로 설정
		if(pageCount == 0)	setPageCount(2);//화면에 보여질 페이지 수 기본설정
		if(listCount == 0)	setListCount(10);//화면에 보여질 게시글 수 기본설정

		//총 페이지 수 계산
		totalPage = (int) (totalCount / listCount);
		if( totalCount % listCount > 0 )	totalPage++;

		//현재 페이지값 보정
		//	현재 페이지가 총 페이지보다 클 때 마지막 페이지로 고정
		if(totalPage < curPage)	curPage = totalPage;

		//화면에 보여질 페이지네이션의 시작번호와 끝번호
//		startPage = ( (curPage-1)/pageCount ) * pageCount + 1;
		startPage = ( (curPage-1)/pageCount ) * pageCount;
//		endPage = startPage + pageCount - 1;
		endPage = startPage + pageCount;
		
		//끝페이지값 보정 totalPage 전체 페이지 수 0 부터 시작 때문에  -1
		//	끝 페이지가 총 페이지보다 클 때 총 페이지를 마지막으로 고정
//		if( endPage > totalPage ) endPage = totalPage;
		if(totalPage >= 1) {
			if( endPage > totalPage - 1) endPage = totalPage - 1;
		}else {
			if( endPage > totalPage ) endPage = totalPage;
		}
	
		//화면에 보여질 게시글 시작번호와 끝번호
		startNo = (curPage-1) * listCount + 1;
		endNo = curPage * listCount;
	}
	
	
	
	@Override
	public String toString() {
		return "Paging [curPage=" + curPage + ", totalCount=" + totalCount + ", listCount=" + listCount + ", totalPage="
				+ totalPage + ", pageCount=" + pageCount + ", startPage=" + startPage + ", endPage=" + endPage
				+ ", startNo=" + startNo + ", endNo=" + endNo + "]";
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public int getListCount() {
		return listCount;
	}
	public void setListCount(int listCount) {
		this.listCount = listCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getStartNo() {
		return startNo;
	}
	public void setStartNo(int startNo) {
		this.startNo = startNo;
	}
	public int getEndNo() {
		return endNo;
	}
	public void setEndNo(int endNo) {
		this.endNo = endNo;
	}
}
