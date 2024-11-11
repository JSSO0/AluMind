package com.alumind.llm.model;

public class FeedbackResponse {
    private int id;
    private String sentiment;
    private FeedbackDetails feedbackDetails;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSentiment() {
        return sentiment;
    }

    public void setSentiment(String sentiment) {
        this.sentiment = sentiment;
    }

    public FeedbackDetails getRequestFeaturesModel() {
        return feedbackDetails;
    }

    public void setRequestFeaturesModel(FeedbackDetails feedbackDetails) {
        this.feedbackDetails = feedbackDetails;
    }

}
