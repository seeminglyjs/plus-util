package com.source.plusutil.menu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name="tb_head_info") //db상에 테이블명을 명시한다. [다른 dto / vo 객체에 똑같은 entity 선언하면 충돌남
public class HeadDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="head_no")
    long headNo;
    @Column(name="nav_no")
    long navNo;
    @Column(name="nav_name")
    String navName;
    @Column(name="head_name")
    String headName;
    @Column(name="use_yn")
    String useYn;
    public String getUseYn() {
        return useYn;
    }
    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }
    public long getNavNo() {
        return navNo;
    }

    public void setNavNo(long navNo) {
        this.navNo = navNo;
    }

    public String getNavName() {
        return navName;
    }

    public void setNavName(String navName) {
        this.navName = navName;
    }

    public long getHeadNo() {
        return headNo;
    }

    public void setHeadNo(long headNo) {
        this.headNo = headNo;
    }

    public String getHeadName() {
        return headName;
    }

    public void setHeadName(String headName) {
        this.headName = headName;
    }
}
