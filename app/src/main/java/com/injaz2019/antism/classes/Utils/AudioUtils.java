package com.injaz2019.antism.classes.Utils;

import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AudioUtils {

    public static final String GALLERY_DIRECTORY_NAME = "MAutismApp";
    public static final String AUDIO_EXTENSION = "3gp";

    public static MediaRecorder initAudioRecorder(String outputFile) {
        MediaRecorder myAudioRecorder = new MediaRecorder();
        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        myAudioRecorder.setOutputFile(outputFile);

        return myAudioRecorder;
    }

    /**
     * Creates and returns the image or video file before opening the camera
     */
    public static File getOutputMediaFile() {
        // External sdcard location
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC),
                GALLERY_DIRECTORY_NAME);
        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.e(GALLERY_DIRECTORY_NAME, "Oops! Failed create " + GALLERY_DIRECTORY_NAME + " directory");
                return null;
            }
        }
        // Preparing media file naming convention
        // adds timestamp
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + "AUD_" + timeStamp + "." + AUDIO_EXTENSION);

        return mediaFile;
    }
}
