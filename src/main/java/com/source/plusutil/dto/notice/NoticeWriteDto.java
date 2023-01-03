package com.source.plusutil.dto.notice;


import groovy.transform.builder.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
public class NoticeWriteDto { //게시글 작성 DTO

	private String title;
	private String content;
	private String category;
	private String writer;
	private String writeTime;

	@Builder
	public NoticeWriteDto(String title, String content, String category, String writer, String writeTime) {
		this.title = title;
		this.content = content;
		this.category = category;
		this.writer = writer;
		this.writeTime = writeTime;
	}	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getWriteTime() {
		return writeTime;
	}
	public void setWriteTime(String writeTime) {
		this.writeTime = writeTime;
	}



}
