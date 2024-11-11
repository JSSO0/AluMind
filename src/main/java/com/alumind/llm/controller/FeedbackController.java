package com.alumind.llm.controller;

import com.alumind.llm.model.FeedbackResult;
import com.alumind.llm.model.SimpleErrorResponse;
import com.alumind.llm.service.FeedbackProcessingService;
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
    private FeedbackProcessingService feedbackProcessingService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> handleFeedbackSubmission(@RequestBody String feedback) throws JsonProcessingException {
        FeedbackResult result = feedbackProcessingService.processFeedback(feedback);

        if (result.isSpam()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SimpleErrorResponse(result.getErrorMessage()));
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(result.getFeedbackResponse());
        }
    }
}
