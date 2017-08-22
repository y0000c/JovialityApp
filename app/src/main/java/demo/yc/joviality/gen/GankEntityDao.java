package demo.yc.joviality.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import demo.yc.joviality.entity.GankEntity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "gank_collection".
*/
public class GankEntityDao extends AbstractDao<GankEntity, Long> {

    public static final String TABLENAME = "gank_collection";

    /**
     * Properties of entity GankEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property DescX = new Property(1, String.class, "descX", false, "DESC_X");
        public final static Property PublishedAtX = new Property(2, String.class, "publishedAtX", false, "PUBLISHED_AT_X");
        public final static Property UrlX = new Property(3, String.class, "urlX", false, "URL_X");
        public final static Property WhoX = new Property(4, String.class, "whoX", false, "WHO_X");
    }


    public GankEntityDao(DaoConfig config) {
        super(config);
    }
    
    public GankEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"gank_collection\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"DESC_X\" TEXT," + // 1: descX
                "\"PUBLISHED_AT_X\" TEXT," + // 2: publishedAtX
                "\"URL_X\" TEXT," + // 3: urlX
                "\"WHO_X\" TEXT);"); // 4: whoX
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"gank_collection\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, GankEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String descX = entity.getDescX();
        if (descX != null) {
            stmt.bindString(2, descX);
        }
 
        String publishedAtX = entity.getPublishedAtX();
        if (publishedAtX != null) {
            stmt.bindString(3, publishedAtX);
        }
 
        String urlX = entity.getUrlX();
        if (urlX != null) {
            stmt.bindString(4, urlX);
        }
 
        String whoX = entity.getWhoX();
        if (whoX != null) {
            stmt.bindString(5, whoX);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, GankEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String descX = entity.getDescX();
        if (descX != null) {
            stmt.bindString(2, descX);
        }
 
        String publishedAtX = entity.getPublishedAtX();
        if (publishedAtX != null) {
            stmt.bindString(3, publishedAtX);
        }
 
        String urlX = entity.getUrlX();
        if (urlX != null) {
            stmt.bindString(4, urlX);
        }
 
        String whoX = entity.getWhoX();
        if (whoX != null) {
            stmt.bindString(5, whoX);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public GankEntity readEntity(Cursor cursor, int offset) {
        GankEntity entity = new GankEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // descX
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // publishedAtX
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // urlX
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4) // whoX
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, GankEntity entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setDescX(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPublishedAtX(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setUrlX(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setWhoX(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(GankEntity entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(GankEntity entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(GankEntity entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}