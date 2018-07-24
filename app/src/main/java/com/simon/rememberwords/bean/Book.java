package com.simon.rememberwords.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;

/**
 * Created by fengzimin  on  2018/07/23.
 * interface by
 */

@Entity(   indexes = {
        @Index(value = "bookname DESC", unique = true)})
public class Book {

    @Id(autoincrement = true)
    private Long id;
    private String bookname;//单词本名称
    private int sumWordNum;//单词总数
    private double passRate;//通过率
    private int sumWeight;//总权重

    @Generated(hash = 1511043636)
    public Book(Long id, String bookname, int sumWordNum, double passRate,
                int sumWeight) {
        this.id = id;
        this.bookname = bookname;
        this.sumWordNum = sumWordNum;
        this.passRate = passRate;
        this.sumWeight = sumWeight;
    }

    @Generated(hash = 1839243756)
    public Book() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookname() {
        return this.bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public int getSumWordNum() {
        return this.sumWordNum;
    }

    public void setSumWordNum(int sumWordNum) {
        this.sumWordNum = sumWordNum;
    }

    public double getPassRate() {
        return this.passRate;
    }

    public void setPassRate(double passRate) {
        this.passRate = passRate;
    }

    public int getSumWeight() {
        return this.sumWeight;
    }

    public void setSumWeight(int sumWeight) {
        this.sumWeight = sumWeight;
    }
}
