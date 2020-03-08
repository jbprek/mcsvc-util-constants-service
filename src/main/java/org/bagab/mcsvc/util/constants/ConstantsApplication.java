package org.bagab.mcsvc.util.constants;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.PostConstruct;

@Slf4j
@AllArgsConstructor
@EnableDiscoveryClient
@SpringBootApplication
public class ConstantsApplication {

    private Environment environment;

    @PostConstruct
    public void setup() {
        log.info("Started  EUREKA instance :{}", environment.getProperty("eureka.client.serviceUrl.defaultZone"));
    }


    @Configuration
    public static class SecurityPermitAllConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().anyRequest().permitAll()
                    .and().csrf().disable();
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(ConstantsApplication.class, args);
    }

}
