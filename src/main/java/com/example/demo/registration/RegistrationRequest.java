package com.example.demo.registration;

import com.example.demo.role.EnumRole;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class RegistrationRequest {


    private String name;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email register invalid")
    private String email;

    @NotEmpty(message = "Password should not be empty")
    @Size(min = 5,message = "Password should be at least 5 characters")
    private String password;

    public RegistrationRequest() {
    }

    public RegistrationRequest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "RegistrationRequest{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
