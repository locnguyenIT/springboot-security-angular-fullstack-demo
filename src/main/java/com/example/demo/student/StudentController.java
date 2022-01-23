package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.*;

@RestController //This annotation make this class request/response API from client
@RequestMapping(path = "/api/student") //mapping to the endpoint url
public class StudentController { //Request API , Response API

    private final StudentService studentService;
    @Autowired //StudentService autowired inject into StudentController (Dependency Injection)
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping //GET student
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
//    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public List<Student> getStudent()
    {
        System.out.println(studentService.getStudent());
        return studentService.getStudent();
    }

//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
//    @PostMapping(path = "/{studentId}/upload/image",
//                consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
//                produces = MediaType.APPLICATION_JSON_VALUE)
//    public void uploadImageStudent(@PathVariable("studentId") Integer studentId,
//                                   @RequestParam("file") MultipartFile file)
//    {
//        studentService.uploadImageStudent(studentId, file);
//    }

//    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
//    @GetMapping(path = "/show/{studentId}/download/image",produces = {IMAGE_PNG_VALUE, IMAGE_JPEG_VALUE, IMAGE_GIF_VALUE})
//    public byte[] downloadImageStudent(@PathVariable("studentId") Integer studentId)
//    {
//        return studentService.downloadStudentImage(studentId);
//    }



    //case 2:
    @GetMapping(path = "/find/{studentId}") //GET student by id
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public Optional<Student> getStudentById(@PathVariable("studentId") Integer studentId) //@PathVariable is extract the values (studentID) from the URL
    {
        return studentService.getStudentById(studentId);
    }

    @PostMapping(path = "/add") //POST student
    @PreAuthorize("hasRole('ADMIN')")
    public Student addStudent(@Valid @RequestBody Student student) //@RequestBody is take the request body client and map it into a student
    {
        return studentService.addStudent(student);
    }

    @DeleteMapping(path = "/delete/{studentId}") //Delete student with studentId
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteStudent(@PathVariable("studentId") Integer studentId) //@PathVariable is extract the values (studentID) from the URL
    {
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "update/{studentId}") //Update student with studentId
    @PreAuthorize("hasRole('ADMIN')")
    public void updateStudent(  @PathVariable("studentId") Integer studentId, //@PathVariable is extract the values (studentID) from the URL
                              @RequestParam(required = false) String name,  //@RequestParam is extract the query parameters from the URL. required = false mean is not required have parameter
                               @RequestParam(required = false) String email,
                              @RequestParam(required = false) String gender,
                              @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dob) //@RequestParam is extract the query parameters from the URL. required = false mean is not required have parameter
    {
        studentService.updateStudent(studentId,name,email,gender,dob);
    }


}
