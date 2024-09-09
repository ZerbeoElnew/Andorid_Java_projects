package com.example.music;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private ImageButton playButton;
    private ImageButton pauseButton;
    private ImageButton nextButton;
    private ImageButton previousButton;

    private int[] songs = {R.raw.al3ao, R.raw.mashel}; // Add your song resource IDs here
    private int currentSongIndex = 0;
    private boolean isPlaying = false; // Track whether music is playing or not

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize MediaPlayer with the first song
        mediaPlayer = MediaPlayer.create(this, songs[currentSongIndex]);

        playButton = findViewById(R.id.music_play);
        pauseButton = findViewById(R.id.music_pause);
        nextButton = findViewById(R.id.next_music);
        previousButton = findViewById(R.id.imageButton4);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) {
                    mediaPlayer.pause();
                    updatePlayButtonIcon(true); // Show play icon
                } else {
                    mediaPlayer.start();
                    updatePlayButtonIcon(false); // Show pause icon
                }
                isPlaying = !isPlaying;
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) {
                    mediaPlayer.pause();
                    updatePlayButtonIcon(true); // Show play icon
                    isPlaying = false;
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playNext();
            }
        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playPrevious();
            }
        });
    }

    private void playNext() {
        mediaPlayer.release();
        currentSongIndex = (currentSongIndex + 1) % songs.length;
        mediaPlayer = MediaPlayer.create(this, songs[currentSongIndex]);
        mediaPlayer.start();
        updatePlayButtonIcon(false); // Show pause icon
        isPlaying = true;
    }

    private void playPrevious() {
        mediaPlayer.release();
        currentSongIndex = (currentSongIndex - 1 + songs.length) % songs.length;
        mediaPlayer = MediaPlayer.create(this, songs[currentSongIndex]);
        mediaPlayer.start();
        updatePlayButtonIcon(false); // Show pause icon
        isPlaying = true;
    }

    private void updatePlayButtonIcon(boolean showPlayIcon) {
        if (showPlayIcon) {
            playButton.setImageResource(android.R.drawable.ic_media_play);
        } else {
            playButton.setImageResource(android.R.drawable.ic_media_pause);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}