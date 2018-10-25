package com.example.cris.quizapp;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeInitializationResult;

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
    private TextView question;
    private Question currentQuestion;
    private TextView TvScore;


    private YouTubePlayerView playerView;
    private YouTubePlayer.OnInitializedListener onInitializedListener;

    private int Score;
    private boolean answered;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_mcq);

        //defined all the variable by id
        playerView = findViewById(R.id.video);
        TvScore = findViewById(R.id.textview_sorce);
        order = findViewById(R.id.textview_order);
        question = findViewById(R.id.textview_question);
        option = findViewById(R.id.radio_question);
        option1 = findViewById(R.id.radio_question1);
        option2 = findViewById(R.id.radio_question2);
        option3 = findViewById(R.id.radio_question3);
        option4 = findViewById(R.id.radio_question4);
        Button comfirm = findViewById(R.id.button_confirm);
        Button next = findViewById(R.id.button_next);
        Button preview = findViewById(R.id.button_preview);

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





        showNextQuestion();

        /*
        comfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(option1.isChecked()||option2.isChecked()||option3.isChecked()||option4.isChecked()){
                    RadioButton radioButton = findViewById(option.getCheckedRadioButtonId());
                    int answer = option.indexOfChild(radioButton)+1;
                    if(answer ==currentQuestion.getAnswerN()) {
                        Score++;
                        score.setText("Score: " + Score);
                    }else {
                        Toast.makeText(QuizActivity.this,"You have to choose one answer",Toast.LENGTH_LONG);
                    }
            }
        }});


*/




    }

    private void showNextQuestion(){
        option1.setTextColor(Color.BLACK);
        option2.setTextColor(Color.BLACK);
        option3.setTextColor(Color.BLACK);
        option4.setTextColor(Color.BLACK);
        option.clearCheck();

        if (questionCounter < Total) {
            currentQuestion = questionList.get(questionCounter);

            question.setText(currentQuestion.getQuestion());
            option1.setText(currentQuestion.getOption1());
            option2.setText(currentQuestion.getOption2());
            option3.setText(currentQuestion.getOption3());
            option4.setText(currentQuestion.getOption4());


            questionCounter++;
            order.setText("Question: " + questionCounter + "/" + Total);
            answered = false;

        }
    }

    public void checkAnswer(){


    }

    private void finishQuiz(){
        finish();
    }
}
