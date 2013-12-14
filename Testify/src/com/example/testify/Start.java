package com.example.testify;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Start extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);
		Button b1 = (Button)findViewById(R.id.button1);
		b1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
					startActivity(new Intent("com.example.testify.HOMESCREEN"));
			}
		});
		
		Button b2 = (Button)findViewById(R.id.button2);
		b2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
					startActivity(new Intent("com.example.testify.HOMESCREEN1"));
			}
		});
		
		
		Button b3 = (Button)findViewById(R.id.button3);
		b3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
	            System.exit(0);
			}
		});
		
		
	}
}	
