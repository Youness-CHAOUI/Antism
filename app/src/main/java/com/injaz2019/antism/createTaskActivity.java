package com.injaz2019.antism;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.injaz2019.antism.classes.Database.myDBHelper;
import com.injaz2019.antism.classes.Dialog.Popup;
import com.injaz2019.antism.classes.Metier.Routine;
import com.injaz2019.antism.classes.Metier.Tache;
import com.injaz2019.antism.classes.Utils.AudioUtils;
import com.injaz2019.antism.classes.Utils.CameraUtils;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.injaz2019.antism.classes.Utils.CameraUtils.CAMERA_CAPTURE_IMAGE_REQUEST_CODE;
import static com.injaz2019.antism.classes.Utils.CameraUtils.CAMERA_CAPTURE_VIDEO_REQUEST_CODE;
import static com.injaz2019.antism.classes.Utils.CameraUtils.GALLERY_IMAGE_REQUEST_CODE;
import static com.injaz2019.antism.classes.Utils.CameraUtils.KEY_IMAGE_STORAGE_PATH;
import static com.injaz2019.antism.classes.Utils.CameraUtils.MEDIA_TYPE_IMAGE;
import static com.injaz2019.antism.classes.Utils.CameraUtils.MEDIA_TYPE_VIDEO;

public class createTaskActivity extends AppCompatActivity implements View.OnClickListener {

    /**/
    public static final int RequestPermissionCode = 1;
    private static String _imageStoragePath = "EMPTY_IMAGE";
    private static String _audioStoragePath = "EMPTY_AUDIO";
    private static String _heure = "10:00", _format = "AM";
    myDBHelper dbHelper;
    /**/
    Button btn_save, btn_from_gallery, btnTerminer;
    ImageButton btn_picture, btn_audio, btn_time;
    CardView cv_picture, cv_audio;
    CardView cv_check1, cv_check2;
    ImageButton btn_start_record, btn_stop_record, btn_play_audio, btn_stop_audio;
    View view;
    TimePicker tp_time;
    AlertDialog popupPickTitme;
    TextView tv_time, tv_timer;
    /**/
    MediaRecorder myAudioRecorder;
    MediaPlayer mediaPlayer;
    private long _startTime;
    private Handler _customHandler = new Handler();
    private long _timeInMilliseconds = 0L;
    private long _timeSwapBuff = 0L;
    private Runnable _updateTimerThread;
    private ImageView iv_playing;
//    VideoView videoView;
//    Button btn_video;
//    ImageView imageView5;
//    ToggleButton tbtn_morning, tbtn_night;
//    EditText et_desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        dbHelper = new myDBHelper(this);
        /**/
        btn_save = findViewById(R.id.btn_save);
        btn_from_gallery = findViewById(R.id.btn_from_gallery);
        btn_picture = findViewById(R.id.btn_picture);
        cv_picture = findViewById(R.id.cv_picture);
        cv_audio = findViewById(R.id.cv_audio);
        cv_check1 = findViewById(R.id.cv_check1);
        cv_check2 = findViewById(R.id.cv_check2);
//        btn_video = findViewById(R.id.btn_video);
        btn_start_record = findViewById(R.id.btn_start_record);
        btn_stop_record = findViewById(R.id.btn_stop_record);
        btn_stop_record.setEnabled(false);
        btn_play_audio = findViewById(R.id.btn_play_audio);
        btn_play_audio.setEnabled(false);
        btn_stop_audio = findViewById(R.id.btn_stop_audio);
        btn_stop_audio.setEnabled(false);
//        tbtn_morning = findViewById(R.id.tbtn_morning);
//        tbtn_night = findViewById(R.id.tbtn_night);
//        et_desc = findViewById(R.id.et_desc);
        tv_time = findViewById(R.id.tv_time);
        tv_timer = findViewById(R.id.tv_timer);
        iv_playing = findViewById(R.id.iv_playing);
        btn_time = findViewById(R.id.btn_time);
        view = LayoutInflater.from(this).inflate(R.layout.popup_pick_time, null);
        btnTerminer = view.findViewById(R.id.btnTerminer);
        popupPickTitme = Popup.createPopup(this, view, true);
        tp_time = view.findViewById(R.id.timePicker);


//        imageView5 = findViewById(R.id.imageView5);
//        videoView = findViewById(R.id.videoView);

