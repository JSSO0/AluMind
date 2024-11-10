package com.alumind.llm.nlp;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class OpenAiRequest {
    @Value("${openai.api.url}")
    private String apiUrl;

    @Value("${openai.api.key}")
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
        requestBody.put("model", "text-davinci-003");
        requestBody.put("prompt", prompt);
        requestBody.put("max_tokens", 10);

        return new HttpEntity<>(requestBody.toString(), headers);
    }
}
