package ssu.opensource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OpensourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpensourceApplication.class, args);
	}

}
