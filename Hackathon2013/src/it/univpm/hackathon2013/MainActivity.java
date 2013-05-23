package it.univpm.hackathon2013;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	
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
	int bacino=-1;
	Button misaButton;
	Button esinoButton;
	Button cesanoButton;
	Button metauroButton;
	
	Button chartButton;
	
	TextView selArea;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        getPreferences();
        
        Log.d("debuglog","gettedpreferences");
        selArea = (TextView) findViewById(R.id.selectedArea);
        
        
        gpsCall();
       
        
        misaButton = (Button) findViewById(R.id.misabutton);
        misaButton.setOnClickListener(new OnClickListener()
        {
        	@Override
        	public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this , BasinActivity.class);
                intent.putExtra("basin", basinInt);
				startActivity( intent  );
			}
        });
        
        metauroButton = (Button) findViewById(R.id.metaurobutton);
        metauroButton.setOnClickListener(new OnClickListener() {
        	@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this , BasinActivity.class);
                intent.putExtra("basin", basinInt);
				startActivity( intent  );
			}
		});
        
        esinoButton = (Button) findViewById(R.id.esinobutton);
        esinoButton.setOnClickListener(new OnClickListener() {
        	@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this , BasinActivity.class);
                intent.putExtra("basin", basinInt);
				startActivity( intent  );
			}
		});
        
        cesanoButton = (Button) findViewById(R.id.cesanobutton);
        cesanoButton.setOnClickListener(new OnClickListener() {
        	@Override
			public void onClick(View v) {
        		Intent intent = new Intent(MainActivity.this , BasinActivity.class);
                intent.putExtra("basin", basinInt);
				startActivity( intent  );
			}
		});
        
        selArea = (TextView) findViewById(R.id.selectedArea);
        selAreaSetText();
        
        
        chartButton = (Button) findViewById(R.id.chartButton);
        chartButton.setOnClickListener(new OnClickListener() {
        	@Override
			public void onClick(View v) {
        		Intent intent = new Intent(MainActivity.this , ChartActivity.class);
                
				startActivity( intent  );
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
	        			selArea.setText("Bacino selezionato: Misa");
	        			break;
	        		case 1:
	        			Log.d("debuglog","cesano");
	        			selArea.setText("Bacino selezionato: Cesano");
	        			break;
	        		case 2:
	        			Log.d("debuglog","metauro");
	        			selArea.setText("Bacino selezionato: Metauro");
	        			break;
	        		case 3:
	        			Log.d("debuglog","esino");
	        			selArea.setText("Bacino selezionato: Esino");
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
    	choiceMod=(preferences.getString("area", "-1"));
    	basin=(preferences.getString("manual", "-1"));
    	basinInt=Integer.parseInt(basin);
    	modInt=Integer.parseInt(choiceMod);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	menu.add(Menu.NONE, 1, 1, "Settings");
    	menu.add(Menu.NONE, 2, 2, "Quit");
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
    		finish();
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
    
    
    
}
