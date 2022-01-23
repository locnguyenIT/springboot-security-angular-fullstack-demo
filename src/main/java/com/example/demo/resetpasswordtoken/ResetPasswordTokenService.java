package com.example.demo.resetpasswordtoken;

import com.example.demo.mail.MailService;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ResetPasswordTokenService {

    private final UserRepository userRepository;
    private final MailService mailService;
    private final ResetPasswordTokenRepository resetPasswordTokenRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public ResetPasswordTokenService(UserRepository userRepository,
                                     MailService mailService,
                                     ResetPasswordTokenRepository resetPasswordTokenRepository,
                                     BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.mailService = mailService;
        this.resetPasswordTokenRepository = resetPasswordTokenRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void sendTokenToResetPassword(String email) {
        //1. Find the user
        User user = userRepository.findByEmail(email).orElseThrow(()->
                new IllegalStateException(String.format("User with email '%s' was not found",email)));
        //2. Create & Save token
        String token = UUID.randomUUID().toString();
        ResetPasswordToken reset_password_token = new ResetPasswordToken(token, LocalDateTime.now(),LocalDateTime.now().plusMinutes(15),user);
        resetPasswordTokenRepository.save(reset_password_token);
        //3. Send token to email
        String link = "http://localhost:8080/#/index/reset-password";
        mailService.sendEmail(user.getEmail(),buildEmail(user.getName(),link,token));
    }

    public String buildEmail(String name, String link, String token) {
        return  "<p>Hi "+ name +"</p>"
                + "<p>Thank you for visiting our website</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>This is your token: <strong>" +token+ "</strong></p>"
                + "<p>Click the link below to reset your password:</p>"
                + "<p><a href=\"" + link + "\">Reset my password</a></p>"
                + "<p>Token will expire in 15 minutes</p>"
                + "<p>See you soon !</p>";
    }

    @Transactional
    public void resetPasswordWithToken(String token, String password) {
        //1. Find token
        ResetPasswordToken reset_password_token = resetPasswordTokenRepository.findByToken(token).orElseThrow(()->
                new IllegalStateException("Token was not found"));
        //2. Get the user from token
        User user = userRepository.getById(reset_password_token.getUser().getId());
        //3. Check token already confirmed
        if (reset_password_token.getConfirmedAt() != null) {
            throw new IllegalStateException("Email already confirmed");
        }
        //4. Check token is expired
        LocalDateTime expiredAt = reset_password_token.getExpiredAt();
        if (expiredAt.isBefore(LocalDateTime.now())) {
            resetPasswordTokenRepository.delete(reset_password_token);
            throw new IllegalStateException("Token expired");
        }
        //5. Reset password
        user.setPassword(bCryptPasswordEncoder.encode(password));
        resetPasswordTokenRepository.updateConfirmedAt(token,LocalDateTime.now());
    }
}
