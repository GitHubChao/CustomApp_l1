package com.chao.lp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;
import com.chao.custom_library.view.MultiItemView;

public class TestActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);

        MultiItemView fiv_multiItemView = this.findViewById(R.id.fiv_multiItemView);
        fiv_multiItemView.setOnMultiItemClickListener(new MultiItemView.OnclickListener() {
            @Override
            public void onItemClick(int i) {
                switch (i) {
                    case 0:
                    case 1:
                    case 2:
                        Toast.makeText(TestActivity.this, i + "", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }


}
