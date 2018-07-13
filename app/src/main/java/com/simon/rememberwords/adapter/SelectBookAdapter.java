package com.simon.rememberwords.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.simon.rememberwords.R;

import java.util.List;

/**
 * Created by fengzimin  on  2018/07/13.
 * interface by
 */
public class SelectBookAdapter extends RecyclerView.Adapter<SelectBookAdapter.ViewHolder> {
    private List<String> mData;
    private int mPosition = 0;

    public void setData(List<String> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_book, parent,
                false);
        SelectBookAdapter.ViewHolder viewHolder = new SelectBookAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.nameTv.setText(mData.get(position));
        holder.mSelectIv.setSelected(position == mPosition);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPosition = position;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mData == null) {
            return 0;
        }
        return mData.size() == 0 ? 0 : mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTv;
        private ImageView mSelectIv;

        public ViewHolder(View itemView) {
            super(itemView);
            mSelectIv = itemView.findViewById(R.id.iv_select);
            nameTv = itemView.findViewById(R.id.tv_book_name);
        }
    }


}
