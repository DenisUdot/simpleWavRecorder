package com.gmail.denisudot.recorder;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaPlayer;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class ReverseWavPlayer {

    private int bufferSize = 0;

    public ReverseWavPlayer() {
        bufferSize = AudioRecord.getMinBufferSize(Constants.RECORDER_SAMPLE_RATE,
                Constants.RECORDER_CHANNELS, Constants.RECORDER_AUDIO_ENCODING) * 3;
    }

    private Thread readingThread = null;

    public void startReading() {
        readingThread = new Thread(new Runnable() {
            @Override
            public void run() {
                readAudioDataFromFile();
            }
        }, "ReadingWav Thread");

        readingThread.start();
    }

    private String getFilename() {
        String filepath = Environment.getExternalStorageDirectory().getPath();
        File file = new File(filepath, Constants.AUDIO_RECORDER_FOLDER);
        File wavFile = new File(filepath, Constants.AUDIO_RECORDER_WAV_FILE);

        if (wavFile.exists()) {
            wavFile.delete();
        }

        return (file.getAbsolutePath() + "/" + Constants.AUDIO_RECORDER_WAV_FILE);
    }

    private void readAudioDataFromFile() {

        FileInputStream in = null;
        List<Byte> reversList = new ArrayList<Byte>();
        byte[] data = new byte[bufferSize];

        try {
            in = new FileInputStream(getFilename());
//
            String filepath = Environment.getExternalStorageDirectory().getPath();
            File file = new File(filepath, Constants.AUDIO_RECORDER_FOLDER);
            File tempFile = new File(filepath, Constants.AUDIO_RECORDER_TEMP_FILE);

            if (tempFile.exists())
                tempFile.delete();


            //

            FileOutputStream out = new FileOutputStream(file.getAbsolutePath() + "/" +  "path_to_file1.wav");
            while (in.read(data) != -1) {
                for (byte i : data) {
                    reversList.add(i);
                }
            }
            in.close();
                byte[] reversData = new byte[reversList.size()];
                for (int i = 0; i < 44; i++) {
                    reversData[i] = reversList.get(0);
                    reversList.remove(0);
                }
                Collections.reverse(reversList);
                for (int i = 0; i < reversList.size(); i++) {
                    reversData[i + 44] = reversList.get(i);
                }
                out.write(reversData);
//                    out.write(data);




            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

//    public void createAudioRecord(byte[] data){
//
//    }
//
//    public void play() {
//        MediaPlayer mediaPlayer = new MediaPlayer();
//        try {
//            mediaPlayer.setDataSource(recordFile);
//            mediaPlayer.prepare();
//            mediaPlayer.start();
//        } catch (Exception e) {
//            // make something
//        }
//
//    }
}