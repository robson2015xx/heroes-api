package br.com.gubee.interview.adapters.configuration;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.TimeZone;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.annotation.PostConstruct;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

	public static final ZoneId ZONE_ID = ZoneOffset.UTC;
	
    @PostConstruct
    void init() {
        TimeZone.setDefault(TimeZone.getTimeZone(ZONE_ID));
    }
}