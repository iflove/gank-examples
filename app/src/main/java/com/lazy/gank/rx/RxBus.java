package com.lazy.gank.rx;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by Sky on 2016/9/27.
 *
 * @Describe
 */

public final class RxBus {
	private static volatile RxBus instance;
	private final Subject<Object, Object> BUS;

	private RxBus() {
		BUS = new SerializedSubject<>(PublishSubject.create());
	}

	public static RxBus getDefault() {
		if (instance == null) {
			synchronized (RxBus.class) {
				if (instance == null) {
					instance = new RxBus();
				}
			}
		}
		return instance;
	}

	public void post(Object event) {
		BUS.onNext(event);
	}

	public <T> Observable<T> toObserverable(Class<T> eventType) {
		// ofType = filter + cast
		return BUS.ofType(eventType);
	}
}
