package app;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;

@RibbonClient(name = "eureka-fastpass-service", configuration = TollClientRoutingConfig.class)
@Controller
public class FastPassController {

    @Inject
    private RestTemplate restTemplate;

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @HystrixCommand(fallbackMethod = "getFastPassCustomerDetailsFallback")
    @RequestMapping(path = "/customerdetails", params = {"fastpassid"})
    public String getFastPassCustomerDetails(@RequestParam String fastpassid, Model m) {

        FastPassCustomer c = restTemplate.getForObject("http://eureka-fastpass-service/fastpass?fastpassid=" + fastpassid, FastPassCustomer.class);
        System.out.println("retrieved customer details");
        m.addAttribute("customer", c);
        return "console";
    }

    public String getFastPassCustomerDetailsFallback(@RequestParam String fastpassid, Model m) {
        FastPassCustomer c = new FastPassCustomer();
        c.setFastPassId(fastpassid);
        System.out.println("Fallback operation called");
        m.addAttribute("customer", c);

        return "console";
    }
}
