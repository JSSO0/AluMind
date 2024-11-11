package com.alumind.llm.nlp;

import com.alumind.llm.model.FeedbackModel;
import com.alumind.llm.model.RequestFeaturesModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;;

@Service
public class FeedbackAnalyze {
    @Autowired
    private OpenAiRequest openAiRequest;

    public FeedbackModel analyze(String feedback) throws JsonProcessingException {
        String prompt = "Classifique o sentimento do seguinte feedback como 'POSITIVO', 'NEGATIVO' ou 'INDIFERENTE' e Identifique a solicitação de funcionalidade no feedback a seguir e classifique o código (como 'EDITAR_PERFIL', 'ALTERAR_SENHA' ou outros) e a razão da solicitação. e Me devolva na estrutura SENTIMENTO: CODE: REASON: lembrando da quebra de linha ; o Feedback: \"" + feedback + "\".";
        String responseBody = openAiRequest.sendRequest(prompt, feedback);

        FeedbackModel feedbackModel = new FeedbackModel();
        RequestFeaturesModel requestFeaturesModel = new RequestFeaturesModel();
        ObjectMapper mapper = new ObjectMapper();

        JsonNode jsonNode = mapper.readTree(feedback);
        String feedbackContent = jsonNode.get("feedback").asText();
        feedbackModel.setRequestFeaturesModel(requestFeaturesModel);
        feedbackModel.setFeedbackOriginal(feedbackContent);

        if (responseBody != null) {

            JsonNode jsonNodee = mapper.readTree(responseBody);
            String content = jsonNodee.get("choices").get(0).get("message").get("content").asText();
            String[] lines = content.split("\n");

            for (String line : lines) {
                String key = line.split(":")[0].trim();
                String value = line.substring(line.indexOf(":") + 1).trim();
                switch (key) {
                    case "SENTIMENTO":
                        feedbackModel.setSentiment(value);
                        break;
                    case "CODE":
                        requestFeaturesModel.setCode(value);
                        break;
                    case "REASON":
                        requestFeaturesModel.setReason(value);
                        break;
                }
            }
        } else {
            feedbackModel.setSentiment("Nenhum sentimento detectado");
            requestFeaturesModel.setCode("Nenhum código detectado");
            requestFeaturesModel.setReason("Nenhuma funcionalidade solicitada identificada.");
        }
        return feedbackModel;
    }
}
