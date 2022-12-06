package com.IronHackRaulRuiz.FinalProjectRaulRuiz.services.accounts;

import com.IronHackRaulRuiz.FinalProjectRaulRuiz.models.accounts.StudentChecking;
import com.IronHackRaulRuiz.FinalProjectRaulRuiz.repositories.accounts.StudentCheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StudentCheckingService {

    @Autowired
    StudentCheckingRepository studentCheckingRepository;

    public List<StudentChecking> findAll() {

        return studentCheckingRepository.findAll();

    }

    public StudentChecking findById(Long id) {

        if (studentCheckingRepository.findById(id).isPresent()) {

            return studentCheckingRepository.findById(id).get();

        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID no encontrado");

    }

    public void deleteById(Long id) {

        studentCheckingRepository.deleteById(id);

    }

}
