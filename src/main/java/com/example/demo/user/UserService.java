package com.example.demo.user;

import com.example.demo.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User getUser(String username) {
        return  userRepository.findByEmail(username).orElseThrow(()-> new IllegalStateException("Username "+username+ " was not found"));
    }

    public void addUser(User user, Integer roleId) {
        Optional<User> userByName = userRepository.findByName(user.getName());
        if(userByName.isPresent())
        {
            throw new IllegalStateException("Name already taken");
        }
        Optional<User> userByEmail = userRepository.findByEmail(user.getEmail());
        if(userByEmail.isPresent())
        {
            throw new IllegalStateException("Email already taken");
        }

        user.setRole(roleRepository.getById(roleId));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }

    @Transactional
    public void updateUser(Integer userId, String name, String email, boolean isActive,Integer roleId) {
        User userById = userRepository.getById(userId);
        if(name != null && name.length() > 0 && !Objects.equals(userById.getName(),name))
        {
            Optional<User> userByName = userRepository.findByName(name);
            if (userByName.isPresent())
            {
                throw new IllegalStateException("Name already taken");
            }
            userById.setName(name);
        }
        if(email != null && email.length() > 0 && !Objects.equals(userById.getEmail(),email))
        {
            Optional<User> userByEmail = userRepository.findByEmail(email);
            if (userByEmail.isPresent())
            {
                throw new IllegalStateException("Email already taken");
            }
            userById.setEmail(email);
        }
        if(roleId != null && !Objects.equals(userById.getRole().getName(),roleId))
        {
            userById.setRole(roleRepository.getById(roleId));
        }
        userById.setActive(isActive);

    }


}
