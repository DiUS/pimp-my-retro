package com.dius.pimpmyretro.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.dius.pimpmyretro.R;
import com.dius.pimpmyretro.adapter.TopicAdapter;
import com.dius.pimpmyretro.data.NewTopicEvent;
import com.dius.pimpmyretro.data.PimpEventBus;
import com.dius.pimpmyretro.data.Topic;
import com.dius.pimpmyretro.data.TopicCategory;
import com.dius.pimpmyretro.util.Constants;
import com.firebase.client.Firebase;
import com.squareup.otto.Subscribe;

public class TopicListFragment extends Fragment implements Constants{

	private TopicCategory mCategory;
	
	private ImageView mCategoryImage;
	
	private ListView mTopicListView;
	
	private TopicAdapter mTopicAdapter;
	
	private Firebase mDataRef;

	private List<String> mTopicsIds = new ArrayList<String>();
	
	public TopicListFragment() {
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		PimpEventBus.register(this);
		mDataRef = new Firebase(FIREBASE_URL);
		Bundle bundle = this.getArguments();
		if (getArguments() != null) {
			mCategory = TopicCategory.valueOf(bundle.getString(BUNDLE_CATEGORY));
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_topic_list, container, false);
		mCategoryImage = (ImageView) view.findViewById(R.id.topicImageView);
		mTopicListView = (ListView) view.findViewById(R.id.topicListView);
		mTopicAdapter = new TopicAdapter(getActivity());
		mTopicListView.setAdapter(mTopicAdapter);
		
		if (mCategory!= null){
			mCategoryImage.setImageResource(mCategory.getImageId());
		}
		return view;
	}
	
	@Subscribe
	public void newTopic(NewTopicEvent event){
		if (mCategory.equals(event.getCategory())){
			if (event.getId()== null ){
				// Push to Firebase 
				Firebase child = mDataRef.child("topics");
				Map<String, Object> toSet = new HashMap<String, Object>();
				toSet.put("text", event.getContent());
				toSet.put("user", event.getUsername());
				Map<String, Object> category = new HashMap<String, Object>();
				category.put("class", event.getCategory().getClasse());
				toSet.put("category", category);
				child.push().setValue(toSet);
			}else if (!mTopicsIds.contains(event.getId())){
			mTopicAdapter.addTopic(new Topic(event.getContent(), event.getCategory(), event.getUsername()));
			mTopicsIds.add(event.getId());
		}
	}
	}
	
}
