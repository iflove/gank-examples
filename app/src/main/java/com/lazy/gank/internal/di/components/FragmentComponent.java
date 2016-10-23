package com.lazy.gank.internal.di.components;

import com.lazy.gank.all.GankFragment;
import com.lazy.gank.base.BaseFragment;
import com.lazy.gank.internal.di.scope.FragmentScope;
import com.lazy.gank.meizi.MeiziFragment;
import com.lazy.gank.recommend.RecommendGankFragment;

import dagger.Subcomponent;

/**
 * Created by Sky on 2016/9/26.
 *
 * @Describe
 */
@FragmentScope
@Subcomponent()
public interface FragmentComponent {

	void inject(BaseFragment baseFragment);

	void inject(RecommendGankFragment baseFragment);

	void inject(MeiziFragment baseFragment);

	void inject(GankFragment baseFragment);
}
