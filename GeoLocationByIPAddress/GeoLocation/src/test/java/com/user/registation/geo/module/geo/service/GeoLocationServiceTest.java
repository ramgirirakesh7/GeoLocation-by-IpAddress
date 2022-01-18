package com.user.registation.geo.module.geo.service;

import com.user.registation.geo.module.geo.dto.UserDetailsRequestDto;
import com.user.registation.geo.module.geo.exception.InvalidIPProvideException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class GeoLocationServiceTest {

    @Autowired
    GeoLocationService service;

    @Test(expected = InvalidIPProvideException.class)
    public void findUserDetailsByIpIfInvalidRequest() {
        UserDetailsRequestDto userDetailsRequestDto = new UserDetailsRequestDto();
        userDetailsRequestDto.setUsername("Testusername");
        userDetailsRequestDto.setIpAddress("127.0.0.1");
        userDetailsRequestDto.setPassword("Test$123");
        service.findUserDetailsByIp(userDetailsRequestDto);
    }

    @Test
    public void findUserDetailsByIpIfValidRequest() {
        UserDetailsRequestDto userDetailsRequestDto = new UserDetailsRequestDto();
        userDetailsRequestDto.setUsername("Testusername");
        userDetailsRequestDto.setIpAddress("24.48.0.1");
        userDetailsRequestDto.setPassword("Test$123");
        service.findUserDetailsByIp(userDetailsRequestDto);
    }
}
