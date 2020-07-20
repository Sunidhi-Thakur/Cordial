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

public class DepressionQuiz extends AppCompatActivity implements View.OnClickListener {


    private TextView question, count;
    private Button opt1, opt2, opt3, opt4;
    private List<Question> questionList;
    int quesNum;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depression_quiz);

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

        questionList.add(new Question("Falling Asleep", "I never take longer than 30 minutes.", "I take at least 30 minutes to fall asleep, less than half the time.", "I take at least 30 minutes to fall asleep, more than half the time.",
                "I take more than 60 minutes to fall asleep, more than half the time."));
        questionList.add(new Question("Feeling Sad", "I do not feel sad.", "I feel sad less than half the time.", "I feel sad more than half the time.", "I feel sad nearly all the time."));
        questionList.add(new Question("Appetite", "There is no change in my usual appetite.", "I am eating healthy", "I've lost my appetite.", "I'm eating too much."));
        questionList.add(new Question("Concentration/Decision Making", "There is no change in my usual capacity to concentrate or make decisions.", "I occasionally feel indecisive or find that my attention wanders.",
                "Most of the time I struggle to focus my attention or to make decisions.", "I cannot concentrate well enough to read or cannot even make minor decisions."));
        questionList.add(new Question("View on Myself", "I see myself as equally worthwhile and deserving as other people.", "I am more self-blaming than usual.", "I largely believe that I cause problems for others.",
                "I think almost constantly about major and minor defects in my life"));
        questionList.add(new Question("Energy Level", "There is no change in my usual level of energy.", "I get tired more easily than usual.", "I have to make a big effort to start or finish my usual daily activities (for example, shopping, homework, cooking, or going to work).",
                "I really cannot carry out most of my usual daily activities because I just don’t have the energy."));
        questionList.add(new Question("Feeling Slowed Down", "I think, speak, and move at my usual rate of speed.", "I find that my thinking is slowed down or my voice sounds dull or flat.", "It takes me several seconds to respond to most questions and I am sure my thinking is slowed.",
                "I am often unable to respond to questions without extreme effort."));


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
            Intent intent = new Intent(DepressionQuiz.this, DepressionScore.class);
            if(score == 7){
                intent.putExtra("RESULT","YOU HAVE NEGLIGIBLE DEPRESSION ");}
            else if(score>7 && score<=14){
                intent.putExtra("RESULT","YOU HAVE LOW DEPRESSION LEVEL");}
            else if(score>14 && score<=21){
                intent.putExtra("RESULT","YOU HAVE MODERATE DEPRESSION LEVEL");}
            else{
                intent.putExtra("RESULT","YOU HAVE HIGH DEPRESSION LEVEL");}
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
                                view.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#F06292")));

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
