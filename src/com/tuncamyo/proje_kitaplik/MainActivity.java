package com.tuncamyo.proje_kitaplik;

import java.util.List;

import com.tuncamyo.proje_kitaplik.model.Book;
import com.tuncamyo.proje_kitaplik.sqlite.*;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	ArrayAdapter<String> itemsAdapter;
	ListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		MyDataBase db = new MyDataBase(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        List<Book> list = db.getAllBooks();
        //Toast.makeText(MainActivity.this,list.get(1).toString(), Toast.LENGTH_LONG).show();
        ListView lv = (ListView)findViewById(R.id.lv_kitaplar);
        String[] kitap = new String[list.size()];
        for (int i = 0; i < kitap.length; i++) {
			kitap[i] = list.get(i).getId()+" - "+list.get(i).getAuthor()+" - " + list.get(i).getTitle();
		}
       itemsAdapter = 
        	    new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, kitap);
       lv.setAdapter(itemsAdapter);
       lv.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Intent intent  = new Intent(MainActivity.this,kitap_duzenle.class);
			intent.putExtra("kitapid", ""+(id+1));
			startActivity(intent);
			
		}
	});
       
     
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		case R.id.action_kitap_ekle:
			Intent intent = new Intent(MainActivity.this,kitap_ekle.class);
			startActivity(intent);
			return true;
		case R.id.action_cikis:
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
			
		}
		
		
		
	}

	

	
	
	
}
