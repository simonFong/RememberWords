package com.simon.rememberwords.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.simon.rememberwords.App;
import com.simon.rememberwords.R;
import com.simon.rememberwords.adapter.CommonPagerAdapter;
import com.simon.rememberwords.base.BaseActivity;
import com.simon.rememberwords.bean.Word;
import com.simon.rememberwords.bean.WordBean;
import com.simon.rememberwords.fragment.MainFragment;
import com.simon.rememberwords.fragment.SettingFragment;
import com.simon.rememberwords.utils.LocalJsonResolutionUtils;
import com.simon.rememberwords.utils.OtherSpDataHelper;
import com.yinglan.alphatabs.AlphaTabView;
import com.yinglan.alphatabs.AlphaTabsIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.viewpager)
    ViewPager mViewpager;
    @InjectView(R.id.tab_remember)
    AlphaTabView mTabRemember;
    @InjectView(R.id.tab_setting)
    AlphaTabView mTabSetting;
    @InjectView(R.id.alphaIndicator)
    AlphaTabsIndicator mAlphaIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initView();
    }

    /**
     * 获取数据，暂时只有一个本地json文件，以后或者会使用后台更新
     */
    private void initData() {
        setDatabase();
    }

    private void setDatabase() {
        List<String> books = new ArrayList();
        books.add("Book1");
        OtherSpDataHelper.saveBookNameList(books);

        List<WordBean> parse = LocalJsonResolutionUtils.parse(MainActivity.this);
        for (int i = 0; i < parse.size(); i++) {
            WordBean wordBean = parse.get(i);
            Word word = new Word();
            word.setWord(wordBean.getWord());
            word.setBookName("Book1");
            App.getInstances().getDaoSession().insertOrReplace(word);
        }
    }

    private void initView() {
        List<Fragment> mFragments = new ArrayList<>();
        mFragments.add(new MainFragment());
        mFragments.add(new SettingFragment());
        CommonPagerAdapter mCommonPagerAdapter =
                new CommonPagerAdapter(getSupportFragmentManager(), mFragments);
        mViewpager.setOffscreenPageLimit(2);
        mViewpager.setAdapter(mCommonPagerAdapter);
        mAlphaIndicator.setViewPager(mViewpager);
    }
}
