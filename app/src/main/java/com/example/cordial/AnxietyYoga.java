package com.example.cordial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.ohoussein.playpause.PlayPauseView;

import java.io.IOException;

public class AnxietyYoga extends AppCompatActivity implements MediaPlayer.OnPreparedListener{

private MediaPlayer mediaPlayer1, mediaPlayer2, mediaPlayer3, mediaPlayer4;
private PlayPauseView imagePlayPause1, imagePlayPause2, imagePlayPause3, imagePlayPause4;
        ProgressBar pBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anxiety_yoga);
        mediaPlayer1 = new MediaPlayer();
        mediaPlayer2 = new MediaPlayer();
        mediaPlayer3 = new MediaPlayer();
        mediaPlayer4 = new MediaPlayer();


        imagePlayPause1 = findViewById(R.id.play_pause_view1);
        imagePlayPause2 = findViewById(R.id.play_pause_view2);
        imagePlayPause3 = findViewById(R.id.play_pause_view3);
        imagePlayPause4 = findViewById(R.id.play_pause_view4);

        mediaPlayer1.setAudioStreamType(AudioManager.STREAM_MUSIC);
        pBar = findViewById(R.id.pBar);

        imagePlayPause1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer2.reset();
                mediaPlayer3.reset();
                mediaPlayer4.reset();
                pBar.setVisibility(View.VISIBLE);
                imagePlayPause1.toggle();
                fetchAudioUrlFromFirebase1();
                if (mediaPlayer1.isPlaying()) {
                    pBar.setVisibility(View.GONE);
                    mediaPlayer1.pause();
                } else {
                    mediaPlayer1.start();
                }
                if(mediaPlayer1.isPlaying())
                    pBar.setVisibility(View.GONE);

            }

        });

        imagePlayPause2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer1.reset();
                mediaPlayer3.reset();
                mediaPlayer4.reset();
                pBar.setVisibility(View.VISIBLE);
                imagePlayPause2.toggle();
                fetchAudioUrlFromFirebase2();
                if (mediaPlayer2.isPlaying()) {
                    pBar.setVisibility(View.GONE);
                    mediaPlayer2.pause();
                } else {
                    mediaPlayer2.start();
                }
                if(mediaPlayer2.isPlaying())
                    pBar.setVisibility(View.GONE);

            }

        });

        imagePlayPause3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pBar.setVisibility(View.VISIBLE);
                mediaPlayer1.reset();
                mediaPlayer2.reset();
                mediaPlayer4.reset();
                imagePlayPause3.toggle();
                fetchAudioUrlFromFirebase3();
                if (mediaPlayer3.isPlaying()) {
                    pBar.setVisibility(View.GONE);
                    mediaPlayer3.pause();
                } else {
                    mediaPlayer3.start();
                }
                if(mediaPlayer3.isPlaying())
                    pBar.setVisibility(View.GONE);

            }

        });



        imagePlayPause4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer1.reset();
                mediaPlayer2.reset();
                mediaPlayer3.reset();
                pBar.setVisibility(View.VISIBLE);
                imagePlayPause4.toggle();
                fetchAudioUrlFromFirebase4();
                if (mediaPlayer4.isPlaying()) {
                    pBar.setVisibility(View.GONE);
                    mediaPlayer4.pause();
                } else {
                    mediaPlayer4.start();
                }
                if(mediaPlayer4.isPlaying())
                    pBar.setVisibility(View.GONE);

            }

        });
    }

    private void fetchAudioUrlFromFirebase1() {
        final FirebaseStorage storage = FirebaseStorage.getInstance();
        // Create a storage reference from our app
        StorageReference storageRef = storage.getReferenceFromUrl("gs://health-project-9c67b.appspot.com/A Powerful 10 Minute Guided Meditation.mp3");
        storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                try {
                    // Download url of file
                    final String url = uri.toString();
                    mediaPlayer1.setDataSource(url);
                    // wait for media player to get prepare
                    mediaPlayer1.setOnPreparedListener(AnxietyYoga.this);
                    mediaPlayer1.prepareAsync();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AnxietyYoga.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        pBar.setVisibility(View.GONE);
                    }
                });

    }

    private void fetchAudioUrlFromFirebase2() {
        final FirebaseStorage storage = FirebaseStorage.getInstance();
        // Create a storage reference from our app
        StorageReference storageRef = storage.getReferenceFromUrl("gs://health-project-9c67b.appspot.com/10-Minute Meditation For Anxiety (mp3cut.net).mp3");
        storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                try {
                    // Download url of file
                    final String url = uri.toString();
                    mediaPlayer2.setDataSource(url);
                    // wait for media player to get prepare
                    mediaPlayer2.setOnPreparedListener(AnxietyYoga.this);
                    mediaPlayer2.prepareAsync();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AnxietyYoga.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        pBar.setVisibility(View.GONE);
                    }
                });

    }

    private void fetchAudioUrlFromFirebase3() {
        final FirebaseStorage storage = FirebaseStorage.getInstance();
        // Create a storage reference from our app
        StorageReference storageRef = storage.getReferenceFromUrl("gs://health-project-9c67b.appspot.com/10 Minute Chakra Balance Guided Meditation for Positive Energy.mp3");
        storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                try {
                    // Download url of file
                    final String url = uri.toString();
                    mediaPlayer1.setDataSource(url);
                    // wait for media player to get prepare
                    mediaPlayer1.setOnPreparedListener(AnxietyYoga.this);
                    mediaPlayer1.prepareAsync();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AnxietyYoga.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        pBar.setVisibility(View.GONE);
                    }
                });

    }

    private void fetchAudioUrlFromFirebase4() {
        final FirebaseStorage storage = FirebaseStorage.getInstance();
        // Create a storage reference from our app
        StorageReference storageRef = storage.getReferenceFromUrl("gs://health-project-9c67b.appspot.com/Guided meditation - Mastering your Thoughts and Emotions for sleep.mp3");
        storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                try {
                    // Download url of file
                    final String url = uri.toString();
                    mediaPlayer1.setDataSource(url);
                    // wait for media player to get prepare
                    mediaPlayer1.setOnPreparedListener(AnxietyYoga.this);
                    mediaPlayer1.prepareAsync();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AnxietyYoga.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        pBar.setVisibility(View.GONE);
                    }
                });

    }
    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
        if (mp.isPlaying()) {
            pBar.setVisibility(View.GONE);
        }
    }

}
