package com.injaz2019.antism.classes.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.injaz2019.antism.R;
import com.injaz2019.antism.ShowTaskActivity;
import com.injaz2019.antism.classes.Metier.Tache;

import java.util.ArrayList;
import java.util.Random;


/**
 * Created by CY_15 on 28/04/2018.
 */
public class rv_taskAdapter extends RecyclerView.Adapter<rv_taskAdapter.myViewHolder> {
    ArrayList<Tache> listeTaches;
    private String[] _colors = {"#90A4AE", "#FF3D00", "#FFEA00", "#C6FF00", "#00E676",
            "#40C4FF", "#1DE9B6", "#B388FF", "#FF1744", "#FFB74D",
            "#66cdaa", "#ff4040", "#3399ff", "#ff1493", "#ccff00", "#d3ffce"};

    public rv_taskAdapter(ArrayList<Tache> liste) {
        listeTaches = liste;
    }

    public void setlisteTaches(ArrayList<Tache> listeTaches) {
        this.listeTaches = listeTaches;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tache_card_item, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(myViewHolder myviewholder, int position) {
        myviewholder.update(listeTaches.get(position), position);
    }

    @Override
    public int getItemCount() {
        return listeTaches.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder //implements View.OnCreateContextMenuListener//, View.OnContextClickListener
    {
        final Context itemContext;
        TextView tv_id, tv_date, tv_nb;
        ProgressBar progressBar1;
//        CardView card_view_task;

        public myViewHolder(final View itemView) {
            super(itemView);
            itemContext = itemView.getContext();

            tv_id = itemView.findViewById(R.id.tv_id);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_nb = itemView.findViewById(R.id.tv_nb);
            progressBar1 = itemView.findViewById(R.id.progressBar1);
//            card_view_task = itemView.findViewById(R.id.card_view_task);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    progressBar1.setVisibility(View.VISIBLE);
                    Intent i = new Intent(itemContext, ShowTaskActivity.class);
                    i.putExtra("ID_TACHE", Integer.parseInt(tv_id.getText().toString()));
                    itemContext.startActivity(i);
                }
            });
//            itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    new AlertDialog.Builder(itemContext)
//                            .setTitle("La Tache : "+tv_id.getText())
//                            .setMessage(tv_date.getText())
//                            .show();
//                    return false;
//                }
//            });
        }

        private void update(Tache c, int pos) {
            tv_id.setText(String.valueOf(c.getId()));
            tv_date.setText(c.getHeure());
            tv_nb.setText(String.valueOf(pos + 1));

            tv_nb.setBackgroundColor(Color.parseColor(_colors[new Random().nextInt(16)]));
        }
    }
}
