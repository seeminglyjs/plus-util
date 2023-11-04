package com.source.plusutil.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto {

    private String token;
    private String userName;
    private Date IssueDate;
    private Date ExpirationDate;

}
