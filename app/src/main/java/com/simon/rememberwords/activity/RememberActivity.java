package com.simon.rememberwords.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.simon.rememberwords.R;
import com.simon.rememberwords.bean.WordBean;
import com.simon.rememberwords.service.AudioService;
import com.simon.rememberwords.utils.LocalJsonResolutionUtils;

import java.util.List;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class RememberActivity extends AppCompatActivity {
    @InjectView(R.id.et_word)
    EditText mEtWord;
    @InjectView(R.id.btn_sound)
    Button mBtnSound;
    @InjectView(R.id.btn_make_sure)
    Button mBtnMakeSure;
    @InjectView(R.id.tv_word)
    TextView mTvWord;
    @InjectView(R.id.tv_chinese)
    TextView mTvChinese;
    @InjectView(R.id.tv_kind)
    TextView mTvKind;
    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.btn_next)
    Button btnNext;
    private List<WordBean> mWordBeans;
    private WordBean mWordBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remember);
        ButterKnife.inject(this);
        initData();
        reset();
    }

    /**
     * 导入数据
     */
    private void initData() {
        mWordBeans = LocalJsonResolutionUtils.parse(this);

    }

    //生成随机数
    private int testRandom1(int size) {
        Random random = new Random();
        int i = random.nextInt(size);
        return i;
    }


    @OnClick({R.id.btn_sound, R.id.btn_make_sure, R.id.btn_next, R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_sound://发音
                Intent intent = new Intent(this, AudioService.class);
                intent.putExtra("query", mWordBean.getWord());
                this.startService(intent);
                break;
            case R.id.btn_make_sure://确定
                String s = mEtWord.getText().toString().trim();
                if (s.equals(mWordBean.getWord())) {
                    Toast.makeText(this, "输入正确", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "输入错误", Toast.LENGTH_SHORT).show();
                }

                mTvWord.setText(mWordBean.getWord());
                mTvChinese.setText(mWordBean.getChinese());
                mTvKind.setText(mWordBean.getKind());
                break;
            case R.id.btn_next:
                reset();
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    /**
     * 重置或更新
     */
    private void reset() {
        int i = testRandom1(mWordBeans.size());
        Log.e("simon", mWordBeans.get(i).toString());
        mWordBean = mWordBeans.get(i);

        mTvWord.setText("");
        mTvChinese.setText("");
        mTvKind.setText("");
        mEtWord.setText("");
    }


}
