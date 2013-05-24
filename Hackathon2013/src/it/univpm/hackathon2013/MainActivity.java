package it.univpm.hackathon2013;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
//import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	  private static final int SIMPLE_NOTIFICATION_ID = 1;
	  NotificationManager mNotificationManager;
//	String misa="misaBasin";
//	String cesano="cesanoBasin";
//	String esino="esinoBasin";
//	String metauro="metauroBasin";
	double latitudine=0;
	double longitudine=0;
	SharedPreferences preferences;
	String choiceMod;
	String basin;
	int basinInt;
	int modInt;
	int bacino=0;
//	Button misaButton;
//	Button esinoButton;
//	Button cesanoButton;
//	Button metauroButton;
	
//	Button chartButton;
//	Button notifyButton;
	ImageView mappa;
	
	TextView selArea;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        getPreferences();
        
//        HtmlParser _parser = new HtmlParser(this, basinInt);
//        _parser.refresh(basinInt);
        
        Log.d("debuglog","gettedpreferences");
        selArea = (TextView) findViewById(R.id.selectedArea);
        
        
        gpsCall();
       
        
//        misaButton = (Button) findViewById(R.id.misabutton);
//        misaButton.setOnClickListener(new OnClickListener()
//        {
//        	@Override
//        	public void onClick(View v) {
//				Intent intent = new Intent(MainActivity.this , BasinActivity.class);
//                intent.putExtra("basin", basinInt);
//				startActivity( intent  );
//			}
//        });
//        
//        metauroButton = (Button) findViewById(R.id.metaurobutton);
//        metauroButton.setOnClickListener(new OnClickListener() {
//        	@Override
//			public void onClick(View v) {
//				Intent intent = new Intent(MainActivity.this , BasinActivity.class);
//                intent.putExtra("basin", basinInt);
//				startActivity( intent  );
//			}
//		});
//        
//        esinoButton = (Button) findViewById(R.id.esinobutton);
//        esinoButton.setOnClickListener(new OnClickListener() {
//        	@Override
//			public void onClick(View v) {
//				Intent intent = new Intent(MainActivity.this , BasinActivity.class);
//                intent.putExtra("basin", basinInt);
//				startActivity( intent  );
//			}
//		});
//        
//        cesanoButton = (Button) findViewById(R.id.cesanobutton);
//        cesanoButton.setOnClickListener(new OnClickListener() {
//        	@Override
//			public void onClick(View v) {
//        		Intent intent = new Intent(MainActivity.this , BasinActivity.class);
//                intent.putExtra("basin", basinInt);
//				startActivity( intent  );
//			}
//		});
       
      
        
  		mNotificationManager = (NotificationManager) getSystemService(this.NOTIFICATION_SERVICE);
        
//      notifyButton = (Button) findViewById(R.id.notifyButton);
//      notifyButton.setOnClickListener(new OnClickListener() {
//      	@Override
//			public void onClick(View v) {
//      		sendSimpleNotification();
//
//			}
//		});
        
        
        
        
        selArea = (TextView) findViewById(R.id.selectedArea);
        selAreaSetText();
        
        
