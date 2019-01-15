package com.chao.custom_library.view;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.chao.custom_library.R;

import java.lang.reflect.Field;

/**
 * 多条目列表
 */
public class MultiItemView extends LinearLayout {
    private LinearLayout content;
    private OnclickListener onClickListener;

    public MultiItemView(Context context) {
        super(context);
        initView(context);
    }

    public MultiItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        /**
         * 初始化属性值
         */
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.MultiItemView);
        CharSequence[] textArray = obtainStyledAttributes.getTextArray(R.styleable.MultiItemView_titleArray);
        CharSequence[] iconArray = obtainStyledAttributes.getTextArray(R.styleable.MultiItemView_iconArray);

        int font_size = (int) obtainStyledAttributes.getDimension(R.styleable.MultiItemView_itemFontSize, 0);
        int color = obtainStyledAttributes.getColor(R.styleable.MultiItemView_itemFontColor, Color.BLACK);

        boolean isShowRightIcon = obtainStyledAttributes.getBoolean(R.styleable.MultiItemView_isShowListRightIcon, true);
        boolean isShowTopView = obtainStyledAttributes.getBoolean(R.styleable.MultiItemView_isShowItemTopView, true);

        if (iconArray != null && iconArray.length != textArray.length) {
            throw new RuntimeException("please check arrays.xml,and perhaps the length arrys is wrong");
        }

        if (textArray == null) {
            throw new RuntimeException("you sure you had configuration arrays in arrays.xml?");
        }
        SingleItemView firstItem = new SingleItemView(context);
        firstItem.setText(textArray[0].toString());
        firstItem.setTextColor(color);
        firstItem.setRightIconVisible(isShowRightIcon);
        firstItem.setShowMarginTopView(isShowTopView);
        if (font_size != 0) {
            firstItem.setTextSize(context, font_size);
        }
        if (iconArray == null) {
            firstItem.setLeftIconVisible(false);
            firstItem.setShowMarginTopView(false);
            firstItem.setText2Visible(false);
            content.addView(firstItem);
            firstItem.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickListener != null) {
                        onClickListener.onItemClick(0);
                    }

                }
            });
            for (int i = 1; i < textArray.length; i++) {
                String title = textArray[i].toString();
                SingleItemView item = new SingleItemView(context);
                item.setText(title);
                item.setRightIconVisible(isShowRightIcon);
                item.setShowMarginTopView(isShowTopView);
                if (font_size != 0) {
                    item.setTextSize(context, font_size);
                }
                item.setTextColor(color);
                item.setLeftIconVisible(false);
                item.setText2Visible(false);
                final int finalI = i;
                item.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onClickListener != null) {
                            onClickListener.onItemClick(finalI);
                        }

                    }
                });
                content.addView(item);
            }
        } else {
            int resId = -1;
//            int resId = getResourceByPicName(iconArray[0].toString());
//            if (resId != -1) {
//                firstItem.setLeftIcon(resId);
//            }
//
//            firstItem.setShowMarginTopView(false);
//            firstItem.setText2Visible(false);
//            firstItem.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (onClickListener != null) {
//                        onClickListener.onClick(0);
//                    }
//
//                }
//            });
//            content.addView(firstItem);

            for (int i = 0; i < textArray.length; i++) {
                String title = textArray[i].toString();
                SingleItemView item = new SingleItemView(context);
                item.setText(title);
                item.setRightIconVisible(isShowRightIcon);
                if (font_size != 0) {
                    item.setTextSize(context, font_size);
                }
                item.setTextColor(color);
                resId = getResourceByPicName(iconArray[i].toString());//修改每一个图片
                item.setShowMarginTopView(isShowTopView);
                if (resId != -1) {
                    item.setLeftIcon(resId);
                }
//                item.setShowMarginTopView(false);
                item.setText2Visible(false);
                final int finalI = i;
                item.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onClickListener != null) {
                            onClickListener.onItemClick(finalI);
                        }

                    }
                });

                //整个列表第一个条目显示最上面的分割线
                if (i == 0)
                    item.isShowFirstLine(true);
                else
                    item.isShowFirstLine(false);

                content.addView(item);
            }
        }
        obtainStyledAttributes.recycle();
    }

    public MultiItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @SuppressLint("NewApi")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MultiItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context) {
        View.inflate(context, R.layout.multi_item_view_layout, this);
        content = this.findViewById(R.id.ll_content);
    }

    /**
     * 图片名称，通过图片名称反射获取资源ID
     *
     * @param fileName 文件名称，不是资源文件id
     * @return
     */
    public int getResourceByPicName(String fileName) {
        Class drawable = R.drawable.class;
        Field field = null;
        try {
            field = drawable.getField(fileName);
            int resId = field.getInt(field.getName());
            return resId;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * click的回调接口
     *
     * @param onClickListener
     */
    public void setOnMultiItemClickListener(OnclickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnclickListener {
        /**
         * i 为点击条目的位置
         *
         * @param i
         */
        void onItemClick(int i);
    }
}
