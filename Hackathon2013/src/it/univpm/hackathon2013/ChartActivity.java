package it.univpm.hackathon2013;

import org.achartengine.ChartFactory;
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
import android.widget.LinearLayout;

public class ChartActivity extends Activity {
	
	private XYMultipleSeriesDataset multipleDataset = new XYMultipleSeriesDataset();
	private XYMultipleSeriesRenderer multipleRenderer = new XYMultipleSeriesRenderer();
	private TimeSeries basinDataset = new TimeSeries("Basin Level");
	private GraphicalView multipleChartView;
	private XYSeriesRenderer basinRenderer = new XYSeriesRenderer();
	private TimeSeries rainDataset= new TimeSeries("Rain Level");
	private XYSeriesRenderer rainRenderer= new XYSeriesRenderer();
	
//	double[] prova= {4,4,4,4,4};
//	int[] ind= {0,1,2,3,4,5};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chart);
		
		
		
		graphInitialization();
		
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			double val = bundle.getDouble("valore", 0);
			int tipo=bundle.getInt("tipo",0);
			String nome=bundle.getString("stazione");
			addValueToGraph(val, tipo, nome);
		} else {
			// refreshTabella(db.fetchStazioneByCodiceBacino(0));
		}
		
		if (multipleChartView==null){
        	LinearLayout layout = (LinearLayout) findViewById(R.id.graphView);
            multipleChartView=ChartFactory.getLineChartView(this, multipleDataset, multipleRenderer);
            layout.addView(multipleChartView);
            
        }
        else{
        	multipleChartView.repaint();
        }
		
//		adaptGraphToBasin();
//		
//		for (int i=0; i<6; i++){
//			int l=ind.length;
//			addValueToGraph(ind[i], prova[i], 0);
//		}
		
		
		
		
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
	
	public void adaptGraphToBasin(){
    	multipleRenderer.setRange(new double[] {0,2,0,4});
    	multipleRenderer.setXTitle(" ");
		multipleRenderer.setYTitle("Basin Level [m]");
    }
	
	public void adaptGraphToRain(){
    	multipleRenderer.setRange(new double[] {0,2,0,50});
    	multipleRenderer.setXTitle(" ");
		multipleRenderer.setYTitle("Rain Level [mm]");
	}
	
	public void addValueToGraph (double value, int type, String stazione){
		if (type==0){
			adaptGraphToBasin();
			basinDataset.add(1, value);
			if (multipleChartView != null){
				
				multipleRenderer.setChartTitle(stazione);
				multipleChartView.repaint();
				
			}
		}
		else if (type==1){
			adaptGraphToRain();
			rainDataset.add(1, value);
			if (multipleChartView != null){
				multipleRenderer.setChartTitle(stazione);
				multipleChartView.repaint();
			}
		}
	}

}
