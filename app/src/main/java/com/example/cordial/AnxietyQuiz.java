package com.example.cordial;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AnxietyQuiz extends AppCompatActivity implements View.OnClickListener {

    private TextView question, count;
    private Button opt1, opt2, opt3, opt4;
    private List<Question> questionList;
    int quesNum;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anxiety_quiz);

        question = findViewById(R.id.questions);
        count = findViewById(R.id.count);

        opt1 = findViewById(R.id.button);
        opt2 = findViewById(R.id.button2);
        opt3 = findViewById(R.id.button3);
        opt4 = findViewById(R.id.button4);

        opt1.setOnClickListener(this);
        opt2.setOnClickListener(this);
        opt3.setOnClickListener(this);
        opt4.setOnClickListener(this);

        getQuestionList();
        score = 0;


    }

    private void getQuestionList() {
        questionList = new ArrayList<>();

        questionList.add(new Question("Do you worry a lot?", "Never", "Sometimes", "Often", "Very Often"));
        questionList.add(new Question("Does worry make you feel fatigued or irritable?", "Never", "Sometimes", "Often", "Very Often"));
        questionList.add(new Question("Does worry or anxiety interfere with your sleep or ability to concentrate?", "Never", "Sometimes", "Often", "Very Often"));
        questionList.add(new Question("Do you experience repetitive and persistent thoughts that are upsetting and unwanted?", "Never", "Sometimes", "Often", "Very Often"));
        questionList.add(new Question("Do you experience strong fear that causes panic, shortness of breath, chest pains, a pounding heart, sweating, shaking, nausea, dizziness, and/or fear of dying?", "Never", "Sometimes", "Often", "Very Often"));
        questionList.add(new Question("Do you ever avoid places or social situations for fear of this panic?", "Never", "Sometimes", "Often", "Very Often" ));
        questionList.add(new Question("Do you ever engage in repetitive behaviors to manage your worry? (i.e. checking the oven is off, locking doors, washing hands, counting, repeating words)", "Never", "Sometimes", "Often", "Very Often"));


        setQuestion();
    }

    private void setQuestion() {
        question.setText(questionList.get(0).getQuestion());
        opt1.setText(questionList.get(0).getOptionA());
        opt2.setText(questionList.get(0).getOptionB());
        opt3.setText(questionList.get(0).getOptionC());
        opt4.setText(questionList.get(0).getOptionD());

        count.setText(String.valueOf(1) + "/" + String.valueOf(questionList.size()));
        quesNum = 0;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button: {
                v.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                score = score + 1;
                break;
            }
            case R.id.button2: {
                v.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                score = score + 2;
                break;
            }

            case R.id.button3: {
                v.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                score = score + 3;
                break;
            }
            case R.id.button4: {
                v.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                score = score + 4;
                break;
            }
            default:

        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run () {
                changeQuestion();
            }

        },100);
    }

    private void changeQuestion() {
        if (quesNum < questionList.size() - 1) {

            quesNum++;

            playAnim(question, 0, 0);
            playAnim(opt1, 0, 1);
            playAnim(opt2, 0,2);
            playAnim(opt3, 0,3);
            playAnim(opt4, 0,4);

            count.setText(String.valueOf(quesNum+1) + "/" + String.valueOf(questionList.size()));
        }
        else {
            //Go to Score Activity
            Intent intent = new Intent(AnxietyQuiz.this, AnxietyScore.class);
            if(score == 7){
                intent.putExtra("RESULT","YOU HAVE NEGLIGIBLE ANXIETY ");}
            else if(score>7 && score<=14){
                intent.putExtra("RESULT","YOU HAVE LOW ANXIETY LEVEL");}
            else if(score>14 && score<=21){
                intent.putExtra("RESULT","YOU HAVE MODERATE ANXIETY LEVEL");}
            else{
                intent.putExtra("RESULT","YOU HAVE HIGH ANXIETY LEVEL");}
            intent.putExtra("SCORE", String.valueOf(score) + "/" + String.valueOf(questionList.size()*4));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
            //MainActivity.this.finish();
        }
    }

    private void playAnim(final View view, final int value, final int viewNum){
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500)
                .setStartDelay(100).setInterpolator(new DecelerateInterpolator())
                .setListener(new Animator.AnimatorListener(){

                    @Override
                    public void onAnimationStart(Animator animation) {


                    }
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if(value == 0)
                        {
                            switch(viewNum)
                            {
                                case 0:
                                    ((TextView)view).setText(questionList.get(quesNum).getQuestion());
                                    break;
                                case 1:
                                    ((TextView)view).setText(questionList.get(quesNum).getOptionA());
                                    break;
                                case 2:
                                    ((TextView)view).setText(questionList.get(quesNum).getOptionB());
                                    break;
                                case 3:
                                    ((TextView)view).setText(questionList.get(quesNum).getOptionC());
                                    break;
                                case 4:
                                    ((TextView)view).setText(questionList.get(quesNum).getOptionD());
                                    break;

                            }

                            if(viewNum != 0)
                                view.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4DB6AC")));

                            playAnim(view,1,viewNum);
                        }

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }
                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
    }
}


