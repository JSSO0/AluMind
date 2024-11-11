package com.alumind.llm.nlp;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenAiRequestService {
    @Value("${openai.api.url}")
    private String apiUrl;

    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    private RestTemplate restTemplate = new RestTemplate();

    public String sendRequest(String prompt, String feedback) {
        HttpEntity<String> requestEntity = createRequestEntity(prompt, feedback);
        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);
        return response.getBody();
    }
    private HttpEntity<String> createRequestEntity(String prompt, String feedback) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        JSONObject requestBody = new JSONObject();
        requestBody.put("model", "gpt-4o-mini-2024-07-18");
        requestBody.put("messages", new JSONArray());
        requestBody.put("temperature", 1);
        requestBody.put("max_tokens", 2048);
        requestBody.put("top_p", 1);
        requestBody.put("frequency_penalty", 0);
        requestBody.put("presence_penalty", 0);
        JSONObject responseFormat = new JSONObject();
        responseFormat.put("type", "text");
        requestBody.put("response_format", responseFormat);
        requestBody.put("messages", new JSONArray().put(new JSONObject().put("role", "user").put("content", prompt)));

        return new HttpEntity<>(requestBody.toString(), headers);
    }
}
