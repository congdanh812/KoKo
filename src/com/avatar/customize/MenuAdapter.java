package com.avatar.customize;

import com.avatar.koko_avt.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MenuAdapter extends BaseAdapter {
	Context context;
	String[] arr_menu;
	int resource;

	public MenuAdapter(Context context, int resource, String[] arr_menu) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.arr_menu = arr_menu;
		this.resource = resource;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arr_menu.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = convertView;
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(resource, null);
		}
		TextView textView = (TextView) v.findViewById(R.id.tv_menu);
		if (textView != null) {
			textView.setText(arr_menu[position].toString());
		}

		return v;
	}

}
