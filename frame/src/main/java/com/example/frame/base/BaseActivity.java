package com.example.frame.base;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.frame.R;
import com.example.frame.interfaces.OnClickPopup;
import com.example.frame.utils.StatusBar;

import java.io.Serializable;

/**
 * Song Wenjun
 * Created by dell on 2019/2/23 16:10
 * Created prepare
 * package is com.example.frame.base
 * <p>
 * This class is used to do:
 */
public class BaseActivity extends AppCompatActivity {

    protected boolean eee = true;
    protected boolean www = true;
    protected boolean vvv = true;
    protected boolean ddd = true;
    protected boolean iii = true;
    private PopupWindow popWindow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBar.fullScreen(this);
        initViewBase();
        initDataBase();
    }

    protected void initViewBase() {

    }

    protected void initDataBase() {
    }

    public void showToast(final String content) {
        String name = Thread.currentThread().getName();
        if (name.equals("main")) {
            Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
        } else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(BaseActivity.this, content, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void showLongToast(final String content) {
        String name = Thread.currentThread().getName();
        if (name.equals("main")) {
            Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
        } else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(BaseActivity.this, content, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    protected void e(String msg) {
        if (eee) {
            Log.e(this.getLocalClassName(), msg);
        }
    }

    protected void d(String msg) {
        if (ddd) {
            Log.d(this.getLocalClassName(), msg);
        }
    }

    protected void w(String msg) {
        if (www) {
            Log.w(this.getLocalClassName(), msg);
        }
    }

    protected void i(String msg) {
        if (iii) {
            Log.i(this.getLocalClassName(), msg);
        }
    }

    protected void v(String msg) {
        if (vvv) {
            Log.v(this.getLocalClassName(), msg);
        }
    }

    public PopupWindow showPopuWindow(String title, String ok, String no, final OnClickPopup clickPopup) {
        final View popView = View.inflate(this, R.layout.layout_show_popuwindow, null);

        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;

        popWindow = new PopupWindow(popView, width, height);

        popWindow.setAnimationStyle(R.style.AnimBottom);
        popWindow.setFocusable(true);
        popWindow.setOutsideTouchable(true);// 设置允许在外点击消失
        popWindow.setTouchable(true);

        popWindow.setTouchable(true);
        popWindow.setBackgroundDrawable(new ColorDrawable(0x0000));
        popWindow.showAtLocation(this.getWindow().getDecorView(), Gravity.NO_GRAVITY, 0, 0);

        popWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        TextView tvTitle = popView.findViewById(R.id.tv_title);
        TextView btnOk = popView.findViewById(R.id.tv_confirm);
        TextView btnNo = popView.findViewById(R.id.tv_cancel);
        tvTitle.setText(title);
        btnNo.setText(no);
        btnOk.setText(ok);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindow.dismiss();
                if (clickPopup != null)
                    clickPopup.clickOk();
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindow.dismiss();
                if (clickPopup != null) {
                    clickPopup.clickNo();
                }
            }
        });
        return popWindow;
    }

    public View showPopuWindow(int layoutId) {
        View popView = LayoutInflater.from(this).inflate(layoutId, null);

        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;

        popWindow = new PopupWindow(popView, width, height);

        popWindow.setAnimationStyle(R.style.AnimBottom);
        popWindow.setFocusable(true);
        popWindow.setTouchable(true);

        popWindow.setTouchable(true);
        popWindow.setBackgroundDrawable(new ColorDrawable(0x0000));
        popWindow.showAtLocation(this.getWindow().getDecorView(), Gravity.NO_GRAVITY, 0, 0);

        popWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        return popView;
    }

    public void closeWindow() {
        if (popWindow != null) {
            popWindow.dismiss();
        }
    }

    /**
     * 页面跳转 在所有继承BaseActivity的类中进行跳转直接调本方法
     *
     * @param activityClass 要跳转的Activity的Class对象
     * @param o             可变参数，用来两个界面的值传递，要求必须实现Serializable接口
     * @param <T>           泛型，规范值传递，必须实现序列化
     */
    @SafeVarargs
    public final <T extends Serializable> void startActivityToActivity(Class activityClass, T... o) {
        Intent intent = new Intent(this, activityClass);
        if (o != null) {
            for (int i = 0; i < o.length; i++) {
                intent.putExtra(i + "", o[i]);
            }
        }
        startActivity(intent);
    }

    //**************************************************************环信************************************************
}
