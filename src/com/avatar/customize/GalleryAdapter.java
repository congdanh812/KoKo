package com.avatar.customize;

import com.avatar.koko_avt.R;

import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

public class GalleryAdapter extends BaseAdapter {

	Context context;
	int[] arr_img;
	int resource;
	int width;
	int height;

	public GalleryAdapter(Context context, int[] arrimg, int resource,
			int width, int height) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.arr_img = arrimg;
		this.resource = resource;
		this.width=width;
		this.height=height;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arr_img.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
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
		ImageView imageView = (ImageView) v.findViewById(R.id.image_img);
		if (imageView != null) {
			imageView.setBackgroundResource(arr_img[position]);
//			imageView.setScaleType(ScaleType.FIT_XY);
			 imageView.setLayoutParams(new
			 LinearLayout.LayoutParams(width,
			 height));
		}
		return v;
	}

}
