package com.simon.rememberwords.utils;

import android.os.Looper;
import android.util.Log;

import com.youdao.sdk.app.Language;
import com.youdao.sdk.app.LanguageUtils;
import com.youdao.sdk.ydonlinetranslate.Translator;
import com.youdao.sdk.ydtranslate.Translate;
import com.youdao.sdk.ydtranslate.TranslateErrorCode;
import com.youdao.sdk.ydtranslate.TranslateListener;
import com.youdao.sdk.ydtranslate.TranslateParameters;

import java.util.List;

import static com.lzy.okgo.utils.HttpUtils.runOnUiThread;

/**
 * Created by fengzimin  on  2018/07/13.
 * interface by
 */
public class YoudaoWrapper {
    private CallBack mCallBack;

    private static YoudaoWrapper instance = new YoudaoWrapper();


    public static YoudaoWrapper newInstance() {
        return instance;
    }

    public YoudaoWrapper translate(String code) {
        initYoudao(code);
        return instance;
    }

    private void initYoudao(String code) {
        //查词对象初始化，请设置source参数为app对应的名称（英文字符串）
        Language langFrom = LanguageUtils.getLangByName("中文");
        //若设置为自动，则查询自动识别源语言，自动识别不能保证完全正确，最好传源语言类型
        //Language langFrom = LanguageUtils.getLangByName("自动");
        Language langTo = LanguageUtils.getLangByName("英文");

        TranslateParameters tps = new TranslateParameters.Builder()
                .source("ydtranslate-demo")
                .from(langFrom).to(langTo).build();

        Translator translator = Translator.getInstance(tps);


        //查询，返回两种情况，一种是成功，相关结果存储在result参数中，另外一种是失败，失败信息放在TranslateErrorCode中，TranslateErrorCode
        // 是一个枚举类，整个查询是异步的，为了简化操作，回调都是在主线程发生。

        translator.lookup(code, "45338ded8d7c0f58", new TranslateListener() {

            @Override
            public void onError(TranslateErrorCode translateErrorCode, String s) {//查询失败
                Log.e("simon", "thresd：" + Looper.getMainLooper().getThread());
            }

            @Override
            public void onResult(final Translate translate, String s, String s1) {//查询成功
                //居然不是在ui线程
                boolean b = Thread.currentThread() == Looper.getMainLooper().getThread();
                Log.e("simon", "thresd：" + b);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mCallBack.onSingleCallBack(translate);
                    }
                });
            }

            @Override
            public void onResult(final List<Translate> list, List<String> list1,
                                 List<TranslateErrorCode> list2, String s) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mCallBack.onMultiCallBack(list);
                    }
                });
            }
        });
    }

    public interface CallBack {
        void onSingleCallBack(Translate translate);

        void onMultiCallBack(List<Translate> list);

    }

    public void setOnCallBack(CallBack callBack) {
        this.mCallBack = callBack;
    }

}
