package it.univpm.hackathon2013;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class BasinActivity extends Activity {

	// public SqliteManager db=null;
	// public DataSource datasource;
	public ListView lv;
	public SimpleCursorAdapter adapter3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(it.univpm.hackathon2013.R.layout.basin_activity);
		//lv = (ListView) this.findViewById(R.id.list);
		//TextView productsTv = (TextView) findViewById(R.id.product);
		MyDatabase db = new MyDatabase(getApplicationContext());
		db.open();
		Cursor c = null;
		/*
// GESTIONE DATI DEI BACINI
		if (db.fetchBacini().getCount() == 0) {
			db.inseisciBacino(0, "Misa");
			db.inseisciBacino(1, "Cesano");
			db.inseisciBacino(2, "Metauro");
			db.inseisciBacino(3, "Esino");
		}
		// CREAZIONE ADAPTER
		Cursor c = db.fetchBacini(); 
		startManagingCursor(c);
		SimpleCursorAdapter adapter1 = new SimpleCursorAdapter(
				this, R.layout.stazione_list_item,
				c, new String[] { MyDatabase.BacinoMetaData.NOME },
				new int[] { R.id.data });
		productsLv.setAdapter(adapter1); 
		// FINE
		// ESTRAZIONE DATI E INSERIMENTO IN TEXTVIEW
		int nameCol1 = c.getColumnIndex(MyDatabase.BacinoMetaData.ID);
		int priceCol1 = c.getColumnIndex(MyDatabase.BacinoMetaData.NOME);
		if (c.moveToFirst()) {
			do {
				productsTv.append("Product Name:" + c.getString(nameCol1)+ ", Price:" + c.getInt(priceCol1) + "\n");
			} while (c.moveToNext());
		}
		c.close();
		db.close();
// FINE GESTIONE BACINI
// GESTIONE DATI DELLE STAZIONI
		db.open();*/
		if (db.fetchStazioni().getCount() == 0) {
			db.inseisciStazione("001", "FRasassi", 0, 0);
			db.inseisciStazione("001", "FRasassi", 0, 1);
			db.inseisciStazione("003", "FRasassi", 0, 2);
			db.inseisciStazione("003", "FRasassi", 0, 3);
			db.inseisciStazione("001", "FRasassi", 1, 0);
			db.inseisciStazione("004", "FRasassi", 1, 1);
			db.inseisciStazione("003", "FRasassi", 1, 2);
			db.inseisciStazione("001", "FRasassi", 1, 3);
		}/*
		// CREAZIONE ADAPTER
		c = db.fetchStazioni(); // query
		startManagingCursor(c);
		SimpleCursorAdapter adapter2 = new SimpleCursorAdapter(
				this, R.layout.stazione_list_item,
				c, new String[] { MyDatabase.StazioneMetaData.NOME, MyDatabase.StazioneMetaData.CODICE },
				new int[] { R.id.data, R.id.ora });
		productsLv.setAdapter(adapter2); 
		// FINE
		// ESTRAZIONE DATI E INSERIMENTO IN TEXTVIEW
		int nameCol2 = c.getColumnIndex(MyDatabase.StazioneMetaData.ID);
		int priceCol2 = c.getColumnIndex(MyDatabase.StazioneMetaData.NOME);
		if (c.moveToFirst()) {
			do {
				productsTv.append("Product Name:" + c.getString(nameCol2)
						+ ", Price:" + c.getInt(priceCol2) + "\n");
			} while (c.moveToNext());
		}
		c.close();
		db.close();
// FINE GESTIONE STAZIONI
*/
// GESTIONE DATI DELLE LETTURE
		if (db.fetchLetture().getCount() == 0) {

			db.inseisciLettura("23/05/2014", "14:56", 12, "001");
			db.inseisciLettura("23/05/2014", "14:56", 12, "002");
			db.inseisciLettura("23/05/2014", "14:56", 12, "003");
			db.inseisciLettura("23/05/2014", "14:56", 12, "003");
			db.inseisciLettura("23/05/2014", "14:56", 11, "003");
			db.inseisciLettura("23/05/2014", "14:56", 145, "003");
			db.inseisciLettura("23/05/2014", "14:56", 17, "003");
			db.inseisciLettura("23/05/2014", "14:56", 12, "004");
			
		}
		// CREAZIONE ADAPTER
		//c = db.fetchLettureMisaByCodiceStazione("003");
		//c.moveToFirst();
//		
//		startManagingCursor(c);
//		adapter3 = new SimpleCursorAdapter(this,R.layout.stazione_list_item, c, 
//				new String[] {"data", "ora","valore"},
//				new int[] {R.id.data, R.id.ora, R.id.valore });
//		//lv.setAdapter(adapter3);
//		adapter3.getCursor().requery();
		// FINE

		// ESTRAZIONE DATI E INSERIMENTO IN TEXTVIEW
//		int nameCol3 = c.getColumnIndex(MyDatabase.LetturaMetaData.ID);
//		int priceCol3 = c.getColumnIndex(MyDatabase.LetturaMetaData.VALORE);

//		if (c.moveToFirst()) {
//			do {
//				productsTv.append("Product Name:" + c.getString(nameCol3)
//						+ ", Price:" + c.getInt(priceCol3) + "\n");
//			} while (c.moveToNext());
//		}
//		c.close();
		//db.close();
		ArrayList<ArrayList<String>> tabella=db.fetchLettureMisaByCodiceStazione("001");
		Iterator<ArrayList<String>> iter=tabella.iterator();
		while(iter.hasNext()){
			refreshRiga(iter.next());	
		}
		
// FINE GESTIONE STAZIONI
	}
	public void refreshRiga(ArrayList<String> list){
		LinearLayout layout=(LinearLayout)this.findViewById(R.id.mainLayout);
		LinearLayout row=new LinearLayout(this);
		row.setOrientation(LinearLayout.HORIZONTAL);
		Iterator<String> iter=list.iterator();
		while(iter.hasNext()){
			TextView cell=new TextView(this);
			cell.setText(iter.next().toString());
			row.addView(cell);
		}
		ImageButton graphButton = new ImageButton (this);
		graphButton.setImageResource(R.drawable.graph);
		layout.addView(row);
	}
}
