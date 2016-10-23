package com.lazy.gank.recommend;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lazy.gank.R;
import com.lazy.gank.base.BaseFragment;
import com.lazy.gank.entity.DayGankResults;
import com.lazy.gank.entity.GankResult;
import com.lazy.gank.interfaces.mvp.Call;
import com.lazy.gank.internal.di.components.FragmentComponent;
import com.lazy.gank.widget.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Sky on 2016/9/12.
 *
 * @Describe
 */

public class RecommendGankFragment extends BaseFragment implements GankRecommendContract.View, View.OnClickListener, Call<GankRecommendPresenter> {

	//Declare View
	@BindView(R.id.history_gank_list)
	RecyclerView historyGankList;
	@BindView(R.id.swip_refresh_layout)
	SwipeRefreshLayout swipRefreshLayout;
	@BindView(R.id.loading_progress)
	ProgressBar loadingProgress;

	View headerView;

	//Declare Adapter
	GankListAdapter mGankListAdapter;

	//DI Obj
	@Inject
	public GankRecommendPresenter mPresenter;

	//Flag
	int page;

	public RecommendGankFragment() {
	}

	@NonNull
	public static RecommendGankFragment newFragment() {
		return new RecommendGankFragment();
	}

	@Override
	protected void initializeInjector(@NonNull FragmentComponent component) {
		//DI 当前图表对象
		component.inject(this);
	}

	@Override
	protected void initialized() {
		mPresenter.setView(this);
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_recommend_gank;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}

	@Override
	public void findViews() {
		mGankListAdapter = new GankListAdapter(new ArrayList<GankResult>(0));
		headerView = getHeaderView();
		mGankListAdapter.addHeaderView(headerView);
		historyGankList.setLayoutManager(new LinearLayoutManager(getContext()));
		historyGankList.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL));
		historyGankList.setAdapter(mGankListAdapter);
	}

	@Override
	public void setViewListener() {
		swipRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				page = 1;
				mPresenter.showHistoryGank();
				swipRefreshLayout.setRefreshing(false);
			}
		});
		mGankListAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
			@Override
			public void onItemClick(View view, int i) {
				GankResult gankResult = mGankListAdapter.getData().get(i);
				if ("福利".equals(gankResult.getType())) {

				} else {
				}
				showMessage(gankResult.getUrl());

			}
		});
		headerView.setOnClickListener(this);
	}

	@Override
	public void processExtraData() {
		mPresenter.subscribe();
	}

	@Override
	public void addGanks(List<GankResult> ganks) {
		mGankListAdapter.addData(ganks);
	}

	@Override
	public void showDayGankResults(DayGankResults dayGankResults) {
		hideLoading();
	}

	@Override
	public void showLoading() {
		loadingProgress.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideLoading() {
		loadingProgress.setVisibility(View.GONE);
	}


	@Override
	public void onDestroyView() {
		super.onDestroyView();
//		mPresenter.unsubscribe(); //auto unsubscribe
	}

	public View getHeaderView() {
		View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.gank_recommend_head, null);
		SimpleDraweeView img = (SimpleDraweeView) headerView.findViewById(R.id.headImg);
		img.setImageURI(Uri.parse("http://ww1.sinaimg.cn/large/610dc034jw1f7sszr81ewj20u011hgog.jpg"));
		return headerView;
	}

	@Override
	public void onClick(View v) {

	}

	@Override
	public GankRecommendPresenter getPresenter() {
		return mPresenter;
	}
}
