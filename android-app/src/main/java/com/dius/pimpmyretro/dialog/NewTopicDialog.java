package com.dius.pimpmyretro.dialog;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.dius.pimpmyretro.R;
import com.dius.pimpmyretro.data.NewTopicEvent;
import com.dius.pimpmyretro.data.PimpEventBus;
import com.dius.pimpmyretro.data.TopicCategory;
import com.dius.pimpmyretro.util.Constants;

public class NewTopicDialog extends Dialog implements Constants {

	private TopicCategory mCategory;
	
	private Drawable mBackground;
	
	private ViewGroup mDialogContent;
	
	public NewTopicDialog(Context context) {
		this(context, R.layout.dialog_new_topic);
		this.mBackground = context.getResources().getDrawable(R.drawable.bg_dialog);
		this.mBackground = this.mBackground.mutate();
	}

	private void updateColor(){
		if (mCategory!= null){
			int colorIdx = mCategory.getBackgroundColor();
			colorIdx = getContext().getResources().getColor(colorIdx);
			this.mBackground.setColorFilter(colorIdx, Mode.MULTIPLY);
		}
		setBackground(mDialogContent, this.mBackground);	
	}
	
	@Override
	public void show() {
		updateColor();
		super.show();
	}
	
	public NewTopicDialog(Context context, int layoutId) {
		super(context);
		setCancelable(false);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setBackgroundDrawable(
				new ColorDrawable(android.graphics.Color.TRANSPARENT));
		setContentView(layoutId);

		final EditText editTextContent = (EditText) findViewById(R.id.editTextContent);
		final EditText editTextUsername = (EditText) findViewById(R.id.editTextUserName);
		ImageView buttonHappy = (ImageView) findViewById(R.id.imageViewHappy);
		mDialogContent = (ViewGroup)findViewById(R.id.dialogContent);
		buttonHappy.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setCategory(TopicCategory.HAPPY);
			}
		});
		
		ImageView buttonUnHappy = (ImageView) findViewById(R.id.imageViewUnHappy);
		buttonUnHappy.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setCategory(TopicCategory.UNHAPPY);
			}
		});
		
		ImageView buttonNoIdea = (ImageView) findViewById(R.id.imageViewNoIdea);
		buttonNoIdea.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setCategory(TopicCategory.NO_IDEA);
			}
		});
		
		Button buttonAdd = (Button) findViewById(R.id.actionAdd);
		buttonAdd.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (mCategory == null){
					Toast.makeText(getContext(), "Choose a category before!!!", Toast.LENGTH_LONG).show();
				}else{
					
					String textContent = editTextContent.getText().toString();
					String userNameContent = editTextUsername.getText().toString();
					if(TextUtils.isEmpty(userNameContent)){
						Toast.makeText(getContext(), "Fill the username", Toast.LENGTH_LONG).show();	
					}
					else if(TextUtils.isEmpty(textContent)){
						Toast.makeText(getContext(), "Fill the content", Toast.LENGTH_LONG).show();
					}else{
						// save to firebase
						PimpEventBus.post(new NewTopicEvent(null, textContent, mCategory, userNameContent));
						hide();
					}
				}
				
			}
		});
		
		Button buttonCancel = (Button) findViewById(R.id.actionCancel);
		buttonCancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				hide();
			}
		});
	}
	
	private void setCategory(TopicCategory category){
		this.mCategory = category;
		updateColor();
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
