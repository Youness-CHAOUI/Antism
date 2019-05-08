package com.injaz2019.antism.classes.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.injaz2019.antism.R;
import com.injaz2019.antism.YoutubePlayerActivity;
import com.injaz2019.antism.classes.Metier.Video;

import java.util.ArrayList;


/**
 * Created by CY_15 on 28/04/2018.
 */
public class rv_videoAdapter extends RecyclerView.Adapter<rv_videoAdapter.myViewHolder> {
    ArrayList<Video> listeVideos;

    public rv_videoAdapter(ArrayList<Video> liste) {
        listeVideos = liste;
    }

    public void setlisteVideos(ArrayList<Video> listeVideos) {
        this.listeVideos = listeVideos;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vid_card_item, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(myViewHolder myviewholder, int position) {
        myviewholder.update(listeVideos.get(position));
    }

    @Override
    public int getItemCount() {
        return listeVideos.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder //implements View.OnCreateContextMenuListener//, View.OnContextClickListener
    {
        Context itemContext;
        TextView tv_id, tv_title, tv_duration, tv_path;

        public myViewHolder(final View itemView) {
            super(itemView);
            itemContext = itemView.getContext();

            tv_id = itemView.findViewById(R.id.tv_id);
            tv_path = itemView.findViewById(R.id.tv_path);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_duration = itemView.findViewById(R.id.tv_duration);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(itemContext, YoutubePlayerActivity.class);
                    i.putExtra("VID_PATH", tv_path.getText());
                    ((Activity) itemContext).startActivityForResult(i, 22);
                }
            });

//            itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    new AlertDialog.Builder(itemContext)
//                            .setTitle("La Video : "+tv_id.getText())
//                            .setMessage(tv_date.getText())
//                            .show();
//                    return false;
//                }
//            });
        }

        private void update(Video c) {
            tv_id.setText(String.valueOf(c.getId()));
            tv_title.setText(c.getTitle());
            tv_path.setText(c.getPath());
            tv_duration.setText(c.getDuration());
        }
    }
}
