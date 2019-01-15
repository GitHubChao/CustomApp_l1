package com.chao.custom_library.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chao.custom_library.R;

/**
 * 自定义Dialog
 * 默认有两个按钮，如果只需要一个按钮，可以只调用 setRightButton()
 */
public class CustomDialog extends Dialog {

    public CustomDialog(@NonNull Context context) {
        super(context);
    }

    public CustomDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected CustomDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder {
        private final Display mDisplay;
        private Context mContext;
        private CharSequence mTitle;
        private CharSequence mMessage;
        private int mMsgGravity = Gravity.CENTER;
        private CharSequence mLeftButtonText;
        private CharSequence mRightButtonText;
        private View.OnClickListener mLeftButtonClickListener;
        private View.OnClickListener mRightButtonClickListener;
        private boolean mCancelable = true;
        private int leftBtnTxtColor = -1;
        private int rightBtnTxtColor = -1;

        public Builder(@NonNull Context context) {
            mContext = context;
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            mDisplay = windowManager.getDefaultDisplay();
        }

        public Builder setTitle(CharSequence title) {
            mTitle = title;
            return this;
        }

        public Builder setTitle(@StringRes int resId) {
            mTitle = mContext.getString(resId);
            return this;
        }

        public Builder setMessage(CharSequence message) {
            mMessage = message;
            return this;
        }

        public Builder setMessage(CharSequence message, int gravity) {
            mMessage = message;
            mMsgGravity = gravity;
            return this;
        }

        public Builder setMessage(@StringRes int resId) {
            mMessage = mContext.getString(resId);
            return this;
        }

        public Builder setMessage(@StringRes int resId, int gravity) {
            mMessage = mContext.getString(resId);
            mMsgGravity = gravity;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            mCancelable = cancelable;
            return this;
        }

        public Builder setLeftButton(CharSequence leftButtonText, View.OnClickListener onClickListener) {
            mLeftButtonText = leftButtonText;
            mLeftButtonClickListener = onClickListener;
            return this;
        }

        public Builder setLeftButton(@StringRes int resId, View.OnClickListener onClickListener) {
            mLeftButtonText = mContext.getString(resId);
            mLeftButtonClickListener = onClickListener;
            return this;
        }

        public Builder setRightButton(CharSequence rightButtonText, View.OnClickListener onClickListener) {
            mRightButtonText = rightButtonText;
            mRightButtonClickListener = onClickListener;
            return this;
        }

        public Builder setRightButton(@StringRes int resId, View.OnClickListener onClickListener) {
            mRightButtonText = mContext.getString(resId);
            mRightButtonClickListener = onClickListener;
            return this;
        }

        public Builder setLeftButtonTxtColor(int textColor) {
            leftBtnTxtColor = textColor;
            return this;
        }

        public Builder setRightButtonTxtColor(int textColor) {
            rightBtnTxtColor = textColor;
            return this;
        }

        public CustomDialog create() {
            View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_twobutton, null);
            LinearLayout container = (LinearLayout) view.findViewById(R.id.dialog_layout);
            final CustomDialog dialog = new CustomDialog(mContext, R.style.MyDialogStyle);
            dialog.setContentView(view);
            dialog.setCancelable(mCancelable);
            // 标题
            TextView title = (TextView) view.findViewById(R.id.tv_title);
            if (isEmpty(mTitle)) {
                title.setVisibility(View.GONE);
            } else {
                title.setText(mTitle);
            }
            // 消息
            TextView message = (TextView) view.findViewById(R.id.tv_message);
            if (isEmpty(mMessage)) {
                message.setVisibility(View.GONE);
            } else {
                message.setText(mMessage);
                message.setGravity(mMsgGravity);
            }

            // 左按钮
            Button leftButton = (Button) view.findViewById(R.id.tv_ok_left);
            if (isEmpty(mLeftButtonText)) {
                leftButton.setVisibility(View.GONE);
            } else {
                if (leftBtnTxtColor != -1) {
                    leftButton.setTextColor(leftBtnTxtColor);
                }
                leftButton.setText(mLeftButtonText);
                leftButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        if (mLeftButtonClickListener != null) {
                            mLeftButtonClickListener.onClick(v);
                        }
                    }
                });
            }
            // 右按钮
            Button rightButton = (Button) view.findViewById(R.id.tv_ok_right);
            if (isEmpty(mRightButtonText)) {
                rightButton.setVisibility(View.GONE);
            } else {
                if (rightBtnTxtColor != -1) {
                    rightButton.setTextColor(rightBtnTxtColor);
                }
                rightButton.setText(mRightButtonText);
                rightButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        if (mRightButtonClickListener != null) {
                            mRightButtonClickListener.onClick(v);
                        }
                    }
                });
            }

            // 按钮中间的分隔线
            View divider = view.findViewById(R.id.divider);
            if (isEmpty(mLeftButtonText) || isEmpty(mRightButtonText)) {
                divider.setVisibility(View.GONE);
            }

            // 调整dialog背景大小 0.8宽
            // 使用FrameLayout.LayoutParams设置参数
            container.setLayoutParams(new FrameLayout.LayoutParams((int) (mDisplay.getWidth() * 0.8),
                    FrameLayout.LayoutParams.WRAP_CONTENT));
            return dialog;
        }

        public void show() {
            create().show();
        }

        private static boolean isEmpty(CharSequence text) {
            return text == null || TextUtils.isEmpty(text.toString().trim());
        }
    }

}

