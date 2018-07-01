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

    @Id
    private Long id;
    private String word;
    private String chinese;
    @Generated(hash = 1900685935)
    public Words(Long id, String word, String chinese) {
        this.id = id;
        this.word = word;
        this.chinese = chinese;
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
}
