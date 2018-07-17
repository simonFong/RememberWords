package com.simon.rememberwords;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.simon.rememberwords.bean.Word;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "WORD".
*/
public class WordDao extends AbstractDao<Word, Long> {

    public static final String TABLENAME = "WORD";

    /**
     * Properties of entity Word.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Word = new Property(1, String.class, "word", false, "WORD");
        public final static Property Explain = new Property(2, String.class, "explain", false, "EXPLAIN");
        public final static Property BookName = new Property(3, String.class, "bookName", false, "BOOK_NAME");
        public final static Property RightNum = new Property(4, int.class, "rightNum", false, "RIGHT_NUM");
        public final static Property WrongNum = new Property(5, int.class, "wrongNum", false, "WRONG_NUM");
        public final static Property Weight = new Property(6, int.class, "weight", false, "WEIGHT");
    }


    public WordDao(DaoConfig config) {
        super(config);
    }
    
    public WordDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"WORD\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"WORD\" TEXT," + // 1: word
                "\"EXPLAIN\" TEXT," + // 2: explain
                "\"BOOK_NAME\" TEXT," + // 3: bookName
                "\"RIGHT_NUM\" INTEGER NOT NULL ," + // 4: rightNum
                "\"WRONG_NUM\" INTEGER NOT NULL ," + // 5: wrongNum
                "\"WEIGHT\" INTEGER NOT NULL );"); // 6: weight
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"WORD\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Word entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String word = entity.getWord();
        if (word != null) {
            stmt.bindString(2, word);
        }
 
        String explain = entity.getExplain();
        if (explain != null) {
            stmt.bindString(3, explain);
        }
 
        String bookName = entity.getBookName();
        if (bookName != null) {
            stmt.bindString(4, bookName);
        }
        stmt.bindLong(5, entity.getRightNum());
        stmt.bindLong(6, entity.getWrongNum());
        stmt.bindLong(7, entity.getWeight());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Word entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String word = entity.getWord();
        if (word != null) {
            stmt.bindString(2, word);
        }
 
        String explain = entity.getExplain();
        if (explain != null) {
            stmt.bindString(3, explain);
        }
 
        String bookName = entity.getBookName();
        if (bookName != null) {
            stmt.bindString(4, bookName);
        }
        stmt.bindLong(5, entity.getRightNum());
        stmt.bindLong(6, entity.getWrongNum());
        stmt.bindLong(7, entity.getWeight());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Word readEntity(Cursor cursor, int offset) {
        Word entity = new Word( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // word
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // explain
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // bookName
            cursor.getInt(offset + 4), // rightNum
            cursor.getInt(offset + 5), // wrongNum
            cursor.getInt(offset + 6) // weight
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Word entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setWord(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setExplain(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setBookName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setRightNum(cursor.getInt(offset + 4));
        entity.setWrongNum(cursor.getInt(offset + 5));
        entity.setWeight(cursor.getInt(offset + 6));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Word entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Word entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Word entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
