package com.dius.pimpmyretro.data;

public class NewTopicEvent {

	private String mContent;
	
	private TopicCategory mCategory;
	
	private String mUsername;
	
	public NewTopicEvent(String content, TopicCategory category, String username) {
		this.mContent = content;
		this.mCategory = category;
		this.mUsername = username;
	}

	public TopicCategory getCategory() {
		return mCategory;
	}

	public String getContent() {
		return mContent;
	}

	public String getUsername() {
		return mUsername;
	}

	public void setContent(String mContent) {
		this.mContent = mContent;
	}

}
