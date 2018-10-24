package com.example.cris.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // define the button
        Button btnStart = findViewById(R.id.button_start);

        // create onClickListener for button
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startQuiz();
            }
        });
    }

    // method move to video intent and start the quiz
    public void startQuiz(){
        Intent intent = new Intent(MainActivity.this, QuizActivity.class);
        startActivity(intent);
    }


}

