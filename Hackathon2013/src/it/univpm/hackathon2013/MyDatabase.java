package it.univpm.hackathon2013;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDatabase {

	SQLiteDatabase mDb;
	DbHelper mDbHelper;
	Context mContext;
	private static final String DB_NAME = "tutorialdb";
	private static final int DB_VERSION = 1; 

	public MyDatabase(Context ctx) {
		mContext = ctx;
		mDbHelper = new DbHelper(ctx, DB_NAME, null, DB_VERSION);
	}

	public void open() { 
		mDb = mDbHelper.getWritableDatabase();

	}

	public void close() {
		mDb.close();
	}

	// BACINI
	public void inseisciBacino(int id, String nome) {
		ContentValues cv = new ContentValues();
		cv.put(BacinoMetaData.ID, id);
		cv.put(BacinoMetaData.NOME, nome);
		mDb.insert(BacinoMetaData.NOME_TABELLA, null, cv);
	}

	public Cursor fetchBacini() {
		return mDb.query(BacinoMetaData.NOME_TABELLA, null, null, null, null,
				null, null);
	}

	static class BacinoMetaData { 
		static final String NOME_TABELLA = "Bacino";
		static final String ID = "_id";
		static final String NOME = "nome";
	}

	private static final String BACINO_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS "
			+ BacinoMetaData.NOME_TABELLA
			+ " ("
			+ BacinoMetaData.ID
			+ " integer primary key autoincrement, "
			+ BacinoMetaData.NOME
			+ " text not null);";

	// FINE BACINI
	// STAZIONI
	public void inseisciStazione(String codice, String nome, int tipo,
			int id_bacino) { 
		ContentValues cv = new ContentValues();
		// cv.put(StazioneMetaData.ID, id);
		cv.put(StazioneMetaData.CODICE, codice);
		cv.put(StazioneMetaData.NOME, nome);
		cv.put(StazioneMetaData.TIPO, tipo);
		cv.put(StazioneMetaData.ID_BACINO, nome);
		mDb.insert(StazioneMetaData.NOME_TABELLA, null, cv);
	}

	public Cursor fetchStazioni() {
		return mDb.query(StazioneMetaData.NOME_TABELLA, null, null, null, null,
				null, null);
	}

	static class StazioneMetaData { 
		static final String NOME_TABELLA = "Stazione";
		static final String ID = "_id";
		static final String NOME = "nome";
		static final String CODICE = "codice";
		static final String TIPO = "tipo";
		static final String ID_BACINO = "id_bacino";
	}

	private static final String STAZIONE_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS "
			+ StazioneMetaData.NOME_TABELLA
			+ " ("
			+ StazioneMetaData.ID
			+ " integer primary key autoincrement, "
			+ StazioneMetaData.NOME
			+ " text not null,"
			+ StazioneMetaData.CODICE
			+ " text not null,"
			+ StazioneMetaData.TIPO
			+ " integer not null,"
			+ StazioneMetaData.ID_BACINO + " integer not null);";

	// FINE STAZIONI
	// LETTURE
	public void inseisciLettura(String data, String ora, int valore,
			String codice) {
		ContentValues cv = new ContentValues();
		// cv.put(StazioneMetaData.ID, id);
		cv.put(LetturaMetaData.DATA, data);
		cv.put(LetturaMetaData.ORA, ora);
		cv.put(LetturaMetaData.VALORE, valore);
		cv.put(LetturaMetaData.ID_STAZIONE, codice);
		mDb.insert(LetturaMetaData.NOME_TABELLA, null, cv);
	}

	public Cursor fetchLetture() {
		return mDb.query(LetturaMetaData.NOME_TABELLA, null, null, null, null,
				null, null);
	}

	static class LetturaMetaData {
		static final String NOME_TABELLA = "Lettura";
		static final String ID = "_id";
		static final String DATA = "data";
		static final String ORA = "ora";
		static final String VALORE = "valore";
		static final String ID_STAZIONE = "id_stazione";
	}

	private static final String LETTURA_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS " 
			+ LetturaMetaData.NOME_TABELLA
			+ " ("
			+ LetturaMetaData.ID
			+ " integer primary key autoincrement, "
			+ LetturaMetaData.DATA
			+ " text not null,"
			+ LetturaMetaData.ORA
			+ " text not null,"
			+ LetturaMetaData.VALORE
			+ " integer not null,"
			+ LetturaMetaData.ID_STAZIONE + " text not null);";

	// FINE LETTURE
	/**
	 * QUERY COMPLESSE
	 * @author enrico
	 *
	 */
	public Cursor fetchLettureMisaByCodiceStazione(String codice_stazione) {
		String sql="SELECT " +
				" Lettura._id as _id," +
				" Stazione.nome as nome, " +
				" Lettura.data as data," +
				" Lettura.ora as ora," +
				" Lettura.valore as valore " +
				"FROM Lettura, Stazione " +
				"WHERE Stazione.codice = Lettura.id_stazione AND Lettura.id_stazione =\""+codice_stazione+"\"";
		try{
			Cursor c=mDb.rawQuery(sql, null);
			Log.i("ENRICO", "Cursor di lunghezza: "+c.getCount());
			c.moveToFirst();
			while(!c.isAfterLast()){
				Log.e("TTT","STRINGAAAAAAAAA"+c.getString(2));
				c.moveToNext();
			}
			return c;
		}catch(NullPointerException e){
			return null;
		}
	}
	
	private class DbHelper extends SQLiteOpenHelper {
		public DbHelper(Context context, String name, CursorFactory factory,
				int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase _db) {
			_db.execSQL(BACINO_TABLE_CREATE);
			_db.execSQL(STAZIONE_TABLE_CREATE);
			_db.execSQL(LETTURA_TABLE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
			//TODO: Non serve
		}

	}

}
