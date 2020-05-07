package com.wedding.usermanage.vo;

import java.io.Serializable;

public class IntroductionVO implements Serializable {
    String[] tagList;
    QuestionVO[] questionList;
    String introduction;

    public IntroductionVO() {
    }

    public IntroductionVO(String[] tagList, QuestionVO[] questionList, String introduction) {
        this.tagList = tagList;
        this.questionList = questionList;
        this.introduction = introduction;
    }

    public String[] getTagList() {
        return tagList;
    }

    public void setTagList(String[] tagList) {
        this.tagList = tagList;
    }

    public QuestionVO[] getQuestionList() {
        return questionList;
    }

    public void setQuestionList(QuestionVO[] questionList) {
        this.questionList = questionList;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
