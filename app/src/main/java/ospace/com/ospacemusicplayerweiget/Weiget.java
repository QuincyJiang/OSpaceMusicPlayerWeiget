package ospace.com.ospacemusicplayerweiget;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

import static ospace.com.ospacemusicplayerweiget.StringUtils.Str2Duration;

/**
 * Created by jiang on 2017/2/8.
 */

public class Weiget extends LinearLayout implements View.OnClickListener,SeekBar.OnSeekBarChangeListener{


@Bind(R.id.seek_bar_music_player)
 SeekBar seek_bar_music_player;
@Bind(R.id.tv_music_player_time_left)
TextView tv_music_player_time_left;
@Bind(R.id.tv_music_player_total_time)
TextView tv_music_player_total_time;
@Bind(R.id.iv_play_back)
ImageView iv_play_back;
@Bind(R.id.iv_play_forward)
ImageView iv_play_forward;
@Bind(R.id.iv_play_pause)
ImageView iv_play_pause;
private ServiceConnection connection;
private MusicControllerInterf controller;
private MusicPlayingState isMusicPlaying;
    private String musicLength;
private Timer mTimer;

    private Handler mHandler;
    static int UPDATE_UI=0;

            ;
//    public void setUrl(String url) {
//        if(controller!=null)
//       controller.setMusicURL(url);
//
//    }


    public Weiget(Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.music_player_layout,this,true);
        ButterKnife.bind(view);
        init();
    }

    public Weiget(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.music_player_layout,this,true);
        ButterKnife.bind(view);
        init();
    }
//// TODO: 2017/2/9  
    public Weiget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.music_player_layout,this,true);
        ButterKnife.bind(view);
        init();

    }

    void init(){
//// TODO: 2017/2/15 seekbar 的播放进度和缓存条进度以及左右播放时间
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(controller!=null){
                    tv_music_player_time_left.setText(controller.getCurrentPosition());
                    seek_bar_music_player.setProgress(getProgress(controller.getCurrentPosition(),controller.getMusicLength()));
                    seek_bar_music_player.setSecondaryProgress(controller.getBuffering());
                    musicLength=controller.getMusicLength();
                    tv_music_player_total_time.setText(musicLength);
                    if(controller.isMusicPlaying()==MusicPlayingState.FINISH){
                        mTimer.cancel();
                        mTimer=null;
                        tv_music_player_time_left.setText(msg.getData().getString("00:00"));
                        seek_bar_music_player.setSecondaryProgress(0);
                        seek_bar_music_player.setProgress(0);
                        iv_play_pause.setBackgroundResource(R.drawable.library_voice_mediacontroller_play);
                    }
                }




                }


        };

        iv_play_pause.setOnClickListener(this);
        iv_play_forward.setOnClickListener(this);
        iv_play_back.setOnClickListener(this);
        seek_bar_music_player.setOnSeekBarChangeListener(this);
        Intent intent = new Intent(getContext(),MusicPlayerService.class);

        getContext().startService(intent);
       getContext().bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                controller =   (MusicControllerInterf)service;
                controller.setMusicURL("http://img.owspace.com/F_btd408361_1486390559.6014230.mp3");

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);
    }
    @Override
    public void onClick(View v) {
        isMusicPlaying=controller.isMusicPlaying();
        switch(v.getId()){

            case R.id.iv_play_pause:

                if(isMusicPlaying ==MusicPlayingState.PAUSE||isMusicPlaying==MusicPlayingState.STOP||isMusicPlaying==MusicPlayingState.FINISH)
                {controller.playMusic();
                    iv_play_pause.setBackgroundResource(R.drawable.library_voice_mediacontroller_pause);
                }
                else{
controller.pauseMusic();
                    iv_play_pause.setBackgroundResource(R.drawable.library_voice_mediacontroller_play);}

                startTimer();
                break;
            case R.id.iv_play_forward:
controller.playforwardMusic();
                break;
            case R.id.iv_play_back:
                controller.playBackwardMusic();
                break;

        }


    }
private int getProgress(String currentPosition, String musicLength){
    int position= Str2Duration(currentPosition);
    int length = Str2Duration(musicLength);
if(length!=0)
    return 100*position/length;
else return 0;
}


  public void   startTimer(){
      if(mTimer==null){
          mTimer = new Timer();}

          mTimer.schedule(new TimerTask() {
              @Override
              public void run() {
if(controller!=null){




    mHandler.sendEmptyMessage(UPDATE_UI);
}
              }
          },0,1000);

      }



    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(fromUser){
            if(controller!=null){

                controller.seekTo( StringUtils.Str2Duration(musicLength)*progress/100);
            }
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

}
