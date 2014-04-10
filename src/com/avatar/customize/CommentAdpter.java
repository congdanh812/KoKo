package com.avatar.customize;

import java.util.ArrayList;

import com.avatar.koko_avt.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CommentAdpter extends BaseAdapter {

	Context context;
	ArrayList<String> arrStr;
	int resource;

	public CommentAdpter(Context context, ArrayList<String> arrStr, int resource) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.arrStr = arrStr;
		this.resource = resource;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arrStr.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		View v = arg1;
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(resource, null);
		}
		ImageView imageView = (ImageView) v.findViewById(R.id.img_avtCMT);
		if (imageView != null) {
			imageView.setBackgroundResource(R.drawable.antispyware2);
		}
		TextView textView = (TextView) v.findViewById(R.id.tv_CMT);
		if (textView != null) {
			textView.setText(arrStr.get(arg0));
		}
		return v;
	}

}
