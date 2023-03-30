package com.source.plusutil.dto.notice;

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

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity(name="tb_notice_info") //db상에 테이블명을 명시한다. [다른 dto / vo 객체에 똑같은 entity 선언하면 충돌남
public class NoticeDto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="notice_no") //명시적으로 적어두는게 좋다.
	private Integer noticeNo;
	
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
}
