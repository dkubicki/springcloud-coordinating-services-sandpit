package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class EurekaTollRateApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaTollRateApplication.class, args);
    }
}
