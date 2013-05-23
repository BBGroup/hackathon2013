package it.univpm.hackathon2013;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

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
   
   
    //i seguenti 2 metodi servono per la lettura/scrittura del db. aggiungete e modificate a discrezione
   // consiglio:si potrebbe creare una classe Prodotto, i quali oggetti verrebbero passati come parametri dei seguenti metodi, rispettivamente ritornati. Lacio a voi il divertimento

   
    public void inserisciBacino(int id, String nome){ //metodo per inserire i dati
            ContentValues cv=new ContentValues();
            cv.put("id", id);
            cv.put("nome", nome);
            mDb.insert("Bacino", null, cv);
    }
    public void inserisciLettura(String data, String ora, String valore, String id_stazione){ //metodo per inserire i dati
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
   
    public Cursor fetchProducts(){ //metodo per fare la query di tutti i dati
            return mDb.query("Bacino", null,null,null,null,null,null);              
    }

  

    private static final String CREAZIONE_BACINO = "CREATE TABLE IF NOT EXISTS Bacino(" +
    		"id integer primary key, " +
    		"nome text not null);";
    private static final String CREAZIONE_BACINOSTAZIONE="CREATE TABLE IF NOT EXISTS BacinoStazione(" +
    		"_id integer primary key autoincrement," +
    		"id_bacino integer not null," +
    		"id_stazione integer not null)";
    private static final String CREAZIONE_STAZIONE="CREATE TABLE IF NOT EXISTS Stazione(" +
    		"_id integer primary key," +
    		"codice string primary key," +
    		"nome text not null" +
    		"tipo integer not null," +
    		"id_bacino integer not null)";
    private static final String CREAZIONE_LETTURA="CREATE TABLE IF NOT EXISTS Lettura (" +
    		"_id integer primary key autoincrement, "+
    		"data text not null, "+
    		"ora text not null,"+
    		"valore text not null,"+
    		"id_stazione integer not null);";
    
    

    private class DbHelper extends SQLiteOpenHelper { //classe che ci aiuta nella creazione del db

            public DbHelper(Context context, String name, CursorFactory factory,int version) {
                    super(context, name, factory, version);
            }

            @Override
            public void onCreate(SQLiteDatabase _db) { //solo quando il db viene creato, creiamo la tabella
                    _db.execSQL(CREAZIONE_BACINO);
                    _db.execSQL(CREAZIONE_STAZIONE);
                    _db.execSQL(CREAZIONE_LETTURA);
            }

            @Override
            public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
                    //qui mettiamo eventuali modifiche al db, se nella nostra nuova versione della app, il db cambia numero di versione

            }

    }
           

}