package com.avatar.koko_avt;

import java.io.IOException;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.content.res.AssetFileDescriptor;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Gallery;
import android.widget.Gallery.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.avatar.customize.GalleryAdapter;
import com.avatar.customize.VideoControllerView;

public class VideoDocActivity extends Fragment implements
		SurfaceHolder.Callback, MediaPlayer.OnPreparedListener,
		VideoControllerView.MediaPlayerControl {
	View view;
	SurfaceView videoSurface;
	MediaPlayer player;
	VideoControllerView controller;
	FrameLayout frameLayout;
	private boolean mFullScreen = true;

	DisplayMetrics displaymetrics;
	int height;
	int width;

	Gallery gallery;
	GalleryAdapter adpter;

	int[] arr_img = { R.drawable.bn1, R.drawable.bn2, R.drawable.bn3,
			R.drawable.bn4 };

	int playingtime = 0;

	//
	LinearLayout layoutBmt;
	LinearLayout layoutTitle;
	LinearLayout layoutDesc;
	LinearLayout layoutIntro;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.activity_video_doc, null);

		initView();
		initGallery();

		videoSurface = (SurfaceView) view.findViewById(R.id.videoSurface);
		frameLayout = (FrameLayout) view
				.findViewById(R.id.videoSurfaceContainer);
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

	private void initView() {
		layoutBmt = (LinearLayout) view.findViewById(R.id.lynout_bmtVideodoc);
		layoutTitle = (LinearLayout) view.findViewById(R.id.lynout_titleDoc);
		layoutDesc = (LinearLayout) view.findViewById(R.id.lynout_contentDesc);
		layoutIntro = (LinearLayout) view
				.findViewById(R.id.lynout_contentTitleDoc);
	}

	private void initGallery() {
		checkSizeScreen();
		gallery = (Gallery) view.findViewById(R.id.gallery_banner);
		adpter = new GalleryAdapter(view.getContext(), arr_img, R.layout.image,
				width / 2, height / 6);
		gallery.setAdapter(adpter);
		gallery.setSpacing(20);
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

	public void checkSizeScreen() {
		displaymetrics = new DisplayMetrics();
		((Activity) view.getContext()).getWindowManager().getDefaultDisplay()
				.getMetrics(displaymetrics);
		height = displaymetrics.heightPixels;
		width = displaymetrics.widthPixels;
	}

	@Override
	public void toggleFullScreen() {
		// TODO Auto-generated method stub
		setFullScreen(isFullScreen());
	}

	public void setFullScreen(boolean fullScreen) {
		fullScreen = false;

		if (mFullScreen)

		{

			// "-----------Set full screen SCREEN_ORIENTATION_LANDSCAPE------------");
			((Activity) view.getContext())
					.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

			layoutBmt.setVisibility(View.GONE);
			layoutDesc.setVisibility(View.GONE);
			layoutTitle.setVisibility(View.GONE);
			layoutIntro.setVisibility(View.GONE);

			// checkSizeScreen();
			// android.widget.FrameLayout.LayoutParams params =
			// (android.widget.FrameLayout.LayoutParams) videoSurface
			// .getLayoutParams();
			// params.width = width;
			// params.height = height;
			// params.setMargins(0, 0, 0, 0);
			// videoSurface.setLayoutParams(params);

			// set icon is full screen

			mFullScreen = fullScreen;
		} else {

			// "-----------Set small screen SCREEN_ORIENTATION_PORTRAIT------------");
			((Activity) view.getContext())
					.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

			layoutBmt.setVisibility(View.VISIBLE);
			layoutTitle.setVisibility(View.VISIBLE);
			layoutDesc.setVisibility(View.VISIBLE);
			layoutIntro.setVisibility(View.VISIBLE);

			// checkSizeScreen();
			// // int height = displaymetrics.heightPixels;
			// height = frameLayout.getHeight();// get height Frame Container
			// video
			//
			// android.widget.FrameLayout.LayoutParams params =
			// (android.widget.FrameLayout.LayoutParams) videoSurface
			// .getLayoutParams();
			// params.width = width;
			// params.height = height;
			// params.setMargins(0, 0, 0, 0);
			// videoSurface.setLayoutParams(params);
			// set icon is small screen
			mFullScreen = !fullScreen;
		}
	}

	// Implement MediaPlayer.OnPreparedListener
	@Override
	public void onPrepared(MediaPlayer mp) {
		controller.setMediaPlayer(this);
		controller.setAnchorView((FrameLayout) view
				.findViewById(R.id.videoSurfaceContainer));
		player.seekTo(10000);
		// player.pause();

	}

	// End MediaPlayer.OnPreparedListener

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
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

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		player.stop();
		playingtime = player.getCurrentPosition();

	}
}
