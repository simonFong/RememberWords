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
    private String explain;
    private String kind;

    public WordBean(String word, String explain, String kind) {
        this.word = word;
        this.explain = explain;
        this.kind = kind;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
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
                ", explain='" + explain + '\'' +
                ", kind='" + kind + '\'' +
                '}';
    }
}
