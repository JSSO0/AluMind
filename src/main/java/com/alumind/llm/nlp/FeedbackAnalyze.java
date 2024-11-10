package com.alumind.llm.nlp;

import com.alumind.llm.model.FeedbackModel;
import com.alumind.llm.model.RequestFeaturesModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackAnalyze {
    @Autowired
    private OpenAiRequest openAiRequest;

    public FeedbackModel analyze(String feedback) {
        String prompt = "Classifique o sentimento do seguinte feedback como 'POSITIVO', 'NEGATIVO' ou 'INDIFERENTE' e Identifique a solicitação de funcionalidade no feedback a seguir e classifique o código (como 'EDITAR_PERFIL', 'ALTERAR_SENHA' ou outros) e a razão da solicitação.Feedback: \"" + feedback + "\".";
        String responseBody = openAiRequest.sendRequest(prompt, feedback);
        FeedbackModel feedbackModel = new FeedbackModel();
        RequestFeaturesModel requestFeaturesModel = new RequestFeaturesModel();
        feedbackModel.setRequestFeaturesModel(requestFeaturesModel);
        if (responseBody != null) {
            String[] lines = responseBody.split("\n");
            for (String line : lines) {
                if (line.startsWith("SENTIMENTO:")) {
                    feedbackModel.setSentiment(line.substring(12).trim());
                } else if (line.startsWith("CODE:")) {
                    requestFeaturesModel.setCode(line.substring(5).trim());
                } else if (line.startsWith("REASON:")) {
                    requestFeaturesModel.setReason(line.substring(7).trim());
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
