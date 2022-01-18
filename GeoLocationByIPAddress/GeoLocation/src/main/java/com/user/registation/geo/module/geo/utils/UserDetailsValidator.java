package com.user.registation.geo.module.geo.utils;

import com.user.registation.geo.module.geo.dto.UserDetailsRequestDto;
import com.user.registation.geo.module.geo.exception.InvalidUserDetailsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

import static com.user.registation.geo.module.geo.utils.AppConstants.*;

@Service
public class UserDetailsValidator {

    public void validateUserDetails(UserDetailsRequestDto userDetails) throws RuntimeException{

        String passwordRegex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[_#$%.])"
                + "(?=\\S+$).{8,}$";
        String zeroTo255
                = "(\\d{1,2}|(0|1)\\"
                + "d{2}|2[0-4]\\d|25[0-5])";
        String ipAddressRegEx
                = zeroTo255 + "\\."
                + zeroTo255 + "\\."
                + zeroTo255 + "\\."
                + zeroTo255;

        Pattern ipAddressPattern = Pattern.compile(ipAddressRegEx);
        Pattern passwordPattern = Pattern.compile(passwordRegex);

        if(StringUtils.isEmpty(userDetails.getUsername()))
            throw new InvalidUserDetailsException(USERNAME_SHOULD_NOT_BE_EMPTY);
        else if(StringUtils.isEmpty(userDetails.getPassword()))
            throw new InvalidUserDetailsException(PASSWORD_SHOULD_NOT_BE_EMPTY);
        else if(!passwordPattern.matcher(userDetails.getPassword()).matches())
            throw new InvalidUserDetailsException(INVALID_PASSWORD_PROVIDED);
        else if(StringUtils.isEmpty(userDetails.getIpAddress()))
            throw new InvalidUserDetailsException(IP_ADDRESS_SHOULD_NOT_BE_EMPTY);
        else if(!ipAddressPattern.matcher(userDetails.getIpAddress()).matches())
            throw new InvalidUserDetailsException(INVALID_IP_ADDRESS_PROVIDE);
    }
    public boolean isValidCAUser(String countryCode) {
        return countryCode.equalsIgnoreCase(CA);
    }

    public String formatString(String username, String city) {
        return String.format(RESPONSE_STRING, username, city);
    }
}
