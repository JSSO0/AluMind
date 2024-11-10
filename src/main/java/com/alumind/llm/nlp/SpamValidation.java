package com.alumind.llm.nlp;

import com.alumind.llm.model.FeedbackModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpamValidation {
    @Autowired
    private OpenAiRequest openAiRequest;

    @Autowired
    private FeedbackModel feedbackModel;

    public boolean analyze(String feedback) {
        feedbackModel.setFeedbackOriginal(feedback);
        String prompt = "Classifique o seguinte texto como 'spam' ou 'não spam'. Texto: \"" + feedback + "\".";
        String responseBody = openAiRequest.sendRequest(prompt, feedback);
        if (responseBody.contains("spam")) {
            return true;
        } else {
            return false;
        }
    }
}
