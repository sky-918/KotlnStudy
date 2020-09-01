package com.example.kotlinstudy.savedata

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinstudy.R
import kotlinx.android.synthetic.main.activity_save_data.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class SaveDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_data)
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
    }

    private fun saveSql() {

        val dbHelper = MyDatabaseHelper(this, "BookStore.db", 1)
        dbHelper.writableDatabase

    }

    @SuppressLint("CommitPrefEdits")
    private fun onSaveSharedPreferences() {
//        val shared = getSharedPreferences("data_share", Context.MODE_PRIVATE).edit()
        val shared = getPreferences(Context.MODE_PRIVATE).edit()

        shared.putString("name", etName.text.toString())
        shared.putString("age", etAge.text.toString())
        shared.putString("info", etInfo.text.toString())
        shared.apply()

    }

    override fun onResume() {
        super.onResume()
        val str = loadData()
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
        val outOpen = openFileOutput("dataFile", Context.MODE_PRIVATE)
        val writer = BufferedWriter(OutputStreamWriter(outOpen))
        writer.use { it.write(str) }
    }

    fun loadData(): String {
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