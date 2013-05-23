package it.univpm.hackathon2013;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

public class BasinActivity extends ListActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.setContentView(it.univpm.hackathon2013.R.layout.basin_activity);
		
		ListAdapter adapter=createAdapter();
		setListAdapter(adapter);
		
	}
	
	private ListAdapter createAdapter(){
		String[] textValues=new String[]{"Ciao","Ciao","Bambina"};
		ListAdapter adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,textValues);
		return adapter;
	}

}
