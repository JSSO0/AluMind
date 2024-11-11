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

    private static final String INSERT_FEEDBACK_SQL = "INSERT INTO feedback (sentiment, code, reason, feedbackOriginal) VALUES (?, ?, ?, ?)";

    public FeedbackModel insertFeedback(FeedbackModel feedbackModel) {
        System.out.print(feedbackModel.getFeedbackOriginal());
        System.out.print(feedbackModel.getSentiment());
        System.out.print(feedbackModel.getRequestFeaturesModel().getCode());
        System.out.print(feedbackModel.getRequestFeaturesModel().getReason());
        System.out.print(feedbackModel.getFeedbackOriginal());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_FEEDBACK_SQL, new String[]{"id"});
            ps.setString(1, feedbackModel.getSentiment());
            ps.setString(2, feedbackModel.getRequestFeaturesModel().getCode());
            ps.setString(3, feedbackModel.getRequestFeaturesModel().getReason());
            ps.setString(4, feedbackModel.getFeedbackOriginal());
            return ps;
        }, keyHolder);
        int id = keyHolder.getKey().intValue();
        feedbackModel.setId(id);
        return feedbackModel;
    }
}
