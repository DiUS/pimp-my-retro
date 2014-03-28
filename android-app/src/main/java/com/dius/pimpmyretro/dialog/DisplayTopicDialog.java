package com.dius.pimpmyretro.dialog;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dius.pimpmyretro.R;
import com.dius.pimpmyretro.data.Topic;
import com.dius.pimpmyretro.util.Constants;

public class DisplayTopicDialog extends Dialog implements Constants {

	private Topic mTopic;
	
	private TextView mEditTextContent;
	
	private TextView mEditTextUsername;
	
	private ImageView mButtonCategory;
	
	private Drawable mBackground;
	
	private ViewGroup mDialogContent;
	
	public DisplayTopicDialog(Context context) {
		this(context, R.layout.dialog_view_topic);
		this.mBackground = context.getResources().getDrawable(R.drawable.bg_dialog);
		this.mBackground = this.mBackground.mutate();
	}
	
	public void setTopic(Topic topic){
		this.mTopic = topic;
	}
	
	private void updateColor(){
		if (mTopic!= null){
			int colorIdx = mTopic.getCategory().getBackgroundColor();
			colorIdx = getContext().getResources().getColor(colorIdx);
			this.mBackground.setColorFilter(colorIdx, Mode.MULTIPLY);
		}
		setBackground(mDialogContent, this.mBackground);	
	}
	

	public DisplayTopicDialog(Context context, int layoutId) {
		super(context);
		setCancelable(true);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setBackgroundDrawable(
				new ColorDrawable(android.graphics.Color.TRANSPARENT));
		setContentView(layoutId);
		mDialogContent = (ViewGroup)findViewById(R.id.dialogContent);
		mEditTextContent = (TextView) findViewById(R.id.textViewContent);
		mEditTextUsername = (TextView) findViewById(R.id.textViewUserName);
		mButtonCategory = (ImageView) findViewById(R.id.imageViewType);
		Button buttonCancel = (Button) findViewById(R.id.actionCancel);
		buttonCancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				hide();
			}
		});
	}

	@Override
	public void show() {
		mEditTextContent.setText(mTopic.getTopicContent());
		mEditTextUsername.setText(mTopic.getUsername());
		mButtonCategory.setImageResource(mTopic.getCategory().getImageId());
		updateColor();
		super.show();
	}
	
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@SuppressWarnings("deprecation")
	public static void setBackground(View view, Drawable drawable) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			view.setBackground(drawable);
		} else {
			view.setBackgroundDrawable(drawable);
		}
	}
}
