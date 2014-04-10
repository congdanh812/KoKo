package com.avatar.koko_avt;

import java.io.IOException;
import java.util.ArrayList;

import com.avatar.customize.CommentAdpter;
import com.avatar.customize.VideoControllerView;

import android.app.Activity;
import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class VideoLiveActivity extends Fragment implements OnClickListener,
		SurfaceHolder.Callback, MediaPlayer.OnPreparedListener,
		VideoControllerView.MediaPlayerControl {
	View view;
	DisplayMetrics displaymetrics;
	int height;
	int width;

	// layout
	RelativeLayout rlLayout;
	ListView lv_cmt;
	String[] arr = { "hay vl", "hay tuyet", "good job", "damm like shit",
			"oh yeahhhhhhh !!!!!!!!!!", "hahahahhah", "OMG is that mrVin",
			"My Idol", "blah blah" };
	ArrayList<String> arrStr;
	EditText ed_cmt;
	Button bt_send;
	String strCmt;
	CommentAdpter cmtAdapter;
	InputMethodManager inputManager;

	SurfaceView videoSurface;
	MediaPlayer player;
	VideoControllerView controller;
	FrameLayout frameLayout;
	private boolean mFullScreen = true;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.activity_video_live, null);
		// Initilization
		checkSizeScreen();
		initListView();

		videoSurface = (SurfaceView) view.findViewById(R.id.videoSurface2);
		frameLayout = (FrameLayout) view
				.findViewById(R.id.videoSurfaceContainer2);
		SurfaceHolder videoHolder = videoSurface.getHolder();
		videoHolder.addCallback(this);

		frameLayout.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				controller.show();
				return false;
			}
		});

		player = new MediaPlayer();
		controller = new VideoControllerView(view.getContext());
		try {
			Uri raw_uri = Uri.parse("android.resource://"
					+ view.getContext().getPackageName() + "/" + R.raw.tupac);

			player.setAudioStreamType(AudioManager.STREAM_MUSIC);
			player.setDataSource(view.getContext(), raw_uri);
			player.setOnPreparedListener(this);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return view;
	}

	public void initListView() {
		arrStr = new ArrayList<String>();
		for (int i = 0; i < arr.length; i++) {
			arrStr.add(arr[i]);
		}
		inputManager = (InputMethodManager) view.getContext().getSystemService(
				view.getContext().INPUT_METHOD_SERVICE);

		lv_cmt = (ListView) view.findViewById(R.id.lv_cmt);
		cmtAdapter = new CommentAdpter(view.getContext(), arrStr,
				R.layout.text_cmt);
		lv_cmt.setAdapter(cmtAdapter);
		lv_cmt.setDividerHeight(0);

		ed_cmt = (EditText) view.findViewById(R.id.ed_textCMT);

		bt_send = (Button) view.findViewById(R.id.bt_Send);
		bt_send.setOnClickListener(this);

		rlLayout = (RelativeLayout) view.findViewById(R.id.lynout_cmt);

	}

	public void checkSizeScreen() {
		displaymetrics = new DisplayMetrics();
		((Activity) view.getContext()).getWindowManager().getDefaultDisplay()
				.getMetrics(displaymetrics);
		height = displaymetrics.heightPixels;
		width = displaymetrics.widthPixels;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bt_Send:
			strCmt = ed_cmt.getText().toString();
			arrStr.add(strCmt);
			cmtAdapter.notifyDataSetChanged();
			inputManager.hideSoftInputFromInputMethod(ed_cmt.getWindowToken(),
					0);
			lv_cmt.setSelection(arrStr.size() - 1);
			ed_cmt.setText("");
			break;

		default:
			break;
		}
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		player.start();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		player.pause();
	}

	@Override
	public int getDuration() {
		// TODO Auto-generated method stub
		return player.getDuration();
	}

	@Override
	public int getCurrentPosition() {
		// TODO Auto-generated method stub
		return player.getCurrentPosition();
	}

	@Override
	public void seekTo(int pos) {
		// TODO Auto-generated method stub
		player.seekTo(pos);
	}

	@Override
	public boolean isPlaying() {
		// TODO Auto-generated method stub
		return player.isPlaying();
	}

	@Override
	public int getBufferPercentage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean canPause() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canSeekBackward() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canSeekForward() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isFullScreen() {
		// TODO Auto-generated method stub
		if (mFullScreen) {
			// Log.v("FullScreen", "--set icon full screen--");
			return false;
		} else {
			// Log.v("FullScreen", "--set icon small full screen--");
			return true;
		}
		// return false;

	}

	@Override
	public void toggleFullScreen() {
		// TODO Auto-generated method stub
		setFullScreen(isFullScreen());
	}

	@Override
	public void onPrepared(MediaPlayer arg0) {
		// TODO Auto-generated method stub
		controller.setMediaPlayer(this);
		controller.setAnchorView((FrameLayout) view
				.findViewById(R.id.videoSurfaceContainer2));
		player.start();
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		player.setDisplay(holder);
		player.prepareAsync();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}

	public void setFullScreen(boolean fullScreen) {
		fullScreen = false;

		if (mFullScreen)

		{

			// "-----------Set full screen SCREEN_ORIENTATION_LANDSCAPE------------");
			((Activity) view.getContext())
					.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
			rlLayout.setVisibility(View.GONE);
			mFullScreen = fullScreen;
		} else {

			// "-----------Set small screen SCREEN_ORIENTATION_PORTRAIT------------");
			((Activity) view.getContext())
					.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			rlLayout.setVisibility(View.VISIBLE);
			mFullScreen = !fullScreen;
		}
	}
}
