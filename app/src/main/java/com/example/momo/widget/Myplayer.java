package com.example.momo.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.momo.R;
import com.example.momo.activitys.PlayVedioActivity;
import com.example.momo.base.BaseActivity;
import com.example.momo.utils.CommonUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.exoplayer2.ui.PlayerView;

import java.util.Timer;
import java.util.TimerTask;

public class Myplayer extends PlayerView {

    private Toolbar toolbar;
    private SeekBarModle seekBarModle02;
    private SeekBarModle seekBarModle01;
    private Context mcontext;
    private View view;
    private ImageView imageView;
    private SeekBar seekBar;
    private Context c;
    private SimpleExoPlayer mPlayer;
    private float voice;


    public Myplayer(Context context) {
        super(context);
    }

    public Myplayer(Context context, AttributeSet attrs) {
        super(context, attrs);

//        setControllerHideOnTouch(true);
       setControllerAutoShow(false);
setControllerShowTimeoutMs(1600);
        view = LayoutInflater.from(context).inflate(R.layout.player_voice_item, null, false);
        imageView = view.findViewById(R.id.voice);
        seekBar = view.findViewById(R.id.voice_seekbar);
        c = context;

        addView(view);

        seekBar.setProgress(100);
        mcontext = context;
        setWindowBrightness(10);
        seekBarModle01 = new SeekBarModle();
        seekBarModle01.setProgress(100);
        seekBarModle01.setCurrentPositon(100);
        seekBarModle02 = new SeekBarModle();
        seekBarModle02.setCurrentPositon(100);
        seekBarModle02.setProgress(100);
        view.setVisibility(View.GONE);
        initCoverView();
    }
private View preview;
    private  SimpleDraweeView previewImageView;
  public void  initCoverView(){

     preview=  LayoutInflater.from(mcontext).inflate(R.layout.player_child_layout,this,false);

      preview.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
       previewImageView=preview.findViewById(R.id.preview_image);
        addView(preview);

      preview.setOnClickListener(new OnClickListener() {
          @Override
          public void onClick(View v) {
              getPlayer().setPlayWhenReady(true);
              setPreImageLayoutVisility(false);

          }
      });

   }

  public void  setPreViewImageView(String path){
      if(previewImageView!=null){
          previewImageView.setImageURI(Uri.parse(path));
      }

   }
   public void setPreImageLayoutVisility(boolean b){
      int  v=b==true?View.VISIBLE:View.GONE;
       preview.setVisibility(v);

   }

    @Override
    public void setPlayer(Player player) {

        super.setPlayer(player);
        toolbar = ((PlayVedioActivity) mcontext).getPalyerToolBar();
        toolbar.getParent().requestDisallowInterceptTouchEvent(false);

        if (player instanceof SimpleExoPlayer) {
            mPlayer = (SimpleExoPlayer) getPlayer();
            voice = mPlayer.getVolume();

        }
    }

    private void setWindowBrightness(int brightness) {
        if (mcontext instanceof PlayVedioActivity) {

            Window window = ((PlayVedioActivity) mcontext).getWindow();
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.screenBrightness = brightness / 255.0f;
            window.setAttributes(lp);
        }
    }

    public Myplayer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        //如果new 的话不走构造第二方法，不用c
        int[] w = CommonUtil.getScreenPix((BaseActivity) getContext());
        int www =0;
        int hhh =0;
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){

            www = MeasureSpec.makeMeasureSpec((w[0]), MeasureSpec.AT_MOST);
            hhh = MeasureSpec.makeMeasureSpec((int) (w[1]-CommonUtil.getStatusBarHeight(getContext())), MeasureSpec.AT_MOST);


        }else {

            www = MeasureSpec.makeMeasureSpec((w[0]), MeasureSpec.AT_MOST);
            hhh = MeasureSpec.makeMeasureSpec((int) (0.56 * 1.0f * w[0]), MeasureSpec.AT_MOST);
        }

//        int width = getDefaultSize(0,widthMeasureSpec);
//        int height = getDefaultSize(0,heightMeasureSpec);


        //recyclerview 两边的padding30
