package com.example.demo.student;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Optional;


@Entity(name = "Student") //Mapping the Student class to Student table in database
@Table //Have column id, name, email, dob
public class Student {

    @Id //id will be primary key of table student
    @SequenceGenerator(name = "student_sequence",sequenceName = "student_sequence",allocationSize = 1) //generate sequence with id auto increment begin 1
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence") //use sequence is just defined above
    private int id;

    @Column(name = "name",nullable = false)
    @NotEmpty(message = "Name should not be empty")
    private String name;

    @Column(name = "email",nullable = false,unique = true)
    @Email(message = "Email invalid")
    private String email;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "gender",nullable = false)
    private Gender gender;

    @Column(name = "dob",nullable = false,columnDefinition = "DATETIME")
    private LocalDateTime dob;

    public Student() {}

    public Student(String name, String email, Gender gender, LocalDateTime dob) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.dob = dob;
    }

//    public Student(String name, String email, Gender gender, LocalDate dob) {
//        this.name = name;
//        this.email = email;
//        this.gender = gender;
//        this.dob = dob;
//    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
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

    public LocalDateTime getDob() {
        return dob;
    }

    public void setDob(LocalDateTime dob) {
        this.dob = dob;
    }


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", dob=" + dob +
                '}';
    }
}
