package com.conexxionbusiness.quizapp;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionBank {

    // Example for index 0:
    // What is 3 + 4?
    //     10
    //     8
    //     7

    public int[] leftAdders = {3,16,45,17,6,52,16,45,68,54,12,36,25,19,61,84,52,22,29,5,13,15,62,24,7,3,5,81,64};
    public int[] rightAdders = {4,32,12,16,14,46,65,35,19,85,63,58,32,94,13,49,74,14,18,19,15,47,15,93,6,9,55,17,46};
    public int[] correctAnswers = {7,48,57,33,20,98,81,80,87,139,75,94,57,113,74,133,126,36,47,24,28,62,77,117,13,12,60,98,110};
    public int[] firstIncorrectAnswers = {8,52,63,23,18,106,79,75,88,128,79,90,59,103,68,123,122,30,43,25,26,60,79,119,12,11,50,88,112};
    public int[] secondIncorrectAnswers = {10,61,55,43,22,88,68,70,97,140,70,84,62,116,72,135,136,42,53,34,30,72,89,127,15,16,70,95,100};

    private String[] mQuestions = {
            "Question 1",
            "Question 2",
            "Question 3",
            "Question 4",
            "Question 5",
            "Question 6",
    };

    private int leftAdder;
    private int rightAdder;
    private List<String> options = new ArrayList<String>(3);
    private Random randomGenerator = new Random();
    private int correctAnswer;




    public String getQuestion() {
        String question;
        Random randomGenerator = new Random();
        //int randomNumber = randomGenerator.nextInt(mQuestions.length);
        //question = mQuestions[randomNumber];
        leftAdder = randomGenerator.nextInt(51);
        rightAdder = randomGenerator.nextInt(51);
        question = "What is " + leftAdder + " + " + rightAdder + "?";

        return question;
    }

    public List<String> getOptions() {
        int incorrectAnswer2;
        int incorrectAnswer1;

        /*
        For a more generic "in between two numbers" use:

        Random r = new Random();
        int Low = 10;
        int High = 100;
        int Result = r.nextInt(High-Low) + Low;
        */

        correctAnswer = leftAdder + rightAdder;
        incorrectAnswer1 = randomGenerator.nextInt((correctAnswer + 11) - (correctAnswer + 1)) + (correctAnswer + 1);
        incorrectAnswer2 = randomGenerator.nextInt((correctAnswer - 1) - (correctAnswer - 11)) + (correctAnswer - 11);

        options.clear();
        int counter = 0;
        do {
            options.add("4");
            counter++;
        } while (counter < 3);
        addOptionToList(correctAnswer + "");
        addOptionToList(incorrectAnswer1 + "");
        addOptionToList(incorrectAnswer2 + "");

        return options;
    }

    private void addOptionToList(String option) {
        int randomIndex;
        boolean optionAdded = false;
        do {
            randomIndex = randomGenerator.nextInt(3);
            if (options.get(randomIndex) == "4") {
                options.set(randomIndex, option + "");
                optionAdded = true;
            }
        } while (!optionAdded);
    }

    public String getCorrectAnswer() {
        return Integer.toString(correctAnswer);
    }

}
