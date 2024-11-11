package com.alumind.llm.service;

import com.alumind.llm.model.*;
import com.alumind.llm.nlp.*;
import com.alumind.llm.repository.FeedbackRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackProcessingService {
    @Autowired
    private SpamDetectionService spamDetectionService;
    @Autowired
    private FeedbackAnalyze feedbackAnalyze;
    @Autowired
    private FeedbackRepository feedbackRepository;

    public FeedbackResult processFeedback(String feedback) throws JsonProcessingException {
        boolean isSpam = spamDetectionService.isSpam(feedback);
        if (isSpam) {
            return new FeedbackResult("Feedback não processado devido a conteúdo suspeito");
        } else {
            FeedbackRequest feedbackRequest = feedbackAnalyze.analyze(feedback);
            FeedbackResponse feedbackResponse = feedbackRepository.saveFeedback(feedbackRequest);
            return new FeedbackResult(feedbackResponse);
        }
    }
}
