package com.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {

    @GetMapping("/getStudent")
    public ResponseEntity<Map<String, Object>> getStudent() {
        Map<String, Object> student = new HashMap<>();
        student.put("id", 1);
        student.put("name", "John Student");
        student.put("course", "Computer Science");
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
}
