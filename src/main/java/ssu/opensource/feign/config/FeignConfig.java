package ssu.opensource.feign.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import ssu.opensource.OpensourceApplication;

@EnableFeignClients(basePackageClasses = OpensourceApplication.class)
@Configuration
public class FeignConfig {
}