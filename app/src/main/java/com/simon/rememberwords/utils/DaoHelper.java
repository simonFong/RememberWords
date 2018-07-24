package com.simon.rememberwords.utils;

import com.simon.rememberwords.App;
import com.simon.rememberwords.bean.Book;
import com.simon.rememberwords.bean.Word;
import com.simon.rememberwords.bean.WordBean;
import com.simon.rememberwords.db.BookDao;
import com.simon.rememberwords.db.WordDao;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by fengzimin  on  2018/07/17.
 * interface by
 */
public class DaoHelper {

    /**
     * 新增单词本数据库
     *
     * @param words
     */
    public static void insertWord(List<WordBean> words) {
        //导入数据库表单词
        for (int i = 0; i < words.size(); i++) {
            WordBean wordBean = words.get(i);
            Word word = new Word();
            word.setWord(wordBean.getWord());
            word.setBookName(wordBean.getBookName());
            word.setExplain(wordBean.getExplain());
            word.setRightNum(0);
            word.setRightRate(0);
            word.setWrongNum(0);
            word.setWrongRate(0);
            word.setWeight(1);
            App.getInstances().getDaoSession().insertOrReplace(word);
        }
        insertBook();
    }

    /**
     * 新增或修改单词本数据库
     */
    public static void insertBook() {
        List<Word> words = App.getInstances().getDaoSession().getWordDao().loadAll();
        //导入数据库表单词本
        Book book = new Book();
        //设置单词本名
        book.setBookname(words.get(0).getBookName());
        //设置单词总数
        book.setSumWordNum(words.size());
        //计算通过率，总权重
        int passNum = 0;
        int sumWeight = 0;
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).getRightRate() > 0) {
                passNum++;
            }
            sumWeight = sumWeight + words.get(i).getWeight();
        }
        double passRate = 0;
//        passRate = (double) passNum / words.size();
        //设置通过率
        double div = div((double) passNum, (double) words.size(), 2);
        book.setPassRate(div);
        //设置总权重
        book.setSumWeight(sumWeight);
        App.getInstances().getDaoSession().insertOrReplace(book);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 新增自定义单词本
     *
     * @param bookname
     */
    public static void insertBook(String bookname) {
        //导入数据库表单词本
        Book book = new Book();
        //设置单词本名
        book.setBookname(bookname);
        //设置单词总数
        book.setSumWordNum(0);
        //计算通过率，总权重
        int passNum = 0;
        int sumWeight = 0;
        double passRate = 0;
        //设置通过率
        book.setPassRate(passRate);
        //设置总权重
        book.setSumWeight(sumWeight);
        App.getInstances().getDaoSession().insertOrReplace(book);
    }


    /**
     * 获取单词本列表
     *
     * @return
     */
    public static List<Book> getBooksList() {
        BookDao bookDao = App.getInstances().getDaoSession().getBookDao();
        List<Book> books = bookDao.loadAll();
        return books;
    }

    /**
     * 获取单词本
     *
     * @param bookname 单词本名称
     * @return
     */
    public static Book getBook(String bookname) {
        List<Book> list = App.getInstances().getDaoSession().getBookDao().queryBuilder().where
                (BookDao.Properties.Bookname.eq(bookname)).list();
        Book book = list.get(0);
        return book;
    }

    /**
     * 获取单词本里的单词列表
     *
     * @param bookname 单词本名称
     * @return
     */
    public static List<Word> getWordListFromBook(String bookname) {
        WordDao wordDao = App.getInstances().getDaoSession().getWordDao();
        List<Word> list = wordDao.queryBuilder().where(WordDao.Properties.BookName.eq(bookname))
                .list();
        return list;
    }

    /**
     * 更新数据库单词
     *
     * @param wordName 单词名
     * @return
     */
    public static Word upDataWord(String wordName, boolean isRight) {
        WordDao wordDao = App.getInstances().getDaoSession().getWordDao();
        List<Word> list = wordDao.queryBuilder().where(WordDao.Properties.Word.eq(wordName)).list();
        Word word = list.get(0);
        if (isRight) {
            //修改对的次数
            word.setRightNum(word.getRightNum() + 1);
        } else {
            //修改错的次数
            word.setWrongNum(word.getWrongNum() + 1);
        }
        int sumNum = word.getRightNum() + word.getWrongNum();
        //修改正确率
        word.setRightRate((double) word.getRightNum() / sumNum);
        //修改错误率
        word.setWrongRate((double) word.getWrongNum() / sumNum);
        wordDao.insertOrReplace(word);
        insertBook();
        return list.get(0);
    }
}
