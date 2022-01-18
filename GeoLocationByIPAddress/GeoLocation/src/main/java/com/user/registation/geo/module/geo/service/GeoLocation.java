package com.user.registation.geo.module.geo.service;

import com.user.registation.geo.module.geo.dto.UserDetailsReponseDto;
import com.user.registation.geo.module.geo.dto.UserDetailsRequestDto;

public interface GeoLocation {
    public UserDetailsReponseDto findUserDetailsByIp(UserDetailsRequestDto userDetailsRequestDto) throws RuntimeException;
}
