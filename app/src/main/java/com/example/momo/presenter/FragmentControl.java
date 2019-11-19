package com.example.momo.presenter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.momo.base.BaseActivity;
import com.example.momo.base.LazyFragment;
import com.example.momo.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class FragmentControl {

	private static final String TAG = "FragmentControl";

	private int constainsID;
	private FragmentManager fragmentmanager;
	private Fragment currentFragment;
	private static FragmentControl fragmentcontrol;
    private BaseActivity mContext;
	public static FragmentControl getInstance(BaseActivity activity,
											  int constainsID , List<LazyFragment> fs) {
		if (fragmentcontrol == null) {
			fragmentcontrol = new FragmentControl(activity, constainsID,fs);
		}
		return fragmentcontrol;
	}
  public List<LazyFragment>  fragments;
	private FragmentControl(BaseActivity activity, int constainsID, List<LazyFragment> fs) {
		currentFragment=null;
    	this.constainsID = constainsID;

		fragmentmanager = activity.getSupportFragmentManager();
		fragments=fs;
//		init();
	}
	private List<LazyFragment> lazyFragments =new ArrayList<LazyFragment>();

public FragmentControl  fragmentChange(LazyFragment fragment){
	//和当前一样的 就不改变
	if(currentFragment==fragment){
		return this;
	}
	FragmentTransaction transaction = fragmentmanager.beginTransaction();
		if(!isAddContain(fragment)){
			lazyFragments.add(fragment);
			//如果第一次添加 当前没有
			if(currentFragment==null){

				currentFragment=fragment;

				transaction.add(constainsID, fragment);

				transaction.commitAllowingStateLoss();
				LogUtil.e("commitAllowingStateLoss--");
//				transaction.show(currentFragment);
			}else {

			 //比如 第二个刚加进来 currentFragmen 不为null是第一个
				transaction.add(constainsID, fragment);


				transaction.hide(currentFragment);
				transaction.show(fragment);
				transaction.commitAllowingStateLoss();
				currentFragment=fragment;

			}
	}else {

			//在已经add过
		show(fragment);

		}


 	return this;
}

	private boolean isAddContain(LazyFragment showfragment) {

	 if(lazyFragments.size()==0)return false;
	for(LazyFragment contain:lazyFragments){

		if(showfragment==contain){
			return true;
		}


	}
	return false;
	}

	private FragmentControl show(Fragment fragment){
		FragmentTransaction transaction = fragmentmanager.beginTransaction();
		transaction.hide(currentFragment);
		transaction.show(fragment);
		transaction.commit();
		currentFragment=fragment;

return  this;
};


	private void init() {

		
		FragmentTransaction transaction = fragmentmanager.beginTransaction();

		for (Fragment fragment : fragments) {

//			if(fragmentmanager.findFragmentById(constainsID)==null){
//
//
//			}
			transaction.add(constainsID, fragment);
			//加入回退栈
//			transaction.addToBackStack(fragment.getId()+"");
			
		}
		
		transaction.commitAllowingStateLoss();

		hide();
		show(1);

	}
	

	public void show(int position) {
		LogUtil.e("c");
		hide();
		FragmentTransaction ft = fragmentmanager.beginTransaction();
		ft.show(fragments.get(position));

		ft.commitAllowingStateLoss();


	}
	

	public void hide() {
		FragmentTransaction ft = fragmentmanager.beginTransaction();
	
			for(Fragment fragment : fragments) {
				if(fragment != null) {
					ft.hide(fragment);

				}
			}

		ft.commitAllowingStateLoss();
		

	}
	public Fragment getFragment(int position) {

		return fragments.get(position);
	}
	



}
