package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button ansA,ansB,ansC,ansD;
    Button SUBMIT;

    int score=0;
    int totalQuestion= QuestionAnswers.question.length;
    int currentQuestionIndex=0;
    String selectedAnswer="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestionsTextView=findViewById(R.id.total_questions);
        questionTextView=findViewById(R.id.Question);
        ansA=findViewById(R.id.ans_A);
        ansB=findViewById(R.id.ans_B);
        ansC=findViewById(R.id.ans_C);
        ansD=findViewById(R.id.ans_D);
        SUBMIT=findViewById(R.id.submit_button);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        SUBMIT.setOnClickListener(this);

        totalQuestionsTextView.setText("Total Questions : "+totalQuestion);

        loadNewQuestion();




    }

    @Override
    public void onClick(View view) {
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);


        Button clickedButton=(Button) view;
        if (clickedButton.getId()==R.id.submit_button){
            if(selectedAnswer.equals(QuestionAnswers.correctAnswers[currentQuestionIndex])){
                score++;
            }
            currentQuestionIndex++;
            loadNewQuestion();


        }else{
            //choices button clicked
            selectedAnswer=clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);

        }


    }
    void loadNewQuestion(){
        if(currentQuestionIndex==totalQuestion){
            finishQuiz();
            return;

        }


        questionTextView.setText(QuestionAnswers.question[currentQuestionIndex]);
        ansA.setText(QuestionAnswers.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswers.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionAnswers.choices[currentQuestionIndex][2]);
        ansD.setText(QuestionAnswers.choices[currentQuestionIndex][3]);
    }
    void finishQuiz(){
        String passStatus="";
        if(score>totalQuestion*0.75){
            passStatus="Passed";
        }else {
            passStatus="Failed";
        }
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is "+ score + "out of "+totalQuestion)
                .setPositiveButton("Restart",(dialogInterface, i) -> restartQuiz())
                .setCancelable(false)
                .show();


    }
    void restartQuiz(){
        score=0;

        currentQuestionIndex=0;
        loadNewQuestion();
    }
}