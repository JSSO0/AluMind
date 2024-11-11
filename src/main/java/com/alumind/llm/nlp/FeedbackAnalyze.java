package com.alumind.llm.nlp;

import com.alumind.llm.model.FeedbackRequest;
import com.alumind.llm.model.FeedbackDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;;

@Service
public class FeedbackAnalyze {
    @Autowired
    private OpenAiRequestService openAiRequestService;

    public FeedbackRequest analyze(String feedback) throws JsonProcessingException {
        String prompt = "Classifique o sentimento do seguinte feedback como 'POSITIVO', 'NEGATIVO' ou 'INDIFERENTE' e Identifique a solicitação de funcionalidade no feedback a seguir e classifique o código (como 'EDITAR_PERFIL', 'ALTERAR_SENHA' ou outros) e a razão da solicitação e uma sugestão de resposta para o feedback. e Me devolva na estrutura SENTIMENT: CODE: REASON: SUGGESTED_ANSWER: lembrando da quebra de linha ; o Feedback: \"" + feedback + "\".";
        String responseBody = openAiRequestService.sendRequest(prompt, feedback);

        FeedbackRequest feedbackRequest = new FeedbackRequest();
        FeedbackDetails feedbackDetails = new FeedbackDetails();
        ObjectMapper mapper = new ObjectMapper();

        JsonNode jsonNode = mapper.readTree(feedback);
        String feedbackContent = jsonNode.get("feedback").asText();
        feedbackRequest.setRequestFeaturesModel(feedbackDetails);
        feedbackRequest.setFeedbackOriginal(feedbackContent);

        if (responseBody != null) {

            JsonNode jsonNodee = mapper.readTree(responseBody);
            String content = jsonNodee.get("choices").get(0).get("message").get("content").asText();
            String[] lines = content.split("\n");

            for (String line : lines) {
                String key = line.split(":")[0].trim();
                String value = line.substring(line.indexOf(":") + 1).trim();
                switch (key) {
                    case "SENTIMENT":
                        feedbackRequest.setSentiment(value);
                        break;
                    case "CODE":
                        feedbackDetails.setCode(value);
                        break;
                    case "REASON":
                        feedbackDetails.setReason(value);
                        break;
                    case "SUGGESTED_ANSWER":
                        feedbackRequest.setSuggestedAnswer(value);
                }
            }
        } else {
            feedbackRequest.setSentiment("Nenhum sentimento detectado");
            feedbackDetails.setCode("Nenhum código detectado");
            feedbackDetails.setReason("Nenhuma funcionalidade solicitada identificada.");
            feedbackRequest.setSuggestedAnswer("Nenhuma resposta sugerida identificada.");
        }
        return feedbackRequest;
    }
}
