package com.simon.rememberwords.utils;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.simon.rememberwords.bean.WordBean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by fengzimin  on  2018/07/02.
 * interface by
 */
public class LocalJsonResolutionUtils {

    /**
     * 得到json文件中的内容
     * @param context
     * @param fileName
     * @return
     */
    public static String getJson(Context context, String fileName){
        StringBuilder stringBuilder = new StringBuilder();
        //获得assets资源管理器
        AssetManager assetManager = context.getAssets();
        //使用IO流读取json文件内容
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName),"utf-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * 将字符串转换为 对象
     * @param json
     * @param type
     * @return
     */
    public  static <T> T JsonToObject(String json, Class<T> type) {
        Gson gson =new Gson();
        return gson.fromJson(json, type);
    }


    private static Reader getJsonReader(Context context) {
        AssetManager assetManager = context.getAssets();
        try {
            InputStream is = assetManager.open("words.json");
            InputStreamReader reader = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(reader);
            return bufferedReader;
        } catch (IOException e) {
            e.printStackTrace();
            //不可达
            return null;
        }
    }

    // 解析
    public static ArrayList<WordBean> parse(Context context) {

        JsonReader reader = new JsonReader(getJsonReader(context));
        reader.setLenient(true);
        ArrayList<WordBean> wordBeans = new ArrayList<>();

        JsonArray jsonArray = new JsonParser().parse(reader).getAsJsonArray();
        Iterator<JsonElement> iterator = jsonArray.iterator();
        while (iterator.hasNext()) {
            JsonObject jsonObject = iterator.next().getAsJsonObject();
            String word = jsonObject.get("word").getAsString();
            String explain = jsonObject.get("explain").getAsString();
            String kind = jsonObject.get("bookName").getAsString();
            WordBean wordBean = new WordBean(word , explain, kind);
            wordBeans.add(wordBean);
        }
        return wordBeans;
    }
}
