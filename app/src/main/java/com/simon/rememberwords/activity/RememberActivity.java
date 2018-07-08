package com.simon.rememberwords.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.simon.rememberwords.App;
import com.simon.rememberwords.R;
import com.simon.rememberwords.WordsDao;
import com.simon.rememberwords.bean.Words;
import com.simon.rememberwords.service.AudioService;

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
    @InjectView(R.id.iv_states)
    ImageView mIvStates;
    private List<Words> mWordsList;
    private Words mWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remember);
        ButterKnife.inject(this);
        reset();
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
                intent.putExtra("query", mWords.getWord());
                this.startService(intent);
                break;
            case R.id.btn_make_sure://确定
                String s = mEtWord.getText().toString().trim();
                mIvStates.setVisibility(View.VISIBLE);
                if (s.equals(mWords.getWord())) {
                    mIvStates.setBackgroundResource(R.mipmap.chenggong);
                } else {
                    mIvStates.setBackgroundResource(R.mipmap.shibai);
                }

                mTvWord.setText(mWords.getWord());
                mTvChinese.setText(mWords.getChinese());
                mTvKind.setText(mWords.getKind());
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
        WordsDao wordsDao = App.getInstances().getDaoSession().getWordsDao();
        mWordsList = wordsDao.loadAll();
        int i = testRandom1(mWordsList.size());
        mWords = mWordsList.get(i);

        mTvWord.setText("");
        mTvChinese.setText("");
        mTvKind.setText("");
        mEtWord.setText("");
        mIvStates.setVisibility(View.INVISIBLE);
    }


}
