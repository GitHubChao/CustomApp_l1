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
 
     <com.chao.custom_library.view.SingleItemView
        android:id="@+id/fiv_singleView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:iconSrc="@mipmap/ic_launcher"
        app:isShowRightArrow="true"
        app:text="文本1"
        app:text2="文本2"
        app:text2_font_color="#FF0000"
        app:text2_font_size="13sp"
        app:text_font_color="#00FF00"
        app:text_font_size="14sp" />
        
 # MultiItemView
 # Xml添加
 
    <com.chao.custom_library.view.MultiItemView
        android:id="@+id/fiv_multiItemView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:iconArray="@array/icon_list"
        app:titleArray="@array/title_list" />
        
        
# \main\res\values\arrays.xml 文件添加内容

     <?xml version="1.0" encoding="utf-8"?>
     <resources>
          <string-array name="title_list">
               <item>标题1</item>
               <item>标题2</item>
               <item>标题3</item>
          </string-array>

          <string-array name="icon_list">
               <item>ic_launcher</item>
               <item>ic_launcher</item>
               <item>ic_launcher</item>
          </string-array>
     </resources>
 
 
 
 
 
 
 
 
 
 