package it.univpm.hackathon2013;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	
	String misa="misaBasin";
//	String cesano="cesanoBasin";
//	String esino="esinoBasin";
//	String metauro="metauroBasin";
	
	SharedPreferences preferences;
	String choiceMod;
	String basin;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        getPreferences();
        
        Button misaButton = (Button) findViewById(R.id.misabutton);
        misaButton.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this , BasinActivity.class);
                intent.putExtra("misa", misa);
				startActivity( intent  );
			}
        });
//        
//        Button metauroButton = (Button) findViewById(R.id.metaurobutton);
//        metauroButton.setOnClickListener(new OnClickListener() {
//        	@Override
//			public void onClick(View v) {
//				Intent intent = new Intent(MainActivity.this , BasinActivity.class);
//                intent.putExtra("metauro", metauro);
//				startActivity( intent  );
//			}
//		});
//        
//        Button esinoButton = (Button) findViewById(R.id.esinobutton);
//        esinoButton.setOnClickListener(new OnClickListener() {
//        	@Override
//			public void onClick(View v) {
//				Intent intent = new Intent(MainActivity.this , BasinActivity.class);
//                intent.putExtra("esino", esino);
//				startActivity( intent  );
//			}
//		});
//        
//        Button cesanoButton = (Button) findViewById(R.id.cesanobutton);
//        cesanoButton.setOnClickListener(new OnClickListener() {
//        	@Override
//			public void onClick(View v) {
//        		Intent intent = new Intent(MainActivity.this , BasinActivity.class);
//                intent.putExtra("cesano", cesano);
//				startActivity( intent  );
//			}
//		});
        
        
        
    }
    
    public void getPreferences(){
    	preferences=PreferenceManager.getDefaultSharedPreferences(this);
    	choiceMod=(preferences.getString("area", ""));
    	basin=(preferences.getString("manual", ""));
    	
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
//    		Intent settingsIntent=new Intent(this, Settings.class);
//    		startActivity(settingsIntent);
    		return true;
    	case 2:
    		finish();
    		return true;
    	}
    	return true;
    	
    }
    
}
