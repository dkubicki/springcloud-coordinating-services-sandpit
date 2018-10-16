package app;

import app.filters.ProxyFilter;
import app.filters.StartPreFilter;
import app.filters.StopPostFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringBootApplication
public class ZuulAPIProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulAPIProxyApplication.class, args);
    }

    @Bean
    public ProxyFilter proxyFilter() {
        return new ProxyFilter();
    }

    @Bean
    public StartPreFilter startPreFilter() {
        return new StartPreFilter();
    }

    @Bean
    public StopPostFilter stopPostFilter() {
        return new StopPostFilter();
    }

}
