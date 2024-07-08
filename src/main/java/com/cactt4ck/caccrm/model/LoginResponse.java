package com.cactt4ck.caccrm.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginResponse {

    private String token;

    private long expiresIn;

}
