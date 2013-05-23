package it.univpm.hackathon2013;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	
	String misa="misaBasin";
	String cesano="cesanoBasin";
	String esino="esinoBasin";
	String metauro="metauroBasin";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button misaButton = (Button) findViewById(R.id.misabutton);
        misaButton.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this , BasinActivity.class);
                intent.putExtra("misa", misa);
				startActivity( intent  );
			}
        });
        
        Button metauroButton = (Button) findViewById(R.id.metaurobutton);
        metauroButton.setOnClickListener(new OnClickListener() {
        	@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this , BasinActivity.class);
                intent.putExtra("metauro", metauro);
				startActivity( intent  );
			}
		});
        
        Button esinoButton = (Button) findViewById(R.id.metaurobutton);
        esinoButton.setOnClickListener(new OnClickListener() {
        	@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this , BasinActivity.class);
                intent.putExtra("esino", esino);
				startActivity( intent  );
			}
		});
        
        Button cesanoButton = (Button) findViewById(R.id.metaurobutton);
        cesanoButton.setOnClickListener(new OnClickListener() {
        	@Override
			public void onClick(View v) {
        		Intent intent = new Intent(MainActivity.this , BasinActivity.class);
                intent.putExtra("cesano", cesano);
				startActivity( intent  );
			}
		});
        
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
