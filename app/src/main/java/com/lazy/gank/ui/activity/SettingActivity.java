package com.lazy.gank.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.lazy.gank.R;
import com.lazy.gank.base.BaseActivity;
import com.lazy.gank.internal.di.components.ActivityComponent;
import com.lazy.gank.ui.adapter.SettingsListAdapter;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lazy on 16/9/17.
 */
public class SettingActivity extends BaseActivity {

	@BindView(R.id.toolbar)
	Toolbar toolbar;

	@BindView(R.id.recyclerView)
	RecyclerView recyclerView;

	SettingsListAdapter settingsListAdapter;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		processExtraData();
	}

	@Override
	public void findViews() {
		setContentView(R.layout.activity_setting);
		//设置标题
		setTitle("设置");
		ButterKnife.bind(this);
		//设置回退按钮
		toolbar.setNavigationIcon(R.drawable.ic_arrow_left_white_24dp);
		//在 setSupportActionBar 之前设置一下将要显示的UI
		setSupportActionBar(toolbar);
		//事件要在 setSupportActionBar 之后
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		String[] stringArray = getResources().getStringArray(R.array.settings);
		List<String> strings = Arrays.asList(stringArray);
		settingsListAdapter = new SettingsListAdapter(strings);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setAdapter(settingsListAdapter);


	}

	@Override
	public void setViewListener() {
		settingsListAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
			@Override
			public void onItemClick(View view, int i) {
				if (i == 1) {
					ImagePipeline imagePipeline = Fresco.getImagePipeline();

					imagePipeline.clearMemoryCaches();
					imagePipeline.clearDiskCaches();

					// combines above two lines
					imagePipeline.clearCaches();

					processExtraData();
				}
			}
		});
	}

	@Override
	public void processExtraData() {
		long cacheSize = Fresco.getImagePipelineFactory().getMainFileCache().getSize();
		String[] stringArray = getResources().getStringArray(R.array.settings);
		List<String> strings = Arrays.asList(stringArray);
		String text = strings.get(1);
		text = text + "		(" + cacheSize / 1024 / 1024 + " Mb )";
		strings.set(1, text);
		settingsListAdapter.setNewData(strings);

	}

	@Override
	protected void initializeInjector(@NonNull ActivityComponent activityComponent) {

	}

}
