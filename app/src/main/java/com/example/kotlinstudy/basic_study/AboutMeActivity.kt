package com.example.kotlinstudy.basic_study

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.kotlinstudy.R
import com.example.kotlinstudy.bean.TestNameBean
import com.example.kotlinstudy.databinding.ActivityAboutMeBinding
import kotlinx.android.synthetic.main.activity_about_me.*
import kotlinx.android.synthetic.main.activity_about_me.view.*

class AboutMeActivity : AppCompatActivity() {
    lateinit var imm: InputMethodManager
    private var nameBean: TestNameBean = TestNameBean("name_tyy", "sky918")
    private lateinit var binding: ActivityAboutMeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_about_me)
        btn_done.setOnClickListener { onClickFunction() }
        //Set the focus to the edit text.
        et_name.requestFocus()
        //show the keyboard
        imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(et_name, 0)
        tv_name.setOnClickListener { onTextviewClick() }
        binding.nameBean = nameBean
    }

    private fun onTextviewClick() {
        tv_name.visibility = View.GONE
        et_name.visibility = View.VISIBLE
        et_name.requestFocus()
        et_name.setSelection(et_name.text.toString().length)
        imm.showSoftInput(et_name, 0)
    }

    private fun onClickFunction() {
        val name = et_name.text.toString()
        if (name.isEmpty()) {
            tv_name.visibility = View.GONE
            et_name.visibility = View.VISIBLE
            Toast.makeText(this, getString(R.string.message), Toast.LENGTH_SHORT).show()

        } else {
            tv_name.visibility = View.VISIBLE
            tv_name.text = name
            et_name.visibility = View.GONE
            imm.hideSoftInputFromWindow(et_name.windowToken, 0)
            nameBean = TestNameBean(name, "")
            binding.invalidateAll()
        }


    }
}
