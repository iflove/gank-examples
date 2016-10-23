package com.lazy.gank.ui.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.lazy.gank.R;
import com.lazy.gank.base.BaseActivity;
import com.lazy.gank.internal.di.components.ActivityComponent;
import com.lazy.gank.widget.MyPhotoView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lazy on 16/9/17.
 */
public class PreviewActivity extends BaseActivity {

	@BindView(R.id.preview_img)
	MyPhotoView previewImg;

	@BindView(R.id.toolbar)
	Toolbar toolbar;


	@Override
	public void findViews() {
		setContentView(R.layout.activity_photo);
		//设置标题
		setTitle("妹纸");
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

		String url = getIntent().getStringExtra("url");
		previewImg.setImageUri(url, null);
	}

	@Override
	public void setViewListener() {

	}

	@Override
	public void processExtraData() {

	}

	@Override
	protected void initializeInjector(@NonNull ActivityComponent activityComponent) {

	}

}
