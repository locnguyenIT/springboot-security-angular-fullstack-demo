package com.example.demo.resetpasswordtoken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/forgot-password")
@Validated
public class ResetPasswordController {

    private final ResetPasswordTokenService resetPasswordTokenService;

    @Autowired
    public ResetPasswordController(ResetPasswordTokenService resetPasswordTokenService) {

        this.resetPasswordTokenService = resetPasswordTokenService;
    }

    @PostMapping(path = "/send-email-token/{email}")
    public void sendTokenToResetPassword(@PathVariable("email")
                                         @NotEmpty(message = "Email should not be empty")
                                         @Email(message = "Email invalid") String email)
    {
        resetPasswordTokenService.sendTokenToResetPassword(email);
    }

    @PutMapping(path = "/reset-password")
    public void resetPasswordWithToken(@RequestParam String token,
                                       @RequestParam
                                       @NotEmpty(message = "Password should not be empty")
                                       @Size(min = 5,message = "Password should be at least 5 characters") String password)
    {
       resetPasswordTokenService.resetPasswordWithToken(token,password);
    }
}
