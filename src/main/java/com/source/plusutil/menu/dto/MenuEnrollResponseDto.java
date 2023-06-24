package com.source.plusutil.menu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MenuEnrollResponseDto {
    Object menuObject;
    String type;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    boolean success;

    public Object getMenuObject() {
        return menuObject;
    }

    public void setMenuObject(Object menuObject) {
        this.menuObject = menuObject;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
