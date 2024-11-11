package com.alumind.llm.repository;

import com.alumind.llm.model.*;
import com.alumind.llm.utils.FeedbackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.*;
import org.springframework.stereotype.Repository;
import java.sql.*;

@Repository
public class FeedbackDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_FEEDBACK_SQL = "INSERT INTO feedback (sentiment, code, reason, feedbackOriginal) VALUES (?, ?, ?, ?)";

    public FeedbackResponse insertFeedback(FeedbackModel feedbackModel) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        FeedbackMapper feedbackMapper = new FeedbackMapper();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_FEEDBACK_SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, feedbackModel.getSentiment());
            ps.setString(2, feedbackModel.getRequestFeaturesModel().getCode());
            ps.setString(3, feedbackModel.getRequestFeaturesModel().getReason());
            ps.setString(4, feedbackModel.getFeedbackOriginal());
            return ps;
        }, keyHolder);
        return feedbackMapper.mapToFeedbackResponse(keyHolder, feedbackModel);
    }
}
