package com.dius.pimpmyretro.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import com.dius.pimpmyretro.R;
import com.dius.pimpmyretro.data.TopicCategory;
import com.dius.pimpmyretro.fragment.TopicListFragment;
import com.dius.pimpmyretro.util.Constants;

public class TopicsActivity extends FragmentActivity implements Constants{

	private TopicListFragment mFragmentHappy;
	private TopicListFragment mFragmentUnHappy;
	private TopicListFragment mFragmentNoIdea;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_topics);
		mFragmentHappy = createFragment(TopicCategory.HAPPY, R.id.fragmentHappy);
		mFragmentUnHappy = createFragment(TopicCategory.UNHAPPY, R.id.fragmentUnHappy);
		mFragmentNoIdea = createFragment(TopicCategory.NO_IDEA, R.id.fragmentNoIdea);
	}
	
	private TopicListFragment createFragment(TopicCategory category, int fragmentLocation){
		TopicListFragment fragment = new TopicListFragment();
		Bundle bundle = new Bundle();
		bundle.putString(BUNDLE_CATEGORY, category.toString());
		fragment.setArguments(bundle);
		replaceFragment(fragmentLocation, fragment);
		return fragment;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.topics, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_add) {
			
			// Diplay 
			
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	protected void replaceFragment(int id, Fragment fragment) {
		FragmentManager fragmentManager = this.getSupportFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.replace(id, fragment).commit();
	}
}
