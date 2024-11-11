package com.alumind.llm.model;

import org.springframework.stereotype.Component;

@Component
public class FeedbackRequest {
    private Integer id;
    private String sentiment;
    private FeedbackDetails feedbackDetails;
    private String feedbackOriginal;
    private String suggestedAnswer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public FeedbackDetails getRequestFeaturesModel() {
        return feedbackDetails;
    }

    public void setRequestFeaturesModel(FeedbackDetails feedbackDetails) {
        this.feedbackDetails = feedbackDetails;
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

    public String getSuggestedAnswer() {
        return suggestedAnswer;
    }

    public void setSuggestedAnswer(String suggestedAnswer) {
        this.suggestedAnswer = suggestedAnswer;
    }
}
