package com.user.registation.geo.module.geo.service;

import com.user.registation.geo.module.geo.config.IPApiConfig;
import com.user.registation.geo.module.geo.dto.GeoApiResponseDto;
import com.user.registation.geo.module.geo.dto.UserDetailsReponseDto;
import com.user.registation.geo.module.geo.dto.UserDetailsRequestDto;
import com.user.registation.geo.module.geo.exception.InvalidIPProvideException;
import com.user.registation.geo.module.geo.utils.UserDetailsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

import static com.user.registation.geo.module.geo.utils.AppConstants.YOU_ARE_NOT_A_VALID_CA_CITIZEN;

@Service
public class GeoLocationService implements GeoLocation {


    @Autowired
    private IPApiConfig ipApiConfig;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserDetailsValidator validator;

    @Override
    public UserDetailsReponseDto findUserDetailsByIp(UserDetailsRequestDto userDetailsRequestDto) throws RuntimeException {
        String city = findGeoLocationByIp(userDetailsRequestDto.getIpAddress());
        if(!StringUtils.isEmpty(city)) {
            return prepareResponse(city, userDetailsRequestDto.getUsername());
        } else {
            throw new InvalidIPProvideException("User is not elligible to register");
        }
    }

    private String findGeoLocationByIp(String ip) {
        GeoApiResponseDto geoApiResponseDto = getGeoApiResponseDto(ip);
        if(!StringUtils.isEmpty(geoApiResponseDto.getCountryCode()) && validator.isValidCAUser(geoApiResponseDto.getCountryCode()))
            return geoApiResponseDto.getCity();
        return null;
    }

    private GeoApiResponseDto getGeoApiResponseDto(String ip) {
        GeoApiResponseDto geoApiResponseDto =
                restTemplate.getForObject(ipApiConfig.getEndpoint()+ ip, GeoApiResponseDto.class);
        return geoApiResponseDto;
    }

    private UserDetailsReponseDto prepareResponse(String city, String username) {
        UserDetailsReponseDto userDetailsReponseDto = new UserDetailsReponseDto();
        userDetailsReponseDto.setUuid(UUID.randomUUID().toString());
        userDetailsReponseDto.setMessage(validator.formatString(username, city));
        return userDetailsReponseDto;
    }

    private UserDetailsReponseDto prepareErrorResponse() {
        UserDetailsReponseDto userDetailsReponseDto = new UserDetailsReponseDto();
        userDetailsReponseDto.setMessage(YOU_ARE_NOT_A_VALID_CA_CITIZEN);
        return userDetailsReponseDto;
    }
}
