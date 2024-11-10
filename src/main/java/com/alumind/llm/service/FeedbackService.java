package com.alumind.llm.service;

import com.alumind.llm.model.FeedbackModel;
import com.alumind.llm.nlp.FeedbackAnalyze;
import com.alumind.llm.nlp.SpamValidation;
import com.alumind.llm.repository.FeedbackDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {
    @Autowired
    private SpamValidation spamValidation;
    @Autowired
    private FeedbackModel feedbackModel;
    @Autowired
    private FeedbackAnalyze feedbackAnalyze;
    @Autowired
    private FeedbackDAO feedbackDAO;

    public FeedbackModel analyzeFeedback(String feedback) {
        boolean isSpam = spamValidation.analyze(feedback);
        if (!isSpam) {
        } else {
            FeedbackModel feedbackModel = feedbackAnalyze.analyze(feedback);
            feedbackDAO.insertFeedback(feedbackModel);
            return feedbackModel;
        }
        return feedbackModel;
    };
}
