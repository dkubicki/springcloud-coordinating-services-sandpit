package app;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.WeightedResponseTimeRule;
import org.springframework.context.annotation.Bean;

import javax.inject.Inject;

public class TollClientRoutingConfig {

    @Inject
    IClientConfig ribbonClientConfig;

    @Bean
    public IRule ribbonRule(IClientConfig config) {

        return new WeightedResponseTimeRule();
    }
}
