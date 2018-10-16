package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;

@EnableBinding(Sink.class)
@SpringBootApplication
public class StreamTollintakeApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamTollintakeApplication.class, args);
    }

    //@ServiceActivator(inputChannel=Sink.INPUT)
    //@SendTo(Processor.OUTPUT)
    @StreamListener(target=Sink.INPUT, condition="headers['speed'] > 40")
    public void logfast(String msg) {
        System.out.println("fast = " + msg);
    }

    //@StreamListener(Sink.INPUT)
    @ServiceActivator(inputChannel = Sink.INPUT)
    public void log(String msg) {
        System.out.println(msg);
    }
}
