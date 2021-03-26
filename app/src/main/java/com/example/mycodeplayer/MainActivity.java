package com.example.mycodeplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn1, btn2, btn3;
    SoundPool spool;
    int[] keys=new int[3];
    int streamID=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {               // API 21 이후
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();

            spool = new SoundPool.Builder().setAudioAttributes(audioAttributes).setMaxStreams(1).build();
        } else {                // API 21 미만
            spool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        }
        keys[0]=spool.load(this, R.raw.cmajor, 1);
        keys[1]=spool.load(this, R.raw.dmajor, 1);
        keys[2]=spool.load(this, R.raw.eminor, 1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spool.stop(streamID);
                streamID=spool.play(keys[0], 1, 1, 0, 0, 1);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spool.stop(streamID);
                streamID=spool.play(keys[1], 1, 1, 0, 0, 1);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spool.stop(streamID);
                streamID=spool.play(keys[2], 1, 1, 0, 0, 1);
            }
        });

    }
}