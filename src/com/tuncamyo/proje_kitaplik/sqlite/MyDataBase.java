package com.tuncamyo.proje_kitaplik.sqlite;

import java.util.LinkedList;
import java.util.List;

import com.tuncamyo.proje_kitaplik.model.Book;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDataBase extends SQLiteOpenHelper{
	// Database Versiyonu
    private static final int DATABASE_VERSION = 1;
    // Database Ad�
    private static final String DATABASE_NAME = "BookDB";
    
    
	public MyDataBase(Context context) {
		super( context, DATABASE_NAME, null, DATABASE_VERSION);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// SQL Kitap tablosunu olu�turma ifadesi
				String CREATE_BOOK_TABLE = "CREATE TABLE books ( " +
		                "id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
						"title TEXT, "+
						"author TEXT,"
						+ "genre TEXT,"
						+ "description TEXT )";
				
				// kitap tablosunu olu�turuyor
				db.execSQL(CREATE_BOOK_TABLE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// E�er kitap tablosu varsa database g�ncellemesi s�ras�nda silme i�lemi yap�yor
        db.execSQL("DROP TABLE IF EXISTS books");
        
        // yeni bir tablo olu�turuyor
        this.onCreate(db);
		
	}
	
	
	/**
     * CRUD i�lemleri (olu�turma "add", okuma "get", g�ncelleme, silme) kitap + t�m kitaplar� getir + t�m kitaplar� sil
     */
	
	// Kitaplar tablosunun ismi
    private static final String TABLE_BOOKS = "books";
    // kitaplar tablosundaki s�tun isimleri
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_AUTHOR = "author";
    private static final String KEY_GENRE = "genre";
    private static final String KEY_DESCRIPTION = "description";
    private static final String[] COLUMNS = {KEY_ID,KEY_TITLE,KEY_AUTHOR,KEY_GENRE,KEY_DESCRIPTION};
	public long addBook(Book book){
		Log.d("addBook", book.toString());
		// 1. yaz�labilir bir database referans� al�r
		SQLiteDatabase db = this.getWritableDatabase();
		 
		// 2. s�t�n de�erleri i�in ContentValues nesnesi olu�turur
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, book.getTitle()); 
        values.put(KEY_AUTHOR, book.getAuthor()); 
        values.put(KEY_GENRE, book.getGenre());
        values.put(KEY_DESCRIPTION, book.getDescription());
        // 3. Ekleme
        long n = db.insert(TABLE_BOOKS, // tablo
        		null, //nullColumnHack
        		values); 
        
        // 4. Database ba�lant�s�n� kapat�r
        db.close(); 
        return n;
	}
	
	public Book getBook(int id){

		// 1. okunabilir bir database referans� alma i�lemi
		SQLiteDatabase db = this.getReadableDatabase();
		 
		// 2. sorgu olu�turma i�lemi
        Cursor cursor = 
        		db.query(TABLE_BOOKS, // a. table
        		COLUMNS, // b. column names
        		" id = ?", // c. selections 
                new String[] { String.valueOf(id) }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        
        // 3. Cursor bo� de�ilse ve ilk de�eri okuyabilirse
        if (cursor != null)
            cursor.moveToFirst();
 
        // 4. kitap nesnesi olu�turur
        Book book = new Book();
        book.setId(Integer.parseInt(cursor.getString(0)));
        book.setTitle(cursor.getString(1));
        book.setAuthor(cursor.getString(2));
        book.setGenre(cursor.getString(3));
        book.setDescription(cursor.getString(4));
		Log.d("getBook("+id+")", book.toString());

        // 5. geriye bir kitap nesnesi d�nd�r�r
        return book;
	}
	
	// T�m kitaplar� getirme
    public List<Book> getAllBooks() {
        List<Book> books = new LinkedList<Book>();

        // 1. Sorguyu olu�turma
        String query = "SELECT  * FROM " + TABLE_BOOKS;
 
    	// 2. yaz�labilir bir database referans� al�r
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
 
        // 3.sat�r sat�r okuyarak buradaki de�erleri kullanarak kitap nesnesi olu�turur ve kitap listesine ekler
        Book book = null;
        if (cursor.moveToFirst()) {
            do {
            	book = new Book();
                book.setId(Integer.parseInt(cursor.getString(0)));
                book.setTitle(cursor.getString(1));
                book.setAuthor(cursor.getString(2));
                book.setGenre(cursor.getString(3));
                book.setDescription(cursor.getString(4));
                // kitaplar list nesnesine kitap ekleme i�lemi
                books.add(book);
            } while (cursor.moveToNext());
        }
        
		Log.d("getAllBooks()", books.toString());

        // de�er olarak list<kitaplar> nesnesini d�nd�r�r
        return books;
    }
	
	 // Tek bir kitab� g�ncelleme i�lemi
    public int updateBook(Book book) {

    	// 1. yaz�labilir bir veritaban� nesnesi referans� al�r
        SQLiteDatabase db = this.getWritableDatabase();
 
		// 2. s�t�n de�erleri i�in ContentValues nesnesi olu�turur
        ContentValues values = new ContentValues();
        values.put("title", book.getTitle());  
        values.put("author", book.getAuthor()); 
        values.put("genre", book.getGenre()); 
        values.put("description", book.getDescription()); 
        // 3. sat�r g�ncelleme i�lemi
        int i = db.update(TABLE_BOOKS, 
        		values, 
        		KEY_ID+" = ?", 
                new String[] { String.valueOf(book.getId()) }); 
        
        // 4. veritaban�n� kapatma
        db.close();
        
        return i;
        
    }

    // Tek bir kitab� silme i�lemi
    public void deleteBook(Book book) {

    	
        SQLiteDatabase db = this.getWritableDatabase();
        
        
        db.delete(TABLE_BOOKS,
        		KEY_ID+" = ?",
                new String[] { String.valueOf(book.getId()) });
        
        
        db.close();
        
		Log.d("deleteBook", book.toString());

    }

}
