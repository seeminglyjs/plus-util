package com.source.plusutil.utilInfo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="tb_util_info")
public class UtilInfoDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="util_no") //명시적으로 적어두는게 좋다.
    private long utilNo;
}
