package com.tuncamyo.proje_kitaplik;

import com.tuncamyo.proje_kitaplik.model.Book;
import com.tuncamyo.proje_kitaplik.sqlite.MyDataBase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class kitap_duzenle extends Activity {
	
	
	Button btn_kj_kaydet;
	Button btn_kj_sil;
	Button btn_kj_iptal;
	EditText et_kj_kitapadi;
	EditText et_kj_yazar;
	EditText et_kj_tur;
	EditText et_kj_aciklama;
	Book book;
	
	String id;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		final MyDataBase db = new MyDataBase(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kitap_duzenle);
		Bundle extras = getIntent().getExtras();
		id = extras.getString("kitapid");
		
		book = db.getBook(Integer.parseInt(id));
		//Toast.makeText(kitap_duzenle.this,book.getGenre() , Toast.LENGTH_LONG).show();
		btn_kj_kaydet = (Button) findViewById(R.id.btn_kd_Kaydet);
		btn_kj_sil = (Button) findViewById(R.id.btn_kd_sil);
		btn_kj_iptal = (Button) findViewById(R.id.btn_kd_iptal);
		
		et_kj_kitapadi = (EditText) findViewById(R.id.et_kd_kitapadi);
		et_kj_yazar = (EditText) findViewById(R.id.et_kd_yazar);
		et_kj_tur = (EditText) findViewById(R.id.et_kd_tur);
		et_kj_aciklama = (EditText) findViewById(R.id.et_kd_aciklama);
		
		et_kj_kitapadi.setText(book.getTitle());
		et_kj_yazar.setText(book.getAuthor());
		et_kj_tur.setText(book.getGenre());
		et_kj_aciklama.setText(book.getDescription());
		
		
		btn_kj_sil.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				db.deleteBook(book);
				Intent intent = new Intent(kitap_duzenle.this,MainActivity.class);
				
				startActivity(intent);
				
			}
		});
		
		
		
		btn_kj_kaydet.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				book.setTitle(et_kj_kitapadi.getText().toString());
				book.setAuthor(et_kj_yazar.getText().toString());
				book.setGenre(et_kj_tur.getText().toString());
				book.setDescription(et_kj_aciklama.getText().toString());
				db.updateBook(book);
				
				Intent intent  = new Intent(kitap_duzenle.this,MainActivity.class);
				startActivity(intent);
				
			}
		});
		btn_kj_iptal.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent  = new Intent(kitap_duzenle.this,MainActivity.class);
				startActivity(intent);
				
			}
		});
		
	}

	
	
	
}
