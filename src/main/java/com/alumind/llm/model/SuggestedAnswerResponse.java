package com.alumind.llm.model;

public class SuggestedAnswerResponse {
    private String suggestedAnswer;

    public SuggestedAnswerResponse(String suggestedAnswer) {
        this.suggestedAnswer = suggestedAnswer;
    }

    public String getSuggestedAnswer() {
        return suggestedAnswer;
    }
}
