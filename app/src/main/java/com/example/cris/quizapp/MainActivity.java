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
        Button btnTopic1 = findViewById(R.id.button_topic1);
        Button btnTopic2 = findViewById(R.id.button_topic2);
        Button btnTopic3 = findViewById(R.id.button_topic3);

        // create onClickListener for topic1 button
        btnTopic1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startQuiz();
            }
        });
        // create OnClickListener for topic2 button
        btnTopic2.setOnClickListener(new View.OnClickListener() {
            @Override
            //move to topic2 activity and quiz intent
            public void onClick(View view) {
                Intent t2 = new Intent(MainActivity.this,Topic2.class);
                startActivity(t2);

            }
        });
        // create OnClickListener for topic3 button
        btnTopic3.setOnClickListener(new View.OnClickListener() {
            //move to topic 3 and start the quiz
            @Override
            public void onClick(View view) {
                Intent t3 = new Intent(MainActivity.this,Topic3.class);
                startActivity(t3);
            }
        });
    }

    // method move to quiz intent and start the topic1
    public void startQuiz(){
        Intent intent = new Intent(MainActivity.this, QuizActivity.class);
        startActivity(intent);
    }


}

