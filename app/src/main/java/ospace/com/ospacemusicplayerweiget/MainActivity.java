package ospace.com.ospacemusicplayerweiget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
private  Weiget player;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player=(Weiget) findViewById(R.id.player);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //player.setUrl("http://img.owspace.com/F_btd408361_1486390559.6014230.mp3");
    }
}
