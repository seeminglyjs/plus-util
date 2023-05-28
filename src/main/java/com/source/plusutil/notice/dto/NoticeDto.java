package com.source.plusutil.notice.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Entity(name="tb_notice_info") //db상에 테이블명을 명시한다. [다른 dto / vo 객체에 똑같은 entity 선언하면 충돌남
public class NoticeDto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="notice_no") //명시적으로 적어두는게 좋다.
	private Long noticeNo;
	
	@Column(name="title") //명시적으로 적어두는게 좋다.
	private String title;
	
	@Column(name="content") //명시적으로 적어두는게 좋다.
	private String content;
	
	@Column(name="write_date") //명시적으로 적어두는게 좋다.
	private String writeDate; //작성일자
	
	@Column(name="update_date") //명시적으로 적어두는게 좋다.
	private String upDateDate; //수정일자
	
	@Column(name="writer") //명시적으로 적어두는게 좋다.
	private String writer; //작성자
	
	@Column(name="category", length = 10) //명시적으로 적어두는게 좋다.
	private String category; //카테고리
	
	@Column(name="sub_info1")
	private String subInfo1;
	
	@Column(name="sub_info2")
	private String subInfo2;
	
	@Column(name="sub_info3")
	private String subInfo3;
	
	@Builder //빌더형식으로 객체를 생성할건지에 대한 어노테이션 게시글 생성
	public NoticeDto(String title, String content, String category, String writer, String writeDate) {
		this.title = title;
		this.content = content;
		this.category = category;
		this.writer = writer;
		this.writeDate = writeDate;
	}
	
	//게시글 작성
    public static NoticeDto writeNotice(NoticeWriteDto noticeWriteDto) {
    	NoticeDto notice = NoticeDto.builder()
    			.title(noticeWriteDto.getTitle())
    			.content(noticeWriteDto.getContent())
    			.category(noticeWriteDto.getCategory())
    			.writer(noticeWriteDto.getWriter())
    			.writeDate(noticeWriteDto.getWriteTime())
    			.build();
        return notice;
    }

	@Override
	public String toString() {
		return "NoticeDto{" +
				"noticeNo=" + noticeNo +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				", writeDate='" + writeDate + '\'' +
				", upDateDate='" + upDateDate + '\'' +
				", writer='" + writer + '\'' +
				", category='" + category + '\'' +
				", subInfo1='" + subInfo1 + '\'' +
				", subInfo2='" + subInfo2 + '\'' +
				", subInfo3='" + subInfo3 + '\'' +
				'}';
	}

	public Long getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(Long noticeNo) {
		this.noticeNo = noticeNo;
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

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public String getUpDateDate() {
		return upDateDate;
	}

	public void setUpDateDate(String upDateDate) {
		this.upDateDate = upDateDate;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubInfo1() {
		return subInfo1;
	}

	public void setSubInfo1(String subInfo1) {
		this.subInfo1 = subInfo1;
	}

	public String getSubInfo2() {
		return subInfo2;
	}

	public void setSubInfo2(String subInfo2) {
		this.subInfo2 = subInfo2;
	}

	public String getSubInfo3() {
		return subInfo3;
	}

	public void setSubInfo3(String subInfo3) {
		this.subInfo3 = subInfo3;
	}
}
