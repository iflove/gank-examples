package com.lazy.gank.ui.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by Sky on 2016/9/26.
 *
 * @Describe
 */

public class SettingsListAdapter extends BaseQuickAdapter<String> {


	public SettingsListAdapter(List<String> data) {
		super(android.R.layout.simple_list_item_1, data);
	}


	@Override
	protected void convert(BaseViewHolder vh, String s) {
		TextView view = vh.getView(android.R.id.text1);
		view.setText(s);
	}
}
