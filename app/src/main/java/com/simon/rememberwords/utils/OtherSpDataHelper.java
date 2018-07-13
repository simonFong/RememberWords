package com.simon.rememberwords.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuyufeng    on  2018/4/10 0010.
 * interface by
 * 用于其他需要存储的轻量信息
 */

public class OtherSpDataHelper {
    private static final String BOOK_NAME = "book_name";//附近的人，记录显示男还是女，还是全部

    /**
     * 存单词本列表
     *
     * @param datalist
     */
    public static void saveBookNameList(List<String> datalist) {
        if (null == datalist || datalist.size() <= 0)
            return;

        Gson gson = new Gson();
        //转换成json数据，再保存
        String strJson = gson.toJson(datalist);
        PrefUtil.getDefault().putString(BOOK_NAME, strJson).apply();
    }

    /**
     * 取单词本列表
     */
    public static List<String> getBookNameList() {
        List<String> datalist = new ArrayList<>();
        String strJson = PrefUtil.getDefault().getString(BOOK_NAME, null);
        if (null == strJson) {
            return datalist;
        }
        Gson gson = new Gson();
        datalist = gson.fromJson(strJson, new TypeToken<List<String>>() {
        }.getType());
        return datalist;
    }


}
