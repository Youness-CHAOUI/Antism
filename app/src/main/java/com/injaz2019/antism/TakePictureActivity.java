package com.injaz2019.antism;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

public class TakePictureActivity extends AppCompatActivity {

    Button btn_save, btn_repeat;
    ImageView iv_picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_picture);

        btn_save = findViewById(R.id.btn_save);
        btn_repeat = findViewById(R.id.btn_repeat);
        iv_picture = findViewById(R.id.iv_picture);

//        Intent intent = PictureUtils.openCamera();
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivityForResult(intent, 1);
//        }
    }
}
