package it.univpm.hackathon2013;

import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;

public class ChartActivity extends Activity {
	
	private XYMultipleSeriesDataset multipleDataset = new XYMultipleSeriesDataset();
	private XYMultipleSeriesRenderer multipleRenderer = new XYMultipleSeriesRenderer();
	private TimeSeries basinDataset = new TimeSeries("Basin Level");
	private GraphicalView multipleChartView;
	private XYSeriesRenderer basinRenderer = new XYSeriesRenderer();
	private TimeSeries rainDataset= new TimeSeries("Rain Level");
	private XYSeriesRenderer rainRenderer= new XYSeriesRenderer();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chart);
		
		graphInitialization();
		
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.chart, menu);
//		return true;
//	}
	
	public void graphInitialization(){
    	basinRenderer.setColor(Color.WHITE);
    	basinRenderer.setPointStyle(PointStyle.CIRCLE);
		basinRenderer.setFillPoints(true);
		basinRenderer.setLineWidth(2);
		
		rainRenderer.setColor(Color.GREEN);
		rainRenderer.setPointStyle(PointStyle.DIAMOND);
		rainRenderer.setFillPoints(true);
		rainRenderer.setLineWidth(2);
		
		
		multipleRenderer.setZoomButtonsVisible(true);

		multipleRenderer.setApplyBackgroundColor(true);
		multipleRenderer.setBackgroundColor(Color.BLACK);
		multipleRenderer.setPanEnabled(true, true);
		multipleRenderer.setMargins(new int[] {15,15,10,15});
		multipleRenderer.setMarginsColor(Color.DKGRAY);
		
		multipleRenderer.addSeriesRenderer(basinRenderer);
		multipleRenderer.addSeriesRenderer(rainRenderer);
		multipleDataset.addSeries(basinDataset);
		multipleDataset.addSeries(rainDataset);
		
    	
    }

}
