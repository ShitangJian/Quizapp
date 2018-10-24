package com.example.cris.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private int Counter;
    private int Total;
    private Question currentQuestion;

    private int score;
    private boolean answered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_mcq);

        TextView score = findViewById(R.id.textview_sorce);
        TextView order = findViewById(R.id.textview_order);
        TextView question = findViewById(R.id.textview_question);
        RadioGroup option = findViewById(R.id.radio_question);
        final RadioButton option1 = findViewById(R.id.radio_question1);
        final RadioButton option2 = findViewById(R.id.radio_question2);
        final RadioButton option3 = findViewById(R.id.radio_question3);
        final RadioButton option4 = findViewById(R.id.radio_question4);
        Button comfirm = findViewById(R.id.button_confirm);

        QuizDbHelper dbHelper = new QuizDbHelper(this);
        List<Question> questionList = dbHelper.getAllQuestions();
        Total = questionList.size();

        //show the question in order
        Collections.shuffle(questionList);

        comfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(option1.isChecked()||option2.isChecked()||option3.isChecked()||option4.isChecked()){
                    checkAnswer();
                }else {
                    Toast.makeText(QuizActivity.this,"You have to choose one answer",Toast.LENGTH_LONG);
                }
            }
        });

    }

    public void checkAnswer(){


    }
}
