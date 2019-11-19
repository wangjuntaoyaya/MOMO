/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.momo.manager;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.example.momo.R;
import com.example.momo.activitys.PlayVedioActivity;
import com.example.momo.utils.LogUtil;
import com.example.momo.widget.Myplayer;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.C.ContentType;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.ext.ima.ImaAdsLoader;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.ads.AdsMediaSource;
import com.google.android.exoplayer2.source.dash.DashChunkSource;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.dash.DefaultDashChunkSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.source.smoothstreaming.DefaultSsChunkSource;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

/**
 * Manages the {@link ExoPlayer}, the IMA plugin and all video playback.
 */
/* package */ public final class PlayerManager implements AdsMediaSource.MediaSourceFactory {

    private final ImaAdsLoader adsLoader;
    private final DataSource.Factory dataSourceFactory;

    private final DashChunkSource.Factory dashSourceFactory;
    private final SsMediaSource.Factory ssSourceFactory;
    private final DefaultSsChunkSource.Factory CC;
    private Context mContext;


    private SimpleExoPlayer player;
    private long contentPosition;
    private ImageView zoom;

    public PlayerManager(Context context) {

        mContext = context;
        String adTag = context.getString(R.string.ad_tag_url);
        adsLoader = new ImaAdsLoader(context, Uri.parse(adTag));
        dataSourceFactory =
                new DefaultDataSourceFactory(
                        context, Util.getUserAgent(context, context.getString(R.string.app_name)));
        dashSourceFactory = new DefaultDashChunkSource.Factory(dataSourceFactory);

        CC = new DefaultSsChunkSource.Factory(dataSourceFactory);
        ssSourceFactory = new SsMediaSource.Factory(CC, dataSourceFactory);
    }

    public void init() {
        DefaultTrackSelector defaultTrackSelector = new DefaultTrackSelector();
// defaultTrackSelector.setParameters(new DefaultTrackSelector.ParametersBuilder().clearVideoSizeConstraints().setMaxVideoSize(320,620));

        // Create a player instance.
        player = ExoPlayerFactory.newSimpleInstance(mContext, defaultTrackSelector);


        //广告
//    adsLoader.setPlayer(player);


    }

    public void setControllViewListener(PlayerControlView.VisibilityListener p) {
        mPlayerView.setControllerVisibilityListener(
                p);
    }
  public void smallScreen(){
      ((PlayVedioActivity) mPlayerView.getContext()).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
      mPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIT);
      zoom.setVisibility(View.VISIBLE);
      isFuLL=false;

  }
  public boolean isFull(){
        return isFuLL;
    }
  private  boolean isFuLL=false;
    public void fullScreen() {
        ((PlayVedioActivity) mPlayerView.getContext()).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        mPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);
        isFuLL=true;
    }

    public SeekBar seekBar;
    public Myplayer mPlayerView;

    class videoZoomlistenerOnClick implements View.OnClickListener {


        @Override
        public void onClick(View v) {

            fullScreen();
            zoom.setVisibility(View.GONE);

        }
    }
  boolean isFisrt=false;
    public void play(String url, Myplayer playerView, Toolbar toolbar) {
        mPlayerView = playerView;
        seekBar = mPlayerView.findViewById(R.id.exo_progress_placeholder);
        zoom = mPlayerView.findViewById(R.id.video_zoom);
        zoom.setClickable(true);
        mPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIT);
        seekBar.setOnSeekBarChangeListener(new SeekBariListener());
        zoom.setOnClickListener(new videoZoomlistenerOnClick());
        mPlayerView.setPlayer(player);

