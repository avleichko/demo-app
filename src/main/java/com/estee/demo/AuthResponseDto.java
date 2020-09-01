package com.estee.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class AuthResponseDto {

    private String token_type;
    private String access_token;
    private Integer expires_in;
    private Integer ext_expires_in;
}
