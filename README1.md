# CustomApp_l1
自定义view

# 1、CustomDialog

# 代码案例

     val textView = this.findViewById<TextView>(R.id.tv_textview)
        textView.setOnClickListener { it ->
            CustomDialog.Builder(this)
                    .setTitle("测试标题")
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
              
 # 2、适用于个人设置页面配置各个条目   
 
 # SingItemView
 # xml中添加
 
 # MultiItemView
 # Xml添加
 
    <com.chao.custom_library.view.MultiItemView
        android:id="@+id/fiv_multiItemView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:iconArray="@array/icon_list"
        app:titleArray="@array/title_list" />

 
 
 
 
 
 
 
 
 
 
