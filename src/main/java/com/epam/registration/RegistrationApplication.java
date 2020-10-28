package com.epam.registration;

import com.epam.registration.model.User;
import com.epam.registration.model.enums.Role;
import com.epam.registration.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class RegistrationApplication {



    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(RegistrationApplication.class, args);
        createAdmin(run);
    }

    public static void createAdmin(ConfigurableApplicationContext run) {
        UserRepository userRepository = run.getBean(UserRepository.class);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User newUserDto = new User();
        newUserDto.setUserName("Admin");
        newUserDto.setPassword(bCryptPasswordEncoder.encode("myPassword1"));
        newUserDto.setCardNumber("4141537587129115");
        newUserDto.setRole(Role.ROLE_ADMIN);
        userRepository.save(newUserDto);
    }

}
