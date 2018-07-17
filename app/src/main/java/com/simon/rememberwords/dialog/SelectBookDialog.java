package com.simon.rememberwords.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.simon.rememberwords.R;
import com.simon.rememberwords.adapter.SelectBookAdapter;
import com.simon.rememberwords.utils.DialogUtil;

import java.util.List;

/**
 * Created by fengzimin  on  2018/07/13.
 * interface by
 */
public class SelectBookDialog extends Dialog {
    private final Context mContext;
    private SelectBookAdapter mSelectBookAdapter;

    public SelectBookDialog(@NonNull Context context) {
        this(context, 0);
    }

    public SelectBookDialog(@NonNull Context context, int themeResId) {
        super(context, R.style.CommonDialogStyle);
        mContext = context;
        DialogUtil.adjustDialogLayout(this, true, false);
        DialogUtil.setGravity(this, Gravity.CENTER);
        setContentView(R.layout.dialog_select_book);
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager
                .VERTICAL, false));
        mSelectBookAdapter = new SelectBookAdapter();
        recyclerView.setAdapter(mSelectBookAdapter);

        //添加单词本
        Button addBtn = findViewById(R.id.btn_add);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddBookNameDialog addBookNameDialog = new AddBookNameDialog(mContext);
                addBookNameDialog.show();
                addBookNameDialog.setOnClickListener(new AddBookNameDialog.OnClickListener() {
                    @Override
                    public void sureClick(String bookname) {

                    }
                });
            }
        });
        //确定
        Button sureBtn = findViewById(R.id.btn_sure);
        sureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void setData(List data) {
        mSelectBookAdapter.setData(data);
    }
}
