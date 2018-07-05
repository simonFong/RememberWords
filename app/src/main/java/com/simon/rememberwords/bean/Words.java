package com.simon.rememberwords.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by fengzimin  on  2018/07/01.
 * interface by
 */
@Entity
public class Words {

    @Id(autoincrement = true)
    private Long id;
    private String word;
    private String chinese;
    private String kind;
    private String bookName;
    private int rightNum;
    private int wrongNum;

    @Generated(hash = 1083551447)
    public Words(Long id, String word, String chinese, String kind, String bookName,
            int rightNum, int wrongNum) {
        this.id = id;
        this.word = word;
        this.chinese = chinese;
        this.kind = kind;
        this.bookName = bookName;
        this.rightNum = rightNum;
        this.wrongNum = wrongNum;
    }
    @Generated(hash = 796553661)
    public Words() {
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
    public String getChinese() {
        return this.chinese;
    }
    public void setChinese(String chinese) {
        this.chinese = chinese;
    }
    public String getKind() {
        return this.kind;
    }
    public void setKind(String kind) {
        this.kind = kind;
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


}
