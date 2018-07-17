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
    private String bookName;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public WordBean(String word, String explain, String kind) {
        this.word = word;
        this.explain = explain;
        this.bookName = kind;
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


    @Override
    public String toString() {
        return "WordBean{" +
                "word='" + word + '\'' +
                ", explain='" + explain + '\'' +
                ", bookName='" + bookName + '\'' +
                '}';
    }
}
