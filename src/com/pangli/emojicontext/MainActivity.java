package com.pangli.emojicontext;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		EmojiconTextView text = (EmojiconTextView) findViewById(R.id.text);
		text.setText("店铺信誉：@@##$$%&&");
	}

}
