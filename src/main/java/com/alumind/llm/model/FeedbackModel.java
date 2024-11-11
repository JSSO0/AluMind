package com.alumind.llm.model;

import org.springframework.stereotype.Component;

@Component
public class FeedbackModel {
    private Integer id;
    private String sentiment;
    private RequestFeaturesModel requestFeaturesModel;
    private String feedbackOriginal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RequestFeaturesModel getRequestFeaturesModel() {
        return requestFeaturesModel;
    }

    public void setRequestFeaturesModel(RequestFeaturesModel requestFeaturesModel) {
        this.requestFeaturesModel = requestFeaturesModel;
    }

    public String getSentiment() {
        return sentiment;
    }

    public void setSentiment(String sentiment) {
        this.sentiment = sentiment;
    }

    public String getFeedbackOriginal() {
        return feedbackOriginal;
    }

    public void setFeedbackOriginal(String feedbackOriginal) {
        this.feedbackOriginal = feedbackOriginal;
    }

}
