package com.simon.rememberwords.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.simon.rememberwords.R;
import com.simon.rememberwords.utils.DialogUtil;

/**
 * Created by fengzimin  on  2018/07/13.
 * interface by
 */
public class AddBookNameDialog extends Dialog {
    private final Context mContext;
    private OnClickListener mOnClickListener;

    public AddBookNameDialog(@NonNull Context context) {
        this(context, 0);
    }

    public AddBookNameDialog(@NonNull Context context, int themeResId) {
        super(context, R.style.CommonDialogStyle);
        mContext = context;
        DialogUtil.adjustDialogLayout(this, true, false);
        DialogUtil.setGravity(this, Gravity.CENTER);
        setContentView(R.layout.dialog_add_book_name);
        final EditText bookNameEt = findViewById(R.id.et_book_name);
        Button sureBtn = findViewById(R.id.btn_sure);
        Button cancelBtn = findViewById(R.id.btn_cancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        sureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnClickListener.sureClick(bookNameEt.getText().toString());
            }
        });

    }

    public interface OnClickListener {
        void sureClick(String bookname);
    }

    public void setOnClickListener(OnClickListener mOnClickChildListener) {
        this.mOnClickListener = mOnClickChildListener;
    }
}
