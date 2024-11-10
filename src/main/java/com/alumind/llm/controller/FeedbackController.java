package com.alumind.llm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/feedback")
public class FeedbackController {

        public ResponseEntity<String> processFeedback(@RequestBody String feedback) {
        return ResponseEntity.status(HttpStatus.CREATED).body(FeedbackModel);
    }
}
