package com.example.momo.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.momo.R;

public class ToastUtils {
	
	private static Toast mToast;
	private static TextView textView;
	/**
	 * 显示Toast
	 */
	public static void showToast(Context context, CharSequence text, int duration) {
  LogUtil.e("'showToast");
		if(mToast == null) {
		View view=	LayoutInflater.from(context).inflate(R.layout.toast_layout,null,false);
			textView=view.findViewById(R.id.toast_text);
			mToast = Toast.makeText(context, text, duration);
			mToast.setView(view);
		} else {

			textView.setText(text);
			mToast.setDuration(duration);
		}
		mToast.show();
	}

}
