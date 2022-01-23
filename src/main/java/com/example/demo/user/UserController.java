package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public List<User> getAllUser()
    {
        return userService.getAllUser();
    }

    @GetMapping(path = "/get/{username}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public User getUser(@PathVariable("username") String username)
    {
        return userService.getUser(username);
    }

    @PostMapping(path = "/add/role/{roleId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void addUser(@Valid @RequestBody User user,
                        @PathVariable("roleId") Integer roleId)
    {
        userService.addUser(user,roleId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = "/delete/{userId}")
    public void deleteUser(@PathVariable("userId") Integer userId)
    {
        userService.deleteUser(userId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(path = "/update/{userId}")
    public void updateUser(@PathVariable("userId") Integer userId,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) String email,
                           @RequestParam(required = false) boolean isActive,
                           @RequestParam(required = false) Integer roleId)
    {
        userService.updateUser(userId,name,email,isActive,roleId);
    }

}
