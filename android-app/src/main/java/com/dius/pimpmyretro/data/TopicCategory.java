package com.dius.pimpmyretro.data;

import com.dius.pimpmyretro.R;


public enum TopicCategory {

	HAPPY(R.drawable.happy, R.color.topic_happy_background_color, "good"), 
	UNHAPPY(R.drawable.sad, R.color.topic_sad_background_color, "bad"), NO_IDEA(R.drawable.confused, R.color.topic_confused_background_color, "confused");

	private int mImageId;
	
	private int mBackgroundColor;
	
	private String mClass;
	
	private TopicCategory(int imageId, int bgColor, String classe){
		this.mImageId = imageId;
		this.mClass = classe;
		this.mBackgroundColor = bgColor;
	}
	
	public int getImageId(){
		return mImageId;
	}
	
	public int getBackgroundColor() {
		return mBackgroundColor;
	}

	public String getClasse() {
		return mClass;
	}
	
	public static TopicCategory findByClass(String classe){
		for (TopicCategory category:TopicCategory.values()){
			if (category.mClass.equalsIgnoreCase(classe)){
				return category;
			}
		}
		return HAPPY;
	}
	
}
