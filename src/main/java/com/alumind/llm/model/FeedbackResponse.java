package com.alumind.llm.model;

public class FeedbackResponse {
    private int id;
    private String sentiment;
    private RequestFeaturesModel requestFeaturesModel;

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

    public RequestFeaturesModel getRequestFeaturesModel() {
        return requestFeaturesModel;
    }

    public void setRequestFeaturesModel(RequestFeaturesModel requestFeaturesModel) {
        this.requestFeaturesModel = requestFeaturesModel;
    }
}
