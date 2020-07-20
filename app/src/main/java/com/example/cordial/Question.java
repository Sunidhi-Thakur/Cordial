package com.example.cordial;

class Question {
    private String question;
    private String optionA, optionB, optionC, optionD;

    Question(String question, String optionA, String optionB, String optionC, String optionD){
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
    }

    String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }


    String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }
}
