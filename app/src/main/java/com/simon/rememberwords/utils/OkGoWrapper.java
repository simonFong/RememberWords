package com.simon.rememberwords.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.Converter;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okrx2.adapter.ObservableBody;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by fengzimin  on  2018/06/30.
 * interface by
 */
public class OkGoWrapper {

    private static class InstanceHolder {
        private static final OkGoWrapper sInstance = new OkGoWrapper();
    }

    public static OkGoWrapper instance() {
        return InstanceHolder.sInstance;
    }


    public <T> void get(String url, HttpHeaders httpHeaders, HttpParams
            httpParams,
                        final Class<T> clazz, Observer<T> observer) {
        OkGo.<T>get(url)//输入的url
                .headers(httpHeaders)
                .params(httpParams)//添加参数
                .converter(new Converter<T>() {
                    @Override
                    public T convertResponse(Response response) throws Throwable {//返回数据转gson
                        ResponseBody body = response.body();
                        if (body == null)
                            return null;
                        String s = body.string().toString();
                        Gson gson = new Gson();
                        T t = gson.fromJson(s, clazz);
                        return t;
                    }
                }).adapt(new ObservableBody<T>())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Log.e("simon", disposable.toString());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public <T> void post(String url, HttpHeaders httpHeaders, HttpParams
            httpParams,
                         final Class<T> clazz, Observer<T> observer) {
        OkGo.<T>post(url)//输入的url
                .headers(httpHeaders)
                .params(httpParams)//添加参数
                .converter(new Converter<T>() {
                    @Override
                    public T convertResponse(Response response) throws Throwable {//返回数据转gson
                        ResponseBody body = response.body();
                        if (body == null)
                            return null;
                        String s = body.string().toString();
                        Gson gson = new Gson();
                        T t = gson.fromJson(s, clazz);
                        return t;
                    }
                }).adapt(new ObservableBody<T>())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Log.e("simon", disposable.toString());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


}
