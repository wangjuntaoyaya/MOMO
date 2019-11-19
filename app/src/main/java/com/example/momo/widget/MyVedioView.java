package com.example.momo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatSeekBar;

import com.example.momo.R;
import com.example.momo.manager.PlayerManager;
import com.google.android.exoplayer2.SimpleExoPlayer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyVedioView extends RelativeLayout {


    private  Myplayer myplayer;
    @BindView(R.id.paly)
    ImageView paly;
    @BindView(R.id.progress_seekbar)
    AppCompatSeekBar progressSeekbar;
    @BindView(R.id.video_progress_ing)
    TextView videoProgressIng;
    @BindView(R.id.video_totoltime)
    TextView videoTotoltime;
    @BindView(R.id.video_zoom)
    ImageView videoZoom;
    private PlayerManager playerManager;


    public MyVedioView(Context context) {
        super(context);
    }

    public MyVedioView(Context context, AttributeSet attrs) {
        super(context, attrs);

        View view = LayoutInflater.from(getContext()).inflate(R.layout.cotntrol_view_layout, this, false);
        ButterKnife.bind(this,view);
         myplayer=new Myplayer(getContext());

        myplayer.setUseController(false);
        RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        myplayer.setLayoutParams(layoutParams);

        RelativeLayout.LayoutParams controll= (LayoutParams) view.getLayoutParams();
//        controll.bottomMargin=22;
        controll.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
     addView(myplayer);
        playerManager =new PlayerManager(getContext());
        addView(view);

    }


    public MyVedioView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyVedioView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {



        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec( myplayer.getMeasuredHeight(),MeasureSpec.EXACTLY));


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {


        super.onSizeChanged(w, h, oldw, oldh);
    }

   public void PlayerVideo(String url){

       playerManager.init();
//       playerManager.play(url,myplayer,t);
       
       
      SimpleExoPlayer player= playerManager.getSimpleExoPlayer();


      




   }

  public PlayerManager getPlayerManager(){
        
        return  playerManager;
  }

    @OnClick({R.id.paly, R.id.progress_seekbar, R.id.video_progress_ing, R.id.video_totoltime, R.id.video_zoom})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.paly:
                break;
            case R.id.progress_seekbar:
                break;
            case R.id.video_progress_ing:
                break;
            case R.id.video_totoltime:
                break;
            case R.id.video_zoom:
                break;
        }
    }
}
