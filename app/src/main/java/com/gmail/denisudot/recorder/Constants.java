package com.gmail.denisudot.recorder;

import android.media.AudioFormat;

public class Constants {
    static final int RECORDER_BPP = 16;
    static final String AUDIO_RECORDER_FOLDER = "AudioRecords";
    static final String AUDIO_RECORDER_TEMP_FILE = "record_temp.raw";
    static final String AUDIO_RECORDER_WAV_FILE = "path_to_file.wav";
    static final int RECORDER_SAMPLE_RATE = 44100;
    static final int RECORDER_CHANNELS = AudioFormat.CHANNEL_IN_MONO;
    static final int RECORDER_AUDIO_ENCODING = AudioFormat.ENCODING_PCM_16BIT;
}
