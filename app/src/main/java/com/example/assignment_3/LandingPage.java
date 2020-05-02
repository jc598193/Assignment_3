package com.example.assignment_3;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

public class LandingPage extends AppCompatActivity {


    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

//        preferences = getSharedPreferences("pref", MODE_PRIVATE);
//        preferences.edit()
////                .putBoolean("play_sound", true)
////                .putBoolean("play_music", true)
//                .putInt("volume", 15)
////                .putInt("music", R.raw.background)
//                .apply();





    }

    @Override
    protected void onStart() {
        super.onStart();

        preferences = getSharedPreferences("pref", MODE_PRIVATE);
        int volume = preferences.getInt("volume", 15);
        boolean play_music = preferences.getBoolean("play_music", true);

        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0);

        if (play_music) {
            AudioPlay.playAudio(getApplicationContext(), R.raw.background);
        }
    }

}
