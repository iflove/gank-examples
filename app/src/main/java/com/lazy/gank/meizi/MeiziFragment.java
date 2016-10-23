package com.lazy.gank.meizi;

import android.support.annotation.NonNull;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lazy.gank.R;
import com.lazy.gank.base.TabContentFragment;
import com.lazy.gank.entity.GankResult;
import com.lazy.gank.entity.GankResults;
import com.lazy.gank.interfaces.mvp.Call;
import com.lazy.gank.internal.di.components.FragmentComponent;
import com.lazy.gank.logging.Logcat;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by Sky on 2016/9/20.
 *
 * @Describe
 */

public class MeiziFragment extends TabContentFragment implements BaseQuickAdapter.RequestLoadMoreListener,
		MeiziContract.View, BaseQuickAdapter.OnRecyclerViewItemClickListener, Call<MeiziPresenter> {

	private MeiziAdapter mMeiziAdapter;

	@Inject
	MeiziPresenter  mMeiziPresenter;

	int mPageSize = 10;

	public MeiziFragment() {
	}

	@NonNull
	public static MeiziFragment newFragment() {
		return new MeiziFragment();
	}

	@Override
	protected void initializeInjector(@NonNull FragmentComponent component) {
		//DI 当前图表对象
		component.inject(this);
	}

	@Override
	protected void initialized() {
		mMeiziPresenter.setView(this);
	}

	@Override
	public void findViews() {
		super.findViews();
		mMeiziAdapter = new MeiziAdapter(R.layout.gank_meizi_item, new ArrayList<GankResult>());
		StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setAdapter(mMeiziAdapter);
	}

	@Override
	public void setViewListener() {
		super.setViewListener();
		mMeiziAdapter.openLoadAnimation();
		mMeiziAdapter.openLoadMore(mPageSize, true);
		mMeiziAdapter.setOnLoadMoreListener(this);
		mMeiziAdapter.setOnRecyclerViewItemClickListener(this);
	}

	@Override
	public void processExtraData() {
		super.processExtraData();
		mMeiziPresenter.subscribe();
	}

	@Override
	public void onRefresh() {
		page = 1;
		mMeiziAdapter.getData().clear();
		mMeiziPresenter.showMeizi(1, mPageSize);
		swipRefreshLayout.setRefreshing(false);
		Logcat.d("onRefresh");
	}

	@Override
	public void onLoadMoreRequested() {
		++page;
		Logcat.d("onLoadMoreRequested" + "page = " + page);
		Toast.makeText(getContext(), "第" + page + "页", Toast.LENGTH_SHORT).show();
		mMeiziPresenter.showMeizi(page, mPageSize);
	}

	@Override
	public void addMeizi(GankResults<GankResult> results) {
		if (!results.error) {
			mMeiziAdapter.addData(results.getResults());
			mMeiziAdapter.notifyDataChangedAfterLoadMore(true);
		} else {
			mMeiziAdapter.notifyDataChangedAfterLoadMore(false);
			showMessage("到底~\\(≧▽≦)/~啦啦啦");
		}

	}

	@Override
	public int getCount() {
		return mPageSize;
	}


	@Override
	public void showLoading() {
		super.showLoading();
	}

	@Override
	public void hideLoading() {
		super.hideLoading();
	}

	@Override
	public void onItemClick(View view, int pos) {
		showMessage(String.valueOf(pos));
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}

	@Override
	public MeiziPresenter  getPresenter() {
		return mMeiziPresenter;
	}
}
