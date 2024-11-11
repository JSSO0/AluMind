package com.alumind.llm.controller;

import com.alumind.llm.model.FeedbackResponse;
import com.alumind.llm.service.FeedbackService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<FeedbackResponse> processFeedback(@RequestBody String feedback) throws JsonProcessingException {
        FeedbackResponse feedbackResponse = feedbackService.analyzeFeedback(feedback);
        return ResponseEntity.status(HttpStatus.CREATED).body(feedbackResponse);
    }
}
