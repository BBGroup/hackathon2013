package it.univpm.hackathon2013;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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
	
	SharedPreferences preferences;
	String choiceMod;
	String basin;
	int basinInt;
	int modInt;
	
	Button misaButton;
	Button esinoButton;
	Button cesanoButton;
	Button metauroButton;
	
	TextView selArea;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        getPreferences();
        
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
        
        
        
        
        
    }
    
    public void selAreaSetText(){
    	if (modInt==0){
    		switch (basinInt){
    		case 0:
    			selArea.setText("Bacino selezionato: Misa");
    			break;
    		case 1:
    			selArea.setText("Bacino selezionato: Cesano");
    			break;
    		case 2:
    			selArea.setText("Bacino selezionato: Metauro");
    			break;
    		case 3:
    			selArea.setText("Bacino selezionato: Esino");
    			break;
    		}
    	}
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
    
}
