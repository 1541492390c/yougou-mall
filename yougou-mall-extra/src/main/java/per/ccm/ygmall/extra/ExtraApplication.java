package per.ccm.ygmall.extra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients(basePackages = "per.ccm.ygmall.feign.user")
@SpringBootApplication(scanBasePackages = "per.ccm.ygmall")
public class ExtraApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExtraApplication.class, args);
    }
}
