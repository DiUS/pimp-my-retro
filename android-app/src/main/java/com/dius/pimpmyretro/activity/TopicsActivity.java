package com.dius.pimpmyretro.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.dius.pimpmyretro.R;
import com.dius.pimpmyretro.data.NewTopicEvent;
import com.dius.pimpmyretro.data.PimpEventBus;
import com.dius.pimpmyretro.data.TopicCategory;
import com.dius.pimpmyretro.dialog.NewTopicDialog;
import com.dius.pimpmyretro.fragment.TopicListFragment;
import com.dius.pimpmyretro.util.Constants;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class TopicsActivity extends FragmentActivity implements Constants {

	private TopicListFragment mFragmentHappy;
	private TopicListFragment mFragmentUnHappy;
	private TopicListFragment mFragmentNoIdea;
	private NewTopicDialog mNewTopicDialog;
	private Firebase mDataRef;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_topics);
		mFragmentHappy = createFragment(TopicCategory.HAPPY, R.id.fragmentHappy);
		mFragmentUnHappy = createFragment(TopicCategory.UNHAPPY, R.id.fragmentUnHappy);
		mFragmentNoIdea = createFragment(TopicCategory.NO_IDEA, R.id.fragmentNoIdea);
		mDataRef = new Firebase(FIREBASE_URL);
		registerToFirebase();
	}

	private void registerToFirebase() {
		mDataRef.child("topics").addValueEventListener(new ValueEventListener() {

			@Override
			public void onDataChange(DataSnapshot snapshot) {
				Object value = snapshot.getValue();
				if (value != null) {
					Map valueMap = (Map) value;
					Set<Map.Entry<String, ?>> entrySet = valueMap.entrySet();
					for (Map.Entry<String, ?> entry : entrySet) {
						Object entryVal = entry.getValue();
						if (entryVal instanceof Map) {
							Map entryValue = (Map) entry.getValue();
							String text = (String) entryValue.get("text");
							String user = (String) entryValue.get("user");
							String id = (String) entry.getKey();
							Map cat = (Map) entryValue.get("category");
							if (!TextUtils.isEmpty(id) && !TextUtils.isEmpty(text) && !TextUtils.isEmpty(user) && cat != null) {
								String category = (String) cat.get("class");
								PimpEventBus.post(new NewTopicEvent(id, text, TopicCategory.findByClass(category), user));
							}
						}
					}

				}

			}

			@Override
			public void onCancelled(FirebaseError error) {
			}
		});
	}

	private TopicListFragment createFragment(TopicCategory category, int fragmentLocation) {
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
			mNewTopicDialog = new NewTopicDialog(this);
			mNewTopicDialog.show();
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
