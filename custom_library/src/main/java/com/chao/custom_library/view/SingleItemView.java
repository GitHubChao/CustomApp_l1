package com.chao.custom_library.view;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chao.custom_library.R;

/**
 * 自定义的功能界面条目，imageView，TextView，rightImageView
 */
public class SingleItemView extends LinearLayout {

    private ImageView left_icon;
    private TextView tv_text;
    private TextView tv_text2;
    private ImageView right_arrow;
    private View first_line;
    private View margin_view;
    private boolean mIsShowRightIcon = true;

    public SingleItemView(Context context) {
        super(context);
        iniView(context);

    }

    public SingleItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        iniView(context);
    }

    @SuppressLint("NewApi")
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public SingleItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        iniView(context);

        //建立映射
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.SingleItemView);
        //设置背景图
        Drawable iconSrc = obtainStyledAttributes.getDrawable(R.styleable.SingleItemView_iconSrc);

        if (iconSrc != null) {
            left_icon.setImageDrawable(iconSrc);
//            left_icon.setBackground(iconSrc);
        } else {
            left_icon.setVisibility(GONE);
        }

        int width = (int) obtainStyledAttributes.getDimension(R.styleable.SingleItemView_icon_width, 0);
        int height = (int) obtainStyledAttributes.getDimension(R.styleable.SingleItemView_icon_height, 0);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) left_icon.getLayoutParams();

        if (width != 0 && height != 0) {
            params.height = height;
            params.width = width;
            left_icon.setLayoutParams(params);
        }

        boolean isShowRightArrow = obtainStyledAttributes.getBoolean(R.styleable.SingleItemView_isShowRightArrow, true);
        mIsShowRightIcon = isShowRightArrow;

        if (isShowRightArrow) {
            right_arrow.setVisibility(VISIBLE);
        } else {
            right_arrow.setVisibility(GONE);
        }

        //设置TextView
        String text = obtainStyledAttributes.getString(R.styleable.SingleItemView_text);
        tv_text.setText(text);
        int color = obtainStyledAttributes.getColor(R.styleable.SingleItemView_text_font_color, Color.BLACK);
        tv_text.setTextColor(color);
        float font_size = obtainStyledAttributes.getDimension(R.styleable.SingleItemView_text_font_size, 0);
        if (font_size != 0) {
            tv_text.setTextSize(px2sp(context, font_size));
        }

        String text2 = obtainStyledAttributes.getString(R.styleable.SingleItemView_text2);
        if (text2 == null) {
            tv_text2.setVisibility(GONE);
        } else {
            color = obtainStyledAttributes.getColor(R.styleable.SingleItemView_text2_font_color, Color.GRAY);
            font_size = obtainStyledAttributes.getDimension(R.styleable.SingleItemView_text2_font_size, 0);
            tv_text2.setText(text2);
            tv_text2.setTextColor(color);
            if (font_size != 0) {
                tv_text2.setTextSize(px2sp(context, font_size));
            }

            // 如果右侧的图标不显示，需要将 tv_text2 放置到右侧
            if (!isShowRightArrow) {
                adjustContent2();
            }
        }

        boolean isShowMarginTopView = obtainStyledAttributes.getBoolean(R.styleable.SingleItemView_isShowMarginTopView, false);
        if (isShowMarginTopView) {
            margin_view.setVisibility(VISIBLE);
        } else {
            margin_view.setVisibility(GONE);
        }

        obtainStyledAttributes.recycle();

    }

    private void adjustContent2() {
        RelativeLayout.LayoutParams p = (RelativeLayout.LayoutParams) tv_text2.getLayoutParams();
        p.addRule(RelativeLayout.ALIGN_PARENT_END);
        tv_text2.setLayoutParams(p);
    }

    private void iniView(Context context) {
        // 初始化控件
        View.inflate(context, R.layout.single_item_view_layout, this);
        first_line = this.findViewById(R.id.first_line);
        left_icon = this.findViewById(R.id.iv_icon);
        tv_text = this.findViewById(R.id.tv_text);
        tv_text2 = this.findViewById(R.id.tv_text2);
        right_arrow = this.findViewById(R.id.my_dextrad1);
        margin_view = this.findViewById(R.id.margin_view);
    }

    public void setLeftIcon(int resourceId) {
        left_icon.setImageResource(resourceId);
    }

    public void setLeftIconVisible(boolean visible) {
        if (visible) {
            left_icon.setVisibility(VISIBLE);
        } else {
            left_icon.setVisibility(GONE);
        }
    }

    public void setText(String text) {
        tv_text.setText(text);
    }

    public void setText2(String text) {
        tv_text2.setText(text);
        if (!mIsShowRightIcon) {
            adjustContent2();
        }
    }

    public void setTextColor(int color) {
        tv_text.setTextColor(color);
    }

    public void setText2Color(int color) {
        tv_text2.setTextColor(color);
    }

    public void setTextSize(Context context, int pxvalue) {
        tv_text.setTextSize(px2sp(context, pxvalue));
    }

    public void setText2Size(Context context, int pxValue) {
        tv_text2.setTextSize(px2sp(context, pxValue));
    }

    public void setText2Visible(boolean visible) {
        if (visible) {
            tv_text2.setVisibility(VISIBLE);
        } else {
            tv_text2.setVisibility(GONE);
        }
    }

    public void setShowMarginTopView(boolean isShow) {
        if (isShow) {
            margin_view.setVisibility(VISIBLE);
        } else {
            margin_view.setVisibility(GONE);
        }
    }

    public void setRightIconVisible(boolean visible) {
        if (visible) {
            right_arrow.setVisibility(VISIBLE);
        } else {
            right_arrow.setVisibility(INVISIBLE);
        }
    }

    public void isShowFirstLine(boolean isShowLine) {
        if (isShowLine)
            first_line.setVisibility(VISIBLE);
        else
            first_line.setVisibility(GONE);
    }

    private int px2sp(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / scale + 0.5f);
    }

    public void setOnClickListener() {

    }
}
