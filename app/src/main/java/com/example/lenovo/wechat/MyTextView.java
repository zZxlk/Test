package com.example.lenovo.wechat;

/**
 * Created by Lenovo on 2017/12/8.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
public class MyTextView extends TextView{
    public MyTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);   }
          public MyTextView(Context context, AttributeSet attrs) {
              super(context, attrs);    }
    public MyTextView(Context context) {
        super(context);      }
    @Override  public boolean isFocused() {
        return true;   }  }
