package com.example.android.ormlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Jusung on 2017. 1. 20..
 */

public class MemberOpenDataHelper extends OrmLiteSqliteOpenHelper{

    private static final String DATABASE_NAME = "Member";
    private static final int DATABASE_VERSION = 1;
    private static final int CONFIG=R.raw.config;
    //디비 CRUD를 위한 로직이 담겨있는 DAO클래스
    private Dao<Member, Long> MemberDao;

    public MemberOpenDataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION,CONFIG);

    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            //디비 생성
            TableUtils.createTable(connectionSource,Member.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource,Member.class,false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        onCreate(database,connectionSource);
    }

    public Dao<Member, Long> getDao() throws SQLException {
        if(MemberDao == null) {
            MemberDao = getDao(Member.class);
        }
        return MemberDao;
    }

}
