package com.lazy.gank.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lazy.gank.rx.RxBus;

import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Sky on 2016/9/27.
 *
 * @Describe
 */

public class RxBusActivity extends AppCompatActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		RxBus.getDefault().post(new OneEvent("Hello"));
		CompositeSubscription subscription = new CompositeSubscription();
		subscription.add(RxBus.getDefault().toObserverable(OneEvent.class).subscribe(new Action1<OneEvent>() {
			@Override
			public void call(OneEvent oneEvent) {

			}
		}));
	}
}

class OneEvent {
	// some data you need ...
	String msg;

	public OneEvent(String msg) {
		this.msg = msg;
	}
}