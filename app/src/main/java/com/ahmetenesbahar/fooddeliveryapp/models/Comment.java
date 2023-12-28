package com.ahmetenesbahar.fooddeliveryapp.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Comment implements Serializable {
    private String userId;

    private String userName;
    private String commentText;
    private String commentImage;
    private float commentRating;

    public Comment() {

    }

    public Comment(String userId, String userName, String commentText, String commentImage, float commentRating) {
        this.userId = userId;
        this.userName = userName;
        this.commentText = commentText;
        this.commentImage = commentImage;
        this.commentRating = commentRating;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getCommentImage() {
        return commentImage;
    }

    public void setCommentImage(String commentImage) {
        this.commentImage = commentImage;
    }

    public float getCommentRating() {
        return commentRating;
    }

    public void setCommentRating(float commentRating) {
        this.commentRating = commentRating;
    }

}