package com.simon.rememberwords.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.simon.rememberwords.R;
import com.simon.rememberwords.service.AudioService;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by fengzimin  on  2018/07/01.
 * interface by
 */
public class MainFragment extends Fragment {


    @InjectView(R.id.et_word)
    EditText mEtWord;
    private String mWrod;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWrod = "hello";
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


    @OnClick({R.id.btn_sound, R.id.btn_make_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_sound:
                Intent intent = new Intent(getActivity(), AudioService.class);
                intent.putExtra("query", mWrod);
                getActivity().startService(intent);
                break;
            case R.id.btn_make_sure:
                String s = mEtWord.getText().toString().trim();
                if(s.equals(mWrod)) {
                    Toast.makeText(getActivity(), "输入正确", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(), "输入错误", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}