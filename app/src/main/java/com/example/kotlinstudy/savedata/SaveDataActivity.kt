package com.example.kotlinstudy.savedata

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kotlinstudy.R
import kotlinx.android.synthetic.main.activity_save_data.*
import timber.log.Timber
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class SaveDataActivity : AppCompatActivity() {
    private lateinit var dbHelper: MyDatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_data)
        dbHelper = MyDatabaseHelper(this, "BookStore.db", 2)
        btnFileSave.setOnClickListener {
            //保存数据
            onSaveFileData()
        }
        btnSaveShared.setOnClickListener {
            onSaveSharedPreferences()
        }
        btnSqlSave.setOnClickListener {
            saveSql()
        }
        addData.setOnClickListener {
            onAddData()
        }
        BtnShowData.setOnClickListener {
            onShowData()
        }


        updateData.setOnClickListener {
            onUpdateData()
        }
        deleteData.setOnClickListener { onDeleteData() }
        queryData.setOnClickListener { onQueryData() }
    }

    private fun onUpdateData() {
        val db = dbHelper.writableDatabase
        val values = ContentValues()
        values.put("name", "Kkkkkotlin")
        db.update("Book", values, "name= ?", arrayOf("Kotlin"))

    }


    private fun onDeleteData() {

        val db = dbHelper.writableDatabase
        db.delete("Book", "name=?", arrayOf("kotlin"))

    }


    private fun onQueryData() {
        val db = dbHelper.writableDatabase
        val cursor = db.query("Book", null, null, null, null, null, null)
        if (cursor.moveToFirst()) {
            do {
                val name = cursor.getString(cursor.getColumnIndex("name"))
                Timber.d("数据库数据：$name")
            } while (cursor.moveToNext())
        }
        cursor.close()
        //这样查询的结果有问题，只出现了两条数据
    }

    private fun onShowData() {
        val db = dbHelper.writableDatabase
        val cursor = db.query("Book", null, null, null, null, null, null)
        if (cursor.moveToFirst()) {
            do {
                val name = cursor.getString(cursor.getColumnIndex("name"))
                Log.d("dbHelper", name)
            } while (cursor.moveToNext())

        }
        cursor.close()
    }

    private fun onAddData() {
        val db = dbHelper.writableDatabase
        val values1 = ContentValues().apply {
            put("name", "Kotlin")
            put("author", "ll")
            put("pages", 500)
            put("price", 79.0)
        }
        db.insert("Book", null, values1)
    }

    private fun saveSql() {

        dbHelper.writableDatabase

    }

    @SuppressLint("CommitPrefEdits")
    private fun onSaveSharedPreferences() {

//        val shared = getSharedPreferences("data_share", Context.MODE_PRIVATE).edit()


//        method by passing in this activity'sclass name as the preferences name.（这个activity的名字作为文件名）
        val shared = getPreferences(Context.MODE_PRIVATE).edit()

        shared.putString("name", etName.text.toString())
        shared.putString("age", etAge.text.toString())
        shared.putString("info", etInfo.text.toString())
        shared.apply()

    }

    override fun onResume() {
        super.onResume()
        val str = loadFileData()
        if (!str.isNullOrEmpty()) {
            etFile.setText(str)
        }

        onLoadSharedPreferencesData()
    }

    private fun onLoadSharedPreferencesData() {
        val shared = getPreferences(Context.MODE_PRIVATE)
//        val shared = getSharedPreferences("data_share", Context.MODE_PRIVATE)
        val name = shared.getString("name", "")
        val age = shared.getString("age", "")
        val info = shared.getString("info", "")
        if (!name.isNullOrEmpty()) {
            etName.setText(name)
        }
        if (!age.isNullOrEmpty()) {
            etAge.setText(age)
        }
        if (!info.isNullOrEmpty()) {
            etInfo.setText(info)
        }

    }

    fun onSaveFileData() {
        val str = etFile.text.toString()
//        MODE_APPEND  追加在后面
//        MODE_PRIVATE 覆盖文件
        val outOpen = openFileOutput("dataFile", Context.MODE_PRIVATE)
        val writer = BufferedWriter(OutputStreamWriter(outOpen))
        writer.use { it.write(str) }
    }

    fun loadFileData(): String {
        val str = StringBuilder()
        val input = openFileInput("dataFile")
        val reader = BufferedReader(InputStreamReader(input))
        reader.use {
            reader.forEachLine {
                str.append(it)
            }
        }
        return str.toString()
    }
}