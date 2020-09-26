package pl.ardas.restfullapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class RestfullapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestfullapiApplication.class, args);
    }
    
    @Bean
    public SpringContext springApplicationContext() {
    	return new SpringContext();
    }
    
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
    	return new BCryptPasswordEncoder();
    }
}
