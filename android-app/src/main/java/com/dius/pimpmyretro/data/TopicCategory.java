package com.dius.pimpmyretro.data;

import com.dius.pimpmyretro.R;


public enum TopicCategory {

	HAPPY(R.drawable.happy), UNHAPPY(R.drawable.unhappy), NO_IDEA(R.drawable.noidead);
	
	private int mImageId;
	
	private TopicCategory(int imageId){
		this.mImageId = imageId;
	}
	
	public int getImageId(){
		return mImageId;
	}
	
}
