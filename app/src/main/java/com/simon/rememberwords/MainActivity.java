package com.simon.rememberwords;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.simon.rememberwords.adapter.CommonPagerAdapter;
import com.simon.rememberwords.base.BaseActivity;
import com.simon.rememberwords.fragment.MainFragment;
import com.simon.rememberwords.fragment.SettingFragment;
import com.simon.rememberwords.fragment.WrongWordsFragment;
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
    @InjectView(R.id.tab_code_wrong)
    AlphaTabView mTabCodeWrong;
    @InjectView(R.id.tab_setting)
    AlphaTabView mTabSetting;
    @InjectView(R.id.alphaIndicator)
    AlphaTabsIndicator mAlphaIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initView();
    }

    private void initView() {
        List<Fragment> mFragments = new ArrayList<>();
        mFragments.add(new MainFragment());
        mFragments.add(new WrongWordsFragment());
        mFragments.add(new SettingFragment());
        CommonPagerAdapter mCommonPagerAdapter =
                new CommonPagerAdapter(getSupportFragmentManager(), mFragments);
        mViewpager.setOffscreenPageLimit(2);
        mViewpager.setAdapter(mCommonPagerAdapter);
        mAlphaIndicator.setViewPager(mViewpager);
    }
}
