package com.example.testify;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

public class splash extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		Thread logotimer = new Thread(){
				public void run(){
					try{
	        			int logotimer = 0;
	        			while(logotimer < 50){
	        				sleep(100);
	        				logotimer++;
	        			}
	        			startActivity(new Intent("com.example.testify.START"));
	        		} 
	        		catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        		finally{
	        			
	        			finish();
	        		}
				}
		};
		logotimer.start();
	}
}
