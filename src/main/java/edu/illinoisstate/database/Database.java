package edu.illinoisstate.database;

import edu.illinoisstate.UserAccount;
import edu.illinoisstate.course.Course;
import org.tmatesoft.sqljet.core.SqlJetException;
import org.tmatesoft.sqljet.core.SqlJetTransactionMode;
import org.tmatesoft.sqljet.core.table.SqlJetDb;

import java.io.File;
import java.util.List;

/**
 * This will eventually be the database class
 */
public class Database {
    private final SqlJetDb db;

    public Database() throws SqlJetException {
        File dbFile = new File("DB_NAME");
        if (dbFile.delete()) {
            System.out.println("Issue with SqlJet database file deletion when starting up.");
        }

        // For the sake of atomicity this example always creates new empty data base
        db = SqlJetDb.open(dbFile, true);
        db.getOptions().setAutovacuum(true);
        db.beginTransaction(SqlJetTransactionMode.WRITE);

        try {
            db.getOptions().setUserVersion(1);
        } finally {
            db.commit();
        }
    }

    public void write() throws SqlJetException {
        db.beginTransaction(SqlJetTransactionMode.WRITE);
        try {
            db.createTable(createTableQuery);
            db.createIndex(createFirstNameIndexQuery);
            db.createIndex(createDateIndexQuery);
        } finally {
            db.commit();
        }
    }

    public UserAccount getUser() {
        return new UserAccount("", "", "");
    }

    public List<Course> getCourseList() {
        return null;
    }


}
