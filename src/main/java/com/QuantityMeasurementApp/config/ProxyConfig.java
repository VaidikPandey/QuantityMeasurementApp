package com.QuantityMeasurementApp.config;

import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProxyConfig {

    // Tells Tomcat to trust Railway's reverse proxy headers
    // Without this, Spring thinks requests are HTTP, session cookies break
    @Bean
    public RemoteIpFilter remoteIpFilter() {
        RemoteIpFilter filter = new RemoteIpFilter();
        filter.setProtocolHeader("X-Forwarded-Proto");
        filter.setRemoteIpHeader("X-Forwarded-For");
        return filter;
    }
}