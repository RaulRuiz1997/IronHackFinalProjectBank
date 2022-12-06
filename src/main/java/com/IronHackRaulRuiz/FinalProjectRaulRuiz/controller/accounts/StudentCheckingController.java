package com.IronHackRaulRuiz.FinalProjectRaulRuiz.controller.accounts;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.StudentChecking;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.services.accounts.StudentCheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student-checking")
public class StudentCheckingController {

    @Autowired
    StudentCheckingService studentCheckingService;

    // GET -> localhost:8080/student-checking/all
    // Método para encontrar todos los Student Checking Accounts
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentChecking> findAll() {
        return studentCheckingService.findAll();
    }

    // GET -> localhost:8080/student-checking/id/5
    // Método para encontrar un Student Checking Account por ID con PathVariable
    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentChecking findByIdPathVariable(@PathVariable Long id) {
        return studentCheckingService.findById(id);
    }

    // GET -> localhost:8080/student-checking/id?id=5
    // Método para encontrar un Student Checking Account por ID con RequestParam
    @GetMapping("/id")
    @ResponseStatus(HttpStatus.OK)
    public StudentChecking findByIdRequestParam(@RequestParam Long id) {
        return studentCheckingService.findById(id);
    }

    // DELETE -> localhost:8080/student-checking/delete/5
    // Método para eliminar un Student Checking Account mediante su ID
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudentCheckingAccount(@PathVariable Long id) {
        studentCheckingService.deleteById(id);
    }

}
