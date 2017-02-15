package ospace.com.ospacemusicplayerweiget;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.IOException;

/**
 * Created by jiang on 2017/2/8.
 */

public class MusicPlayerService extends Service implements  MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener,
        MediaPlayer.OnPreparedListener,MediaPlayer.OnErrorListener {
    private MediaPlayer player;
    MusicPlayingState isMusicPlaying  =MusicPlayingState.STOP;
private  String url;
private int buffering=0;


    @Override
    public void onCreate() {
        super.onCreate();
//        初始化MediaPlayer
//        设置监听器
        player = new MediaPlayer();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.setOnCompletionListener(this);
        player.setOnBufferingUpdateListener(this);
        player.setOnErrorListener(this);
        player.setOnPreparedListener(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //释放资源
        player.release();
        player = null;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MusicController();
    }

    //该类对外提供基本的对播放器的控制方法
    public class MusicController extends Binder implements MusicControllerInterf{
        @Override
        public void playMusic() {
            //播放状态为停止时，才会去重新请求url并播放
if(player!=null&&(isMusicPlaying==MusicPlayingState.STOP||isMusicPlaying()==MusicPlayingState.FINISH)){
    try {
        player.reset();
        player.setDataSource(url);
        player.prepareAsync();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
            if(player!=null&&(isMusicPlaying==MusicPlayingState.PAUSE)){
                player.start();
                isMusicPlaying=MusicPlayingState.PLAYING;
            }

        }

        @Override
        public void pauseMusic() {
if(player!=null&&isMusicPlaying==MusicPlayingState.PLAYING){
    player.pause();
    isMusicPlaying = MusicPlayingState.PAUSE;
}
        }

        @Override
        public void playforwardMusic() {
            {
                if(player!=null)
                player.seekTo(player.getCurrentPosition() + 10000);
            }
        }
        @Override
        public void playBackwardMusic() {
            if(player!=null)
            player.seekTo(player.getCurrentPosition()-10000);
        }

        @Override
        public void seekTo(int progress) {
            if(player!=null)
            player.seekTo(progress);
        }

        @Override
        public String getCurrentPosition() {
            if(player!=null){
            return StringUtils.duration2Str(player.getCurrentPosition());}
            else return "0:00";
        }

        @Override
        public int getBuffering() {
            return buffering;
        }

        @Override
        public MusicPlayingState isMusicPlaying() {
            return isMusicPlaying;
        }

        @Override
        public void setMusicURL(String url) {
            MusicPlayerService.this.url = url;

        }

        @Override
        public String getMusicLength() {
            if(player!=null){
                return StringUtils.duration2Str(player.getDuration());
            }
            return "00:00";
        }


    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
 buffering  = percent;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
isMusicPlaying =MusicPlayingState.FINISH;
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
mp.start();
        isMusicPlaying=MusicPlayingState.PLAYING;
    }


}
