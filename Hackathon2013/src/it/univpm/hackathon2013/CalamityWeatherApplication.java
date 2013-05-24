package it.univpm.hackathon2013;

import android.app.Application;

public class CalamityWeatherApplication extends Application{
	
	private static CalamityWeatherApplication instance=null;
		
	@Override
	public void onCreate(){
		super.onCreate();
		instance=this;
	}

	public static CalamityWeatherApplication getInstance(){
		return instance;
	}
}