        btn_save.setOnClickListener(this);
        btn_from_gallery.setOnClickListener(this);
        btn_picture.setOnClickListener(this);
//        btn_video.setOnClickListener(this);

        btn_start_record.setOnClickListener(this);
        btn_stop_record.setOnClickListener(this);
        btn_play_audio.setOnClickListener(this);
        btn_stop_audio.setOnClickListener(this);

        btn_time.setOnClickListener(this);
//        tbtn_morning.setOnClickListener(this);
//        tbtn_night.setOnClickListener(this);

        // Checking availability of the camera
        if (!CameraUtils.isDeviceSupportCamera(getApplicationContext())) {
            Toast.makeText(getApplicationContext(), "الهاتف لا يتوفر على الكاميرا", Toast.LENGTH_LONG).show();
            // will close the app if the device doesn't have camera
//            finish();
        }
        // restoring storage image path from saved instance state
        // otherwise the path will be null on device rotation
        restoreFromBundle(savedInstanceState);
        /**/
        _updateTimerThread = new Runnable() {
            @Override
            public void run() {
                _timeInMilliseconds = SystemClock.uptimeMillis() - _startTime;
                Long _updatedTime = _timeSwapBuff + _timeInMilliseconds;
                int secs = (int) (_updatedTime / 1000);
                int mins = secs / 60;
                secs = secs % 60;
                int milliseconds = (int) (_updatedTime % 1000);
                tv_timer.setText("" + mins + ":"
                        + String.format("%02d", secs) + ":"
                        + String.format("%03d", milliseconds));
                _customHandler.postDelayed(this, 0);
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_0, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.quitter:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    finishAffinity();
                }
                System.exit(0);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*Save tache*/
            case R.id.btn_save:
                if (saveCurrentTask()) {
                    Intent intent = new Intent();
//                intent.putExtra("res1", "value1");
                    setResult(RESULT_OK, intent);
                    finish();
                }
                break;
            /* Capture image on button onClick */
            case R.id.btn_picture:
                if (CameraUtils.checkPermissions(getApplicationContext())) {
                    captureImage();
                } else {
                    requestCameraPermission(MEDIA_TYPE_IMAGE);
                }
                break;
            /* Record video on button onClick */
//            case R.id.btn_video:
//                if (CameraUtils.checkPermissions(getApplicationContext())) {
//                    captureVideo();
//                } else {
//                    requestCameraPermission(MEDIA_TYPE_VIDEO);
//                }
//                break;
            /* from gallery*/
            case R.id.btn_from_gallery:
                fromGallery();
                break;
            /* pick time */
            case R.id.btn_time:
                popupPickTitme.show();
                break;
            /* start record audio*/
            case R.id.btn_start_record:
                startRecording();
                break;
            /* start record audio*/
            case R.id.btn_stop_record:
                stopRecording();
                break;
            /* start playing audio*/
            case R.id.btn_play_audio:
                playAudio();
                break;
            /* stop playing audio*/
            case R.id.btn_stop_audio:
                stopAudio();
                break;
        }
    }

    private boolean saveCurrentTask() {
        /*image*/
        if (cv_check1.getVisibility() == View.INVISIBLE) {
            Toast.makeText(this, getResources().getString(R.string._error_image), Toast.LENGTH_LONG).show();
            return false;
        }
        /*audio*/
        else if (cv_check2.getVisibility() == View.INVISIBLE) {
            Toast.makeText(this, getResources().getString(R.string._error_audio), Toast.LENGTH_LONG).show();
            return false;
        }
        /*time*/
        else if (tv_time.getText().length() == 0) {
            Toast.makeText(this, getResources().getString(R.string._error_time), Toast.LENGTH_LONG).show();
            return false;
        } else {
            String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
            int ID_ROUTINE = dbHelper.findRoutineByDate(date);
            if (ID_ROUTINE == -1) {
                ID_ROUTINE = dbHelper.addRoutine(new Routine(date)).intValue();
            }
            String image = _imageStoragePath;
            String audio = _audioStoragePath;
            String time = tv_time.getText().toString();
            Tache tache = new Tache(image, audio, time, ID_ROUTINE);

            if (dbHelper.addTacheToRoutine(tache) > 0)
                return true;
            else {
                Toast.makeText(this, getResources().getString(R.string._error_save), Toast.LENGTH_LONG).show();
                return false;
            }
        }
    }

    private void stopAudio() {
        btn_start_record.setEnabled(true);
        btn_start_record.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.btn_radius));
        btn_stop_record.setEnabled(false);
        btn_stop_record.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.btn_radius2));
        btn_play_audio.setEnabled(true);
        btn_play_audio.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.btn_radius));
        btn_stop_audio.setEnabled(false);
        btn_stop_audio.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.btn_radius2));

        iv_playing.setVisibility(View.INVISIBLE);

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    private void playAudio() {
        btn_start_record.setEnabled(false);
        btn_start_record.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.btn_radius2));
        btn_stop_record.setEnabled(false);
        btn_stop_record.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.btn_radius2));
        btn_play_audio.setEnabled(false);
        btn_play_audio.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.btn_radius2));
        btn_stop_audio.setEnabled(true);
        btn_stop_audio.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.btn_radius));

        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(_audioStoragePath);
            mediaPlayer.prepare();
        } catch (IOException e) {
            Toast.makeText(this, "خطـأ في التشغيل", Toast.LENGTH_LONG).show();
            iv_playing.setVisibility(View.INVISIBLE);
            e.printStackTrace();
        }
        iv_playing.setVisibility(View.VISIBLE);
        mediaPlayer.start();
    }

    private void stopRecording() {
        myAudioRecorder.stop();

        btn_start_record.setEnabled(true);
        btn_start_record.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.btn_radius));
        btn_stop_record.setEnabled(true);
        btn_stop_record.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.btn_radius2));
        btn_play_audio.setEnabled(true);
        btn_play_audio.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.btn_radius));
        btn_stop_audio.setEnabled(false);
        btn_stop_audio.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.btn_radius2));

        cv_check2.setVisibility(View.VISIBLE);
        onTimerBegin(false);
        Toast.makeText(this, "انتهى التسجيل", Toast.LENGTH_LONG).show();
    }

    /*start recording*/
    private void startRecording() {
        if (checkPermission()) {
            _audioStoragePath = AudioUtils.getOutputMediaFile().getAbsolutePath();
            Log.i("###", _audioStoragePath);
            myAudioRecorder = AudioUtils.initAudioRecorder(_audioStoragePath);
            try {
                myAudioRecorder.prepare();
                myAudioRecorder.start();
                cv_check2.setVisibility(View.INVISIBLE);
                Toast.makeText(this, "بدأ التسجيل", Toast.LENGTH_LONG).show();

                onTimerBegin(true);

                btn_start_record.setEnabled(false);
                btn_start_record.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.btn_radius2));
                btn_stop_record.setEnabled(true);
                btn_stop_record.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.btn_radius));
                btn_play_audio.setEnabled(false);
                btn_play_audio.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.btn_radius2));
                btn_stop_audio.setEnabled(false);
                btn_stop_audio.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.btn_radius2));
            } catch (Exception e) {
                cv_check2.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "خطأ في التسجيل", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        } else {
            requestPermission();
        }
    }

    private void onTimerBegin(boolean isStart) {
        if (isStart) {
            _startTime = SystemClock.uptimeMillis();
            _customHandler.postDelayed(_updateTimerThread, 0);
        } else {
            tv_timer.setText(getResources().getString(R.string._record));
            _timeSwapBuff = 0;
            _customHandler.removeCallbacks(_updateTimerThread);
        }
    }

    public boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
    }


    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new
                String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, RequestPermissionCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RequestPermissionCode:
                if (grantResults.length > 0) {
                    boolean StoragePermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean RecordPermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if (StoragePermission && RecordPermission) {
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        btnTerminer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strH, strM;
                int h = tp_time.getCurrentHour(), m = tp_time.getCurrentMinute();
                if (h >= 0 && h <= 12)
                    _format = "AM";
                else
                    _format = "PM";

                if (h >= 0 && h <= 9)
                    strH = "0" + h;
                else strH = h + "";
                if (m >= 0 && m <= 9) strM = "0" + m;
                else strM = m + "";

                _heure = strH + ":" + strM;
                tv_time.setText(_heure);
                popupPickTitme.dismiss();
            }
        });
    }

    /**
     * Saving stored image path to saved instance state
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // save file url in bundle as it will be null on screen orientation
        // changes
        outState.putString(KEY_IMAGE_STORAGE_PATH, _imageStoragePath);
        outState.putString("_HEURE", _heure);
        outState.putString("_IMAGE", String.valueOf(cv_check1.getVisibility()));
    }

    /**
     * Restoring store image path from saved instance state
     */
    private void restoreFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("_HEURE")) {
                _heure = savedInstanceState.getString("_HEURE");
            }
            if (savedInstanceState.containsKey("_IMAGE")) {
                cv_check1.setVisibility(Integer.parseInt(savedInstanceState.getString("_IMAGE")));
            }
            if (savedInstanceState.containsKey(KEY_IMAGE_STORAGE_PATH)) {
                _imageStoragePath = savedInstanceState.getString(KEY_IMAGE_STORAGE_PATH);
//                if (!TextUtils.isEmpty(_imageStoragePath)) {
//                    if (_imageStoragePath.substring(_imageStoragePath.lastIndexOf(".")).equals("." + IMAGE_EXTENSION)) {
////                        previewCapturedImage();
//                    } else if (_imageStoragePath.substring(_imageStoragePath.lastIndexOf(".")).equals("." + VIDEO_EXTENSION)) {
////                        previewVideo();
//                    }
//                }
            }
        }
    }

    /**
     * Requesting permissions using Dexter library
     */
    private void requestCameraPermission(final int type) {
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.CAMERA,
                        WRITE_EXTERNAL_STORAGE,
                        RECORD_AUDIO)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            if (type == MEDIA_TYPE_IMAGE) {
                                // capture picture
                                captureImage();
                            } else {
                                captureVideo();
                            }
                        } else if (report.isAnyPermissionPermanentlyDenied()) {
                            showPermissionsAlert();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }


    /**
     * Capturing Camera Image will launch camera app requested image capture
     */
    private void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = CameraUtils.getOutputMediaFile(MEDIA_TYPE_IMAGE);
        if (file != null) {
            _imageStoragePath = file.getAbsolutePath();
        }
        Uri fileUri = CameraUtils.getOutputMediaFileUri(getApplicationContext(), file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        // start the image capture Intent
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
    }

    /**
     * Getting Image from gallery
     */
    private void fromGallery() {
        Intent intent = CameraUtils.openGallery();
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivityForResult(intent, GALLERY_IMAGE_REQUEST_CODE);
    }

    /**
     * Restoring image path from saved instance state
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // get the file url
        _imageStoragePath = savedInstanceState.getString(KEY_IMAGE_STORAGE_PATH);
    }

    /**
     * Launching camera app to record video
     */
    private void captureVideo() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        File file = CameraUtils.getOutputMediaFile(MEDIA_TYPE_VIDEO);
        if (file != null) {
            _imageStoragePath = file.getAbsolutePath();
        }
        Uri fileUri = CameraUtils.getOutputMediaFileUri(getApplicationContext(), file);
        // set video quality
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file
        // start the video capture Intent
        startActivityForResult(intent, CAMERA_CAPTURE_VIDEO_REQUEST_CODE);
    }

    /**
     * Activity result method will be called after closing the camera
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // if the result is capturing Image
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // Refreshing the gallery
                CameraUtils.refreshGallery(getApplicationContext(), _imageStoragePath);
                // successfully captured the image
                // display it in image view
//                previewCapturedImage();
                cv_check1.setVisibility(View.VISIBLE);
                Log.i("***** camera", _imageStoragePath);
            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled Image capture
//                Toast.makeText(getApplicationContext(), "cancel", Toast.LENGTH_SHORT).show();
                cv_check1.setVisibility(View.INVISIBLE);
            } else {
                // failed to capture image
                cv_picture.setCardBackgroundColor(Color.WHITE);
                Toast.makeText(getApplicationContext(), "خطأ في حفظ الصورة", Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == GALLERY_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                previewImageFromGallery(data);
                cv_check1.setVisibility(View.VISIBLE);
            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled recording
//                Toast.makeText(getApplicationContext(), "User cancelled ", Toast.LENGTH_SHORT).show();
                cv_check1.setVisibility(View.INVISIBLE);
            } else {
                cv_check1.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "خطأ في حفظ الصورة", Toast.LENGTH_LONG).show();
            }
        }
//        else if (requestCode == CAMERA_CAPTURE_VIDEO_REQUEST_CODE) {
//            if (resultCode == RESULT_OK) {
//                // Refreshing the gallery
//                CameraUtils.refreshGallery(getApplicationContext(), _imageStoragePath);
//                // video successfully recorded
//                // preview the recorded video
//                previewVideo();
//            } else if (resultCode == RESULT_CANCELED) {
//                // user cancelled recording
////                Toast.makeText(getApplicationContext(), "User cancelled video recording", Toast.LENGTH_SHORT).show();
//            } else {
//                // failed to record video
//                Toast.makeText(getApplicationContext(), "خطأ في حفظ الفيديو", Toast.LENGTH_SHORT).show();
//            }
//        }
    }

    /**
     * Display From Gallery
     */
    private void previewImageFromGallery(Intent data) {
        Uri selectedImage = data.getData();
        String[] filePath = {MediaStore.Images.Media.DATA};
        Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
        c.moveToFirst();
        int columnIndex = c.getColumnIndex(filePath[0]);
        _imageStoragePath = c.getString(columnIndex);
        c.close();
        Log.i("***** gallery", _imageStoragePath);
//        Bitmap thumbnail = CameraUtils.optimizeBitmap2(BITMAP_SAMPLE_SIZE, _imageStoragePath);
//        Bitmap thumbnail = (BitmapFactory.decodeFile(_imageStoragePath));
//        imageView5.setImageBitmap(thumbnail);
    }

    /**
     * Display Captured Image
     */
//    private void previewCapturedImage() {
//        try {
//            Bitmap bitmap = CameraUtils.optimizeBitmap(BITMAP_SAMPLE_SIZE, _imageStoragePath);
//            imageView5.setImageBitmap(bitmap);
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * Displaying video in VideoView
     */
//    private void previewVideo() {
//        try {
//            videoView.setVideoPath(_imageStoragePath);
//            // start playing
//            videoView.start();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * Alert dialog to navigate to app settings
     * to enable necessary permissions
     */
    private void showPermissionsAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("الصلاحيات")
                .setMessage("دعم صلاحيات التصوير من الاعدادات")
                .setPositiveButton("الاعدادات", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        CameraUtils.openSettings(createTaskActivity.this);
                    }
                })
                .setNegativeButton("رفض", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
    }

}
