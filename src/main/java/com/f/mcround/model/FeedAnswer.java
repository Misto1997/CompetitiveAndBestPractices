package com.f.mcround.model;

import java.util.List;

public class FeedAnswer {

    String answer;
    List<CommentDetails> comments;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<CommentDetails> getComments() {
        return comments;
    }

    public void setComments(List<CommentDetails> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "FeedAnswer{" +
                "answer='" + answer + '\'' +
                ", comments=" + comments +
                '}';
    }
}
