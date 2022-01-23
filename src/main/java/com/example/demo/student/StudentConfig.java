package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Configuration //Config & set 2 student in database
public class StudentConfig {

//    private static final String DATE_FORMATTER= "yyyy/MM/dd hh:mm:ss a";
//    LocalDateTime dateTime = LocalDateTime.now();
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
//    String localDateTime = dateTime.format(formatter);

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository)
    {

        return args ->  {
            Student loc = new Student(
//                    "monkey.jpg",
                    "Nguyen Thanh Loc",
                    "ntloc.developer@gmail.com",
                    Gender.Male,
                    LocalDateTime.now());

            Student linh = new Student(
//                    "bird.png",
                    "Tran Ha Linh",
                    "halinh@gmail.com",
                    Gender.Female,
                    LocalDateTime.now());
            Student hoang = new Student(
//                    "javan.jfif",
                    "Nguyen Huy Hoang",
                    "hoang@gmail.com",
                    Gender.Male,
                    LocalDateTime.now());
            Student thao = new Student(
//                    "leesin.jfif",
                    "Phung Hoai Thao",
                    "thao@gmail.com",
                    Gender.Female,
                    LocalDateTime.now());
            Student hung = new Student(
//                    "gnar.jfif",
                    "Nguyen Nam Hung ",
                    "hung@gmail.com",
                    Gender.Male,
                    LocalDateTime.now());

            repository.saveAll(List.of(loc,linh,hoang,thao,hung));
        };
    }
}
