package com.gmail.denisudot.recorder;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.nfc.Tag;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Button record;
    Button play;
    Button stopPlaying;
    WavRecorder vr;
    MediaPlayer mediaPlayer;
    private static final String TAG = "myLogs";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initButtons();
        vr = new WavRecorder();
        mediaPlayer = new MediaPlayer();
    }

    private void initButtons() {
        record = (Button) findViewById(R.id.record_button);
        play = (Button) findViewById(R.id.play_button);
        stopPlaying = (Button) findViewById(R.id.stop_playing);

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!vr.getStatus()){
                    vr.startRecording();
                    Toast.makeText(getApplicationContext(), "Start recording", Toast.LENGTH_SHORT).show();
                    record.setText(R.string.stop_recording);
                    stopPlaying.setEnabled(false);
                }else{
                    Toast.makeText(getApplicationContext(), "Stop recording", Toast.LENGTH_SHORT).show();
                    record.setText(R.string.start_recording);
                }

            }
        });
        MyClickListener mcl = new MyClickListener();
        play.setOnClickListener(mcl);
        stopPlaying.setOnClickListener(mcl);


    }
    private class MyClickListener implements View.OnClickListener{
            @Override
            public void onClick(View v) {
                try {
                    String filepath = Environment.getExternalStorageDirectory().getPath();
                    File file = new File(filepath, Constants.AUDIO_RECORDER_FOLDER);
                    mediaPlayer.setDataSource(file.getAbsolutePath() + "/" + Constants.AUDIO_RECORDER_WAV_FILE);
                    mediaPlayer.prepare();

                    Toast.makeText(getApplicationContext(), "Playing Audio", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                switch (v.getId()) {
                    case R.id.play_button:
                        play.setEnabled(false);
                        stopPlaying.setEnabled(true);
                        vr.stopRecording();
                        mediaPlayer.start();
                        record.setText(R.string.start_recording);

                        break;
                    case R.id.stop_playing:
                        stopPlaying.setEnabled(false);
                        play.setEnabled(true);
                        mediaPlayer.pause();
                        break;
                }
            }


    }

}
