package com.alumind.llm.repository;

import com.alumind.llm.exception.FeedbackOperationException;
import com.alumind.llm.model.*;
import com.alumind.llm.utils.FeedbackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.*;
import org.springframework.stereotype.Repository;
import java.sql.*;

@Repository
public class FeedbackRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_FEEDBACK_SQL = "INSERT INTO feedback (sentiment, code, reason, feedbackOriginal, suggestedAnswer) VALUES (?, ?, ?, ?, ?)";

    public FeedbackResponse saveFeedback(FeedbackRequest feedbackRequest) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        FeedbackMapper feedbackMapper = new FeedbackMapper();

        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(INSERT_FEEDBACK_SQL, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, feedbackRequest.getSentiment());
                ps.setString(2, feedbackRequest.getRequestFeaturesModel().getCode());
                ps.setString(3, feedbackRequest.getRequestFeaturesModel().getReason());
                ps.setString(4, feedbackRequest.getFeedbackOriginal());
                ps.setString(5, feedbackRequest.getSuggestedAnswer());
                return ps;
            }, keyHolder);
        } catch (DataAccessException ex) {
            throw new FeedbackOperationException.DatabaseOperationException("Erro ao salvar o feedback no banco de dados", ex);
        }

        return feedbackMapper.mapToFeedbackResponse(keyHolder, feedbackRequest);
    }
}
