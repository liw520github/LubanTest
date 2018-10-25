package com.example.mb.lubantest.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.example.mb.lubantest.R;

public class DispPwdEditTest extends EditText implements TextWatcher, View.OnFocusChangeListener {
    //隐藏密码
    private Drawable mInvisibleDrawable;
    //显示密码
    private Drawable mVisibleDrawable;
    //判断控件是否有焦点
    private boolean hasFocus;
    private boolean isPwdVisible = false;


    public interface onTextChangeListenr {
        void onTextChangeListenr(CharSequence s, int start, int count, int after);

        void doClearText();
    }

    private onTextChangeListenr onTextChangeListenr;

    public void setOnTextChangeListenr(DispPwdEditTest.onTextChangeListenr onTextChangeListenr) {
        this.onTextChangeListenr = onTextChangeListenr;
    }

    public DispPwdEditTest(Context context) {
        this(context, null);
    }

    public DispPwdEditTest(Context context, AttributeSet attrs) {
        //这里构造方法也很重要，不加这个很多属性不能再XML里面定义
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public DispPwdEditTest(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //获取EditText的DrawableRight,加入没有设置我们就是用默认的图片
        mInvisibleDrawable = getCompoundDrawables()[2];
        mVisibleDrawable = getCompoundDrawables()[2];
        if (mInvisibleDrawable == null) {
            mInvisibleDrawable = getResources().getDrawable(R.mipmap.login_icon_invisible);
        }
        if (mVisibleDrawable == null) {
            mVisibleDrawable = getResources().getDrawable(R.mipmap.login_icon_visible);
        }
        //setBounds指定绘制的区域
        mInvisibleDrawable.setBounds(0, 0, mInvisibleDrawable.getIntrinsicWidth(), mInvisibleDrawable.getIntrinsicHeight());
        mVisibleDrawable.setBounds(0, 0, mVisibleDrawable.getIntrinsicWidth(), mVisibleDrawable.getIntrinsicHeight());
        //默认设置隐藏图标
        setPwdVisible(false);
        //设置焦点改变的监听
        setOnFocusChangeListener(this);
        //设置输入框里面内容发生改变的监听;
        addTextChangedListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (getCompoundDrawables()[2] != null) {
                boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight() -10) && (event.getX() < ((getWidth() - getPaddingRight()) + 10));
                if (touchable) {
                    setPwdVisible(!isPwdVisible);
                }
            }
        }
        return super.onTouchEvent(event);
    }

    public void setPwdVisible(boolean visible) {
        Drawable right = visible ? mVisibleDrawable : mInvisibleDrawable;
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
        this.setTransformationMethod(visible ? (HideReturnsTransformationMethod.getInstance()) : (PasswordTransformationMethod.getInstance()));
        isPwdVisible = visible;
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (hasFocus) {
            setPwdVisible(isPwdVisible);
        }
        if (onTextChangeListenr != null) {
            onTextChangeListenr.onTextChangeListenr(s, start, count, before);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

    }
}
