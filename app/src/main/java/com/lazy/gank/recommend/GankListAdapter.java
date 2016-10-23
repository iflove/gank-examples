package com.lazy.gank.recommend;

import android.net.Uri;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lazy.gank.R;
import com.lazy.gank.entity.GankResult;
import com.lazy.gank.interfaces.IConstant;

import java.text.SimpleDateFormat;
import java.util.List;


public class GankListAdapter extends BaseQuickAdapter<GankResult> implements View.OnClickListener {

	private static final String TAG = "GankListAdapter";
	SimpleDateFormat simpleFormatter = new SimpleDateFormat("MM-dd H:m");

	public GankListAdapter(List<GankResult> data) {
		super(R.layout.gank_item, data);
	}

	@Override
	protected int getDefItemViewType(int position) {
		return super.getDefItemViewType(position);
	}

	@Override
	protected void convert(BaseViewHolder vh, GankResult gankResult) {
		gankResult.url = gankResult.url == null ? IConstant.PLACE_HOLDER : gankResult.url;
		Uri uri = Uri.parse(gankResult.url);

		SimpleDraweeView imageView = vh.getView(R.id.img);
		imageView.setImageURI(uri);
		imageView.setOnClickListener(this);
		imageView.setTag(gankResult);


		vh.setText(R.id.gank_who, gankResult.who);
		vh.setText(R.id.desc, gankResult.desc);
		vh.setText(R.id.type, gankResult.type);
		vh.setText(R.id.publishedAt, simpleFormatter.format(gankResult.publishedAt));

	}

	@Override
	public void onClick(View v) {

	}
}