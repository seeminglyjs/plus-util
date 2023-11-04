package com.source.plusutil.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewTokenRequestDto {

    private Map<String,Object> playLoad;
    private String userName;
    private Date IssueDate;
    private Date ExpirationDate;

}
