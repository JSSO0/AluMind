package com.alumind.llm.model;

public class FeedbackResult {
    private boolean isSpam;
    private FeedbackResponse feedbackResponse;
    private String errorMessage;

    public FeedbackResult(FeedbackResponse feedbackResponse) {
        this.isSpam = false;
        this.feedbackResponse = feedbackResponse;
    }

    public FeedbackResult(String errorMessage) {
        this.isSpam = true;
        this.errorMessage = errorMessage;
    }

    public boolean isSpam() {
        return isSpam;
    }

    public FeedbackResponse getFeedbackResponse() {
        return feedbackResponse;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
