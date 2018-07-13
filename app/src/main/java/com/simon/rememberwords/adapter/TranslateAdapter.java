package com.simon.rememberwords.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.simon.rememberwords.R;

import java.util.List;

/**
 * Created by simon on 2018/7/5.
 */

public class TranslateAdapter extends RecyclerView.Adapter<TranslateAdapter.ViewHolder> {
    private List<String> mData;

    public void setData(List<String> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_translate,
                parent, false);
        TranslateAdapter.ViewHolder viewHolder = new TranslateAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.translationTv.setText(mData.get(position));

    }

    @Override
    public int getItemCount() {
        if (mData == null) {
            return 0;
        }
        return mData.size() == 0 ? 0 : mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView translationTv;

        public ViewHolder(View itemView) {
            super(itemView);
            translationTv = itemView.findViewById(R.id.tv_translation);
        }
    }

}
