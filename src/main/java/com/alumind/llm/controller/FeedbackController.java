package com.alumind.llm.controller;

import com.alumind.llm.model.FeedbackModel;
import com.alumind.llm.service.FeedbackService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<FeedbackModel> processFeedback(@RequestBody String feedback) throws JsonProcessingException {
        FeedbackModel feedbackModel = feedbackService.analyzeFeedback(feedback);
        return ResponseEntity.status(HttpStatus.CREATED).body(feedbackModel);
    }
}
