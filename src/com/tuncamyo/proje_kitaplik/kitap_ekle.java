package com.tuncamyo.proje_kitaplik;

import com.tuncamyo.proje_kitaplik.R;
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

public class kitap_ekle extends Activity{

	Button btn_jv_kaydet;
	EditText et_jv_kitapadi;
	EditText et_jv_yazar;
	EditText et_jv_tur;
	EditText et_jv_aciklama;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		final MyDataBase db = new MyDataBase(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kitap_ekle);
		btn_jv_kaydet = (Button)findViewById(R.id.btn_ke_kaydet);
		et_jv_kitapadi = (EditText)findViewById(R.id.et_ke_kitapadi);
		et_jv_yazar = (EditText)findViewById(R.id.et_ke_yazar);
		et_jv_tur = (EditText)findViewById(R.id.et_ke_tur);
		et_jv_aciklama = (EditText)findViewById(R.id.et_ke_aciklama);
		
		
		
		btn_jv_kaydet.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Book book = new Book();
				if (et_jv_kitapadi.getText().toString().isEmpty() && et_jv_yazar.getText().toString().isEmpty()) {
					Toast.makeText(kitap_ekle.this, "Kitap adý ve yazar bilgisi girilmelidir", Toast.LENGTH_LONG).show();
				}
				else
				{
					book.setTitle((et_jv_kitapadi.getText()).toString());
					book.setAuthor(et_jv_yazar.getText().toString());
					book.setGenre(et_jv_tur.getText().toString());
					book.setDescription(et_jv_aciklama.getText().toString());
					
					//Toast.makeText(kitap_ekle.this,book.getTitle()+"-"+book.getAuthor()+"-"+book.getGenre()+"-"+book.getDescription(), Toast.LENGTH_LONG).show();
					long n = db.addBook(book);
					Intent intent = new Intent(kitap_ekle.this,MainActivity.class);
					//Toast.makeText(kitap_ekle.this, ""+n, Toast.LENGTH_LONG).show();
					startActivity(intent);
				}
				
				
			}
		});
	}
	

}
