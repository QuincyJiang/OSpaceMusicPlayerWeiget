package ospace.com.ospacemusicplayerweiget;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jiang on 2017/2/8.
 */

public class Weiget extends LinearLayout implements View.OnClickListener{


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

    public void setUrl(String url) {
       controller.setMusicURL(url);

    }

    public Weiget(Context context) {
        super(context);
    }

    public Weiget(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
//// TODO: 2017/2/9  
    public Weiget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.music_player_layout,this,true);
        ButterKnife.bind(view);
        iv_play_pause.setOnClickListener(this);
        Intent intent = new Intent(getContext(),MusicPlayerService.class);

        getContext().startService(intent);
        getContext().bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                controller =   (MusicControllerInterf)service;

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
                if(isMusicPlaying ==MusicPlayingState.PAUSE||isMusicPlaying==MusicPlayingState.STOP)
                {controller.playMusic();}
                else
controller.pauseMusic();
                break;
            case R.id.iv_play_forward:
controller.playforwardMuisc();
                break;
            case R.id.iv_play_back:
                controller.playBackwardMusic();
                break;

        }

    }

}
