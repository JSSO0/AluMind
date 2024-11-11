package com.alumind.llm.utils;

import com.alumind.llm.model.*;
import org.springframework.jdbc.support.KeyHolder;

import java.util.Map;

public class FeedbackMapper {
    public FeedbackResponse mapToFeedbackResponse(KeyHolder keyHolder, FeedbackRequest feedbackRequest) {
        Map<String, Object> generatedKeys = keyHolder.getKeys();
        FeedbackResponse feedbackResponse = new FeedbackResponse();
        feedbackResponse.setId((int) generatedKeys.get("id"));
        feedbackResponse.setSentiment(feedbackRequest.getSentiment());

        FeedbackDetails featureModel = new FeedbackDetails();

        featureModel.setCode(feedbackRequest.getRequestFeaturesModel().getCode());
        featureModel.setReason(feedbackRequest.getRequestFeaturesModel().getReason());
        feedbackResponse.setRequestFeaturesModel(featureModel);
        return feedbackResponse;
    }
}
