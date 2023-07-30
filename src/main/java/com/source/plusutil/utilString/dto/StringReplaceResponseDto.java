package com.source.plusutil.utilString.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class StringReplaceResponseDto {
    String findStr;
    String replaceStr;
    String content;

    public String getFindStr() {
        return findStr;
    }

    public void setFindStr(String findStr) {
        this.findStr = findStr;
    }

    public String getReplaceStr() {
        return replaceStr;
    }

    public void setReplaceStr(String replaceStr) {
        this.replaceStr = replaceStr;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
