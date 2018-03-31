package com.multipz.puzzle;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Admin on 10-01-2018.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {
    private Context context;
    private List<PuzzleModel> list;
    private ItemClickListener clickListener;


    public NewsAdapter(List<PuzzleModel> expertsList, Context context) {
        this.list = expertsList;
        this.context = context;
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public Button btn1;


        public MyViewHolder(View view) {
            super(view);
            btn1 = (Button) view.findViewById(R.id.btn1);
            //itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) {
                clickListener.itemClicked(view, getAdapterPosition());
            }
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lawyer_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        PuzzleModel model = list.get(position);
        if (model.getNo() == -1) {
            holder.btn1.setText("?");
            holder.btn1.setOnClickListener(holder);
        } else {
            holder.btn1.setText(model.getNo() + "");
        }

        if (model.isEnable) {
            holder.btn1.setClickable(true);
        } else {
            holder.btn1.setClickable(false);
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
