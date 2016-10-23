package com.lazy.gank.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lazy.gank.BuildConfig;
import com.lazy.gank.app.MyApplication;
import com.lazy.gank.interfaces.BaseViewInterface;
import com.lazy.gank.interfaces.IConstant;
import com.lazy.gank.interfaces.fragment.FragmentLifecycleCallbacks;
import com.lazy.gank.interfaces.mvp.BasePresenter;
import com.lazy.gank.interfaces.mvp.Call;
import com.lazy.gank.internal.di.HasComponent;
import com.lazy.gank.internal.di.components.ActivityComponent;
import com.lazy.gank.internal.di.components.ApplicationComponent;
import com.lazy.gank.internal.di.components.FragmentComponent;
import com.lazy.gank.logging.MyFragmentLifecycle;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lazy on 16/8/14.
 */
public abstract class BaseFragment extends Fragment implements IConstant, BaseViewInterface, HasComponent<FragmentComponent> {
	protected final String TAG = this.getClass().getCanonicalName();
	/**
	 * Acitivity对象
	 **/
	protected Activity mActivity;
	/**
	 * 当前显示的内容
	 **/
	protected View mRootView;

	protected LayoutInflater mInflater;
	protected FragmentLifecycleCallbacks<Fragment> mMyFragmentLifecycle;

	Unbinder mUnbinder;

	//dagger
	protected FragmentComponent mFragmentComponent;

	public BaseFragment() {
		mMyFragmentLifecycle = new MyFragmentLifecycle();
	}

	@Override
	public void onAttachFragment(Fragment childFragment) {
		super.onAttachFragment(childFragment);
		mMyFragmentLifecycle.onAttachFragment(this, childFragment);
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		initializeInjector();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.mActivity = activity;
		mMyFragmentLifecycle.onActivityAttach(this, activity);
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setRetainInstance(true);//Fragment恢复时会跳过onCreate()和onDestroy()方法 不能在onCreate()中放置一些初始化逻辑
		mMyFragmentLifecycle.onFragmentCreate(this);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		this.mInflater = inflater;
		if (mRootView == null) {
			mRootView = inflater.inflate(getLayoutId(), container, false);
		}
		mUnbinder = ButterKnife.bind(this, mRootView);
		ViewGroup parent = (ViewGroup) mRootView.getParent();
		if (parent != null) {
			parent.removeView(mRootView);
		}
		findViews();
		setViewListener();
		processExtraData();
		mMyFragmentLifecycle.onFragmentCreateView(this);
		return mRootView;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mMyFragmentLifecycle.onViewCreated(this);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mMyFragmentLifecycle.onActivityCreated(this, savedInstanceState);
	}

	@Override
	public void onStart() {
		super.onStart();
		mMyFragmentLifecycle.onFragmentStarted(this);
	}

	@Override
	public void onResume() {
		super.onResume();
		mMyFragmentLifecycle.onFragmentResumed(this);
		if (!BuildConfig.DEBUG) {
			MobclickAgent.onResume(this.getContext());
			MobclickAgent.onPageStart(this.getClass().getSimpleName());
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		mMyFragmentLifecycle.onFragmentPaused(this);
		if (!BuildConfig.DEBUG) {
			MobclickAgent.onPause(this.getContext());
			MobclickAgent.onPageEnd(this.getClass().getSimpleName());
		}
	}

	@Override
	public void onStop() {
		super.onStop();
		mMyFragmentLifecycle.onFragmentStopped(this);
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		mMyFragmentLifecycle.onFragmentDestroyView(this);
		mUnbinder.unbind();
		unsubscribePresenter();

	}

	private void unsubscribePresenter() {
		//
		if (this instanceof Call) {
			Call call = (Call) this.getClass().cast(this);
			Object presenter = call.getPresenter();
			if (presenter instanceof BasePresenter) {
				BasePresenter presenter1 = (BasePresenter) presenter;
				presenter1.unsubscribe();
			}
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mMyFragmentLifecycle.onFragmentDestroyed(this);
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mMyFragmentLifecycle.onFragmentDetach(this);
		this.mActivity = null;
	}

	public abstract
	@LayoutRes
	int getLayoutId();

	@Override
	public abstract void findViews();

	@Override
	public abstract void setViewListener();

	@Override
	public abstract void processExtraData();

	@Override
	public void startActivity(Class<? extends Activity> clazz) {
		Intent intent = new Intent(getActivity(), clazz);
		startActivity(intent);
	}

	@NonNull
	@Override
	public MyApplication getMyAppication() {
		return MyApplication.getAppication();
	}

	@Override
	public void showMessage(@NonNull String msg) {
		Snackbar.make(mRootView, msg, Snackbar.LENGTH_SHORT).show();
	}

	private void initializeInjector() {
		mFragmentComponent = getComponent(ActivityComponent.class).getFragmentComponent();
		mFragmentComponent.inject(this);
		initializeInjector(mFragmentComponent);
		initialized();
	}

	/**
	 * Get the Main Application component for dependency injection.
	 *
	 * @return {@link ApplicationComponent}
	 */
	protected ApplicationComponent getApplicationComponent() {
		return getMyAppication().getApplicationComponent();
	}

	@Override
	public FragmentComponent getComponent() {
		return this.mFragmentComponent;
	}

	/**
	 * Gets a component for dependency injection by its type.
	 */
	@SuppressWarnings("unchecked")
	protected <C> C getComponent(Class<C> componentType) {
		return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
	}

	/**
	 * Fragment onAttach 时 注入 图表对象 (graphs)
	 *
	 * @param component
	 */
	protected abstract void initializeInjector(@NonNull FragmentComponent component);

	/**
	 * 完成注入 可以使用	@Inject 成员变量
	 */
	protected abstract void initialized();
}
