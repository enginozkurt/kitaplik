package com.tuncamyo.proje_kitaplik;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class splash_ekrani_iki extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_ekrani_iki);
		
new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				Intent intent= new Intent(splash_ekrani_iki.this,MainActivity.class);
				startActivity(intent);
			}
		}, 2000);
	}

}
