package it.univpm.hackathon2013;

import org.achartengine.model.XYMultipleSeriesDataset;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ChartActivity extends Activity {
	
	private XYMultipleSeriesDataset multipleDataset = new XYMultipleSeriesDataset();
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chart);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.chart, menu);
		return true;
	}

}
