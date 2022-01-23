package com.example.demo.student;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;


@Service //this annotation marks a Java class that performs some service, such as execute business logic
public class StudentService {

    private final StudentRepository studentRepository;


    @Autowired //studentRepository auto wired inject into StudentService (Dependency Injection)
    public StudentService(StudentRepository repository) {
        this.studentRepository = repository;
    }

    public List<Student> getStudent()
    {
        return studentRepository.findAll();
    }

//    case 2
    public Optional<Student> getStudentById(Integer studentId)
    {
        Optional<Student> studentById = studentRepository.findById(studentId);
        if(!studentById.isPresent()) //if id of student does not exist in database
        {
            throw new IllegalStateException("student with id "+studentId+ " does not exist in database");
        }
        return  studentById;

    }

    public Student addStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if(studentByEmail.isPresent()) //if email of student is present in database
        {
            throw new IllegalStateException("Email already exist in database");
        }
        System.out.println(student);
        return studentRepository.save(student);

    }

    public void deleteStudent(Integer studentId)
    {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists)
        {
            throw new IllegalStateException("student with id = "+studentId+ " does not exist in database");
        }
        System.out.println("Student id = "+studentId+" has been delete");
        studentRepository.deleteById(studentId);
    }

    public void updateStudent(Integer studentId, String name, String email, String gender, LocalDateTime dob)
    {
        //Check studentId in database
        Student student = studentRepository.findById(studentId).orElseThrow(()
                -> new IllegalStateException("student with id "+studentId+ " does not exist"));

        if(name != null && name.length() > 0 && !Objects.equals(student.getName(),name)) //if the new name has been provided is not the same name in database
        {
            student.setName(name);
        }
        if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(),email)) //if the new email has been provided is not the same email in database
        {
            Optional<Student> studentByEmail = studentRepository.findStudentByEmail(email);
            if(studentByEmail.isPresent()) //if new email of student is present in database
            {
                throw new IllegalStateException("Email already exist in database");
            }
            student.setEmail(email);
        }
        if(gender != null && !Objects.equals(student.getGender(),gender)) //if the new name has been provided is not the same name in database
        {
            student.setGender(Gender.valueOf(gender));
        }
        if(dob != null && !Objects.equals(student.getDob(),dob)) //if the new name has been provided is not the same name in database
        {
            student.setDob(dob);
        }
        studentRepository.save(student);
    }

}
