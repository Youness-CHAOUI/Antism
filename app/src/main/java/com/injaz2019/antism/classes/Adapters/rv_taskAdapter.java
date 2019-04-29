package com.injaz2019.antism.classes.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.injaz2019.antism.R;
import com.injaz2019.antism.TacheDetailsActivity;
import com.injaz2019.antism.classes.Metier.Tache;

import java.util.ArrayList;


/**
 * Created by CY_15 on 28/04/2018.
 */
public class rv_taskAdapter extends RecyclerView.Adapter<rv_taskAdapter.myViewHolder> {
    ArrayList<Tache> listeTaches;

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
        myviewholder.update(listeTaches.get(position));
    }

    @Override
    public int getItemCount() {
        return listeTaches.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder //implements View.OnCreateContextMenuListener//, View.OnContextClickListener
    {
        final Context itemContext;
        TextView tv_id, tv_date, tv_desc;

        public myViewHolder(final View itemView) {
            super(itemView);
            itemContext = itemView.getContext();

            tv_id = itemView.findViewById(R.id.tv_id);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_desc = itemView.findViewById(R.id.tv_desc);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(itemContext, TacheDetailsActivity.class);
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

        private void update(Tache c) {
            tv_id.setText(String.valueOf(c.getId()));
            tv_date.setText(c.getHeure());
            String nb1 = "أنشطة صباحية: " + c.getIsDone();
            String nb2 = " - أنشطة مسائية: " + c.getRate();
            tv_desc.setText(nb1 + nb2);
        }
    }
}
