package com.dius.pimpmyretro.data;

public class Topic {

	private String mTopicContent;
	
	private TopicCategory mCategory;
	
	private String mUsername;

	public Topic() {
	}
	
	public Topic(String topicContent, TopicCategory category, String username) {
		this.mTopicContent = topicContent;
		this.mCategory = category;
		this.mUsername = username;
	}

	public String getTopicContent() {
		return mTopicContent;
	}

	public TopicCategory getCategory() {
		return mCategory;
	}

	public String getUsername() {
		return mUsername;
	}

	public void setTopicContent(String content) {
		this.mTopicContent = content;
	}

	
}
