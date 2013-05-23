package it.univpm.hackathon2013;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class BasinActivity extends Activity{
	
	public SqliteManager db=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.setContentView(it.univpm.hackathon2013.R.layout.basin_activity);
		ListView lv=(ListView)this.findViewById(R.id.lista);
		
		db=new SqliteManager(getApplicationContext());
        db.open();  //apriamo il db
       
        if(db.fetchStazione().getCount()<1){
        	db.inserisciBacino(0, "Misa");
        	db.inserisciBacino(1, "Cesano");
        	db.inserisciBacino(2, "Metauro");
        	db.inserisciBacino(3, "Esino");
        	
        	db.inserisciStazione("Misa","026",0,0);
        	db.inserisciStazione("Nevola","120",0,0);
        	db.inserisciStazione("Serra de' Conti","003",0,0);
        	db.inserisciStazione("Montecarotto","119",1,0);
        	db.inserisciStazione("Arcevia","123",1,0);
        	
        	db.inserisciStazione("Cesano Foce","121",0,1);
        	db.inserisciStazione("Cesano","018",0,1);
        	db.inserisciStazione("Pergola","108",0,1);
        	db.inserisciStazione("Pergola","108",1,1);
        	
        	db.inserisciStazione("Metauro Foce","152",0,2);
        	db.inserisciStazione("Metauro","017",0,2);
        	db.inserisciStazione("Ghilardino (Tarugo)","105",0,2);
        	db.inserisciStazione("Fossombrone (Metauro)","151",0,2);
        	db.inserisciStazione("Acqualagna (Candigliano)","106",0,2);
        	db.inserisciStazione("Biscubio","107",0,2);
        	db.inserisciStazione("Sant'Angelo in Vado","101",0,2);
        	db.inserisciStazione("Metauro Foce","152",1,2);
        	db.inserisciStazione("Metauro","017",1,2);
        	db.inserisciStazione("Fossombrone (Metauro)","151",1,2);
        	db.inserisciStazione("Biscubio","107",1,2);
        	db.inserisciStazione("Sant'Angelo in Vado","101",1,2);
        	
        	db.inserisciStazione("Monte San Vito (Traponzio)","122",0,3);
        	db.inserisciStazione("Chiaravalle","507",0,3);
        	db.inserisciStazione("Moie","506",0,3);
        	db.inserisciStazione("Gola della Rossa","006",0,3);
        	db.inserisciStazione("Camponocecchio","005",0,3);
        	db.inserisciStazione("San Vittore","004",0,3);
        	db.inserisciStazione("Sassoferrato","505",0,3);
        	db.inserisciStazione("Fabriano","002",0,3);
        	db.inserisciStazione("Fabriano2","028",0,3);
        	db.inserisciStazione("Esanatoglia","001",0,3);
        	db.inserisciStazione("Agugliano","111",1,3);
        	db.inserisciStazione("Jesi","110",1,3);
        	db.inserisciStazione("Montecarotto","119",1,3);
        	db.inserisciStazione("Cupramontana","118",1,3);
        	db.inserisciStazione("San Giovanni","504",1,3);
        	db.inserisciStazione("Campodiegoli","502",1,3);
        	db.inserisciStazione("Esanatoglia 2","503",1,3);
        	
        	db.inserisciLettura("20/10/2013", "13:20", 0,"026"); 
        }
        
        
        Cursor c=db.fetchStazione();
        
        if(c.getCount()>0){
        	startManagingCursor(c);
        SimpleCursorAdapter adapter1=new SimpleCursorAdapter(
        		this, 
        		R.layout.stazione_list_item,
        		c,
        		new String[]{"nome","data","ora","valore"},
        		new int[]{R.id.nome,R.id.data,R.id.ora,R.id.valore});
        lv.setAdapter(adapter1); //la listview ha questo adapter*/
        }else{
        	lv.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,new String[]{"Nessun dato pervenuto"}));
        }
        db.close();
       
        //qui vediamo invece come reperire i dati e usarli, in questo caso li stampiamo in una textview
       
        /*int nameCol=c.getColumnIndex(MyDatabase.ProductsMetaData.PRODUCT_NAME_KEY);  //indici delle colonne
        int priceCol=c.getColumnIndex(MyDatabase.ProductsMetaData.PRODUCT_PRICE_KEY);      
       
        if(c.moveToFirst()){  //se va alla prima entry, il cursore non è vuoto
                do {
                               
                        productsTv.append("Product Name:"+c.getString(nameCol)+", Price:"+c.getInt(priceCol)+"\n"); //estrazione dei dati dalla entry del cursor
                                       
                        } while (c.moveToNext());//iteriamo al prossimo elemento
        }*/
		
	}
	
	private ListAdapter createAdapter(){
		String[] textValues=new String[]{"Ciao","Ciao","Bambina"};
		ListAdapter adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,textValues);
		return adapter;
	}

}
