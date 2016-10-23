package org.apache.library.impl;

import org.apache.library.impl.inter.Get;

/**
 * Created by system on 16/8/6.
 */
public abstract class LazyInitiize<T> implements Get<T> {

	private static final Object mInstanceSync = new Object();//同步


	T obj;

	@Override
	public T get() {
		synchronized (mInstanceSync) {
			if (obj != null) {
				return obj;
			}
			obj = lazy();
		}
		return obj;
	}

	protected abstract T lazy();
}
