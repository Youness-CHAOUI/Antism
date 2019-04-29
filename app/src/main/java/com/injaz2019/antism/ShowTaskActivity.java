package com.injaz2019.antism;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.injaz2019.antism.classes.Database.myDBHelper;
import com.injaz2019.antism.classes.Metier.Tache;
import com.injaz2019.antism.classes.Utils.CameraUtils;

import java.io.File;

public class ShowTaskActivity extends AppCompatActivity {

    ImageButton btn_listen, btn_stop, btn_repeat;
    ImageView iv_picture;

    myDBHelper dbHelper;
    Tache _tache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_task);

        int ID_TACHE = getIntent().getExtras().getInt("ID_TACHE");
        dbHelper = new myDBHelper(this);
        btn_listen = findViewById(R.id.btn_listen);
        btn_stop = findViewById(R.id.btn_stop);
        btn_repeat = findViewById(R.id.btn_repeat);
        iv_picture = findViewById(R.id.iv_picture);

        _tache = dbHelper.findTacheById(ID_TACHE);
        if (_tache == null)
            Toast.makeText(this, "خطأ في تحميل المهمة", Toast.LENGTH_SHORT).show();
        else {
            try {
//            Bitmap bitmap = CameraUtils.optimizeBitmap2(BITMAP_SAMPLE_SIZE_32, _tache.getImage());
//            iv_picture.setImageBitmap(bitmap);
//                iv_picture.setImageBitmap(BitmapFactory.decodeFile(_tache.getImage()));
                Bitmap bitmap = CameraUtils.decodeSampledBitmapFromFile(new File(_tache.getImage()).getAbsolutePath(),
                        1000, 2000);
                Matrix matrix = new Matrix();
                matrix.postRotate(90);
                Bitmap rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                Bitmap scaledBitmap = Bitmap.createScaledBitmap(rotatedBitmap, 1000, 1630, true);
                iv_picture.setImageBitmap(scaledBitmap);
            } catch (NullPointerException e) {
                Toast.makeText(this, "خطأ في تحميل الصورة", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }
}
