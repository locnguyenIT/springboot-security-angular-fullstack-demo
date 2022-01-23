package com.example.demo.registration;

import com.example.demo.role.Role;
import com.example.demo.role.RoleRepository;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.demo.role.EnumRole.*;

@Service
public class RegistrationService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public RegistrationService(RoleRepository roleRepository,
                               UserRepository userRepository,
                               BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void register(RegistrationRequest request)
    {
        Optional<User> userByEmail = userRepository.findByEmail(request.getEmail());
        if(userByEmail.isPresent())
        {
            throw new IllegalStateException("Email are already taken");
        }
        Optional<User> userByName = userRepository.findByName(request.getName());
        if(userByName.isPresent())
        {
            throw new IllegalStateException("Name are already taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(request.getPassword()); //encode password of user

        request.setPassword(encodedPassword);

        Role role = roleRepository.findByName(USER);

        User user = new User(request.getName(),request.getEmail(),request.getPassword(),true,role);

        userRepository.save(user);

        System.out.println(user);

    }

}
