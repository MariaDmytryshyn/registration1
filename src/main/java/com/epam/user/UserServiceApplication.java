package com.epam.user;

import com.epam.user.model.User;
import com.epam.user.model.enums.Role;
import com.epam.user.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
public class UserServiceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(UserServiceApplication.class, args);
        createAdmin(run);
    }

    public static void createAdmin(ConfigurableApplicationContext run) {
        UserRepository userRepository = run.getBean(UserRepository.class);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User newUserDto = new User();
        newUserDto.setUserName("Admin");
        newUserDto.setPassword(bCryptPasswordEncoder.encode("myPassword1"));
        newUserDto.setCardNumber("4141537587129115");
        newUserDto.setDateOfBirth(LocalDate.of(1999, 12, 11));
        newUserDto.setRole(Role.ROLE_ADMIN);
        userRepository.save(newUserDto);
    }

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate()
    {
        return new RestTemplate();
    }
}