//        chartButton = (Button) findViewById(R.id.chartButton);
//        chartButton.setOnClickListener(new OnClickListener() {
//        	@Override
//			public void onClick(View v) {
//        		Intent intent = new Intent(MainActivity.this , ChartActivity.class);
//                
//				startActivity( intent  );
//			}
//		});
        
        mappa = (ImageView) findViewById(R.id.marcheMap);
        mappa.setOnTouchListener(new OnTouchListener() {
        	
			@Override
			public boolean onTouch(View v, MotionEvent ev) {
				final int action = ev.getAction();
				final int evX = (int) ev.getX();
				final int evY = (int) ev.getY();
				int lightred=0xfffcb5bd;
				int lightgreen=0xffb5fcc1;
				int lightyellow=0xfffafcb5;
				int lightblue=0xffb5b8fc;
				int red=0xfffc2a42;
				int green=0xff4bfe69;
				int yellow=0xfff8fd43;
				int blue=0xff555cfe;
				switch (action) {
					case MotionEvent.ACTION_DOWN :
						int touchColor = getHotspotColor (R.id.marcheMap, evX, evY);
						Log.d("debuglog",String.valueOf(touchColor));
						if (lightred==touchColor){
							mappa.setImageResource(R.drawable.marchemet);
						} else if (lightblue==touchColor){
							mappa.setImageResource(R.drawable.marcheces);
						} else if (lightyellow==touchColor){
							mappa.setImageResource(R.drawable.marcheesi);
						} else if (lightgreen==touchColor){
							mappa.setImageResource(R.drawable.marchemis);
						}
				   
						break;
				 
					case MotionEvent.ACTION_UP : 
						int touchColor2 = getHotspotColor (R.id.marcheMap, evX, evY);
						Log.d("debuglog",String.valueOf(touchColor2));
						if (red==touchColor2){
							Intent intent = new Intent(MainActivity.this , BasinActivity.class);
			                intent.putExtra("basin", 2);
							startActivity( intent  );
						} else if (blue==touchColor2){
							Intent intent = new Intent(MainActivity.this , BasinActivity.class);
			                intent.putExtra("basin", 1);
							startActivity( intent  );
						} else if (yellow==touchColor2){
							Intent intent = new Intent(MainActivity.this , BasinActivity.class);
			                intent.putExtra("basin", 3);
							startActivity( intent  );
						} else if (green==touchColor2){
							Intent intent = new Intent(MainActivity.this , BasinActivity.class);
			                intent.putExtra("basin", 0);
							startActivity( intent  );
						}
						break;
				}
				return true;
			}
        });
        
    }
    
    @Override
    public void onResume(){
    	super.onResume();
    	Log.d("debuglog", "onresume");
    	getPreferences();
    	gpsCall();
    	selAreaSetText();
    	mappa.setImageResource(R.drawable.marche);
    }
    
    public int getHotspotColor (int hotspotId, int x, int y) {
    	  ImageView img = (ImageView) findViewById (hotspotId);
    	  img.setDrawingCacheEnabled(true); 
    	  Bitmap hotspots = Bitmap.createBitmap(img.getDrawingCache()); 
    	  img.setDrawingCacheEnabled(false);
    	  return hotspots.getPixel(x, y);
    	}
    
    public void selAreaSetText(){
    	//runOnUiThread(new Runnable() {
	       //	  public void run() {
	       		if(modInt==1){
	        		basinInt=bacino;
	        		Log.d("debuglog","gpsmod");
	        	}
	        	//if (modInt==0){
	        		Log.d("debuglog","manmod");
	        		switch (basinInt){
	        		case 0:
	        			Log.d("debuglog","misa");
	        			selArea.setText("(Bacino selezionato per notifiche: Misa)");
	        			break;
	        		case 1:
	        			Log.d("debuglog","cesano");
	        			selArea.setText("(Bacino selezionato per notifiche: Cesano)");
	        			break;
	        		case 2:
	        			Log.d("debuglog","metauro");
	        			selArea.setText("(Bacino selezionato per notifiche: Metauro)");
	        			break;
	        		case 3:
	        			Log.d("debuglog","esino");
	        			selArea.setText("(Bacino selezionato per notifiche: Esino)");
	        			break;
	        		}
	        	//}
	       	 // }
	       	//});
    	
    	
    }
    
//    public void buttonColor(){
//    	misaButton.setBackgroundColor(Color.GRAY);
//        esinoButton.setBackgroundColor(Color.GRAY);
//        cesanoButton.setBackgroundColor(Color.GRAY);
//        metauroButton.setBackgroundColor(Color.GRAY);
//        
//        
//        switch (basinInt){
//        case 0:
//        	misaButton.setBackgroundColor(Color.YELLOW);
//
//        	break;
//        case 1:
//        	cesanoButton.setBackgroundColor(Color.YELLOW);
//        	break;
//        case 2:
//        	metauroButton.setBackgroundColor(Color.YELLOW);
//        	break;
//        case 3:
//        	esinoButton.setBackgroundColor(Color.YELLOW);
//        	break;
//        
//        
//        }
//    }
    
