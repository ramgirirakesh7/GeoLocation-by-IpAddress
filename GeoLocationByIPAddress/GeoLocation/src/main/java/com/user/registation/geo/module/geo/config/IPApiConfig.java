package com.user.registation.geo.module.geo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import static com.user.registation.geo.module.geo.utils.AppConstants.PREFIX_PATTERN;

@Configuration
@ConfigurationProperties(prefix = PREFIX_PATTERN)
@Data
public class IPApiConfig {
    public String endpoint;
}
