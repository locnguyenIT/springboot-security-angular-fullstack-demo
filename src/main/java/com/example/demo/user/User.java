package com.example.demo.user;

import com.example.demo.resetpasswordtoken.ResetPasswordToken;
import com.example.demo.role.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "user",uniqueConstraints = {
                                            @UniqueConstraint(name = "user_name_unique",columnNames = "name"),
                                            @UniqueConstraint(name = "user_email_unique",columnNames = "email")
                                            }
)
public class User{

    @Id //id will be primary key of table student
    @SequenceGenerator(name = "user_sequence",sequenceName = "user_sequence",allocationSize = 1) //generate sequence with id auto increment begin 1
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence") //use sequence is just defined above
    private int id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "email",nullable = false)
    @Email(message = "Email not valid")
    private String email;
    private String password;
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "role_id",
                referencedColumnName = "id",
                foreignKey = @ForeignKey(name = "fk_role"))
    private Role role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<ResetPasswordToken> resetPasswordToken = new ArrayList<>();

    public User() {
    }

        public User(String name, String email, String password, boolean isActive, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.isActive = isActive;
        this.role = role;
    }

    public User(String name, String email, String password, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", appRole=" + role +
                '}';
    }
}
