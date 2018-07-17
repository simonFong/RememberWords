package com.simon.rememberwords.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.simon.rememberwords.R;
import com.simon.rememberwords.adapter.MainAdapter;
import com.simon.rememberwords.base.BaseActivity;
import com.simon.rememberwords.dialog.AddBookNameDialog;
import com.simon.rememberwords.utils.OtherSpDataHelper;
import com.simon.rememberwords.weight.Titlerbar;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by fengzimin  on  2018/07/17.
 * interface by
 */
public class SelectBookActivity extends BaseActivity {
    @InjectView(R.id.recycler)
    RecyclerView mRecycler;
    @InjectView(R.id.titlebar)
    Titlerbar mTitlebar;
    private MainAdapter mainAdapter;
    private List<String> mBookNameList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_book);
        ButterKnife.inject(this);
        initView();
        initRecycler();
        initData();
    }

    private void initView() {
        mTitlebar.leftExit(this);
        mTitlebar.mRightTextTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AddBookNameDialog addBookNameDialog = new AddBookNameDialog(SelectBookActivity
                        .this);
                addBookNameDialog.show();
                addBookNameDialog.setOnClickListener(new AddBookNameDialog.OnClickListener() {
                    @Override
                    public void sureClick(String bookname) {
                        mBookNameList.add(bookname);
                        OtherSpDataHelper.saveBookNameList(mBookNameList);
                        initData();
                        addBookNameDialog.dismiss();
                    }
                });
            }
        });
    }

    private void initData() {
        mBookNameList = OtherSpDataHelper.getBookNameList();
        mainAdapter.setData(mBookNameList);
    }

    private void initRecycler() {
        mRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager
                .VERTICAL, false));
        mainAdapter = new MainAdapter();
        mRecycler.setAdapter(mainAdapter);
        mainAdapter.setOnitemClickListener(new MainAdapter.ItemClickListener() {
            @Override
            public void itemClick(int position) {
                Intent newIntent = NewWordActivity.getNewIntent(SelectBookActivity.this,
                        mainAdapter.getItem(position));
                startActivity(newIntent);
            }
        });
    }
}
