package com.user.registation.geo.module.geo.controller;

import com.user.registation.geo.module.geo.dto.UserDetailsRequestDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDetailsTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void validateUserDetailsIsStatusOk() {
        UserDetailsRequestDto userDetailsRequestDto = new UserDetailsRequestDto("TestUsername", "Test$1234", "24.48.0.1");
        ResponseEntity<UserDetailsRequestDto> responseEntity = this.restTemplate
                .postForEntity("http://localhost:8080/api/geo/user", userDetailsRequestDto, UserDetailsRequestDto.class);
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
    }

    @Test(expected = HttpClientErrorException.class)
    public void validateUserDetailsIfBadRequest() {
        UserDetailsRequestDto userDetailsRequestDto = new UserDetailsRequestDto("", "Test$1234", "24.48.0.1");
        ResponseEntity<UserDetailsRequestDto> responseEntity = this.restTemplate
                .postForEntity("http://localhost:8080/api/geo/user", userDetailsRequestDto, UserDetailsRequestDto.class);
        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCodeValue());
    }

    @Test(expected = HttpClientErrorException.class)
    public void validateUserDetailsIfNoContent() {
        UserDetailsRequestDto userDetailsRequestDto = new UserDetailsRequestDto("TestUser", "Test$1234", "127.0.0.1");
        ResponseEntity<UserDetailsRequestDto> responseEntity = this.restTemplate
                .postForEntity("http://localhost:8080/api/geo/user", userDetailsRequestDto, UserDetailsRequestDto.class);
        assertEquals(HttpStatus.NOT_FOUND.value(), responseEntity.getStatusCodeValue());
    }
}
