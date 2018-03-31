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
 * Created by Admin on 08-12-2017.
 */

public class BookMarkAdapter extends RecyclerView.Adapter<BookMarkAdapter.MyViewHolder> {
    private List<DataModel> list;
    private Context context;
    private BookMarkAdapter.ClickListener clickListener;

    public interface ClickListener {
        void onItemClick(View view, int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txt_data, txt_category;

        public MyViewHolder(View view) {
            super(view);
            txt_data = (TextView) view.findViewById(R.id.txt_data);
            txt_category = (TextView) view.findViewById(R.id.txt_category);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(view, getAdapterPosition());
        }
    }


    public BookMarkAdapter(Context context, List<DataModel> list) {
        this.list = list;
        this.context = context;
    }

    public void setClickListener(BookMarkAdapter.ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public BookMarkAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_data_list, parent, false);

        return new BookMarkAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BookMarkAdapter.MyViewHolder holder, int position) {
        DataModel model = list.get(position);
        holder.txt_data.setText(model.getNo());
        holder.txt_category.setText(model.getPlay_category());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
