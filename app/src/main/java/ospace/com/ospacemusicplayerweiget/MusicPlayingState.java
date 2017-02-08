package ospace.com.ospacemusicplayerweiget;

/**
 * Created by jiang on 2017/2/8.
 */

public enum MusicPlayingState {
PLAYING(0),PAUSE(1),STOP(2);

    private int isPlaying;


    MusicPlayingState(int isPlaying) {
        this.isPlaying = isPlaying;
    }



}



