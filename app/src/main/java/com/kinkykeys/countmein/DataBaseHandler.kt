package com.kinkykeys.countmein

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASENAME = "MY DATABASE"

val ATTABLE = "studentAttendance"
val COL_DATE = "date"
val COL_SUB = "subject"

val CREDTABLE = "studentCred"
val COL_UID = "username"
val COL_PASSWD = "assword"


class DataBaseHandler(var context:Context) : SQLiteOpenHelper(context, DATABASENAME, null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createATTable = "CREATE TABLE " + ATTABLE + " (" + COL_DATE + " VARCHAR(256)," + COL_SUB + " VARCHAR(256)," + " )"
        val createCRTable = "CREATE TABLE " + CREDTABLE + " (" + COL_UID + " VARCHAR(256)," + COL_PASSWD + " VARCHAR(256)," + " )"
        db?.execSQL(createATTable)
        db?.execSQL(createCRTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //onCreate(db);
    }

    fun insertData(user: User) {

        val database = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(COL_DATE, user.dbDate)
        contentValues.put(COL_SUB, user.dbSub)

        val result = database.insert(ATTABLE, null, contentValues)

        if (result == (0).toLong()) {

            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()

        } else {

            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()

        }

    }


    fun readData(): MutableList<User> {

        val list: MutableList<User> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from $ATTABLE"
        val result = db.rawQuery(query, null)

        if (result.moveToFirst()) {
            do {

                val user = User()
                user.dbDate = result.getString(result.getColumnIndex(COL_DATE))
                user.dbSub = result.getString(result.getColumnIndex(COL_SUB))

                list.add(user)

            } while (result.moveToNext())
        }

        return list
    }

    fun insertCred(user: User) {

        val database = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(COL_UID, user.dbUID)
        contentValues.put(COL_PASSWD, user.dbPasswd)

        val result = database.insert(ATTABLE, null, contentValues)

        if (result == (0).toLong()) {

            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()

        } else {

            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()

        }

    }


    fun readCred(): MutableList<User> {

        val list: MutableList<User> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from $CREDTABLE"
        val result = db.rawQuery(query, null)

        if (result.moveToFirst()) {
            do {

                val user = User()
                user.dbUID = result.getString(result.getColumnIndex(COL_UID))
                user.dbPasswd = result.getString(result.getColumnIndex(COL_PASSWD))

                list.add(user)

            } while (result.moveToNext())
        }

        return list
    }

}