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
@Entity(name="tb_nav_info") //db상에 테이블명을 명시한다. [다른 dto / vo 객체에 똑같은 entity 선언하면 충돌남
public class NavDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="nav_no") //명시적으로 적어두는게 좋다.
    long navNo;
    @Column(name="nav_name") //명시적으로 적어두는게 좋다.
    String navName;

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
}
