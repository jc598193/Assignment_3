package com.example.assignment_3;

public class QuestionDatabase {

    private String[] list_questions = {"What is your answer?", "What is your answer?",
            "What is your answer?", "What is your answer?", "What is X", "What is X",
            "What is your answer?", "What is your answer?", "What is X", "What is your answer?"};
    private Integer[] list_images = new Integer[]{R.drawable.question1, R.drawable.question2,
            R.drawable.question3, R.drawable.question4, R.drawable.question5 , R.drawable.question6,
            R.drawable.question7, R.drawable.question8, R.drawable.question9, R.drawable.question10};
    private String[][] list_answers = new String[][]{{"90", "79", "109", "99"}, {"1", "11", "10", "9"},
            {"20", "18", "23", "21"}, {"30", "31", "21", "35"}, {"4", "1", "5", "0"},
            {"20", "35", "13", "30"}, {"10", "14", "6", "24"}, {"14", "10", "5", "80"},
            {"23", "28", "35", "33"}, {"34", "30", "10", "43"}};
    private String[] correct_answers = {"99", "11", "21", "31", "4", "30", "6", "5", "28", "30"};


    public String getQuestion(int a){
        String question = list_questions[a];
        return question;
    }

    public int getImage(int a){
        int image = list_images[a];
        return image;
    }

    public String getAnswer1(int a){
        String answer = list_answers[a][0];
        return answer;
    }

    public String getAnswer2(int a){
        String answer = list_answers[a][1];
        return answer;
    }

    public String getAnswer3(int a){
        String answer = list_answers[a][2];
        return answer;
    }

    public String getAnswer4(int a){
        String answer = list_answers[a][3];
        return answer;
    }

    public String getCorrect(int a){
        String correct = correct_answers[a];
        return correct;
    }
}
