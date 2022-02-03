package com.f.mcround.model;

public class CommentDetails {

    String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "CommentDetails{" +
                "comment='" + comment + '\'' +
                '}';
    }
}
