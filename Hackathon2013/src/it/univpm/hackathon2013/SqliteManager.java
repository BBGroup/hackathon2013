package it.univpm.hackathon2013;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SqliteManager{  

    SQLiteDatabase mDb;
    DbHelper mDbHelper;
    Context mContext;
    private static final String DB_NAME="weatherwatcher";
    private static final int DB_VERSION=1; 
   
    public SqliteManager(Context ctx){
            mContext=ctx;
            mDbHelper=new DbHelper(ctx, DB_NAME, null, DB_VERSION);
    }
   
    public void open(){
            mDb=mDbHelper.getWritableDatabase();
           
    }
   
    public void close(){ //chiudiamo il database su cui agiamo
            mDb.close();
    }
   
    public void inserisciBacino(int id, String nome){ //metodo per inserire i dati
            ContentValues cv=new ContentValues();
            cv.put("_id", id);
            cv.put("nome", nome);
            mDb.insert("Bacino", null, cv);
    }
    public void inserisciLettura(String data, String ora, double valore, String id_stazione){ //metodo per inserire i dati
        ContentValues cv=new ContentValues();
        cv.put("data", data);
        cv.put("ora", ora);
        cv.put("valore", valore);
        cv.put("id_stazione", id_stazione);
        mDb.insert("Lettura", null, cv);
    }
    public void inserisciStazione(String nome, String codice, int tipo, int id_bacino){ //metodo per inserire i dati
        ContentValues cv=new ContentValues();
        cv.put("codice", codice);
        cv.put("nome", nome);
        cv.put("tipo", tipo);
        cv.put("id_bacino", id_bacino);
        mDb.insert("Stazione", null, cv);
    }
   
    public Cursor fetchStazione(){ 
    	Cursor cursor=null;
    	try{
            cursor=mDb.rawQuery("" +
            		"SELECT Lettura._id as _id," +
            		"Stazione.nome as nome, " +
            		"Lettura.data as data, " +
            		"Lettura.ora as ora, " +
            		"Lettura.valore as valore" +
            		" FROM Stazione, Bacino, Lettura" +
            		" WHERE Stazione.id_bacino = Bacino._id AND Lettura.id_stazione = Stazione.codice",null);
            Log.e("TEXT","Cursor: colonne:"+cursor.getColumnCount()+" righe:"+cursor.getCount());
    	}catch(Exception e){
    		Log.e("SQL","ERRORE QUERY");
    		e.printStackTrace();
    	}finally{
    		
    	}
    	return cursor;
    }

  

    private static final String CREAZIONE_BACINO = "CREATE TABLE Bacino( " +
    		"_id integer primary key, " +
    		"nome text not null)";
    private static final String CREAZIONE_STAZIONE="CREATE TABLE Stazione( " +
    		"_id integer primary key autoincrement," +
    		"codice string," +
    		"nome text not null," +
    		"tipo integer not null," +
    		"id_bacino integer not null)";
    private static final String CREAZIONE_LETTURA="CREATE TABLE Lettura ( " +
    		"_id integer primary key autoincrement, "+
    		"data text not null, "+
    		"ora text not null,"+
    		"valore text not null,"+
    		"id_stazione text not null)";
    
    

    private class DbHelper extends SQLiteOpenHelper { 

            public DbHelper(Context context, String name, CursorFactory factory,int version) {
                    super(context, name, factory, version);
            }

            @Override
            public void onCreate(SQLiteDatabase _db) { 
            	try{
                    _db.execSQL(CREAZIONE_BACINO);
                    _db.execSQL(CREAZIONE_STAZIONE);
                    _db.execSQL(CREAZIONE_LETTURA);
            	}catch(Exception e){
            		Log.e("SQL","ERRORE CREAZIONE!!!!");
            	}
            }

			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion) {
				// TODO Auto-generated method stub
				
			}
    }
           

}