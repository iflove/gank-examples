package com.lazy.gank.all;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lazy.gank.base.TabContentFragment;
import com.lazy.gank.entity.GankResult;
import com.lazy.gank.entity.GankResults;
import com.lazy.gank.interfaces.mvp.Call;
import com.lazy.gank.internal.di.components.FragmentComponent;
import com.lazy.gank.logging.Logcat;
import com.lazy.gank.recommend.GankListAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by Sky on 2016/9/21.
 *
 * @Describe
 */

public class GankFragment extends TabContentFragment implements BaseQuickAdapter.OnRecyclerViewItemClickListener,
		BaseQuickAdapter.RequestLoadMoreListener, GankContract.View, Call<GankPresenter> {
	int mPageSize = 30;

	GankListAdapter mGankListAdapter;

	@Inject
	GankPresenter mPresenter;

	String mCategory;

	public GankFragment() {

	}

	@NonNull
	public static GankFragment newFragment() {
		return new GankFragment();
	}

	@Override
	protected void initializeInjector(@NonNull FragmentComponent component) {
		component.inject(this);
	}

	@Override
	protected void initialized() {
		mPresenter.setView(this);
	}

	@Override
	public void findViews() {
		super.findViews();
		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);
		mGankListAdapter = new GankListAdapter(new ArrayList<GankResult>(0));
		recyclerView.setAdapter(mGankListAdapter);
	}

	@Override
	public void setViewListener() {
		super.setViewListener();
		mGankListAdapter.openLoadAnimation();
		mGankListAdapter.openLoadMore(mPageSize, true);
		mGankListAdapter.setOnRecyclerViewItemClickListener(this);
		mGankListAdapter.setOnLoadMoreListener(this);
	}

	@Override
	public void processExtraData() {
		super.processExtraData();
		mCategory = getArguments().getString("Category");

		mPresenter.subscribe();

	}

	@Override
	public void onRefresh() {
		page = 1;
		mGankListAdapter.getData().clear();
		mPresenter.showGank(page, mPageSize);
	}

	@Override
	public void onLoadMoreRequested() {
		++page;
		Logcat.d("onLoadMoreRequested" + "page = " + page);
		Toast.makeText(getContext(), "第" + page + "页", Toast.LENGTH_SHORT).show();
		mPresenter.showGank(page, mPageSize);
	}

	@Override
	public void onItemClick(View view, int i) {
		GankResult gankResult = mGankListAdapter.getData().get(i);
		showMessage(gankResult.getUrl());
	}

	@Override
	public String getCategory() {
		return mCategory;
	}

	@Override
	public int getCount() {
		return mPageSize;
	}

	@Override
	public void showGank(GankResults<GankResult> results) {
		if (!results.error) {
			mGankListAdapter.addData(results.getResults());
			mGankListAdapter.notifyDataChangedAfterLoadMore(true);
		} else {
			mGankListAdapter.openLoadMore(false);
			showMessage("到底~\\(≧▽≦)/~啦啦啦");
			mGankListAdapter.notifyDataChangedAfterLoadMore(false);
		}
	}

	@Override
	public GankPresenter getPresenter() {
		return mPresenter;
	}
}