//      playerView.setShutterBackgroundColor(Color.TRANSPARENT);
        MediaSource contentMediaSource = buildMediaSource(Uri.parse(url));

        // Compose the content media source into a new AdsMediaSource with both ads and content.
        MediaSource mediaSourceWithAds =
                new AdsMediaSource(contentMediaSource, /* adMediaSourceFactory= */ this, adsLoader, playerView);

        // Prepare the player with the source.
        player.seekTo(contentPosition);

        player.prepare(mediaSourceWithAds);


        player.addListener(new Player.EventListener() {
            boolean isloadingg=false;
            @Override
            public void onTimelineChanged(Timeline timeline, @Nullable Object manifest, int reason) {

            }

            @Override
            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

            }

            @Override
            public void onLoadingChanged(boolean isLoading) {
                LogUtil.e("isLoading-----------------------  "+isLoading);

                if (isLoading == true) {
                    //初始化会调用一次状态改变
                    isloadingg = isLoading;

                }else{

                }
//

            }

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
              if(isFisrt){

                    ControllAppBar(!playWhenReady);
                }
                isFisrt=true;




                timerForHideCotroller(playWhenReady);

                if (playWhenReady == true){
                    seekBarPlay();



                }
                if (playbackState == 3) {

                    //2是闲置状态  可以正式播放为3  请求数据开始
                    isStart = true;

                } else {
                    isStart = false;
                }

            }


            @Override
            public void onRepeatModeChanged(int repeatMode) {

            }

            @Override
            public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {

            }

            @Override
            public void onPositionDiscontinuity(int reason) {

            }

            @Override
            public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

            }

            @Override
            public void onSeekProcessed() {

            }
        });
        player.setPlayWhenReady(false);

    }
    private void  timerForHideCotroller(boolean playWhenReady){
        if(playWhenReady) {
            mPlayerView.setUseController(playWhenReady);
            return;

        }

//        mPlayerView.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                LogUtil.e("");
//                mPlayerView.setUseController(playWhenReady);
//            }
//        },1200);
//
//        handler.sendEmptyMessageDelayed(1,1500);

    }
    private CollapsingToolbarLayout collapsingToolbarLayout;
   public void setCollapsingToolbarLayout(CollapsingToolbarLayout c){
       collapsingToolbarLayout=c;


   }
    public void ControllAppBar(boolean b){
       if(collapsingToolbarLayout==null)return;

        AppBarLayout.LayoutParams layoutParams = (AppBarLayout.LayoutParams) collapsingToolbarLayout.getLayoutParams();

        if(b){
            LogUtil.e("onPlayerStateChanged-----------------------  "+b);
            layoutParams.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL| AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED  );

        }else {
            LogUtil.e("播放-----------------------  清空");
            layoutParams.setScrollFlags(0);
        }
        ( (AppBarLayout)collapsingToolbarLayout.getParent()).setExpanded(true,true);

        collapsingToolbarLayout.requestLayout();
        if(mContext instanceof PlayVedioActivity){

            ((PlayVedioActivity)mContext).setGone(true);
        }

//        collapsingToolbarLayout.setLayoutParams(layoutParams);
    }
    private Handler handler = new Handler(Looper.getMainLooper()) {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            int s = msg.what;
            if (isDrag == true || !isStart) return;


            switch (s) {
                case 0: {

                    //防止空指针
                    if (player == null | seekBar.getProgress() == 100) {
                        removeCallbacksAndMessages(null);
                        return;
                    }
                    //缓存进度小于500 回调监听 加载ing
                    if (loadding != null && player.getBufferedPosition() - player.getContentPosition() < 500) {
                        loadding.callBack(false);

                    } else if (loadding != null) {
                        loadding.callBack(true);
                        LogUtil.e("开始了");
                    }


                    seekBar.setProgress((int) ((1.0f * player.getContentPosition() / player.getDuration()) * 100));
                    seekBar.setSecondaryProgress((int) ((1.0f * player.getBufferedPosition() / player.getDuration()) * 100));
                    sendEmptyMessageDelayed(0, 1000);


                }


            }


        }
    };

    private void seekBarPlay() {

        Message message = handler.obtainMessage();
        message.obj = 0;
//      handler.sendMessage(message);
        handler.sendEmptyMessage(0);


    }

    private boolean isStart = false;
    private boolean isDrag = false;
    private int drag;

    class SeekBariListener implements SeekBar.OnSeekBarChangeListener {


        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            LogUtil.e("fromUserfromUserfromUser" + (fromUser));
            if (fromUser) {

                seekBar.setProgress(progress);
            }

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            isDrag = true;
//           player.setPlayWhenReady(true);


        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            int seerbarProgress = seekBar.getProgress();

            player.seekTo(seerbarProgress * player.getDuration() / 100);

            player.setPlayWhenReady(true);

            isDrag = false;
        }
    }

    public SimpleExoPlayer getSimpleExoPlayer() {


        return player;

    }

    public void reset() {
        if (player != null) {
            contentPosition = player.getContentPosition();
            player.release();
            player = null;
//      adsLoader.setPlayer(null);
        }
    }

    public void release() {
        if (player != null) {
            player.release();
            player = null;
        }
        adsLoader.release();
    }

    // AdsMediaSource.MediaSourceFactory implementation.

    @Override
    public MediaSource createMediaSource(Uri uri) {
        return buildMediaSource(uri);
    }

    @Override
    public int[] getSupportedTypes() {
        // IMA does not support Smooth Streaming ads.
        return new int[]{C.TYPE_DASH, C.TYPE_HLS, C.TYPE_OTHER};
    }

    // Internal methods.

    private MediaSource buildMediaSource(Uri uri) {
        @ContentType int type = Util.inferContentType(uri);
        switch (type) {
            case C.TYPE_DASH:
                return new DashMediaSource.Factory(dashSourceFactory, dataSourceFactory).createMediaSource(uri);
            case C.TYPE_SS:
                return new SsMediaSource.Factory(CC, dataSourceFactory).createMediaSource(uri);
            case C.TYPE_HLS:
                return new HlsMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
            case C.TYPE_OTHER:

                return new ExtractorMediaSource.Factory(new DefaultHttpDataSourceFactory(Util.getUserAgent(mContext, "momo")
                )).createMediaSource(uri);
            default:

                throw new IllegalStateException("Unsupported type: " + type);
        }
    }


    private OnLoadding loadding;

    public void setOnLoaddingStateListener(OnLoadding o) {
        loadding = o;

    }

    public interface OnLoadding {

        abstract void callBack(Boolean b);


    }

}
