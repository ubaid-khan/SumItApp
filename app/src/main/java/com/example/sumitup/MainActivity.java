package com.example.sumitup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton, button0, button1, button2, button3, playAgainButton;
    TextView exprTextView, resultTextView, scoreTextView, timerTextView;
    int locationOfCorrectAnswer, score = 0, noOfQuestions = 0;
    ConstraintLayout mainConstraintLayout;
    ArrayList<Integer> answersArrayList = new ArrayList<Integer>();

    public void generateQuestion() {
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        int incorrectAnswer;

        exprTextView.setText(a + " + " + b);
        locationOfCorrectAnswer = rand.nextInt(4); //0 1 2 3
        answersArrayList.clear();       //to clear previous contents to answer arraylist
        for (int i = 0; i < 4; i++)              //adding correct answer
        {
            if (i == locationOfCorrectAnswer) {
                answersArrayList.add(a + b);
            } else {
                incorrectAnswer = rand.nextInt(21);
                while (incorrectAnswer == a + b) {
                    incorrectAnswer = rand.nextInt(41);
                }
                answersArrayList.add(incorrectAnswer);
            }
        }
        button0.setText(Integer.toString(answersArrayList.get(0)));
        button1.setText(Integer.toString(answersArrayList.get(1)));
        button2.setText(Integer.toString(answersArrayList.get(2)));
        button3.setText(Integer.toString(answersArrayList.get(3)));

        button0.setClickable(true);
        button1.setClickable(true);
        button2.setClickable(true);
        button3.setClickable(true);
    }
    public void chooseAnswer(View view){
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {
            score++;
            resultTextView.setText("Correct");
            resultTextView.setVisibility(View.VISIBLE);
            generateQuestion();
        }
        else{
            score--;
            resultTextView.setText("Incorrect");
            resultTextView.setVisibility(View.VISIBLE);
            generateQuestion();
        }
        noOfQuestions++;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(noOfQuestions));
        generateQuestion(); //generate a new question
        }

        public void playAgain(View view){
            score = 0;
            noOfQuestions = 0;
            timerTextView.setText("30 s");
            scoreTextView.setText("0/0");
            resultTextView.setText("");
            playAgainButton.setVisibility(View.INVISIBLE);
            generateQuestion();
            startTimer();
        }
        public void startTimer(){
            new CountDownTimer(30200, 1000) //30 seconds timer
            {
                @Override
                public void onTick(long millisUntilFinished) {
                    timerTextView.setText(String.valueOf(millisUntilFinished / 1000 + " s"));
                }

                @Override
                public void onFinish() {
                    timerTextView.setText("0 s");
                    resultTextView.setText("Your score : " + Integer.toString(score) + " / " + Integer.toString(noOfQuestions));
                    setButtons(false);
                    playAgainButton.setVisibility(View.VISIBLE);
                } //countdown for 30sec and tick at every 1s
            }.start();
        }

    public void start(View view) {
        mainConstraintLayout.setVisibility(ConstraintLayout.VISIBLE);
        startButton.setVisibility(View.INVISIBLE);
    }

    public void setButtons(boolean enabled) {
        button0.setClickable(enabled);
        button1.setClickable(enabled);
        button2.setClickable(enabled);
        button3.setClickable(enabled);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        exprTextView = findViewById(R.id.expressiontextView);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextVieiw);
        mainConstraintLayout = findViewById(R.id.mainConstraintLayout);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        playAgainButton = findViewById(R.id.playAgainbutton);

        generateQuestion();
        startTimer();
        playAgain(findViewById(R.id.playAgainbutton));

    }
}
