package com.example.assignment_3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Setting extends AppCompatActivity {

    Switch sound, music;
    SeekBar volume;
    TextView volume_value;
    SharedPreferences preferences;
    boolean play_sound, play_music;
    Button confirm;
    int changed_volume;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sound = findViewById(R.id.switch_sound);
        music = findViewById(R.id.switch_music);
        volume = findViewById(R.id.volume);
        volume_value = findViewById(R.id.volume_value);
        confirm = findViewById(R.id.confirm);

        // Access PlayingMusic class


        preferences = getSharedPreferences("pref", MODE_PRIVATE);
        int saved_volume = preferences.getInt("volume", 15);
        play_music = preferences.getBoolean("play_music", true);
        play_sound = preferences.getBoolean("play_sound", true);

        volume.setMax(30);
        volume.setProgress(saved_volume);
        volume_value.setText(String.valueOf(saved_volume));
        sound.setChecked(play_sound);
        sound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    play_sound = true;
                }else{
                    play_sound = false;
                }
            }
        });

        music.setChecked(play_music);
        music.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    play_music = true;
                    AudioPlay.playAudio(getApplicationContext(), R.raw.background);
                }else{
                    play_music = false;
                    AudioPlay.stopAudio();
                }
            }
        });

        volume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                volume_value.setText(String.valueOf(progress));
                AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
                changed_volume = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int int_volume = Integer.parseInt((String) volume_value.getText());
                preferences.edit()
                        .putBoolean("play_music", play_music)
                        .putBoolean("play_sound", play_sound)
                        .putInt("volume", int_volume)
                        .apply();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if(id == R.id.action_game){
            Intent intent = new Intent(this, Game.class);
            startActivity(intent);
            return true;
        }else if(id == R.id.action_scores) {
            Intent intent = new Intent(this, HighScores.class);
            startActivity(intent);
            return true;

        }
        return super.onOptionsItemSelected(item);
    }


}
