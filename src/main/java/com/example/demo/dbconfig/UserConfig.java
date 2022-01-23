package com.example.demo.dbconfig;

import com.example.demo.role.Role;
import com.example.demo.role.RoleRepository;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

import static com.example.demo.role.EnumRole.ADMIN;
import static com.example.demo.role.EnumRole.USER;

@Configuration
public class UserConfig {
    @Bean(name = "user")
    CommandLineRunner commandLineRunner(UserRepository userRepository,
                                        RoleRepository roleRepository,
                                        BCryptPasswordEncoder bCryptPasswordEncoder)
    {
        return args -> {

            Role role_admin = new Role(ADMIN);
            Role role_user = new Role(USER);

            roleRepository.saveAll(List.of(role_admin,role_user));

            User admin = new User("Admin","admin@gmail.com",bCryptPasswordEncoder.encode("admin"),true,role_admin);
            User user = new User("User","user@gmail.com",bCryptPasswordEncoder.encode("user"),false,role_user);
            User loc = new User("loc","loc@gmail.com",bCryptPasswordEncoder.encode("loc"),true,role_user);

            userRepository.saveAll(List.of(admin,user,loc));


        };
    }
}

