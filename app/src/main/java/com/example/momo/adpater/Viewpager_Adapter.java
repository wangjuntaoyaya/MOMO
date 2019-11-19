package com.example.momo.adpater;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class Viewpager_Adapter extends PagerAdapter {

    private ArrayList<String> res;
    private Context context;

      public  Viewpager_Adapter(Context c,ArrayList<String > r){
          res=r;
       context=c;
      }

    @Override
    public int getCount() {
        return 1000;
    }




    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        ImageView imageView1=new ImageView(container.getContext());

        imageView1.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(context)
                .load(res.get(position%res.size())).apply(RequestOptions.bitmapTransform(new RoundedCorners(12))).into(imageView1);




//        SimpleDraweeView imageView=new SimpleDraweeView(container.getContext());
//         imageView.setImageURI(res.get(position%res.size()));
//
//        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,200));
//        imageView.setClickable(true);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ToastUtils.showToast(container.getContext(),""+position,1000);
//            }
//        });
//
//        RoundingParams params = new RoundingParams();
//        params.setRoundAsCircle(false);
//        params.setBorder(Color.BLUE,5);
//
//        params.setCornersRadius(16);
//
//
//
//        imageView.getHierarchy().setRoundingParams(params);
//        imageView.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER);


        container.addView(imageView1);

        return imageView1;
    }
  public int getOriCount(){

          return res.size();
    }
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {


        return  view==object;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {


        container.removeView((View) object);
    }


}
