package it.univpm.hackathon2013;



import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.database.Cursor;

public class HtmlParser {
	public ArrayList bacino0=new ArrayList();
	public ArrayList bacino1=new ArrayList();
	public ArrayList bacino2=new ArrayList();
	public ArrayList bacino3=new ArrayList();
	
	private Activity activity;
	private static Timer timer=null;
	public int bacino;
	private MyDatabase db;
	//SqliteManager sm=new SqliteManager(activity);
//	private static Timer timer=null;
//	public  ArrayList value= new ArrayList();
	
	
public HtmlParser(Activity activity,int bacino){
	
	super();
	this.activity=activity;
	this.bacino=bacino;
	db=new MyDatabase(activity);
	
	
//	this.activity=activity;
//	Cursor cr;
//	activity.startManagingCursor(cr);
//	while(!cr.isAfterLast()){
//		String codice=cr.getString(cr.getColumnIndex("codice"));
//		int tipo=Integer.parseInt(cr.getString(cr.getColumnIndex("tipo")));
//		if(tipo==0){	
//			getLivello(codice);
//		}
//		if(tipo==1){
//			getPioggia(codice);
//		}
//    	cr.moveToNext();
//    	
//    }
//	cr.close();
  
}
public void refresh(int id_bacino){
	int bacino=id_bacino;
	if(timer!=null){
		timer.purge();
		timer.cancel();
		timer=null;
		}
	timer = new Timer();      //timer
    
    //il metodo run viene eseguito ad ogni scadenza del timer
	
    timer.schedule(
            new TimerTask() {                    
                    public void run() { 
                    	getData();
                    	System.out.println("TIMER ATTIVO");
                    }
            },
            0,30000
    );
}
                    

private void getData(){
	db.open();
	ArrayList <ArrayList<String>> stazioni=db.fetchStazioneByCodiceBacino(bacino);
	for(int i=0;i<stazioni.size();i++){
		if(Integer.parseInt(stazioni.get(i).get(2))==0){
			getLivello(stazioni.get(i).get(1));
		}else{
			getPioggia(stazioni.get(i).get(1));
		
		}
	}
	db.close();
	//((MainActivity)activity).sendSimpleNotification();

//	getLivello();
	
}

private void getLivello(String id_stazione){
	String data_livello="";
	String livello;
	 try{
         URL url = new URL("http://www.protezionecivile.marche.it/monitoraggio/idro.aspx");
         BufferedInputStream inStream =new BufferedInputStream(url.openStream());

         InputStreamReader reader = new InputStreamReader(inStream);
         BufferedReader bfr=new BufferedReader(reader);
         String line = "";
        
        
         while((line = bfr.readLine())!= null) {
        	
         	if(line.contains("StationsData['station"+id_stazione+"']")){          	
         		data_livello=line.substring(line.indexOf('=')+2,line.indexOf(';')-1);
         		
         			activity.runOnUiThread(new Runnable() {
         		       	  public void run() {
         		       		  
         		       	  }
         		       	});
         			

         		
         		}
         		
         		
         		
         			
         			
         		
         	}
         	if(line.contains("StationsValore['station"+id_stazione+"']")){
             	
             	livello=line.substring(line.indexOf('=')+2,line.indexOf(';')-1);
             	livello = livello.replace(',', '.').trim();
             	if(!data_livello.equals("")){
             		db.inserisciLettura(data_livello, data_livello,livello, id_stazione);
             	}
             	
             	//Double diff=Double.parseDouble(livello)-rilievo_valore.get(2);
             	
//             	if(rilievo_valore.get(2)>0.0 &&  Double.parseDouble(livello) != rilievo_valore.get(2)){
//             		value.set(8,0.0);
//             		BigDecimal bg = new BigDecimal(diff); 
//             		bg = bg.setScale(2, BigDecimal.ROUND_HALF_UP);
//             		value.set(8,bg.toString());
//             	}
////             	
//             	System.out.println("MEMORIZZATO "+rilievo_valore.get(2));
//             	rilievo_valore.set(2,Double.parseDouble(livello));
//             	
//             	System.out.println("  "+livello +"   "+ rilievo_valore.get(2));
         	
             }}
         
  
     catch(MalformedURLException ex){
         ex.printStackTrace();
     }
     catch(IOException ex){
         ex.printStackTrace();
     }
	
}

private void getPioggia(String id_stazione){
	String data_pioggia="";
	String mm_pioggia;
	 try{
	    	
         URL url = new URL("http://www.protezionecivile.marche.it/monitoraggio/Pioggia.aspx");
         
         BufferedInputStream inStream =new BufferedInputStream(url.openStream());

         InputStreamReader reader = new InputStreamReader(inStream);
         BufferedReader bfr=new BufferedReader(reader);
         String line = "";
         
         while((line = bfr.readLine())!= null) {
         	if(line.contains("StationsData['station"+id_stazione+"']")){
         			
         		data_pioggia=line.substring(line.indexOf('=')+2,line.indexOf(';')-1);
         	
         			activity.runOnUiThread(new Runnable() {
          		       	  public void run() {
          		       		//((BasinActivity)activity).flushpioggiaarcevia();
          		       	  }
          		       	});       			
         		}
         		
         		
         	}
         	if(line.contains("StationsValore['station"+id_stazione+"']")){
              	mm_pioggia=line.substring(line.indexOf('=')+2,line.indexOf(';')-1);
             	mm_pioggia =mm_pioggia.replace(',', '.').trim();
             	if(!data_pioggia.equals("")){
             		//((BasinActivity)activity).db.inserisciLettura(data_pioggia, data_pioggia, mm_pioggia, id_stazione);
             	}
//             	Double diff=Double.parseDouble(mm_pioggia)-rilievo_valore.get(0);
//             	value.set(1,mm_pioggia);
//             	if(rilievo_valore.get(0)>0.0 && Double.parseDouble(mm_pioggia) != rilievo_valore.get(0)){
//             		BigDecimal bg = new BigDecimal(diff); 
//              		bg = bg.setScale(1, BigDecimal.ROUND_HALF_UP);
//              		value.set(2,bg.toString());
//             	}
//             	rilievo_valore.set(0,Double.parseDouble(mm_pioggia));
//             	
//             	System.out.println("  " + mm_pioggia);
             }
         
	 }
catch(MalformedURLException ex){
    ex.printStackTrace();
}
catch(IOException ex){
    ex.printStackTrace();
}
}


public void bollettino(){
	boolean row=false;
	boolean zona=false;
	 try{
	    	
         URL url = new URL("http://www.protezionecivile.marche.it/mig/BvmigSito.asp");
         
         BufferedInputStream inStream =new BufferedInputStream(url.openStream());

         InputStreamReader reader = new InputStreamReader(inStream);
         BufferedReader bfr=new BufferedReader(reader);
         String line = "";
         
         while((line = bfr.readLine())!= null) {
         	if(line.contains("<tr>")){
         		row=true;
}
         	if(line.contains("</tr>")){
         		row=false;
         		zona=false;
         		}
         	if(line.contains("PU-AN") && row){
             		zona=true;
}
         	if(line.contains("MODERATA") || line.contains("ELEVATA") && row && zona){
         		System.out.println("Emettere notifica di bollettino di pericolo");
}
         }
	 }
	 catch(MalformedURLException ex){
	     ex.printStackTrace();
	 }
	 catch(IOException ex){
	     ex.printStackTrace();
	 }
}

	 }