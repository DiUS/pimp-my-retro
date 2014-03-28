package com.dius.pimpmyretro.data;

public class NewTopicEvent {

	private String mContent;
	
	private TopicCategory mCategory;
	
	private String mUsername;
	
	private String mId;
	
	public NewTopicEvent(String id, String content, TopicCategory category, String username) {
		this.mContent = content;
		this.mCategory = category;
		this.mUsername = username;
		this.mId = id;
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

	public String getId() {
		return mId;
	}

}
