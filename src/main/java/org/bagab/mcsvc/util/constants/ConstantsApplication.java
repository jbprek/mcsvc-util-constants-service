package org.bagab.mcsvc.util.constants;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

@Slf4j
@AllArgsConstructor
@SpringBootApplication
public class ConstantsApplication {

    private Environment environment;

    @PostConstruct
    public void setup() {
        log.info("Started  EUREKA instance :{}", environment.getProperty("eureka.client.serviceUrl.defaultZone"));
    }


    public static void main(String[] args) {
        SpringApplication.run(ConstantsApplication.class, args);
    }

}
