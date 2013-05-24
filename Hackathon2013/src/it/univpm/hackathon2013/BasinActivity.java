package it.univpm.hackathon2013;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class BasinActivity extends Activity {

	private static final int SIMPLE_NOTIFICATION_ID = 1;
	NotificationManager mNotificationManager;

	// public SqliteManager db=null;
	// public DataSource datasource;
	public ListView lv;
	public SimpleCursorAdapter adapter3;
	
	public ArrayList<ArrayList<String>> dataset;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(it.univpm.hackathon2013.R.layout.basin_activity);

		mNotificationManager = (NotificationManager) getSystemService(this.NOTIFICATION_SERVICE);

		// lv = (ListView) this.findViewById(R.id.list);
		// TextView productsTv = (TextView) findViewById(R.id.product);
		// MyDatabase db = new MyDatabase(getApplicationContext());
		// db.open();
		// Cursor c = null;

		// db.inserisciBacino(0, "misa");
		// db.inserisciBacino(1, "cesano");
		// db.inserisciBacino(2, "metauro");
		// db.inserisciBacino(3, "esino");
		//
		// db.inserisciStazione("001", "FRasassi", 0, 0);
		// db.inserisciStazione("001", "FRasassi", 0, 1);
		// db.inserisciStazione("003", "FRasassi", 0, 2);
		// db.inserisciStazione("003", "FRasassi", 0, 3);
		// db.inserisciStazione("001", "FRasassi", 1, 0);
		// db.inserisciStazione("004", "FRasassi", 1, 1);
		// db.inserisciStazione("003", "FRasassi", 1, 2);
		// db.inserisciStazione("001", "FRasassi", 1, 3);

		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			int id = bundle.getInt("basin", 0);
			HtmlParser parser = new HtmlParser(this, id);
			parser.refresh(id);
		} else {
			// refreshTabella(db.fetchStazioneByCodiceBacino(0));
		}

		// db.close();
		// FINE GESTIONE STAZIONI
	}

	public void refreshTabella(ArrayList<ArrayList<String>> tabella) {
		dataset=tabella;
		Iterator<ArrayList<String>> iter = dataset.iterator();
		int i = 0;
		
		while (iter.hasNext()) {
			int color = Color.WHITE;
			if (i % 2 == 0) {
				color = Color.GRAY;
			} else {
				color = Color.WHITE;
			}
			refreshRiga(iter.next(), color,i);
			i++;
		}
	}

	public void refreshRiga(ArrayList<String> list,int color, int id_riga){
		LinearLayout layout=(LinearLayout)this.findViewById(R.id.mainLayout);
		LinearLayout row=new LinearLayout(this);
		row.setOrientation(LinearLayout.VERTICAL);
		row.setBackgroundColor(color);
//		Iterator<String> iter=list.iterator();
//		while(iter.hasNext()){
		
		if(list.get(3).equals("0")){
			TextView cell1=new TextView(this);
			cell1.setText("Livello "+list.get(4));
			row.addView(cell1);
		}
		if(list.get(3).equals("1")){
			TextView cell1=new TextView(this);
			cell1.setText("Pioggia "+list.get(4));
			row.addView(cell1);
		}
			TextView cell2=new TextView(this);
			cell2.setText(list.get(0));
			row.addView(cell2);

			TextView cell3=new TextView(this);
			cell3.setText(list.get(1));
			if(Double.parseDouble(list.get(1))>1){
				cell3.setBackgroundColor(Color.RED);
				this.sendSimpleNotification("Attenzione il valore ha superato la soglia!!");
			}else{
				cell3.setBackgroundColor(Color.TRANSPARENT);
			}
			row.addView(cell3);
				
		ImageButton graphButton = new ImageButton (this);
		graphButton.setImageResource(R.drawable.graph);
		graphButton.setId(id_riga);
		row.addView(graphButton);
		graphButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int id_riga=v.getId();
				String nome_staz=dataset.get(id_riga).get(4);
				int tipo=Integer.parseInt(dataset.get(id_riga).get(3));
				double valore=Double.parseDouble(dataset.get(id_riga).get(1));
				Intent i=new Intent(BasinActivity.this, ChartActivity.class);
				i.putExtra("stazione", nome_staz);
				i.putExtra("tipo", tipo);
				i.putExtra("valore", valore);
				startActivity(i);
				
			}
		});
		layout.addView(row);
	}

	public void sendSimpleNotification(String message) {
		mNotificationManager = (NotificationManager) getSystemService(this.NOTIFICATION_SERVICE);
		NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(
				BasinActivity.this);

		// Titolo e testo della notifica
		notificationBuilder.setContentTitle("Calamity Watcher Notification");
		notificationBuilder.setContentText(message);

		// Testo che compare nella barra di stato non appena compare la notifica
		notificationBuilder.setTicker("Allerta!");

		// Data e ora della notifica
		notificationBuilder.setWhen(System.currentTimeMillis());

		// Icona della notifica
		notificationBuilder.setSmallIcon(R.drawable.icona4);

		// Creiamo il pending intent che verr� lanciato quando la notifica
		// viene premuta
		Intent notificationIntent = new Intent(this, MainActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				notificationIntent, 0);

		notificationBuilder.setContentIntent(contentIntent);

		// Impostiamo il suono, le luci e la vibrazione di default
		notificationBuilder.setDefaults(Notification.DEFAULT_SOUND
				| Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);

		mNotificationManager.notify(SIMPLE_NOTIFICATION_ID,
				notificationBuilder.build());
	}
}
