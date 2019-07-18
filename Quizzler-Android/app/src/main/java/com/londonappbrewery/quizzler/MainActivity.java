package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {


    // TODO: Declare member variables here:
    Button True_but;
    Button False_but;
    TextView txt;
    int i;
    int ques;
    int score;
    TextView score_txt;
    ProgressBar mProgressBar;

    // TODO: Uncomment to create question bank
    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13,true)
    };
    // TODO: Declare constants here

    final int PROGRESS_BAR_INC=(int)Math.ceil(100/mQuestionBank.length);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState!=null)
        {
            score=savedInstanceState.getInt("score");
            i=savedInstanceState.getInt("index");

        }
        else
        {
            i=0;
            score=0;
        }
        setContentView(R.layout.activity_main);
        True_but=findViewById(R.id.True);
        False_but=findViewById(R.id.False);
        txt=findViewById(R.id.question_text);
        score_txt=findViewById(R.id.textView2);
        mProgressBar=findViewById(R.id.progressBar3);
        mProgressBar.incrementProgressBy(PROGRESS_BAR_INC*i);
        score_txt.setText("Score "+score+"/"+mQuestionBank.length);

        ques=mQuestionBank[i].getmQuestionID();
        txt.setText(ques);
        True_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Quizzer"," True Button Pressed");
                check(true);
                update();

            }
        });
        False_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Quizzer"," False Button Pressed");
                check(false);
                update();


            }
        });

    }
    private void update()
    {
        i=(i+1)%mQuestionBank.length;
        if(i==0)
        {
            Log.d("hello","over");
            AlertDialog.Builder alert=new AlertDialog.Builder(this);
            alert.setTitle("Game Over");
            alert.setCancelable(false);
            alert.setMessage("You Scored  "+score+" points!");
            alert.setPositiveButton("Close Application", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alert.show();
        }
        ques=mQuestionBank[i].getmQuestionID();
        txt.setText(ques);
        mProgressBar.incrementProgressBy(PROGRESS_BAR_INC);
        score_txt.setText("Score "+score+"/"+mQuestionBank.length);
    }
    private void check(boolean us_in) {
        boolean corr_ans = mQuestionBank[i].isAns();
        if (us_in == corr_ans) {
            Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
            score++;
        } else {
            Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_SHORT).show();
        }
    }
        @Override
        public void onSaveInstanceState(Bundle outState)
        {
          super.onSaveInstanceState(outState);
          outState.putInt("score",score);
          outState.putInt("index",i);
        }
}
