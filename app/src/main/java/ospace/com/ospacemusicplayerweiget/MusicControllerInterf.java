package ospace.com.ospacemusicplayerweiget;

/**
 * Created by jiang on 2017/2/8.
 */

public interface MusicControllerInterf  {
    void playMusic();
    void pauseMusic();
    void playforwardMusic();
    void playBackwardMusic();
    void seekTo(int progress);
    String getCurrentPosition();
    int getBuffering();
    MusicPlayingState isMusicPlaying();
    void setMusicURL(String url);
    String getMusicLength();

}
