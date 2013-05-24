package it.univpm.hackathon2013;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class Info extends Activity {

WebView infoView;
	
	public void onCreate (Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info);
		
		infoView=(WebView) findViewById(R.id.info);
		infoView.getSettings().setJavaScriptEnabled(true);
		infoView.loadUrl("file:///android_asset/info.html");
	}
	
}
