package ospace.com.ospacemusicplayerweiget;

import android.content.Context;
import android.content.Intent;
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

private  String url;
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


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Weiget(Context context) {
        super(context);
    }

    public Weiget(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Weiget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.music_player_layout,this,true);
        ButterKnife.bind(view);
        iv_play_pause.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_play_pause:

                break;
            case R.id.iv_play_forward:
                break;
            case R.id.iv_play_back:
                break;

        }

    }
    protected void playMusic(){
        Intent intent = new Intent(getContext(),MusicPlayerService.class);
        intent.putExtra("option","play");
        getContext().startService(intent);

    }
    protected  void pauseMusic(){
        Intent intent = new Intent(getContext(),MusicPlayerService.class);
        intent.putExtra("option","pause");
        getContext().startService(intent);
    }
    protected void playForward(){
        Intent intent = new Intent(getContext(),MusicPlayerService.class);
        intent.putExtra("option","forward");
        getContext().startService(intent);
    }
    protected void playBackward(){
        Intent intent = new Intent(getContext(),MusicPlayerService.class);
        intent.putExtra("option","backward");
        getContext().startService(intent);
    }
}
