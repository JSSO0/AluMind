package com.alumind.llm.nlp;

import com.alumind.llm.model.FeedbackModel;
import com.alumind.llm.model.RequestFeaturesModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Service
public class FeedbackAnalyze {
    @Autowired
    private OpenAiRequest openAiRequest;

    public FeedbackModel analyze(String feedback) throws JsonProcessingException {
        String prompt = "Classifique o sentimento do seguinte feedback como 'POSITIVO', 'NEGATIVO' ou 'INDIFERENTE' e Identifique a solicitação de funcionalidade no feedback a seguir e classifique o código (como 'EDITAR_PERFIL', 'ALTERAR_SENHA' ou outros) e a razão da solicitação.Feedback: \"" + feedback + "\".";
        String responseBody = openAiRequest.sendRequest(prompt, feedback);
        FeedbackModel feedbackModel = new FeedbackModel();
        RequestFeaturesModel requestFeaturesModel = new RequestFeaturesModel();
        feedbackModel.setRequestFeaturesModel(requestFeaturesModel);

        if (responseBody != null) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(responseBody);
            String content = jsonNode.get("choices").get(0).get("message").get("content").asText();
            String[] lines = content.split("\n");
            Map<String, Consumer<String>> map = new HashMap<>();
            map.put("Sentimento", feedbackModel::setSentiment);
            map.put("Classificação do código", requestFeaturesModel::setCode);
            map.put("Código", requestFeaturesModel::setCode);
            map.put("Razão da solicitação", requestFeaturesModel::setReason);

            for (String line : lines) {
                String key = line.split(":")[0].trim();
                String value = line.substring(line.indexOf(":") + 1).trim();

                Consumer<String> consumer = map.get(key);
                if (consumer != null) {
                    consumer.accept(value);
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
