package com.simon.rememberwords.bean;

/**
 * Created by fengzimin  on  2018/07/02.
 * interface by
 */
public class WordBean {

    /**
     * word : spiral
     * chinese : 螺旋的
     * kind : adj
     */

    private String word;
    private String chinese;
    private String kind;

    public WordBean(String word, String chinese, String kind) {
        this.word = word;
        this.chinese = chinese;
        this.kind = kind;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    @Override
    public String toString() {
        return "WordBean{" +
                "word='" + word + '\'' +
                ", chinese='" + chinese + '\'' +
                ", kind='" + kind + '\'' +
                '}';
    }
}
