package com.simon.rememberwords.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;

/**
 * Created by fengzimin  on  2018/07/01.
 * interface by
 */



@Entity(
        // Define indexes spanning multiple columns here.
        indexes = {
                @Index(value = "word DESC, bookName DESC", unique = true)
        }
)
public class Word {

    @Id(autoincrement = true)
    private Long id;
    private String word;//单词
    private String explain;//解释带类型
    private String bookName;//属于单词本
    private int rightNum;//对的次数
    private int wrongNum;//错的次数
    private int weight;//权重

    @Generated(hash = 81518382)
    public Word(Long id, String word, String explain, String bookName, int rightNum,
                int wrongNum, int weight) {
        this.id = id;
        this.word = word;
        this.explain = explain;
        this.bookName = bookName;
        this.rightNum = rightNum;
        this.wrongNum = wrongNum;
        this.weight = weight;
    }

    @Generated(hash = 3342184)
    public Word() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return this.word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getExplain() {
        return this.explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getBookName() {
        return this.bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getRightNum() {
        return this.rightNum;
    }

    public void setRightNum(int rightNum) {
        this.rightNum = rightNum;
    }

    public int getWrongNum() {
        return this.wrongNum;
    }

    public void setWrongNum(int wrongNum) {
        this.wrongNum = wrongNum;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }


}
