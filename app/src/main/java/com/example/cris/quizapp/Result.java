package com.example.cris.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    private int i;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //assign int i to the score from quizactivity class
        i = getIntent().getExtras().getInt("Score");

        //define the textview with id
        TextView tvResult = findViewById(R.id.textView_result);
        Button btnback = findViewById(R.id.button_back);

        //button to home page
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(Result.this,MainActivity.class);
                startActivity(back);
            }
        });

        //use if else to display different feedback for student for different score level
        if (i >=8){
            tvResult.setText("Wow, you're the best! You live and breath Database 101!");

        }else if(i >=5){
            tvResult.setText("You need to review the video again and try the quiz again");
        }
        else {
            tvResult.setText("Huhuhu. Try again, so you can become the best in Database ");
        }

    }

}
