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
//		MyDatabase db = new MyDatabase(getApplicationContext());
//		db.open();
//		Cursor c = null;
		
//			db.inserisciBacino(0, "misa");
//			db.inserisciBacino(1, "cesano");
//			db.inserisciBacino(2, "metauro");
//			db.inserisciBacino(3, "esino");
//		
//			db.inserisciStazione("001", "FRasassi", 0, 0);
//			db.inserisciStazione("001", "FRasassi", 0, 1);
//			db.inserisciStazione("003", "FRasassi", 0, 2);
//			db.inserisciStazione("003", "FRasassi", 0, 3);
//			db.inserisciStazione("001", "FRasassi", 1, 0);
//			db.inserisciStazione("004", "FRasassi", 1, 1);
//			db.inserisciStazione("003", "FRasassi", 1, 2);
//			db.inserisciStazione("001", "FRasassi", 1, 3);
		
		Bundle bundle = getIntent().getExtras();
		if(bundle!=null){
			int id=bundle.getInt("basin", 0);
			HtmlParser parser=new HtmlParser(this,id);
			parser.refresh(id);
		}else{
			//refreshTabella(db.fetchStazioneByCodiceBacino(0));
		}
		
		
		//db.close();
// FINE GESTIONE STAZIONI
	}
	public void refreshTabella(ArrayList<ArrayList<String>> tabella){
		Iterator<ArrayList<String>> iter=tabella.iterator();
		while(iter.hasNext()){
			refreshRiga(iter.next());	
		}
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
