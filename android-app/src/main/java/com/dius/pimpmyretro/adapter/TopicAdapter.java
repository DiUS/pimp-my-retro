package com.dius.pimpmyretro.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dius.pimpmyretro.R;
import com.dius.pimpmyretro.data.Topic;

public class TopicAdapter extends BaseAdapter {

	private final Context mContext;

	private List<Topic> mTopics;

	private LayoutInflater mInflater;

	public TopicAdapter(Context context) {
		this.mContext = context;
		mTopics = new ArrayList<Topic>();
		mInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		//TODO : REMOVE
		testTopics();
	}

	/**
	 * TODO : REMOVE
	 */
	private void testTopics(){
		Random ramdom = new Random();
		for (int i = 0; i <15;i++){
			Topic topicRandom = new Topic();
			StringBuilder text = new StringBuilder();
			for (int j = 0; j <ramdom.nextInt(50);j++){
				text.append(ramdom.nextInt(50));
			}
			topicRandom.setTopicContent(text.toString());
			mTopics.add(topicRandom);
		}
	}
	
	@Override
	public int getCount() {
		return mTopics.size();
	}

	@Override
	public Topic getItem(int position) {
		return mTopics.get(position);
	}
	
	public void addTopic(Topic topic){
		mTopics.add(topic);
		notifyDataSetChanged();
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		if (rowView == null) {
			rowView = mInflater.inflate(R.layout.view_topic, null);
			TopicViewHolder viewHolder = new TopicViewHolder();
			viewHolder.content = (TextView) rowView
					.findViewById(R.id.textViewTopicContent);
			rowView.setTag(viewHolder);
		}

		// fill data
		TopicViewHolder holder = (TopicViewHolder) rowView.getTag();
		Topic topic = mTopics.get(position);
		holder.content.setText(topic.getTopicContent());
		return rowView;
	}

	class TopicViewHolder {
		TextView content;
	}
}
