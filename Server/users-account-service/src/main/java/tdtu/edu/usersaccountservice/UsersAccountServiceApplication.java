package tdtu.edu.usersaccountservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableEurekaClient
@Configuration
@EnableMethodSecurity
public class UsersAccountServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(UsersAccountServiceApplication.class, args);
	}

}
