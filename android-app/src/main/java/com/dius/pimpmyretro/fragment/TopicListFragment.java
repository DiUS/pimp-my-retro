package com.dius.pimpmyretro.fragment;

import com.dius.pimpmyretro.R;
import com.dius.pimpmyretro.adapter.TopicAdapter;
import com.dius.pimpmyretro.data.TopicCategory;
import com.dius.pimpmyretro.util.Constants;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

public class TopicListFragment extends Fragment implements Constants{

	private TopicCategory mCategory;
	
	private ImageView mCategoryImage;
	
	private ListView mTopicListView;
	
	private TopicAdapter mTopicAdapter;
	
	public TopicListFragment() {
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
	
}
