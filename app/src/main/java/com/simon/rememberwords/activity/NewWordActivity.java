package com.simon.rememberwords.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.simon.rememberwords.App;
import com.simon.rememberwords.R;
import com.simon.rememberwords.db.WordDao;
import com.simon.rememberwords.adapter.TranslateAdapter;
import com.simon.rememberwords.base.BaseActivity;
import com.simon.rememberwords.bean.Word;
import com.simon.rememberwords.bean.WordBean;
import com.simon.rememberwords.utils.YoudaoWrapper;
import com.simon.rememberwords.weight.Titlerbar;
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
    @InjectView(R.id.titlebar)
    Titlerbar mTitlebar;
    private TranslateAdapter mAdapter;
    private String mBookname;
    private String mWord;
    private Translate mTranslate;


    /**
     * @param context
     * @param bookname 单词本名称
     * @return
     */
    public static Intent getNewIntent(Context context, String bookname) {
        Intent intent = new Intent(context, NewWordActivity.class);
        intent.putExtra("bookname", bookname);
        return intent;

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);
        ButterKnife.inject(this);
        mBtnAdd.setClickable(false);
        initData();
        initView();
        initRecycler();
    }

    private void initData() {
        Intent intent = getIntent();
        mBookname = intent.getStringExtra("bookname");
    }

    private void initView() {
        mTitlebar.leftExit(this);

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
                mWord = mEtWord.getText().toString();
                if (mWord.equals("")) {
                    Toast.makeText(NewWordActivity.this, "请输入单词", Toast.LENGTH_SHORT).show();
                    return;
                }
                YoudaoWrapper.newInstance().translate(mWord).setOnCallBack(new YoudaoWrapper
                        .CallBack() {

                    @Override
                    public void onSingleCallBack(Translate translate) {
                        mTvWord.setText(mWord);
                        mTranslate = translate;
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
                addWord();
                break;
        }
    }

    private void addWord() {
        WordBean wordBean = new WordBean(mWord, mTranslate.getExplains().get(0), mBookname);
        Word word = new Word();
        word.setWord(wordBean.getWord());
        word.setBookName(wordBean.getBookName());
        word.setExplain(wordBean.getExplain());
        WordDao wordDao = App.getInstances().getDaoSession().getWordDao();
        wordDao.insertOrReplace(word);

        Toast.makeText(NewWordActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
    }
}
