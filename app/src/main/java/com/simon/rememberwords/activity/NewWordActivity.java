package com.simon.rememberwords.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.simon.rememberwords.R;
import com.simon.rememberwords.adapter.TranslateAdapter;
import com.simon.rememberwords.base.BaseActivity;
import com.simon.rememberwords.utils.YoudaoWrapper;
import com.youdao.sdk.ydtranslate.Translate;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by fengzimin  on  2018/07/13.
 * interface by
 */
public class NewWordActivity extends BaseActivity {

    @InjectView(R.id.et_word)
    EditText mEtWord;
    @InjectView(R.id.btn_cheak)
    Button mBtnCheak;
    @InjectView(R.id.tv_word)
    TextView mTvWord;
    @InjectView(R.id.tv_kind)
    TextView mTvKind;
    @InjectView(R.id.tv_chinese)
    TextView mTvChinese;
    @InjectView(R.id.recycler)
    RecyclerView mRecycler;
    @InjectView(R.id.btn_add)
    Button mBtnAdd;
    private TranslateAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);
        ButterKnife.inject(this);
        mBtnAdd.setClickable(false);
        initRecycler();
    }

    private void initRecycler() {
        mRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false));
        mAdapter = new TranslateAdapter();
        mRecycler.setAdapter(mAdapter);
    }


    @OnClick({R.id.btn_cheak, R.id.btn_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_cheak:
                final String s = mEtWord.getText().toString();
                if (s.equals("")) {
                    Toast.makeText(NewWordActivity.this, "请输入单词", Toast.LENGTH_SHORT).show();
                    return;
                }
                YoudaoWrapper.newInstance().translate(s).setOnCallBack(new YoudaoWrapper.CallBack
                        () {

                    @Override
                    public void onSingleCallBack(Translate translate) {
                        mTvWord.setText(s);
                        if (translate.getExplains() != null) {
                            mAdapter.setData(translate.getExplains());
                            mBtnAdd.setClickable(true);
                        } else {
                            List<String> strings = new ArrayList<>();
                            strings.add("暂无此单词，请检查是否输入错误");
                            mAdapter.setData(strings);
                            mBtnAdd.setClickable(false);
                        }
                    }

                    @Override
                    public void onMultiCallBack(List<Translate> list) {

                    }
                });
                break;
            case R.id.btn_add:

                break;
        }
    }
}
