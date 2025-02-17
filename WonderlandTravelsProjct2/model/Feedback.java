package com.tap.model;

import java.sql.Timestamp;

public class Feedback {
    private int feedbackId;
    private int userId;
    private int wonderId;
    private String comment;
    private int rating;
    private Timestamp createdAt;

    // Constructors
    public Feedback() {
    }

    public Feedback(int userId, int wonderId, String comment, int rating) {
        this.userId = userId;
        this.wonderId = wonderId;
        this.comment = comment;
        this.rating = rating;
    }

    public Feedback(int feedbackId, int userId, int wonderId, String comment, int rating, Timestamp createdAt) {
        this.feedbackId = feedbackId;
        this.userId = userId;
        this.wonderId = wonderId;
        this.comment = comment;
        this.rating = rating;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getWonderId() {
        return wonderId;
    }

    public void setWonderId(int wonderId) {
        this.wonderId = wonderId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return feedbackId + " " + userId + " " + wonderId + " " + comment + " " + rating + " " + createdAt;
    }
}
