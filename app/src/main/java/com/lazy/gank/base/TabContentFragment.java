package com.lazy.gank.base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.lazy.gank.R;

import butterknife.BindView;

/**
 * Created by Sky on 2016/9/14.
 *
 * @Describe
 */

public abstract class TabContentFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

	@BindView(R.id.recyclerView)
	protected RecyclerView recyclerView;
	@BindView(R.id.swip_refresh_layout)
	protected SwipeRefreshLayout swipRefreshLayout;
	@BindView(R.id.loading_progress)
	protected ProgressBar loadingProgress;

	//
	protected int page = 1;

	@Override
	public final int getLayoutId() {
		return R.layout.fragment_tab_content;
	}

	@Override
	public void findViews() {

	}

	@Override
	public void setViewListener() {
		swipRefreshLayout.setOnRefreshListener(this);
	}

	@Override
	public void processExtraData() {

	}

	public int getCurrentPage() {
		return page;
	}

	public void showLoading() {
		loadingProgress.setVisibility(View.VISIBLE);
	}

	public void hideLoading() {
		loadingProgress.setVisibility(View.GONE);
	}
}
