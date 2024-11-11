package com.alumind.llm.utils;

import com.alumind.llm.model.*;
import org.springframework.jdbc.support.KeyHolder;

import java.util.Map;

public class FeedbackMapper {
    public FeedbackResponse mapToFeedbackResponse(KeyHolder keyHolder, FeedbackModel feedbackModel) {
        Map<String, Object> generatedKeys = keyHolder.getKeys();
        FeedbackResponse feedbackResponse = new FeedbackResponse();
        feedbackResponse.setId((int) generatedKeys.get("id"));
        feedbackResponse.setSentiment(feedbackModel.getSentiment());
        RequestFeaturesModel featureModel = new RequestFeaturesModel();
        featureModel.setCode(feedbackModel.getRequestFeaturesModel().getCode());
        featureModel.setReason(feedbackModel.getRequestFeaturesModel().getReason());
        feedbackResponse.setRequestFeaturesModel(featureModel);
        return feedbackResponse;
    }
}
