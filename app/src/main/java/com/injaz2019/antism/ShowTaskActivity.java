package com.injaz2019.antism;

import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.injaz2019.antism.classes.Database.myDBHelper;
import com.injaz2019.antism.classes.Metier.Tache;
import com.injaz2019.antism.classes.Utils.CameraUtils;

import java.io.IOException;

public class ShowTaskActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton btn_listen, btn_stop, btn_repeat;
    ImageView iv_picture;
    MediaPlayer mediaPlayer;

    myDBHelper dbHelper;
    Tache _tache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_task);

        int ID_TACHE = getIntent().getExtras().getInt("ID_TACHE");
        dbHelper = new myDBHelper(this);
        btn_listen = findViewById(R.id.btn_listen);
        btn_listen.setOnClickListener(this);
        btn_stop = findViewById(R.id.btn_stop);
        btn_stop.setOnClickListener(this);
        btn_repeat = findViewById(R.id.btn_repeat);
        btn_repeat.setOnClickListener(this);
        iv_picture = findViewById(R.id.iv_picture);
        mediaPlayer = new MediaPlayer();

        _tache = dbHelper.findTacheById(ID_TACHE);
        if (_tache == null)
            Toast.makeText(this, "خطأ في تحميل النشاط", Toast.LENGTH_SHORT).show();
        else {
            Bitmap bitmap = CameraUtils.optimizeBitmap(_tache.getImage());
            if (bitmap != null)
                iv_picture.setImageBitmap(bitmap);
            else
                Toast.makeText(this, "خطأ في تحميل الصورة", Toast.LENGTH_SHORT).show();
        }
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
            case R.id.btn_listen:
                playAudio(_tache.getAudio());
                btn_listen.setVisibility(View.GONE);
                btn_stop.setVisibility(View.VISIBLE);
                btn_repeat.setVisibility(View.VISIBLE);
                /*If the sound is finished*/
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        btn_listen.setVisibility(View.VISIBLE);
                        btn_stop.setVisibility(View.INVISIBLE);
                        btn_repeat.setVisibility(View.INVISIBLE);
                    }

                });
                break;
            case R.id.btn_stop:
                stopAudio();
                btn_listen.setVisibility(View.VISIBLE);
                btn_stop.setVisibility(View.INVISIBLE);
                btn_repeat.setVisibility(View.INVISIBLE);
                break;
            case R.id.btn_repeat:
                stopAudio();
                playAudio(_tache.getAudio());
                btn_listen.setVisibility(View.GONE);
                btn_stop.setVisibility(View.VISIBLE);
                btn_repeat.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void playAudio(String path) {
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
        } catch (IOException e) {
            Toast.makeText(this, "خطـأ في التشغيل", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        mediaPlayer.start();
    }

    private void stopAudio() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null)
            mediaPlayer.release();
    }
}
