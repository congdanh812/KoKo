package com.avatar.koko_avt;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class Main2Activity extends Activity {
	private DrawerLayout mDrawerLayout2;
	private ActionBarDrawerToggle mDrawerToggle2;
	int activity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);

		Bundle bundle = getIntent().getExtras();
		activity = bundle.getInt("Activity");

		mDrawerLayout2 = (DrawerLayout) findViewById(R.id.drawer_layout2);
		mDrawerToggle2 = new ActionBarDrawerToggle(this, mDrawerLayout2,
				R.drawable.ic_drawer, // nav menu toggle icon
				R.string.app_name, // nav drawer open - description for
									// accessibility
				R.string.app_name // nav drawer close - description for
									// accessibility
		);
		mDrawerLayout2.setDrawerListener(mDrawerToggle2);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		displayView(activity - 1);

		// if (savedInstanceState == null) {
		// // on first time display view for first nav item
		// displayView(0);
		// }
	}

	/**
	 * Diplaying fragment view for selected nav drawer list item
	 * */
	private void displayView(int position) {
		// update the main content by replacing fragments
		Fragment fragment = null;
		switch (position) {
		case 0:
			fragment = new VideoDocActivity();
			break;
		case 1:
			fragment = new VideoLiveActivity();
			break;
		case 2:
			// fragment = new PhotosFragment();
			break;
		case 3:
			// fragment = new CommunityFragment();
			break;
		case 4:
			// fragment = new PagesFragment();
			break;
		case 5:
			// fragment = new WhatsHotFragment();
			break;

		default:
			break;
		}

		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame2_container, fragment).commit();

			// update selected item and title, then close the drawer

			// setTitle(navMenuTitles[position]);
			// mDrawerLayout.closeDrawer(mDrawerList);
		} else {
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title

		finish();
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main2, menu);
		return true;
	}

}
