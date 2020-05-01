package com.example.assignment_3;

import android.content.Context;
import android.media.MediaPlayer;

class PLayingMusic {

    private MediaPlayer background;

    PLayingMusic(Context context){
        background = MediaPlayer.create(context, R.raw.background);

    }
    void playMusic(){
        if(!background.isPlaying()){
            background.setLooping(true);
            background.start();
        }
    }

    void stopMusic(){
        if(background.isPlaying()){
            background.stop();
            background.release();
            background = null;
        }

    }
}
