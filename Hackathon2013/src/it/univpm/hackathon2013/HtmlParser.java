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
import android.app.Activity;

public class HtmlParser {
	
	private Activity activity;
//	private static Timer timer=null;
//	public  ArrayList value= new ArrayList();
	
	
public HtmlParser(Activity activity,String id_stazione,int tipo){
	super();
	this.activity=activity;
	if(tipo==0){	
		getLivello(id_stazione);
	}
	if(tipo==1){
		getPioggia(id_stazione);
	}
  
  
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
             		((BasinActivity)activity).db.inserisciLettura(data_livello, data_livello, Double.parseDouble(livello), id_stazione);
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
	 try{
	    	
         URL url = new URL("http://www.protezionecivile.marche.it/monitoraggio/Pioggia.aspx");
         
         BufferedInputStream inStream =new BufferedInputStream(url.openStream());

         InputStreamReader reader = new InputStreamReader(inStream);
         BufferedReader bfr=new BufferedReader(reader);
         String line = "";
         
         while((line = bfr.readLine())!= null) {
         	if(line.contains("StationsData['station"+id_stazione+"']")){
         			
         		String data_pioggia=line.substring(line.indexOf('=')+2,line.indexOf(';')-1);
         	
         			activity.runOnUiThread(new Runnable() {
          		       	  public void run() {
          		       		//((BasinActivity)activity).flushpioggiaarcevia();
          		       	  }
          		       	});       			
         		}
         		
         		
         	}
         	if(line.contains("StationsValore['station"+id_stazione+"']")){
              	String mm_pioggia=line.substring(line.indexOf('=')+2,line.indexOf(';')-1);
             	mm_pioggia =mm_pioggia.replace(',', '.').trim();
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
	 
   
}