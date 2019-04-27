package com.injaz2019.antism;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.VideoView;

import com.injaz2019.antism.classes.Utils.CameraUtils;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.util.List;

import static com.injaz2019.antism.classes.Utils.CameraUtils.BITMAP_SAMPLE_SIZE;
import static com.injaz2019.antism.classes.Utils.CameraUtils.CAMERA_CAPTURE_IMAGE_REQUEST_CODE;
import static com.injaz2019.antism.classes.Utils.CameraUtils.CAMERA_CAPTURE_VIDEO_REQUEST_CODE;
import static com.injaz2019.antism.classes.Utils.CameraUtils.GALLERY_IMAGE_REQUEST_CODE;
import static com.injaz2019.antism.classes.Utils.CameraUtils.IMAGE_EXTENSION;
import static com.injaz2019.antism.classes.Utils.CameraUtils.KEY_IMAGE_STORAGE_PATH;
import static com.injaz2019.antism.classes.Utils.CameraUtils.MEDIA_TYPE_IMAGE;
import static com.injaz2019.antism.classes.Utils.CameraUtils.MEDIA_TYPE_VIDEO;
import static com.injaz2019.antism.classes.Utils.CameraUtils.VIDEO_EXTENSION;

public class createTaskActivity extends AppCompatActivity implements View.OnClickListener {

    private static String imageStoragePath;
    /**/
    Button btn_save, btn_from_gallery, btn_video;
    ImageButton btn_picture, btn_audio;
    ToggleButton tbtn_morning, tbtn_night;
    EditText et_desc;
    ImageView imageView5;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        btn_save = findViewById(R.id.btn_save);
        btn_from_gallery = findViewById(R.id.btn_from_gallery);
        btn_picture = findViewById(R.id.btn_picture);
        btn_video = findViewById(R.id.btn_video);
        btn_audio = findViewById(R.id.btn_audio);
        tbtn_morning = findViewById(R.id.tbtn_morning);
        tbtn_night = findViewById(R.id.tbtn_night);
        et_desc = findViewById(R.id.et_desc);

        imageView5 = findViewById(R.id.imageView5);
        videoView = findViewById(R.id.videoView);

        btn_save.setOnClickListener(this);
        btn_from_gallery.setOnClickListener(this);
        btn_picture.setOnClickListener(this);
        btn_video.setOnClickListener(this);
        btn_audio.setOnClickListener(this);
        tbtn_morning.setOnClickListener(this);
        tbtn_night.setOnClickListener(this);

        // Checking availability of the camera
        if (!CameraUtils.isDeviceSupportCamera(getApplicationContext())) {
            Toast.makeText(getApplicationContext(), "الهاتف لا يتوفر على الكاميرا", Toast.LENGTH_LONG).show();
            // will close the app if the device doesn't have camera
//            finish();
        }

        // restoring storage image path from saved instance state
        // otherwise the path will be null on device rotation
        restoreFromBundle(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /* Capture image on button onClick */
            case R.id.btn_picture:
                if (CameraUtils.checkPermissions(getApplicationContext())) {
                    captureImage();
                } else {
                    requestCameraPermission(MEDIA_TYPE_IMAGE);
                }
                break;
            /* Record video on button onClick */
            case R.id.btn_video:
                if (CameraUtils.checkPermissions(getApplicationContext())) {
                    captureVideo();
                } else {
                    requestCameraPermission(MEDIA_TYPE_VIDEO);
                }
                break;

            case R.id.btn_from_gallery:
                fromGallery();
                break;
        }
    }

    /**
     * Restoring store image path from saved instance state
     */
    private void restoreFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(KEY_IMAGE_STORAGE_PATH)) {
                imageStoragePath = savedInstanceState.getString(KEY_IMAGE_STORAGE_PATH);
                if (!TextUtils.isEmpty(imageStoragePath)) {
                    if (imageStoragePath.substring(imageStoragePath.lastIndexOf(".")).equals("." + IMAGE_EXTENSION)) {
                        previewCapturedImage();
                    } else if (imageStoragePath.substring(imageStoragePath.lastIndexOf(".")).equals("." + VIDEO_EXTENSION)) {
                        previewVideo();
                    }
                }
            }
        }
    }

    /**
     * Requesting permissions using Dexter library
     */
    private void requestCameraPermission(final int type) {
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.RECORD_AUDIO)
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
            imageStoragePath = file.getAbsolutePath();
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
     * Saving stored image path to saved instance state
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // save file url in bundle as it will be null on screen orientation
        // changes
        outState.putString(KEY_IMAGE_STORAGE_PATH, imageStoragePath);
    }

    /**
     * Restoring image path from saved instance state
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // get the file url
        imageStoragePath = savedInstanceState.getString(KEY_IMAGE_STORAGE_PATH);
    }

    /**
     * Launching camera app to record video
     */
    private void captureVideo() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        File file = CameraUtils.getOutputMediaFile(MEDIA_TYPE_VIDEO);
        if (file != null) {
            imageStoragePath = file.getAbsolutePath();
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
                CameraUtils.refreshGallery(getApplicationContext(), imageStoragePath);
                // successfully captured the image
                // display it in image view
                previewCapturedImage();
            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled Image capture
//                Toast.makeText(getApplicationContext(), "ااب نسنس  نسنسنس", Toast.LENGTH_SHORT).show();
            } else {
                // failed to capture image
                Toast.makeText(getApplicationContext(), "خطأ في حفظ الصورة", Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == GALLERY_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                previewImageFromGallery(data);
            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled recording
//                Toast.makeText(getApplicationContext(), "User cancelled video recording", Toast.LENGTH_SHORT).show();
            } else {
                // failed to record video
                Toast.makeText(getApplicationContext(), "خطأ في حفظ الصورة", Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == CAMERA_CAPTURE_VIDEO_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // Refreshing the gallery
                CameraUtils.refreshGallery(getApplicationContext(), imageStoragePath);
                // video successfully recorded
                // preview the recorded video
                previewVideo();
            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled recording
//                Toast.makeText(getApplicationContext(), "User cancelled video recording", Toast.LENGTH_SHORT).show();
            } else {
                // failed to record video
                Toast.makeText(getApplicationContext(), "خطأ في حفظ الفيديو", Toast.LENGTH_SHORT).show();
            }
        }
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
        imageStoragePath = c.getString(columnIndex);
        c.close();
        Bitmap thumbnail = CameraUtils.optimizeBitmap2(BITMAP_SAMPLE_SIZE, imageStoragePath);
//        Bitmap thumbnail = (BitmapFactory.decodeFile(imageStoragePath));
        imageView5.setImageBitmap(thumbnail);
    }

    /**
     * Display Captured Image
     */
    private void previewCapturedImage() {
        try {
            Bitmap bitmap = CameraUtils.optimizeBitmap(BITMAP_SAMPLE_SIZE, imageStoragePath);
            imageView5.setImageBitmap(bitmap);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displaying video in VideoView
     */
    private void previewVideo() {
        try {
            videoView.setVideoPath(imageStoragePath);
            // start playing
            videoView.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