//              www=www-60;



        super.onMeasure(www, hhh);


//        setMeasuredDimension(w[0], (int) (0.56*1.0f*w[0]));
//        LogUtil.e("getMeasuredWidth"+getMeasuredWidth());
//        LogUtil.e("w"+w[0]);
    }

    private int width;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = getMeasuredWidth();


//        FrameLayout.LayoutParams layoutParams= (FrameLayout.LayoutParams) view.getLayoutParams();
//        layoutParams.leftMargin=100;
//        layoutParams.topMargin=1200;
//        requestLayout();


        super.onSizeChanged(w, h, oldw, oldh);
    }

    public SeekBar getSeerBar() {

        return seekBar;


    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        setControllerVisibilityListener(new PlayerControlView.VisibilityListener() {
            @Override
            public void onVisibilityChange(int visibility) {


            }
        });

    }

    int downX, downY;
    int moveX, moveY;
    private boolean isShow = false;


    long currentTime = 0;

    boolean singleClick = false;
    boolean doubleClick = false;
    int dispatchDownX, dispatchDownY;
    int dispatchMoveX, dispatchMoveY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {


        int a = ev.getAction();
        switch (a) {

            case MotionEvent.ACTION_DOWN: {

                if(!getPlayer().getPlayWhenReady()){

                }

                dispatchDownX = (int) ev.getX();
                dispatchDownY = (int) ev.getY();
                //重置
                doubleClick = false;
                singleClick = false;
//               Toast.makeText(mcontext,(currentTime+"danji" ), Toast.LENGTH_LONG).show();
                long systemtime = System.currentTimeMillis();

                if ((systemtime - currentTime) <= 250 && currentTime > 0) {
                    doubleClick = true;

                    if (mPlayer != null) {

                        if (!mPlayer.getPlayWhenReady()) {
                            mPlayer.setPlayWhenReady(true);
                        } else {
                            mPlayer.setPlayWhenReady(false);

                        }

                    }


                    Toast.makeText(mcontext, (currentTime + "双击成功" + (systemtime - currentTime)), Toast.LENGTH_LONG).show();

                    currentTime = 0;
                } else {
                    singleClick = true;
                    currentTime = System.currentTimeMillis();
                }
                break;
            }
            case MotionEvent.ACTION_MOVE: {

                dispatchMoveY = (int) ev.getX();
                dispatchMoveX = (int) ev.getY();

                if (Math.abs(dispatchMoveY - dispatchDownY) < 6 && Math.abs(dispatchMoveX - dispatchDownX) < 6 && !doubleClick) {
                    singleClick = true;


                }


            }
            case MotionEvent.ACTION_UP: {


                if (doubleClick) {



                } else {


                }


//               timerForHideCotroller();


                if (onclickStateListener != null) {
                    //回调判断是否单击双击
                    if (singleClick) {
                        doubleClick = false;
                        onclickStateListener.OnSingleClick(this);

                    }
                    {
                        singleClick = false;
                        onclickStateListener.OnDoubleClick(this);

                    }


                    //有一种状况是  控制布局显示 但是没播放   如果播放会自动隐藏 这里处理不隐藏
                    if (!getPlayer().getPlayWhenReady()) {
                        //

                    }
                    break;
                }
            }

        }

        return super.dispatchTouchEvent(ev);
    }

    private void timerForHideCotroller() {


    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        int a = ev.getAction();
        switch (a) {

            case MotionEvent.ACTION_DOWN: {
                getParent().requestDisallowInterceptTouchEvent(true);
                downX = (int) ev.getX();
                downY = (int) ev.getY();

                break;
            }
            case MotionEvent.ACTION_MOVE: {

                if (getPlayer().getPlayWhenReady())
                    getParent().requestDisallowInterceptTouchEvent(true);

                //如果没播放 或者显示view 就屏蔽以下事件
//                if(!getPlayer().getPlayWhenReady()||isShowing){
//                    //针对第二个条件 如果播放 显示音频控件
//
//                  return super.onTouchEvent(ev);
//                }
                moveX = (int) ev.getX();
                moveY = (int) ev.getY();

                if (Math.abs(moveX - downX) < Math.abs(moveY - downY) && Math.abs(moveY - downY) > 12 && !isShow) {


                    view.setVisibility(View.VISIBLE);
                    isShow = true;
                    downY = moveY;
                }


                //判断是否为左边还是右边 左边为亮度 右边为声音大小
                if (isShow) {
                    int del = moveY - downY;
                    if (isLeft(downX)) {

                        //左边

                        showBrightness(del);


                    } else {


                        showVoice(del);

                    }
                    downY = moveY;

                }


                break;
            }
            case MotionEvent.ACTION_UP: {


                //检查是否在播放  暂停的话 隐藏进度条
                if (!getPlayer().getPlayWhenReady()) {
                    timerForHideCotroller();
                }
                //如果正在 延迟发送 防止再次点击
                if (!isShowing) {
                    startTimer();
                }
                isShow = false;

                break;
            }
        }
        if (getPlayer().getPlayWhenReady()) {

            getParent().requestDisallowInterceptTouchEvent(true);


        } else {
            getParent().requestDisallowInterceptTouchEvent(false);
        }
        super.onTouchEvent(ev);
//  如果播放状态就 就不能滚动 自己消费


        return true;
    }

    //右边
    private void showVoice(int del) {

        seekBar.setProgress(seekBarModle02.getCurrentPositon());
        int temp = seekBarModle02.getCurrentPositon() - del;

        if (temp > 100) {
            temp = 100;
        }
        if (temp < 0) {
            temp = 0;
        }
        seekBar.setProgress(temp);

        imageView.setImageResource(R.drawable.ic_radio);
        //记录当前的进度
        seekBarModle02.setCurrentPositon(temp);

    }

    private int Voice = 100;
    private int brightness = 100;

    //向下是 为正 声音减小
    private void showBrightness(int del) {
        seekBar.setProgress(seekBarModle01.getCurrentPositon());
        int temp = seekBarModle01.getCurrentPositon() - del;
        if (temp > 100) {
            temp = 100;
        }
        if (temp < 0) {
            temp = 0;
        }
        seekBar.setProgress(temp);

        imageView.setImageResource(R.drawable.ic_brightness_2);
        //记录当前的进度
        seekBarModle01.setCurrentPositon(temp);

    }

    private boolean isLeft(int x) {

        boolean a = false;
        if (x < width / 2) {

            a = true;

        }

        return a;

    }

    private void isPlayering(boolean b) {


    }

    Handler handler = new Handler(Looper.getMainLooper()) {


        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case 0: {
                    view.setVisibility(View.GONE);
                    isShowing = false;

                    break;

                }
                case 1: {

                    setUseController(true);


                    break;

                }
            }


            super.handleMessage(msg);
        }
    };
    //一秒后 隐藏控件
    boolean isShowing = false;

    private void startTimer() {
        isShowing = true;
        handler.sendEmptyMessageDelayed(0, 300);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {


            }
        }, 1000);


    }

    class SeekBarModle {
        private int progress;

        public int getProgress() {
            return progress;
        }

        public int getCurrentPositon() {
            return currentPositon;
        }

        private int currentPositon;


        public void setProgress(int progress) {
            this.progress = progress;
        }

        public void setCurrentPositon(int currentPositon) {
            this.currentPositon = currentPositon;
        }
    }


    interface OnclickStateListener {

        void OnSingleClick(Myplayer myplayer);

        void OnDoubleClick(Myplayer myplayer);


    }

    private OnclickStateListener onclickStateListener;

    public void setOnClickStateListener(OnclickStateListener l) {

        onclickStateListener = l;

    }

    public interface OnPlayingListener {

        void canScroll(boolean b);

    }

    private OnPlayingListener onPlayingListener;

    public void setOnPlayingListener(OnPlayingListener l) {

        onPlayingListener = l;

    }

}
