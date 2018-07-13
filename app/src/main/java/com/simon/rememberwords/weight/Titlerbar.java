package com.simon.rememberwords.weight;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.simon.rememberwords.R;

/**
 * Created by fengzimin  on  2018/07/13.
 * interface by
 */
public class Titlerbar extends LinearLayout {

    private String mTitle;
    private int mBg;
    private Context mContext;
    public ImageView mBackIv;
    public TextView mRightTextTv;
    private boolean mShowRightText;
    private String mRightText;
    private TextView mTitleTv;

    public Titlerbar(Context context) {
        super(context);
    }

    public Titlerbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Titlerbar);
        mTitle = typedArray.getString(R.styleable.Titlerbar_title);
        mBg = typedArray.getColor(R.styleable.Titlerbar_bg, -1);
        mShowRightText = typedArray.getBoolean(R.styleable.Titlerbar_show_right_text, false);
        mRightText = typedArray.getString(R.styleable.Titlerbar_right_text);
        initView();
    }

    private void initView() {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.layout_titler_bar, this,
                true);
        mTitleTv = inflate.findViewById(R.id.tv_title);
        RelativeLayout bgRl = inflate.findViewById(R.id.rl_bg);
        mBackIv = inflate.findViewById(R.id.iv_back);
        mRightTextTv = inflate.findViewById(R.id.tv_right_text);
        mRightTextTv.setVisibility(mShowRightText ? VISIBLE : GONE);
        mRightTextTv.setText(mRightText);
        mTitleTv.setText(mTitle);
        if (mBg != -1) {
            bgRl.setBackgroundResource(mBg);
        }
    }

    public void setTitle(String title) {
        mTitleTv.setText(title);
    }
}
