package com.conexxionbusiness.quizapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // Variables
    private TextView mQuestionView;
    private TextView mOption1View;
    private TextView mOption2View;
    private TextView mOption3View;
    private Button mSubmitAnswer;
    private QuestionBank mQuestionBank = new QuestionBank();
    private ColorWheel mColorWheel = new ColorWheel();
    private RelativeLayout mRelativeLayoutActivityMain;
    private List<String> mOptions = new ArrayList<String>();
    private String answer = "";
    private boolean checked;
    private int radioButtonId;
    private RadioButton radioButtonChecked;
    private String question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assign values to variables
        mQuestionView = (TextView) findViewById(R.id.question);
        mOption1View = (TextView) findViewById(R.id.option1);
        mOption2View = (TextView) findViewById(R.id.option2);
        mOption3View = (TextView) findViewById(R.id.option3);
        mSubmitAnswer = (Button) findViewById(R.id.submitAnswer);
        mRelativeLayoutActivityMain = (RelativeLayout) findViewById(R.id.relativeLayoutActivityMain);
        // First question....
        question = mQuestionBank.getQuestion();
        mOptions = mQuestionBank.getOptions();mQuestionView.setText(question);
        mOption1View.setText(mOptions.get(0));
        mOption2View.setText(mOptions.get(1));
        mOption3View.setText(mOptions.get(2));

        View.OnClickListener submitListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer.equals("")) {
                    // Send error message in a toast here
                    return;
                }
                checkAnswer(answer);
                question = mQuestionBank.getQuestion();
                mOptions = mQuestionBank.getOptions();
                int color = mColorWheel.getColor();

                mQuestionView.setText(question);
                mOption1View.setText(mOptions.get(0));
                mOption2View.setText(mOptions.get(1));
                mOption3View.setText(mOptions.get(2));
                mRelativeLayoutActivityMain.setBackgroundColor(color);
                mSubmitAnswer.setTextColor(color);
                answer = "";
                radioButtonChecked = (RadioButton) findViewById(radioButtonId);
                radioButtonChecked.setChecked(false);
            }
        };

        mSubmitAnswer.setOnClickListener(submitListener);
    }


    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.option1:
                if (checked)
                    answer = (String) mOption1View.getText();
                    radioButtonId = view.getId();
                    break;
            case R.id.option2:
                if (checked)
                    answer = (String) mOption2View.getText();
                    radioButtonId = view.getId();
                    break;
            case R.id.option3:
                if (checked)
                    answer = (String) mOption3View.getText();
                    radioButtonId = view.getId();
                    break;
        }
    }


    private void checkAnswer(String answer) {
        String result;
        if (answer.equals(mQuestionBank.getCorrectAnswer())) {
            result = "Correct Answer!";
        } else {
            result = "Incorrect Answer.";
        }

        // Send success/failure message in a toast here
        sendToast(result);
    }


    private void sendToast(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }






}
