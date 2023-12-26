package com.ahmetenesbahar.fooddeliveryapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Comment implements Parcelable {
    private String userId;

    private String userName;
    private String CommentText;
    private String CommentImage;
    private int CommentRating;

    public Comment() {

    }

    public Comment(String userId, String userName, String commenText, String commentImage, int commentRating) {
        this.userId = userId;
        this.userName = userName;
        CommentText = commenText;
        CommentImage = commentImage;
        CommentRating = commentRating;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCommentText() {
        return CommentText;
    }

    public void setCommentText(String commenText) {
        CommentText = commenText;
    }

    public String getCommentImage() {
        return CommentImage;
    }

    public void setCommentImage(String commentImage) {
        CommentImage = commentImage;
    }

    public int getCommentRating() {
        return CommentRating;
    }

    public void setCommentRating(int commentRating) {
        CommentRating = commentRating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {

    }
}
