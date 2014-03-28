package com.dius.pimpmyretro.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.dius.pimpmyretro.R;
import com.dius.pimpmyretro.data.NewTopicEvent;
import com.dius.pimpmyretro.data.PimpEventBus;
import com.dius.pimpmyretro.data.TopicCategory;

public class NewTopicDialog extends Dialog {

	private TopicCategory mCategory;
	
	public NewTopicDialog(Context context) {
		this(context, R.layout.dialog_new_topic);
	}

	protected NewTopicDialog(Context context, boolean cancelable,
			OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
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
		ImageButton buttonHappy = (ImageButton) findViewById(R.id.imageViewHappy);
		buttonHappy.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mCategory = TopicCategory.HAPPY;
			}
		});
		
		ImageButton buttonUnHappy = (ImageButton) findViewById(R.id.imageViewUnHappy);
		buttonUnHappy.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mCategory = TopicCategory.UNHAPPY;
			}
		});
		
		ImageButton buttonNoIdea = (ImageButton) findViewById(R.id.imageViewNoIdea);
		buttonNoIdea.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mCategory = TopicCategory.NO_IDEA;
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
						PimpEventBus.post(new NewTopicEvent(textContent, mCategory, userNameContent));
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

}
