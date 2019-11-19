package com.example.momo.fragment;

import android.net.Uri;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.momo.R;
import com.example.momo.adpater.ChannelAdapter;
import com.example.momo.base.LazyFragment;
import com.example.momo.utils.LogUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import butterknife.BindView;

public class ChannelFragment extends LazyFragment {
    @BindView(R.id.all)
    SimpleDraweeView all;
    @BindView(R.id.guichu)
    SimpleDraweeView guichu;
    @BindView(R.id.vlog)
    SimpleDraweeView vlog;
    @BindView(R.id.xuexi)
    SimpleDraweeView xuexi;
    @BindView(R.id.lol)
    SimpleDraweeView lol;
    @BindView(R.id.chanel_recyclerview)
    RecyclerView chanelRecyclerview;

    @Override
    protected void loadData() {
        LogUtil.e("ChannelFragment");
    }

    @Override
    protected boolean enableRefush() {
        return true;
    }

    @Override
    public int getLayoutById() {
        return R.layout.channel_layout;
    }

    @Override
    public void initData(boolean b) {



            all.setImageURI(Uri.parse(alll));
        guichu.setImageURI(Uri.parse(guichuu));
        vlog.setImageURI(Uri.parse(vlogg));
        xuexi.setImageURI(Uri.parse(xuexii));
        lol.setImageURI(Uri.parse(loll));



        ArrayList<Integer> arrayList=    new ArrayList<Integer>();
        arrayList.add(R.drawable.ic_category_t15);
        arrayList.add(R.drawable.ic_category_t20);
        arrayList.add(R.drawable.ic_category_t21);
        arrayList.add(R.drawable.ic_category_t19);
        arrayList.add(R.drawable.ic_category_t22);
        arrayList.add(R.drawable.ic_category_t23);
        arrayList.add(R.drawable.ic_category_t24);
        arrayList.add(R.drawable.ic_category_t25);
        arrayList.add(R.drawable.ic_category_t26);
        arrayList.add(R.drawable.ic_category_t27);
        arrayList.add(R.drawable.ic_category_t28);
        arrayList.add(R.drawable.ic_category_t29);
        arrayList.add(R.drawable.ic_category_t30);
        arrayList.add(R.drawable.ic_category_t31);
        arrayList.add(R.drawable.ic_category_t32);
        arrayList.add(R.drawable.ic_category_t33);
        arrayList.add(R.drawable.ic_category_t34);
        arrayList.add(R.drawable.ic_category_t37);
        arrayList.add(R.drawable.ic_category_t39);
        arrayList.add(R.drawable.ic_category_t47);
        arrayList.add(R.drawable.ic_category_t51);
        arrayList.add(R.drawable.ic_category_t54);
        arrayList.add(R.drawable.ic_category_t59);
        arrayList.add(R.drawable.ic_category_t60);
        arrayList.add(R.drawable.ic_category_t65);
        arrayList.add(R.drawable.ic_category_t161);
        arrayList.add(R.drawable.ic_category_t76);
        arrayList.add(R.drawable.ic_category_t75);
        arrayList.add(R.drawable.ic_category_t71);
        arrayList.add(R.drawable.ic_category_t82);
        arrayList.add(R.drawable.ic_category_t83);
        arrayList.add(R.drawable.ic_category_t85);
        arrayList.add(R.drawable.ic_category_t86);
        arrayList.add(R.drawable.ic_category_t95);
        arrayList.add(R.drawable.ic_category_t96);
        arrayList.add(R.drawable.ic_category_t98);
        arrayList.add(R.drawable.ic_category_t119);



        GridLayoutManager gridLayoutManager=   new GridLayoutManager(getContext(),4);


        chanelRecyclerview.setLayoutManager(gridLayoutManager);
        chanelRecyclerview.setAdapter(new ChannelAdapter(getContext(),arrayList));



    }
    @Override
    protected void setOnRefreshLitener(SwipeRefreshLayout swipeRefreshLayout) {

    }

    @Override
    public void onHiddenChanged(boolean hidden) {

        super.onHiddenChanged(hidden);
    }

    @Override
    public boolean useCache() {
        return false;
    }

    private static  final String alll="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1570732849295&di=c0b512fe004a915ec8f58088764e1f4e&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201708%2F31%2F20170831182516_tRAxz.thumb.700_0.png";
    private static  final String loll="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1570732604562&di=6d147a5ae5e6f7f64bf23bf54bb891c9&imgtype=0&src=http%3A%2F%2Fwww.uzzf.com%2Fup%2F2014-4%2F105025_6736478507518.jpg";
    private static  final String xuexii="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1571327747&di=66460a308e494a71295ea680f0aff0c7&imgtype=jpg&er=1&src=http%3A%2F%2Fgss0.baidu.com%2F-Po3dSag_xI4khGko9WTAnF6hhy%2Fzhidao%2Fpic%2Fitem%2F86d6277f9e2f0708bb969b4de824b899a801f2e8.jpg";
    private static  final String vlogg="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1571327714&di=bfcb9650a4b264b201cb1f799d30fa67&imgtype=jpg&er=1&src=http%3A%2F%2Fi1.hdslb.com%2Fbfs%2Farchive%2Fea80f4661bdda7c2365ee5a680f26f374e9f5ff3.jpg";
    private static  final String guichuu="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1571327694&di=659cdc0633886df26f906185ba274f2d&imgtype=jpg&er=1&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fq_70%2Cc_zoom%2Cw_640%2Fimages%2F20190217%2F44dce07ef93646f08fe676235ab73e3e.jpeg";




}
