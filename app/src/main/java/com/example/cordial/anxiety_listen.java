package com.example.cordial;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class anxiety_listen extends Fragment {

    public anxiety_listen() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_anxiety_listen, container, false);


        CardView quiz = rootView.findViewById(R.id.quizC);
        CardView music = rootView.findViewById(R.id.musicC);
        CardView exercise = rootView.findViewById(R.id.exerciseC);


        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuizDetail();
            }
        });

        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateMusicDetail();
            }
        });

        exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateExerciseDetail();
            }
        });
        return rootView;
    }

    private void updateQuizDetail() {
        Intent intent = new Intent(getActivity(), AnxietyQuiz.class);
        startActivity(intent);
    }

    private void updateMusicDetail() {
        Intent intent = new Intent(getActivity(), SongsUpload.class);
        startActivity(intent);
    }

    private void updateExerciseDetail() {
        Intent intent = new Intent(getActivity(), AnxietyYoga.class);
        startActivity(intent);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }
}


