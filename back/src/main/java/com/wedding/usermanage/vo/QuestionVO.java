package com.wedding.usermanage.vo;

import java.io.Serializable;

public class QuestionVO implements Serializable {
    int questionid;
    String answer;

    public QuestionVO() {
    }

    public QuestionVO(int questionid, String answer) {
        this.questionid = questionid;
        this.answer = answer;
    }

    public int getQuestionid() {
        return questionid;
    }

    public void setQuestionid(int questionid) {
        this.questionid = questionid;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
