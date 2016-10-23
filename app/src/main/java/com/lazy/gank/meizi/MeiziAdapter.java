package com.lazy.gank.meizi;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.common.logging.FLog;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.lazy.gank.R;
import com.lazy.gank.entity.GankResult;
import com.lazy.gank.ui.activity.PreviewActivity;

import java.util.List;
import java.util.Random;

/**
 * Created by Sky on 2016/9/20.
 *
 * @Describe
 */

public class MeiziAdapter extends BaseQuickAdapter<GankResult> implements View.OnClickListener {

	public MeiziAdapter(int layoutResId, List<GankResult> data) {
		super(layoutResId, data);
	}

	@Override
	protected void convert(BaseViewHolder vh, GankResult dataResult) {
		ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
			@Override
			public void onFinalImageSet(
					String id,
					@Nullable ImageInfo imageInfo,
					@Nullable Animatable anim) {
				if (imageInfo == null) {
					return;
				}
				QualityInfo qualityInfo = imageInfo.getQualityInfo();
				FLog.d("Final image received! " +
								"Size %d x %d",
						"Quality level %d, good enough: %s, full quality: %s",
						imageInfo.getWidth(),
						imageInfo.getHeight(),
						qualityInfo.getQuality(),
						qualityInfo.isOfGoodEnoughQuality(),
						qualityInfo.isOfFullQuality());
			}

			@Override
			public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {
				FLog.d("Intermediate image received", id);
			}

			@Override
			public void onFailure(String id, Throwable throwable) {
				FLog.e(getClass(), throwable, "Error loading %s", id);
			}
		};
		Uri uri = Uri.parse(dataResult.url);

		SimpleDraweeView imageView = vh.getView(R.id.img);
		ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
		layoutParams.height = 400 + new Random().nextInt(300);
		imageView.setLayoutParams(layoutParams);
		imageView.setImageURI(uri);
		imageView.setOnClickListener(this);
		imageView.setTag(dataResult.url);
	}

	@Override
	public void onClick(View v) {
		String url = (String) v.getTag();
		Intent intent = new Intent(this.mContext, PreviewActivity.class);
		intent.putExtra("url", url);
		mContext.startActivity(intent);
	}
}
