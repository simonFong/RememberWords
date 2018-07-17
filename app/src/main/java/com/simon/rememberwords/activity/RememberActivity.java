package com.simon.rememberwords.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.simon.rememberwords.R;
import com.simon.rememberwords.adapter.TranslateAdapter;
import com.simon.rememberwords.base.BaseActivity;
import com.simon.rememberwords.bean.Word;
import com.simon.rememberwords.service.AudioService;
import com.simon.rememberwords.utils.YoudaoWrapper;
import com.simon.rememberwords.weight.Titlerbar;
import com.youdao.sdk.ydonlinetranslate.Translator;
import com.youdao.sdk.ydtranslate.Translate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class RememberActivity extends BaseActivity {

    @InjectView(R.id.titlebar)
    Titlerbar mTitlebar;
    @InjectView(R.id.btn_sound)
    Button mBtnSound;
    @InjectView(R.id.iv_states)
    ImageView mIvStates;
    @InjectView(R.id.tv_word)
    TextView mTvWord;
    @InjectView(R.id.tv_kind)
    TextView mTvKind;
    @InjectView(R.id.tv_chinese)
    TextView mTvChinese;
    @InjectView(R.id.recycler)
    RecyclerView mRecycler;
    @InjectView(R.id.et_word)
    EditText mEtWord;
    @InjectView(R.id.btn_make_sure)
    Button mBtnMakeSure;
    @InjectView(R.id.btn_next)
    Button mBtnNext;
    private List<Word> mWordsList;
    private Word mWords;
    private Translator translator;
    private TranslateAdapter mTranslateAdapter;
    private String mBook;

    /**
     * @param context
     * @param book    单词库的名字
     * @return
     */
    public static Intent getNewIntent(Context context, String book) {
        Intent intent = new Intent(context, RememberActivity.class);
        intent.putExtra("book", book);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remember);
        ButterKnife.inject(this);
        initView();
        initRecycler();
        reset();
    }

    private void initView() {
        Intent intent = getIntent();
        mBook = intent.getStringExtra("book");
        mTitlebar.setTitle(mBook);
        mTitlebar.mBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initRecycler() {
        mRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false));
        mTranslateAdapter = new TranslateAdapter();
        mRecycler.setAdapter(mTranslateAdapter);
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
                String replace = mWords.getWord().replace(" ", "");
                intent.putExtra("query", replace);
                this.startService(intent);
                break;
            case R.id.btn_make_sure://确定

                YoudaoWrapper.newInstance().translate(mWords.getWord()).setOnCallBack(new YoudaoWrapper.CallBack() {

                    @Override
                    public void onSingleCallBack(Translate translate) {

                        String s = mEtWord.getText().toString().trim();
                        mIvStates.setVisibility(View.VISIBLE);
                        if (s.equals(mWords.getWord())) {
                            mIvStates.setBackgroundResource(R.mipmap.chenggong);
                        } else {
                            mIvStates.setBackgroundResource(R.mipmap.shibai);
                        }

                        if (translate.getExplains() == null) {

                        } else {
                            mTranslateAdapter.setData(translate.getExplains());

                        }
                    }

                    @Override
                    public void onMultiCallBack(List<Translate> list) {

                    }
                });
                mTvWord.setText(mWords.getWord());
                break;
            case R.id.btn_next:
                reset();
                break;
        }
    }

    /**
     * 重置或更新
     */
    private void reset() {

        int i = testRandom1(mWordsList.size());
        mWords = mWordsList.get(i);
        mTvWord.setText("");
        mTranslateAdapter.setData(new ArrayList<String>());
        mEtWord.setText("");
        mTvChinese.setText("");
        mTvKind.setText("");
        mIvStates.setVisibility(View.INVISIBLE);
    }


}
