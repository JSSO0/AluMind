package com.alumind.llm.model;

public class FeedbackModel {
    private Integer id;
    private String sentiment;
    private RequestFeaturesModel requestFeaturesModel;
    private String FeedbackOriginal;

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
        return FeedbackOriginal;
    }

    public void setFeedbackOriginal(String feedbackOriginal) {
        FeedbackOriginal = feedbackOriginal;
    }

}
