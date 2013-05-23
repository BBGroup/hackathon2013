package it.univpm.hackathon2013;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button misaButton = (Button) findViewById(R.id.misabutton);
        misaButton.setOnClickListener(new OnClickListener(){
        	
        	@Override
        	public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
        });
        
        Button metauroButton = (Button) findViewById(R.id.metaurobutton);
        metauroButton.setOnClickListener(new OnClickListener() {
        	
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
        
        Button esinoButton = (Button) findViewById(R.id.metaurobutton);
        esinoButton.setOnClickListener(new OnClickListener() {
        	
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
        
        Button cesanoButton = (Button) findViewById(R.id.metaurobutton);
        cesanoButton.setOnClickListener(new OnClickListener() {
        	
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
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
