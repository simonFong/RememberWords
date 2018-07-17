package com.simon.rememberwords.utils;

import com.simon.rememberwords.App;
import com.simon.rememberwords.DaoSession;

/**
 * Created by fengzimin  on  2018/07/17.
 * interface by
 */
public class WordDaoHelper {

    private final DaoSession mDaoSession;

    public WordDaoHelper() {
        mDaoSession = App.getInstances().getDaoSession();
    }

    public void saveWord(){


    }
}
