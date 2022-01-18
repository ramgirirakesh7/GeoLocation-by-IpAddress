package com.user.registation.geo.module.geo.utils;

import com.user.registation.geo.module.geo.dto.UserDetailsRequestDto;
import com.user.registation.geo.module.geo.exception.InvalidUserDetailsException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDetailsValidatorTest {

    @Autowired
    private UserDetailsValidator userDetailsValidator;

    @Test
    public void assertIsValidCAUser() {
        Assert.assertTrue(userDetailsValidator.isValidCAUser("CA"));
        Assert.assertFalse(userDetailsValidator.isValidCAUser("GB"));
    }

    @Test(expected = InvalidUserDetailsException.class)
    public void assertIsValidUsername() {
        UserDetailsRequestDto userDetailsRequestDto = new UserDetailsRequestDto();
        userDetailsRequestDto.setUsername("");
        userDetailsRequestDto.setIpAddress("");
        userDetailsRequestDto.setPassword("");
        userDetailsValidator.validateUserDetails(userDetailsRequestDto);
    }

    @Test
    public void assertIsValidateUserDetails() {
        UserDetailsRequestDto userDetailsRequestDto = new UserDetailsRequestDto();
        userDetailsRequestDto.setUsername("Testusername");
        userDetailsRequestDto.setIpAddress("127.0.0.1");
        userDetailsRequestDto.setPassword("Test$123");
        userDetailsValidator.validateUserDetails(userDetailsRequestDto);
    }
}
