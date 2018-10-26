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

    //define the object which would be used in the activity and mehod as well
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
    private Button confirm;


    private YouTubePlayerView playerView;
    private YouTubePlayer.OnInitializedListener onInitializedListener;

    private int Score;
    private boolean answered;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcq);

        //defined all the variable by specific id
        playerView = findViewById(R.id.video);
        TvScore = findViewById(R.id.textview_sorce);
        order = findViewById(R.id.textview_order);
        tvquestion = findViewById(R.id.textview_question);
        option = findViewById(R.id.radio_question);
        option1 = findViewById(R.id.radio_question1);
        option2 = findViewById(R.id.radio_question2);
        option3 = findViewById(R.id.radio_question3);
        option4 = findViewById(R.id.radio_question4);
        confirm = findViewById(R.id.button_confirm);
        next = findViewById(R.id.button_next);


        // initialize the Youtube video to the playerview
        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            //using youtu video id to load the specific video
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
        //assign int Total to the total number of question
        Total = questionList.size();
        //make the order of question become static
        Collections.shuffle(questionList);

        //student could not click the next button before make a choice of answer
        next.setVisibility(View.INVISIBLE);
        //run showNextQuestion method to load the question at the beginning
        showNextQuestion();

        //setting the action once student click on next button
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //show the next question
                showNextQuestion();
                //student only could see the confirm button once he move to the next question
                confirm.setVisibility(View.VISIBLE);
                //student could not click the next button before make a choice of answer
                next.setVisibility(View.INVISIBLE);
            }


        });



        //setting the action for clicking confirm button
        //reference from https://codinginflow.com/tutorials/android/quiz-app-with-sqlite/part-5-quiz-activity
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //use if else to identify whether student choose a option
                if(option1.isChecked()||option2.isChecked()||option3.isChecked()||option4.isChecked()){
                    //define the radio button to see which option student select
                    RadioButton radioButton = findViewById(option.getCheckedRadioButtonId());
                    //assign the option index to an integer for the result of question
                    int answer = option.indexOfChild(radioButton)+1;
                    //the option index equal to the answerNr mean student select the right answer
                    //the score would increase once the student make a correct decision
                    //the new score would show in the textview
                    if(answer == currentQuestion.getAnswerNr()) {
                        Score++;
                        TvScore.setText("Score: " + Score);
                    }
                    //once student select an option and press confirm button, we would to provide a
                    //feedback for it.
                    showSolution();
                    //once student click confirm button it would disappear
                    confirm.setVisibility(view.INVISIBLE);
                    //student is able to move to the next qusetion at that moment
                    next.setVisibility(View.VISIBLE);
            }   //try to display a tips for student "You have to make a choice"
                else {
                    Toast.makeText(QuizActivity.this,"You have to choose one answer",Toast.LENGTH_LONG);}
        }});

    }


    //the method to show the question to textview
    //reference from https://codinginflow.com/tutorials/android/quiz-app-with-sqlite/part-5-quiz-activity
    private void showNextQuestion(){
        //set the option color to black
        option1.setTextColor(Color.BLACK);
        option2.setTextColor(Color.BLACK);
        option3.setTextColor(Color.BLACK);
        option4.setTextColor(Color.BLACK);
        option.clearCheck();

        //use if else to identify the which question should be present next
        if (questionCounter < Total) {
            //currentquestion would be an object assign to the qestion object stored in questionList
            currentQuestion = questionList.get(questionCounter);

            //set the text for question and option from currentquestion object
            tvquestion.setText(currentQuestion.getQuestion());
            option1.setText(currentQuestion.getOption1());
            option2.setText(currentQuestion.getOption2());
            option3.setText(currentQuestion.getOption3());
            option4.setText(currentQuestion.getOption4());

            //when the question counter increase the current question object would chage as well
            questionCounter++;
            //tell student how many question left
            order.setText("Question: " + questionCounter + "/" + Total);
        }
        //once the all the question was appeared, we want to move to new intent to show the result and
        //make some comment for student
        else {
            finishQuiz();
        }
    }
    //method to show the result to specific question
    private void showSolution() {
        //set all the option color to red which mean incorrect
        option1.setTextColor(Color.RED);
        option2.setTextColor(Color.RED);
        option3.setTextColor(Color.RED);
        option4.setTextColor(Color.RED);

        //use the answerNr to find the right answer and change the right option text to green color which
        // the correct answer
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


    //when student dinish the quiz it would move to a result page by using intent, also we pass the
    //score to the result page and display differ comment for different score
    private void finishQuiz(){
        //create a new intent
        Intent intent1 = new Intent(QuizActivity.this, Result.class);
        //pass the score to result class
        intent1.putExtra("Score",Score);
        //move to the new intent
        startActivity(intent1);
    }
}
