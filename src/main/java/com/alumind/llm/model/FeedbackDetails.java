package com.alumind.llm.model;

import org.springframework.stereotype.Component;

@Component
public class FeedbackDetails {
    private String code;
    private String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
