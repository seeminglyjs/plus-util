package com.source.plusutil.utilIp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MyIpCheckResponseDto {
    String ip;
    String country;

    @Override
    public String toString() {
        return "MyIpCheckResponseDto{" +
                "ip='" + ip + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
