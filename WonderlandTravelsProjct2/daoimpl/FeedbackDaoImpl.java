package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.FeedbackDao;
import com.tap.model.Feedback;

public class FeedbackDaoImpl implements FeedbackDao {

    private static Connection con;
    private static String insertFeedbackQuery = "INSERT INTO feedback (user_id, wonder_id, comment, rating) VALUES (?, ?, ?, ?)";
    private static String getAllFeedbacksQuery = "SELECT * FROM feedback";

    private PreparedStatement pstmt;
    private static Statement stmt;
    private static ResultSet resultSet;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/a8wonderproject", "root", "Suresh@2724");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void submitFeedback(Feedback feedback) {
        try {
            pstmt = con.prepareStatement(insertFeedbackQuery);
            pstmt.setInt(1, feedback.getUserId());
            pstmt.setInt(2, feedback.getWonderId());
            pstmt.setString(3, feedback.getComment());
            pstmt.setInt(4, feedback.getRating());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        List<Feedback> feedbackList = new ArrayList<>();
        try {
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(getAllFeedbacksQuery);
            while (resultSet.next()) {
                feedbackList.add(new Feedback(
                        resultSet.getInt("feedback_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("wonder_id"),
                        resultSet.getString("comment"),
                        resultSet.getInt("rating"),
                        resultSet.getTimestamp("created_at")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return feedbackList;
    }
}
