package com.example.cris.quizapp;

public class Question {
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private int answerN;

    public Question(){}

    // create constructor for question
    public Question(String question, String option1, String option2, String option3,String option4, int answerN) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answerN = answerN;
    }

    // create getter and setter
    public String getQuestion() {
        return question;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }

    public int getAnswerN() {
        return answerN;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public void setAnswerN(int answerN) {
        this.answerN = answerN;
    }
}
