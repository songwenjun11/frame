package com.example.frame.base;

import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

/**
 * Song Wenjun
 * Created by dell on 2019/2/23 16:29
 * Created prepare
 * package is com.example.frame.base
 * <p>
 * This class is used to do:
 */
public class BaseFragment extends Fragment {

    protected boolean eee = true;
    protected boolean www = true;
    protected boolean vvv = true;
    protected boolean ddd = true;
    protected boolean iii = true;

    public void showToast(String content) {
        Toast.makeText(getActivity(), content, Toast.LENGTH_SHORT).show();
    }

    public void showLongToast(String content) {
        Toast.makeText(getActivity(), content, Toast.LENGTH_LONG).show();
    }

    protected void e(String msg) {
        if (eee) {
            Log.e(getActivity().getLocalClassName(), msg);
        }
    }

    protected void d(String msg) {
        if (ddd) {
            Log.d(getActivity().getLocalClassName(), msg);
        }
    }

    protected void w(String msg) {
        if (www) {
            Log.w(getActivity().getLocalClassName(), msg);
        }
    }

    protected void i(String msg) {
        if (iii) {
            Log.i(getActivity().getLocalClassName(), msg);
        }
    }

    protected void v(String msg) {
        if (vvv) {
            Log.v(getActivity().getLocalClassName(), msg);
        }
    }
}
