package com.example.momo.activitys;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.binder.IMyAidlInterface;
import com.example.momo.R;
import com.example.momo.base.BaseActivity;
import com.example.momo.dao.entity.ChaofanBanJiang;
import com.example.momo.dao.entity.News;
import com.example.momo.dao.entity.User;
import com.example.momo.datebase.UserDataBase;
import com.example.momo.datebase.UserDateSoureImp;
import com.example.momo.datebase.UserRepository;
import com.example.momo.utils.LogUtil;
import com.example.momo.utils.ToastUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

@Route(path = "/acitvitys/OtherActvity")
public class OtherActvity extends BaseActivity {


    @BindView(R.id.image_lager)
    SimpleDraweeView imageLager;
    private ServiceConnect serviceConnect;
    private UserDataBase userDataBase;
    private File file;

    private static final String TAG = "MessengerActivity";
    public static final int MESSAGE_FROM_CLIENT=02;

    private Messenger mService;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.e(TAG, "ServiceConnection-->" + System.currentTimeMillis());
            //通过服务端返回的Binder创建Messenger
            mService = new Messenger(iBinder);
            //创建消息，通过Bundle传递数据
            Message message = Message.obtain(null, MESSAGE_FROM_CLIENT);
            Bundle bundle = new Bundle();
            bundle.putString("msg", "hello service,this is client");
            message.setData(bundle);
            try {
                //向服务端发送消息
                mService.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.e(TAG, "onServiceDisconnected-->binder died");
        }
    };
    private Intent serviceIntent;


    @Override
    public void initViews(Bundle savedInstanceState) {

         serviceIntent= new Intent();
        serviceIntent.setComponent(new ComponentName("com.example.binder","com.example.binder.MessengerService"));
//        Intent intent = new Intent(this, DanmuService.class);
//        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        HashMap<String,String> hashMap= new HashMap<String,String>();
        String string= (String) hashMap.put("juntao","hah");
        String string1= (String) hashMap.put("juntao","6");
        SparseArray sparseArray = new SparseArray<Integer>();
        LogUtil.e(string1+"'string"+string+"HashMap"+hashMap.size());

//        sparseArray.put(2, 20);
//        sparseArray.put(8, 30);
//        sparseArray.put(66, 40);
//        sparseArray.put(12, 90);
//        LogUtil.e("sparseArray" + sparseArray.keyAt(0));
//        LogUtil.e("sparseArray" + sparseArray.get(8));
//        LogUtil.e("sparseArray" + sparseArray.get(2));
//        LogUtil.e("sparseArray" + sparseArray.indexOfKey(3));
//        Intent intent=new Intent(this,DanmuService.class);
//        ButterKnife.bind(this);

//bindService(intent, serviceConnect, Context.BIND_AUTO_CREATE);


    }

    @Override
    protected void onNewIntent(Intent intent) {
//        android.R.id.content
        LogUtil.e("onNewIntent");
        super.onNewIntent(intent);
    }

    @Override
    protected void onRestart() {
        LogUtil.e("onRestart");

        super.onRestart();
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
  public void getStudent(ChaofanBanJiang chaofanBanJiang){

        ToastUtils.showToast(this,chaofanBanJiang.getName(),1000);

       LogUtil.e("'chaofanBanJiangchaofanBanJiang"+chaofanBanJiang.getName());


  }


    @Override
    public void initToolBar() {
      LinearLayout linearLayout= findViewById(R.id.other_layout);
//       View view2= LayoutInflater.from(this).inflate(R.layout.item_text_layout,linearLayout,true);
//        view2.setBackgroundColor(Color.BLUE);
        View view1=  LayoutInflater.from(this).inflate(R.layout.meger_layout,linearLayout,true);
        view1.setBackgroundColor(Color.BLUE);
        //        linearLayout.addView(view2);

        LogUtil.e("getExternalCacheDir"+getExternalCacheDir());
//        Glide.get(this).clearMemory();
//
//        ExecutorService executors= Executors.newSingleThreadExecutor();
//        executors.execute(() ->{        Glide.get(this).clearDiskCache();
//        });

        Executors.newSingleThreadExecutor().execute( new Runnable() {
            @Override
            public void run() {

//                RequestOptions requestBuilder=new RequestOptions().diskCacheStrategy()

                FutureTarget<File> fileFutureTask=Glide.with(OtherActvity.this).asFile().listener(new RequestListener<File>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<File> target, boolean isFirstResource) {
                        LogUtil.e("onLoadFailed");
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(File resource, Object model, Target<File> target, DataSource dataSource, boolean isFirstResource) {
                        LogUtil.e(resource.getAbsolutePath()+"isFirstResource"+isFirstResource);
                        return false;
                    }
                }).load("http://pzish1vj4.bkt.clouddn.com/ic_006.jpg").submit();
                try {
                    file= fileFutureTask.get();
                    LogUtil.e("filefile"+file.exists()+file.getAbsolutePath()+file.length());
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {

                }
            }
        });


        Glide.with(this).load("http://pzish1vj4.bkt.clouddn.com/ic_006.jpg").diskCacheStrategy(DiskCacheStrategy.ALL).into(imageLager);
//        imageLager.setImageURI("http://pzish1vj4.bkt.clouddn.com/ic_006.jpg");
     serviceConnect=  new ServiceConnect();
        imageLager.setOnClickListener(view->{

            Observable.create(new ObservableOnSubscribe<Object>() {
                @Override
                public void subscribe(ObservableEmitter<Object> e) throws Exception {

                    bindService(serviceIntent, serviceConnect, Context.BIND_AUTO_CREATE);
                    LogUtil.e("添加");
                    User user=new User();
                    user.setAge(20);
                    user.setName("juntao"+ UUID.randomUUID().toString());
                    user.setId((int) Math.random());
                    userRepository.insertUser(user);

                    e.onComplete();


                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                    .subscribe(new Consumer<Object>() {
                        @Override
                        public void accept(Object o) throws Exception {

                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {

                        }
                    }, new Action() {
                        @Override
                        public void run() throws Exception {
                            LogUtil.e("更新");
                            loadData();
                        }
                    });
//

        });

    }

    private void textArray() {
        LinkedHashMap linkedHashMap= new LinkedHashMap<Integer,String>(10,0.75f,true);
        linkedHashMap.put(1,"taotao1");
        linkedHashMap.put(2,"taotao2");
        linkedHashMap.put(3,"taotao3");
        linkedHashMap.put(4,"taotao4");
        Iterator<Map.Entry<Integer,String>> iterator= linkedHashMap.entrySet().iterator();
        while (iterator.hasNext()){
            LogUtil.e("linkedHashMap"+iterator.next().getKey());
        }
        linkedHashMap.get(2);

        Iterator<Map.Entry<Integer,String>> iterator2= linkedHashMap.entrySet().iterator();
        while (iterator2.hasNext()){
//            iterator.remove();
            LogUtil.e("linkedHashMap---"+iterator2.next().getKey());
        }
        ArrayDeque arrayDeque=new ArrayDeque<>();
        arrayDeque.add(1);
        arrayDeque.add(2);
        arrayDeque.add(3);


        Boolean b=arrayDeque.offer(4);

        LogUtil.e(arrayDeque.getFirst()+"getFirst"+arrayDeque.getLast()+"offer"+arrayDeque.size());
        ArrayList arrayList=new ArrayList<>();
        HashSet<Integer> hashSet=new HashSet<Integer>();
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(3);
        hashSet.add(4);
        Iterator<Integer> iterator3= hashSet.iterator();
        while (iterator3.hasNext()){
    ;
        LogUtil.e("hashSet"+iterator3.next().intValue() );
        }
    }

    @Override
    protected int getLayoutID() {
        return R.layout.text_layout01;
    }
    UserRepository userRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         userDataBase= UserDataBase.getInstance(this);
         userRepository=  UserRepository.getInstance(new UserDateSoureImp(userDataBase.userDao()));
//       loadData();
    }


    private void loadData() {




        Disposable sd = userRepository.getAllUsers().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<User>>() {
            @Override
            public void accept(List<User> users) throws Exception {
LogUtil.e("List"+users.size());

            }
        });

    }
    private void textBlocking(){
        SynchronousQueue synchronousQueue= new SynchronousQueue<News>();
        LinkedBlockingDeque linkedBlockingDeque=new LinkedBlockingDeque<>();
        LinkedBlockingQueue linkedBlockingQueue=new  LinkedBlockingQueue<>();

//        boolean offer(E e):将元素追加到队列末尾,若添加成功则返回true。
//        E poll():从队首删除并返回该元素。
//        E peek():返回队首元素，但是不删除
        ArrayBlockingQueue arrayBlockingQueue=new ArrayBlockingQueue(16);




    }
    class ServiceConnect implements ServiceConnection {


        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IMyAidlInterface iMyAidlInterface=  IMyAidlInterface.Stub.asInterface(service);
            try {
                iMyAidlInterface.setRemoteDate("hello");
            } catch (RemoteException e) {
                LogUtil.e("RemoteException"+e.getMessage().toString());
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }

        @Override
        public void onBindingDied(ComponentName name) {

        }
    }

    class MyTask extends AsyncTask<String,String,String>{


        @Override
        protected String doInBackground(String... strings) {
            return null;
        }
    }

    @Override
    protected void onStop() {
//        Intent intent=new Intent(this,DanmuService.class);
//        stopService(intent);
        super.onStop();

    }

    @Override
    protected void onDestroy() {

        unbindService(serviceConnect);
        super.onDestroy();

    }

    @Override
    public void onBackPressed() {
//             finish();
        super.onBackPressed();
    }
}
