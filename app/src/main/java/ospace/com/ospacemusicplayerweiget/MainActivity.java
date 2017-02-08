package ospace.com.ospacemusicplayerweiget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
private  Weiget player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player=(Weiget) findViewById(R.id.player);
        //// TODO: 2017/2/9
        player.setUrl("");
    }
}
