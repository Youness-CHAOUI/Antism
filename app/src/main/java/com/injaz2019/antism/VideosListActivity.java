package com.injaz2019.antism;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.injaz2019.antism.classes.Adapters.rv_videoAdapter;
import com.injaz2019.antism.classes.Utils.YoutubeApiConfig;

public class VideosListActivity extends AppCompatActivity {

    RecyclerView rv_videos;
    rv_videoAdapter videoAdapter;
    private String _vids_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos_list);
        _vids_type = getIntent().getExtras().getString("TYPE");

        rv_videos = (RecyclerView) findViewById(R.id.rv_videos);
        rv_videos.setLayoutManager(new LinearLayoutManager(this, 1, false));
//        rv_routines.setLayoutManager(new GridLayoutManager(this, 2));
//        rv_routines.setLayoutManager(new GridLayoutManager(this, 1, 0, false));

        switch (_vids_type) {
            case "EXPERTS":
                setTitle(R.string._experts);
                videoAdapter = new rv_videoAdapter(YoutubeApiConfig.expertsVideos);
                break;
            case "PARENTS":
                setTitle(R.string._parent);
                videoAdapter = new rv_videoAdapter(YoutubeApiConfig.parentsVideos);
                break;
            case "MODULAR":
                videoAdapter = new rv_videoAdapter(YoutubeApiConfig.expertsVideos);
                break;
        }
        rv_videos.setAdapter(videoAdapter);
    }
}
