package com.chao.lp.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.chao.custom_library.dialog.CustomDialog
import com.chao.custom_library.view.SingleItemView
import com.chao.custom_library.view.MultiItemView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = this.findViewById<TextView>(R.id.tv_textview)
        textView.setOnClickListener { it ->
            CustomDialog.Builder(this)
//                    .setTitle("测试标题")
                    .setMessage("对话框消息\n测试消息")
                    .setLeftButton("左侧按钮") {
                        Toast.makeText(this, "测试", Toast.LENGTH_SHORT).show()
                    }
                    .setRightButton("右侧按钮") {
                        Toast.makeText(this, "测试右侧", Toast.LENGTH_SHORT).show()
                    }
                    .setLeftButtonTxtColor(resources.getColor(R.color.colorAccent))
                    .setRightButtonTxtColor(resources.getColor(R.color.colorPrimary))
                    .create()
                    .show()
        }

        val fiv_singleView = this.findViewById<SingleItemView>(R.id.fiv_singleView)
        fiv_singleView.setOnClickListener()

        val fiv_multiItemView = this.findViewById<MultiItemView>(R.id.fiv_multiItemView)
        fiv_multiItemView.setOnMultiItemClickListener {
            startActivity(Intent(this, TestActivity::class.java))
        }

    }

}

