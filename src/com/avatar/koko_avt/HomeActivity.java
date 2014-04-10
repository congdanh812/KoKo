package com.avatar.koko_avt;

import com.avatar.customize.GalleryAdapter;

import android.app.Fragment;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ListView;

public class HomeActivity extends Fragment {
	int[] arr_img = { R.drawable.bn1, R.drawable.bn2, R.drawable.bn3,
			R.drawable.bn4 };
	View view;
	ImageView imgView;
	ListView listView;

	//
	Gallery gallery;
	GalleryAdapter adpter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.activity_home, null);

		initListView();

		return view;
	}

	private void initListView() { 
		Display display = getActivity().getWindowManager().getDefaultDisplay();
		int width = display.getWidth();
		int height = display.getHeight();

		// set content for Banner
		gallery = (Gallery) view.findViewById(R.id.gallery_banner);
		adpter = new GalleryAdapter(view.getContext(), arr_img, R.layout.image,
				width, height/3);
		gallery.setAdapter(adpter);

		// set content for ListView
		listView = (ListView) view.findViewById(R.id.lv_main);
		listView.setAdapter(adpter);
	}
}
