package com.example.demo.resetpasswordtoken;

import com.example.demo.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
public class ResetPasswordToken {

    @Id
    @SequenceGenerator(name = "token_sequence",sequenceName = "token_sequence",allocationSize = 1) //generate sequence with id auto increment begin 1
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "token_sequence")
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false,columnDefinition = "TIMESTAMP")
    private LocalDateTime createAt;

    @Column(nullable = false,columnDefinition = "TIMESTAMP")
    private LocalDateTime expiredAt;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(name = "user_id",
                referencedColumnName = "id",
                foreignKey = @ForeignKey(name = "fk_user"))
    private User user;

//    public ResetPasswordToken(String token, LocalDateTime createAt, LocalDateTime expiredAt, Optional<User> userByEmail) {
//        this.token = token;
//        this.createAt = createAt;
//        this.expiredAt = expiredAt;
//        this.user = userByEmail.get();
//    }

    public ResetPasswordToken() {
    }

    public ResetPasswordToken(String token, LocalDateTime createAt, LocalDateTime expiredAt, User user) {
        this.token = token;
        this.createAt = createAt;
        this.expiredAt = expiredAt;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(LocalDateTime expiredAt) {
        this.expiredAt = expiredAt;
    }

    public LocalDateTime getConfirmedAt() {
        return confirmedAt;
    }

    public void setConfirmedAt(LocalDateTime confirmedAt) {
        this.confirmedAt = confirmedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ResetPasswordToken{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", createAt=" + createAt +
                ", expiredAt=" + expiredAt +
                ", confirmedAt=" + confirmedAt +
                ", user=" + user +
                '}';
    }
}
