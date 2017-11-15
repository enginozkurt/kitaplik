package com.tuncamyo.proje_kitaplik;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class splash_ekrani_bir extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_ekrani_bir);
		
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				Intent intent= new Intent(splash_ekrani_bir.this,splash_ekrani_iki.class);
				startActivity(intent);
			}
		}, 2000);
	}
}
