package com.user.registation.geo.module.geo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsRequestDto {
    private String username;
    private String password;
    private String ipAddress;
}
