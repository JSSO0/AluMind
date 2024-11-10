package com.alumind.llm.repository;

import com.alumind.llm.model.FeedbackModel;
import com.alumind.llm.model.RequestFeaturesModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
public class FeedbackDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private FeedbackModel feedbackModel;

    @Autowired
    private RequestFeaturesModel requestFeaturesModel;

    private static final String INSERT_FEEDBACK_SQL = "INSERT INTO feedback (sentiment, code, reason, feedbackOriginal) VALUES (?, ?, ?, ?)";

    public FeedbackModel insertFeedback( FeedbackModel feedbackModel) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_FEEDBACK_SQL, new String[]{"id"});
            ps.setString(1, feedbackModel.getSentiment());
            ps.setString(2, requestFeaturesModel.getCode());
            ps.setString(3, requestFeaturesModel.getReason());
            ps.setString(4, feedbackModel.getFeedbackOriginal());
            return ps;
        }, keyHolder);
        int id = keyHolder.getKey().intValue();
        feedbackModel.setId(id);
        return feedbackModel;
    }
}
