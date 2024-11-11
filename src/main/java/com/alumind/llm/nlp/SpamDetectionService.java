package com.alumind.llm.nlp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpamDetectionService {
    @Autowired
    private OpenAiRequestService openAiRequestService;

    public boolean isSpam(String feedback) throws JsonProcessingException {
        String prompt = "Me retorne se é SPAM ou NÃO SPAM. Texto: \"" + feedback + "\".";
        String responseBody = openAiRequestService.sendRequest(prompt, feedback);
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
