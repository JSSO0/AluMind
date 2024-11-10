package com.alumind.llm.service;

import com.alumind.llm.model.FeedbackModel;
import com.alumind.llm.nlp.FeedbackAnalyze;
import com.alumind.llm.nlp.SpamValidation;
import org.springframework.beans.factory.annotation.Autowired;

public class FeedbackService {
    @Autowired
    private SpamValidation spamValidation;
    @Autowired
    private FeedbackModel feedbackModel;
    @Autowired
    private FeedbackAnalyze feedbackAnalyze;

    public FeedbackModel AnalyzeFeedback(String feedback) {
       boolean isSpam = spamValidation.analyze(feedback);
        if (!isSpam) {
            feedbackModel = feedbackAnalyze.analyze(feedback);

        } else {

        }
    };
}
