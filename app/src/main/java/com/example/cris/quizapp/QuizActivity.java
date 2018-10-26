package com.example.cris.quizapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.Collections;
import java.util.List;

public class QuizActivity extends YouTubeBaseActivity {

    private int Total;
    private RadioButton option1;
    private RadioButton option2;
    private RadioButton option3;
    private RadioButton option4;
    private RadioGroup option;
    private int questionCounter;
    private List<Question> questionList;
    private TextView order;
    private TextView tvquestion;
    private Question currentQuestion;
    private TextView TvScore;
    private SQLiteDatabase db;
    private Button next;
    private Button comfirm;


    private YouTubePlayerView playerView;
    private YouTubePlayer.OnInitializedListener onInitializedListener;

    private int Score;
    private boolean answered;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcq);

        //defined all the variable by id
        playerView = findViewById(R.id.video);
        TvScore = findViewById(R.id.textview_sorce);
        order = findViewById(R.id.textview_order);
        tvquestion = findViewById(R.id.textview_question);
        option = findViewById(R.id.radio_question);
        option1 = findViewById(R.id.radio_question1);
        option2 = findViewById(R.id.radio_question2);
        option3 = findViewById(R.id.radio_question3);
        option4 = findViewById(R.id.radio_question4);
        comfirm = findViewById(R.id.button_confirm);
        next = findViewById(R.id.button_next);


        // initialize the Youtube video to the playerview
        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("AXoIDTFeuvY");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        //set Youtube api and run the initialized method
            playerView.initialize("AIzaSyB3FmRgFwg5_fWR16K0wGRgqMBHrQybvAY",onInitializedListener);

        //integrate dbHelper from database to activity
        QuizDbHelper dbHelper = new QuizDbHelper(this);


        //integrate questionlist from database class to activity
        questionList = dbHelper.getAllQuestions();
        Total = questionList.size();
        Collections.shuffle(questionList);

        next.setVisibility(View.INVISIBLE);
        showNextQuestion();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showNextQuestion();
                comfirm.setVisibility(View.VISIBLE);
                next.setVisibility(View.INVISIBLE);
            }


        });




        comfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(option1.isChecked()||option2.isChecked()||option3.isChecked()||option4.isChecked()){
                    RadioButton radioButton = findViewById(option.getCheckedRadioButtonId());
                    int answer = option.indexOfChild(radioButton)+1;
                    if(answer == currentQuestion.getAnswerNr()) {
                        Score++;
                        TvScore.setText("Score: " + Score);
                    }
                    showSolution();
                    comfirm.setVisibility(view.INVISIBLE);
                    next.setVisibility(View.VISIBLE);
            }
                else {
                    Toast.makeText(QuizActivity.this,"You have to choose one answer",Toast.LENGTH_LONG);}
        }});

    }



    private void showNextQuestion(){
        option1.setTextColor(Color.BLACK);
        option2.setTextColor(Color.BLACK);
        option3.setTextColor(Color.BLACK);
        option4.setTextColor(Color.BLACK);
        option.clearCheck();

        if (questionCounter < Total) {
            currentQuestion = questionList.get(questionCounter);

            tvquestion.setText(currentQuestion.getQuestion());
            option1.setText(currentQuestion.getOption1());
            option2.setText(currentQuestion.getOption2());
            option3.setText(currentQuestion.getOption3());
            option4.setText(currentQuestion.getOption4());

            questionCounter++;
            order.setText("Question: " + questionCounter + "/" + Total);


        }else {
            finishQuiz();
        }
    }

    private void showSolution() {
        option1.setTextColor(Color.RED);
        option2.setTextColor(Color.RED);
        option3.setTextColor(Color.RED);
        option4.setTextColor(Color.RED);

        switch (currentQuestion.getAnswerNr()) {
            case 1:
                option1.setTextColor(Color.GREEN);
                break;
            case 2:
                option2.setTextColor(Color.GREEN);
                break;
            case 3:
                option3.setTextColor(Color.GREEN);
                break;
            case 4:
                option4.setTextColor(Color.GREEN);
                break;
        }


    }

    public void checkAnswer(){


    }

    private void finishQuiz(){
        Intent intent = new Intent(QuizActivity.this, Result.class);
        startActivity(intent);
    }
}
