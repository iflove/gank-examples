package com.lazy.gank.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.lazy.gank.R;
import com.lazy.gank.all.GankFragment;
import com.lazy.gank.base.BaseActivity;
import com.lazy.gank.internal.di.components.ActivityComponent;
import com.lazy.gank.meizi.MeiziFragment;
import com.lazy.gank.recommend.RecommendGankFragment;
import com.lazy.gank.ui.activity.SettingActivity;
import com.lazy.gank.util.ActManager;
import com.lazy.gank.util.DoubleClick;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
		implements NavigationView.OnNavigationItemSelectedListener {

	@BindView(R.id.toolbar)
	Toolbar toolbar;
	@BindView(android.R.id.tabs)
	TabLayout tabs;
	@BindView(R.id.viewPager)
	ViewPager viewPager;
	@BindView(R.id.nav_view)
	NavigationView navView;
	@BindView(R.id.drawer_layout)
	DrawerLayout drawerLayout;

	@BindArray(R.array.titile_tabs)
	String[] titleTabs;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void findViews() {
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		setSupportActionBar(toolbar);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawerLayout.setDrawerListener(toggle);
		toggle.syncState();

		viewPager.setAdapter(new FragmentPagerAdapter(this.getSupportFragmentManager()) {
			@Override
			public Fragment getItem(int position) {
				switch (position) {
					case 1:
						return RecommendGankFragment.newFragment();
					case 2:
						return MeiziFragment.newFragment();
					default:
						GankFragment gankFragment = GankFragment.newFragment();
						Bundle args = new Bundle();
						args.putString("Category", titleTabs[position]);
						if (position == 0) {
							args.putString("Category", "all");
						}
						gankFragment.setArguments(args);
						return gankFragment;
				}
			}

			@Override
			public int getCount() {
				return titleTabs.length;
			}

			@Override
			public CharSequence getPageTitle(int position) {
				return titleTabs[position];
			}

		});

		viewPager.setCurrentItem(1);

		tabs.setupWithViewPager(viewPager);

	}

	@Override
	public void setViewListener() {
		navView.setNavigationItemSelectedListener(this);
	}

	@Override
	public void processExtraData() {

	}

	@Override
	protected void initializeInjector(ActivityComponent activityComponent) {

	}

	@Override
	public void onBackPressed() {
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		if (drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			startActivity(SettingActivity.class);
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		// Handle navigation view item clicks here.
		int id = item.getItemId();
		// Handle the item action
		if (id == R.id.nav_all) {
			viewPager.setCurrentItem(0);
		} else if (id == R.id.nav_recommend) {
			viewPager.setCurrentItem(1);
		} else if (id == R.id.nav_fuli) {
			viewPager.setCurrentItem(2);
		} else if (id == R.id.nav_android) {
			viewPager.setCurrentItem(3);
		} else if (id == R.id.nav_ios) {
			viewPager.setCurrentItem(4);
		} else if (id == R.id.nav_video) {
			viewPager.setCurrentItem(5);
		} else if (id == R.id.nav_resource) {
			viewPager.setCurrentItem(6);
		} else if (id == R.id.nav_html) {
			viewPager.setCurrentItem(7);
		} else if (id == R.id.nav_share) {
			showMessage("nav_share");
		} else if (id == R.id.nav_manage) {
			startActivity(SettingActivity.class);
		}


		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (DoubleClick.isDouble()) {
				ActManager.AppExit();
			} else {
				showMessage("再按一次，退出App");
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
