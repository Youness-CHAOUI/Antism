package com.injaz2019.antism.classes.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.injaz2019.antism.R;
import com.injaz2019.antism.RoutineDetailsActivity;
import com.injaz2019.antism.classes.Metier.Routine;

import java.util.ArrayList;


/**
 * Created by CY_15 on 28/04/2018.
 */
public class rv_routineAdapter extends RecyclerView.Adapter<rv_routineAdapter.myViewHolder>
{
    ArrayList<Routine> listeRoutines;

    public rv_routineAdapter(ArrayList<Routine> liste)
    {
        listeRoutines=liste;
    }

    public void setlisteRoutines(ArrayList<Routine> listeRoutines)
    {
        this.listeRoutines = listeRoutines;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.routine_card_item, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(myViewHolder myviewholder, int position)
    {
        myviewholder.update(listeRoutines.get(position));
    }

    @Override
    public int getItemCount() {
        return listeRoutines.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder //implements View.OnCreateContextMenuListener//, View.OnContextClickListener
    {
        TextView tv_id, tv_date, tv_desc;
        final Context itemContext;

        public myViewHolder(final View itemView)
        {
            super(itemView);
            itemContext = itemView.getContext();

            tv_id = itemView.findViewById(R.id.tv_id2);
            tv_date = itemView.findViewById(R.id.tv_date2);
            tv_desc = itemView.findViewById(R.id.tv_desc2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(itemContext, RoutineDetailsActivity.class);
                    i.putExtra("idRoutine", Integer.parseInt(tv_id.getText().toString()));
                    itemContext.startActivity(i);
                }
            });
//            itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    new AlertDialog.Builder(itemContext)
//                            .setTitle("La Routine : "+tv_id.getText())
//                            .setMessage(tv_date.getText())
//                            .show();
//                    return false;
//                }
//            });
        }

        private void update(Routine c)
        {
            tv_id.setText(String.valueOf(c.getId()));
            tv_date.setText(c.getDate());
            tv_desc.setText(c.getDesc());
        }
    }
}
