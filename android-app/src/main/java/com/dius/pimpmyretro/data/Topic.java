package com.dius.pimpmyretro.data;

public class Topic {

	private String topicContent;
	
	private TopicCategory category;

	public Topic() {
	}
	
	public String getTopicContent() {
		return topicContent;
	}

	public void setTopicContent(String topicContent) {
		this.topicContent = topicContent;
	}

	public TopicCategory getCategory() {
		return category;
	}

	public void setCategory(TopicCategory category) {
		this.category = category;
	}
	
}
