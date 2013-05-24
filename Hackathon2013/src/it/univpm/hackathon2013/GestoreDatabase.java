package it.univpm.hackathon2013;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class GestoreDatabase extends SQLiteOpenHelper{

	public GestoreDatabase(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
/*
	private static GestoreDatabase instance=null;
	private static int DATABASE_VERSION=1;
	private static String DATABASE_NAME="calamityweather.db";
	
	public static GestoreDatabase getInstance(){
		Context context=CalamityWeatherApplication.getInstance().getApplicationContext();
		if(instance==null){
			instance=new GestoreDatabase(context);
		}
		return instance;
	}
	
	private GestoreDatabase(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db){
		Log.w("DB","Avviato onCreate del GestoreDB");
		db=getWritableDatabase();
		if(!db.isReadOnly()){
			db.execSQL("PRAGMA foreign_keys=ON;");
		}
		//Creazione impostazioni
		db.execSQL("CREATE TABLE "+Bacino.NOME_TABELLA+" (" +
				Bacino._ID+" INTEGER  NOT NULL PRIMARY KEY," +
				Bacino.NOME+" INTEGER NOT NULL);");
		//Creazione dichiarazione
		db.execSQL("CREATE TABLE "+Stazione.NOME_TABELLA+" (" +
				Stazione._ID+" INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT," +
				Stazione.CODICE+" TEXT NOT NULL," +
				Stazione.NOME+" TEXT  NOT NULL);" +
				Stazione.TIPO+" INTEGER NOT NULL," +
				"FOREIGN KEY("+Stazione.ID_BACINO+") REFERENCES "+Bacino.NOME_TABELLA+"("+Bacino._ID+") ON DELETE CASCADE)");
		//Creazione InfoLocali
		db.execSQL("CREATE TABLE "+Lettura.NOME_TABELLA+" (" +
				Lettura._ID+" INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT," +
				Lettura.DATA+" TEXT NOT NULL," +
				Lettura.ORA+" TEXT NOT NULL," +
				Lettura.VALORE+" INTEGER  NOT NULL," +
				Lettura.ID_STAZIONE+" INTEGER NOT NULL," +
				"FOREIGN KEY("+Lettura.ID_STAZIONE+") REFERENCES "+Stazione.NOME_TABELLA+"("+Stazione._ID+") ON DELETE CASCADE);");
		
		Log.w("DB", "Inizio inserimenti dei dati");
		
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS "+Bacino.NOME_TABELLA);
		db.execSQL("DROP TABLE IF EXISTS "+Stazione.NOME_TABELLA);
		db.execSQL("DROP TABLE IF EXISTS "+Lettura.NOME_TABELLA);
		Log.e("ENRICO","Ho droppato tutte le tabelle perchè esistevano!!!");
		onCreate(db);
	}
	
//
//	/**
//	 * Obsoleto
//	 * @param tableCode
//	 */
//	private void elencaCampi(int tableCode){
//		String tableName="";
//		String[] colList=null;
//		switch(tableCode){
//		case 0: tableName=Bacino.NOME_TABELLA;colList=Bacino.COLUMNS;break;
//		case 1: tableName=Stazione.NOME_TABELLA;colList=Stazione.COLUMNS;break;
//		case 2: tableName=Lettura.NOME_TABELLA;colList=Lettura.COLUMNS;break;
//		}
//		System.out.println("Tabella: "+tableName);
//		for(String field: colList){
//			System.out.println("Campo: "+field);
//		}
//	}
//	/**
//	 * Obsoleto
//	 * @param tableCode
//	 */
//	private void visualizza_tabella(int tableCode){
//		
//		SQLiteDatabase db=this.getReadableDatabase();
//		String tableName="";
//		String[] colList=null;
//		switch(tableCode){
//		case 0: tableName=Bacino.NOME_TABELLA;colList=Bacino.COLUMNS;break;
//		case 1: tableName=Stazione.NOME_TABELLA;colList=Stazione.COLUMNS;break;
//		case 2: tableName=Lettura.NOME_TABELLA;colList=Lettura.COLUMNS;break;
//		}
//		System.out.println("Inizio visualizzazione tabella "+tableName+"...");
//		Cursor c=db.query(tableName, colList, null, null, null, null, null);
//		int i=0;
//		while(c.moveToNext()){
//			System.out.println(i+"° elemento: "+c.getString(i));
//			i++;
//		}
//		c.deactivate();
//		c.close();
//		db.close();
//		System.out.println("Visualizzazione tabella completata");
//	}
//	
//	/**
//	 * @param nome_partita
//	 * @param partita_serializzata
//	 * @return
//	 */
////	public void inserisciBacino(int id, String nome_bacino){
////		System.out.println("Iniziato tentativo di inserimento");
////		ContentValues cv=new ContentValues();
////		cv.put("_id", id);
////		cv.put("nome", nome_bacino);
////		SQLiteDatabase db=this.getWritableDatabase();
////		db.insertOrThrow(Bacino.NOME_TABELLA, null, cv);
////		db.close();
////		System.out.println("Inserimento completato.");
////		System.out.println("Bacino inserito con ID: "+ottieni_id_bacino(nome_bacino));
////	}
//	
//	public int ottieni_id_bacino(String nome_bacino){
//		if(esiste(nome_bacino)){
//			SQLiteDatabase db=this.getReadableDatabase();
//			Cursor c=db.query(Bacino.NOME_TABELLA,
//					new String[]{Bacino._ID},
//					Bacino.NOME+"=?",
//					new String[]{nome_bacino},null,null,null);
//			c.moveToFirst();
//			int index=c.getInt(0);
//			System.out.println("Ottenuto indice: "+index);
//			c.close();
//			return index;
//		}else{
//			System.out.println("Questo bacino non esiste");
//			return -1;
//		}
//	}
//	
//	/**
//	 * Restituisce true se esiste gi\E0 nel database una partita con il nome specificato
//	 * come parametro. Altrimenti restituisce false;
//	 * @param nome_partita
//	 * @return
//	 */
//	public boolean esiste(String nome_bacino) {
//		SQLiteDatabase db=getReadableDatabase();
//		Cursor cursor = db.rawQuery("select 1 from Bacino where nome=?",new String[] { nome_bacino});
//		boolean exists = (cursor.getCount() > 0);
//		cursor.close();
//		return exists;
//	}
//	
//	public Cursor elenca_stazioni_del_bacino(int id_bacino){
//		SQLiteDatabase db=this.getReadableDatabase();
//		Cursor cursor = db.query(Bacino.NOME_TABELLA, new String[]{Stazione._ID,Stazione.NOME,Stazione.CODICE, Stazione.TIPO}, null, null, null, null, null);
//		if(cursor.getCount()==0){
//			System.out.println("Non ci sono stazioninel database!!");
//		}else{
//			System.out.println("Ci sono "+cursor.getCount()+" stazioni...adesso tocca tirarle fuori!!!!");
//			cursor.moveToFirst();
//			String nome=cursor.getString(cursor.getColumnIndex(Stazione.NOME));
//			String codice=cursor.getString(cursor.getColumnIndex(Stazione.CODICE));
//			int id=cursor.getInt(cursor.getColumnIndex(Stazione._ID));
//			System.out.println("Trovata satzione "+nome+" con ID: "+id+" e CODICE: "+codice);
//			while(cursor.moveToNext()){
//				nome=cursor.getString(cursor.getColumnIndex(Stazione.NOME));
//				id=cursor.getInt(cursor.getColumnIndex(Stazione._ID));
//				System.out.println("Trovata partita "+nome+" con ID: "+id);
//			}
//			return cursor;
//		}
//		System.out.println("Elenco terminato");
//		cursor.close();
//		db.close();
//		return null;
//	}
//	
//	public void inserisci(int codice_tabella, ContentValues valori){
//		String nome_tabella=getNomeTabellaByCodice(codice_tabella);
//		//db.insert(nome_tabella, null, valori);
//	}
//	
//	/**
//	 * Il codice è quell odella tabella dato da NomeTabella.CODICE;
//	 * @param codice
//	 * @return
//	 */
//	private String[] getListaColonneByCodice(int codice){
//		String[] lista_colonne=null;
//		switch(codice){
//		case 0: lista_colonne=Bacino.COLUMNS; break;
//		case 1: lista_colonne=Stazione.COLUMNS; break;
//		case 2: lista_colonne=Lettura.COLUMNS; break;
//		}
//		return lista_colonne;
//	}
//	private String getNomeTabellaByCodice(int codice){
//		String nome_tabella=null;
//		switch(codice){
//		case 0: nome_tabella=Bacino.NOME_TABELLA; break;
//		case 1: nome_tabella=Stazione.NOME_TABELLA; break;
//		case 2: nome_tabella=Lettura.NOME_TABELLA; break;
//		}
//		return nome_tabella;
//	}
//	public void inserisciBacino(int id, String nome){ //metodo per inserire i dati
//        ContentValues cv=new ContentValues();
//        cv.put(Bacino._ID, id);
//        cv.put(Bacino.NOME, nome);
//        this.inserisci(Bacino.CODICE_TABELLA, cv);
//	}
//	public void inserisciLettura(String data, String ora, String valore, String id_stazione){
//		ContentValues cv=new ContentValues();
//		cv.put(Lettura.DATA, data);
//		cv.put(Lettura.ORA, ora);
//		cv.put(Lettura.VALORE, valore);
//		cv.put(Lettura.ID_STAZIONE, id_stazione);
//		this.inserisci(Lettura.CODICE_TABELLA, cv);
//	}
//	public void inserisciStazione(String nome, String codice, int tipo, int id_bacino){
//		ContentValues cv=new ContentValues();
//		cv.put(Stazione.CODICE, codice);
//		cv.put(Stazione.NOME, nome);
//		cv.put(Stazione.TIPO, tipo);
//		cv.put(Stazione.ID_BACINO, id_bacino);
//		this.inserisci(Stazione.CODICE_TABELLA, cv);
//}
	
}