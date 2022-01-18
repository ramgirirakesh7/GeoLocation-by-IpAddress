package com.user.registation.geo.module.geo.controller;

import com.user.registation.geo.module.geo.dto.UserDetailsReponseDto;
import com.user.registation.geo.module.geo.dto.UserDetailsRequestDto;
import com.user.registation.geo.module.geo.exception.InvalidIPProvideException;
import com.user.registation.geo.module.geo.exception.InvalidUserDetailsException;
import com.user.registation.geo.module.geo.service.GeoLocationService;
import com.user.registation.geo.module.geo.utils.UserDetailsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/geo")
public class GeoLocationController {

    @Autowired
    private GeoLocationService geoLocatoionService;

    @Autowired
    private UserDetailsValidator userDetailsValidator;

    @PostMapping("/user")
    public ResponseEntity validateUserDetails(@RequestBody UserDetailsRequestDto userDetailsRequestDto) {
        try {
            userDetailsValidator.validateUserDetails(userDetailsRequestDto);
            UserDetailsReponseDto userDetailsReponseDto = geoLocatoionService.findUserDetailsByIp(userDetailsRequestDto);
            return new ResponseEntity<>(userDetailsReponseDto, HttpStatus.OK);
        } catch (InvalidIPProvideException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (InvalidUserDetailsException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
