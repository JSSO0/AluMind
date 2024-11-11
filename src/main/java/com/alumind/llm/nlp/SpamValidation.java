package com.alumind.llm.nlp;

import com.alumind.llm.model.FeedbackModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpamValidation {
    @Autowired
    private OpenAiRequest openAiRequest;

    @Autowired
    private FeedbackModel feedbackModel;

    public boolean analyze(String feedback) throws JsonProcessingException {
        feedbackModel.setFeedbackOriginal(feedback);
        String prompt = "Me retorne se é SPAM ou NÃO SPAM. Texto: \"" + feedback + "\".";
        String responseBody = openAiRequest.sendRequest(prompt, feedback);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(responseBody);
        String content = jsonNode.get("choices").get(0).get("message").get("content").asText();
        if (content.equalsIgnoreCase("SPAM")) {
            return true;
        } else {
            return false;
        }
    }
}
