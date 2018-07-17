package com.simon.rememberwords.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simon.rememberwords.R;
import com.simon.rememberwords.activity.RememberActivity;
import com.simon.rememberwords.activity.SelectBookActivity;
import com.simon.rememberwords.adapter.MainAdapter;
import com.simon.rememberwords.utils.OtherSpDataHelper;
import com.simon.rememberwords.weight.Titlerbar;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by fengzimin  on  2018/07/01.
 * interface by
 */
public class MainFragment extends Fragment {


    @InjectView(R.id.recycler)
    RecyclerView recycler;
    @InjectView(R.id.titlebar)
    Titlerbar mTitlebar;
    private MainAdapter mainAdapter;
    private List<String> mBookNameList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_main,
                container, false);
        ButterKnife.inject(this, inflate);
        return inflate;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }


    private void initData() {
        mBookNameList = OtherSpDataHelper.getBookNameList();
        mainAdapter.setData(mBookNameList);

    }

    private void initView() {
        mTitlebar.mBackIv.setVisibility(View.GONE);
        mTitlebar.mRightTextTv.setText("自定义");
        mTitlebar.mRightTextTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SelectBookActivity.class);
                startActivity(intent);
            }
        });

        recycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager
                .VERTICAL, false));
        mainAdapter = new MainAdapter();
        recycler.setAdapter(mainAdapter);
        mainAdapter.setOnitemClickListener(new MainAdapter.ItemClickListener() {
            @Override
            public void itemClick(int position) {
                String item = mainAdapter.getItem(position);
                Intent newIntent = RememberActivity.getNewIntent(getActivity(), item);
                startActivity(newIntent);


            }
        });
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