//    private boolean canWriteOnExternalStorage() {
//    	String state = Environment.getExternalStorageState();
//    	if (Environment.MEDIA_MOUNTED.equals(state)) {
//    	return true;
//    	} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
//    	return false;
//    	} else {
//    	return false;
//    	}
//    	}
//    public String date(){
//    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
//		GregorianCalendar actually = new GregorianCalendar();
//		String actuallyString=sdf.format(actually.getTime());
//		return actuallyString;
//	}
//    public void writeData(String input){
//    	String data=input;
//    	if (canWriteOnExternalStorage()){
//    		
//    	    
//    		String date=date();
//    		
//    		
//    		File root = android.os.Environment.getExternalStorageDirectory(); 
//    	    
//    	    File dir = new File (root.getAbsolutePath()+"/calamity");
//    	    dir.mkdirs();
//    	    String dataname="data.txt";
//    	    File datafile = new File(dir, dataname);    	    
//    	    
//    	    
//    	    try {
//    	    	FileOutputStream datafileOut = new FileOutputStream(datafile);
//    	        PrintWriter pw = new PrintWriter(datafileOut);
//    	       
//    	        pw.append(data+",");
//    	        pw.close();
//    	        datafileOut.close();
//    	    } catch (FileNotFoundException e) {
//    	        e.printStackTrace();
//    	        Log.i("debuglog", "File non trovato" +
//    	                " permesso nel manifest??");
//    	    } catch (IOException e) {
//    	        e.printStackTrace();
//    	    }
//    	}
//    }
//    
//    public void readData (){
//    	File sdcard = Environment.getExternalStorageDirectory();
//    	File root = android.os.Environment.getExternalStorageDirectory(); 
//    	File dir = new File (root.getAbsolutePath()+"/calamity");
//    	File datafile=new File(dir,"data.txt" );
//    	StringBuilder text = new StringBuilder();
//    	try {
//    	    BufferedReader br = new BufferedReader(new FileReader(datafile));
//    	    String line;
//
//    	    while ((line = br.readLine()) != null) {
//    	        text.append(line);
//    	        text.append('\n');
//    	    }
//    	}
//    	catch (IOException e) {
//    	    
//    	}
//
//    }
    
    public void gpsCall(){
    	if(modInt==1){
        	Log.d("debuglog","startinglocation");
        	GpsPosition gps = new GpsPosition(this);
        
          
        if(gps.canGetLocation()){
             
            latitudine = gps.getLatitude();
            longitudine = gps.getLongitude();
              
            Log.d("debuglog","latitude: "+String.valueOf(latitudine));
            Log.d("debuglog","longitude: "+String.valueOf(longitudine));
        }
        searchlocation();
        }
    }
    
    public void getPreferences(){
    	preferences=PreferenceManager.getDefaultSharedPreferences(this);
    	choiceMod=(preferences.getString("area", "0"));
    	basin=(preferences.getString("manual", "0"));
    	basinInt=Integer.parseInt(basin);
    	modInt=Integer.parseInt(choiceMod);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	menu.add(Menu.NONE, 1, 1, "Settings");
    	menu.add(Menu.NONE, 2, 2, "Info");
    	menu.add(Menu.NONE, 3, 3, "Quit");
    	menu.add(Menu.NONE,4,4,"Grafico");
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected (MenuItem item){
    	int id=item.getItemId();
    	switch (id){
    	case 1:
    		Intent settingsIntent=new Intent(this, Settings.class);
    		startActivity(settingsIntent);
    		return true;
    	case 2:
    		Intent intent = new Intent(this, Info.class);
            startActivityForResult( intent , 1 );
            return true;
    	case 3:
    		finish();
    		return true;
    	case 4:
    		Intent intent2 = new Intent(MainActivity.this , ChartActivity.class);
          
			startActivity( intent2  );
			return true;
    	}
    	return true;
    	
    }
    public void searchlocation(){
    	double min=-1;
    	
    	double[] poslatitudini=new double[] {43.66256,43.70164,43.77779,43.53909};
    	double[] poslongitudini=new double[] {13.16488,13.07716,13.00232,13.29620};
    	for(int i=0;i<4;i++){
    		double ris=Math.sqrt(Math.pow((latitudine-poslatitudini[i]), 2)+Math.pow((longitudine-poslongitudini[i]), 2));
    		if(bacino==-1 ||ris<min){
    			min=ris;
    			bacino=i;
    			Log.d("debuglog","bacino: "+String.valueOf(bacino));
    			
    		}
    	}
    	selAreaSetText();
    }
    
    
    public void sendSimpleNotification() {
    	mNotificationManager = (NotificationManager) getSystemService(this.NOTIFICATION_SERVICE);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(
                        MainActivity.this);

        // Titolo e testo della notifica
        notificationBuilder.setContentTitle("Calamity Watcher Notification");
        notificationBuilder.setContentText("Allerta idrogeologica area ?");

        // Testo che compare nella barra di stato non appena compare la notifica
        notificationBuilder.setTicker("Allerta!");

        // Data e ora della notifica
        notificationBuilder.setWhen(System.currentTimeMillis());

        // Icona della notifica
        notificationBuilder.setSmallIcon(R.drawable.icona4);

        // Creiamo il pending intent che verr� lanciato quando la notifica
        // viene premuta
        Intent notificationIntent = new Intent(this,  MainActivity.class);
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
