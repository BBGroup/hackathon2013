package it.univpm.hackathon2013;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

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
	public static ArrayList<String> rilievo_data=new ArrayList<String>();
	public static ArrayList<Double> rilievo_valore=new ArrayList<Double>();
	private Activity activity;
	private static Timer timer=null;
	public  ArrayList value= new ArrayList();
	
	
public HtmlParser(int id_stazione,int tipo){
	super();
	
	if(tipo==0){	
		getLivello(id_stazione);
	}
	if(tipo==1){
		getPioggia(id_stazione);
	}
  
  
}


private void getLivello(int id_stazione){
	
}

private void getPioggia(int id_stazione){
	 try{
	    	
         URL url = new URL("http://www.protezionecivile.marche.it/monitoraggio/Pioggia.aspx");
         
         BufferedInputStream inStream =new BufferedInputStream(url.openStream());

         InputStreamReader reader = new InputStreamReader(inStream);
         BufferedReader bfr=new BufferedReader(reader);
         String line = "";
         
         while((line = bfr.readLine())!= null) {
         	if(line.contains("StationsData['station"+id_stazione+"']")){
         			
         		String data_pioggia=line.substring(line.indexOf('=')+2,line.indexOf(';')-1);
         		if(!rilievo_data.get(0).toString().equalsIgnoreCase(data_pioggia)){
         			rilievo_data.set(0,data_pioggia);
         			activity.runOnUiThread(new Runnable() {
          		       	  public void run() {
          		       		//((BasinActivity)activity).flushpioggiaarcevia();
          		       	  }
          		       	});       			
         		}
         		
         		value.set(0,data_pioggia);
         		
         		System.out.print("Pioggia Arcevia "+data_pioggia);
         	}
         	if(line.contains("StationsValore['station"+id_stazione+"']")){
              	String mm_pioggia=line.substring(line.indexOf('=')+2,line.indexOf(';')-1);
             	mm_pioggia =mm_pioggia.replace(',', '.').trim();
             	Double diff=Double.parseDouble(mm_pioggia)-rilievo_valore.get(0);
             	value.set(1,mm_pioggia);
             	if(rilievo_valore.get(0)>0.0 && Double.parseDouble(mm_pioggia) != rilievo_valore.get(0)){
             		BigDecimal bg = new BigDecimal(diff); 
              		bg = bg.setScale(1, BigDecimal.ROUND_HALF_UP);
              		value.set(2,bg.toString());
             	}
             	rilievo_valore.set(0,Double.parseDouble(mm_pioggia));
             	
             	System.out.println("  " + mm_pioggia);
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
	 
   
public void getData( ){
	//((MypcandroidActivity)activity).getData();

    	 
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
    	                            
    	          
    		
    	
     try{
    	
            URL url = new URL("http://www.protezionecivile.marche.it/monitoraggio/Pioggia.aspx");
            
            BufferedInputStream inStream =new BufferedInputStream(url.openStream());

            InputStreamReader reader = new InputStreamReader(inStream);
            BufferedReader bfr=new BufferedReader(reader);
            String line = "";
            
            while((line = bfr.readLine())!= null) {
            	if(line.contains("StationsData['station123']")){
            		//int i=line.indexOf("StationsData['station123']"); //data pioggia arcevia            	
            		String data_pioggia=line.substring(line.indexOf('=')+2,line.indexOf(';')-1);
            		if(!rilievo_data.get(0).toString().equalsIgnoreCase(data_pioggia)){
            			rilievo_data.set(0,data_pioggia);
            			activity.runOnUiThread(new Runnable() {
             		       	  public void run() {
             		       		//((BasinActivity)activity).flushpioggiaarcevia();
             		       	  }
             		       	});
            			
//            			pioggia_arcevia2.setBackground(null);
//            			pioggia_arcevia3.setBackground(null);
//            			pioggia_arcevia4.setBackground(null);
//            			pioggia_arcevia4.setText("");
            		}
            		//pioggia_arcevia=new JLabel();
            		//pioggia_arcevia1.setText("Pioggia Arcevia");
            		value.set(0,data_pioggia);
            		
            		System.out.print("Pioggia Arcevia "+data_pioggia);
            	}
            	if(line.contains("StationsValore['station123']")){
                	//int i=line.indexOf("StationsValore['station123']"); //pioggia arcevia
                	String mm_pioggia=line.substring(line.indexOf('=')+2,line.indexOf(';')-1);
                	mm_pioggia =mm_pioggia.replace(',', '.').trim();
                	Double diff=Double.parseDouble(mm_pioggia)-rilievo_valore.get(0);
                	value.set(1,mm_pioggia);
                	if(rilievo_valore.get(0)>0.0 && Double.parseDouble(mm_pioggia) != rilievo_valore.get(0)){
                		BigDecimal bg = new BigDecimal(diff); 
                 		bg = bg.setScale(1, BigDecimal.ROUND_HALF_UP);
                 		value.set(2,bg.toString());
                	}
                	rilievo_valore.set(0,Double.parseDouble(mm_pioggia));
                	
                	System.out.println("  " + mm_pioggia);
                }
            	if(line.contains("StationsData['station119']")){
            		//int i=line.indexOf("StationsData['station123']"); //data pioggia arcevia            	
            		String data_pioggia=line.substring(line.indexOf('=')+2,line.indexOf(';')-1);
            		if(!rilievo_data.get(1).toString().equalsIgnoreCase(data_pioggia)){
            			rilievo_data.set(1,data_pioggia);
            			activity.runOnUiThread(new Runnable() {
             		       	  public void run() {
             		       		 // ((MypcandroidActivity)activity).flushpioggiamontecarotto();
             		       	  }
             		       	});
//            			pioggia_montecarotto2.setBackground(null);
//            			pioggia_montecarotto3.setBackground(null);
//            			pioggia_montecarotto4.setBackground(null);
//            			pioggia_montecarotto4.setText("");
            		}
            		
            	
            		
            		//pioggia_montecarotto1.setText("Pioggia Montecarotto");
            		value.set(3,data_pioggia);
            		
            		System.out.print("Pioggia Montecarotto "+data_pioggia);
            		
            	}
            	if(line.contains("StationsValore['station119']")){
                	//int i=line.indexOf("StationsValore['station123']"); //pioggia arcevia
                	String mm_pioggia=line.substring(line.indexOf('=')+2,line.indexOf(';')-1);
                	mm_pioggia =mm_pioggia.replace(',', '.').trim();
                	value.set(4,mm_pioggia);
                	Double diff=Double.parseDouble(mm_pioggia)-rilievo_valore.get(1);
                	if(rilievo_valore.get(1)>0.0 && Double.parseDouble(mm_pioggia) != rilievo_valore.get(1)){
                		BigDecimal bg = new BigDecimal(diff); 
                 		bg = bg.setScale(1, BigDecimal.ROUND_HALF_UP);
                 		value.set(5,bg.toString());
                	}
                	rilievo_valore.set(1,Double.parseDouble(mm_pioggia));
                	
                	//value.set(6,mm_pioggia);
                	System.out.println("  "+ mm_pioggia);
                }
            }
     }
        catch(MalformedURLException ex){
            ex.printStackTrace();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
     try{
         URL url = new URL("http://www.protezionecivile.marche.it/monitoraggio/idro.aspx");
         
         BufferedInputStream inStream =new BufferedInputStream(url.openStream());

         InputStreamReader reader = new InputStreamReader(inStream);
         BufferedReader bfr=new BufferedReader(reader);
         String line = "";
        
        
         while((line = bfr.readLine())!= null) {
        	
         	if(line.contains("StationsData['station003']")){
         		//int i=line.indexOf("StationsData['station003']"); //data misa serra dei conti            	
         		String data_livello=line.substring(line.indexOf('=')+2,line.indexOf(';')-1);
         		//livello_arcevia1.setText(" Livello Arcevia");
         		if(!rilievo_data.get(2).toString().equalsIgnoreCase(data_livello)){
         			value.set(6,data_livello);
         			activity.runOnUiThread(new Runnable() {
         		       	  public void run() {
         		       		  //((MypcandroidActivity)activity).flushlivelloarcevia();
         		       	  }
         		       	});
         			
//         		livello_arcevia2.setBackground(null);
//         		livello_arcevia3.setBackground(null);
//         		livello_arcevia4.setBackground(null);
//         		livello_arcevia4.setText("");
         		rilievo_data.set(2,data_livello);
         		}
         		
         		
         		
         			
         			
         		System.out.print(" Livello Serra dei Conti "+data_livello);
         	}
         	if(line.contains("StationsValore['station003']")){
             	//int i=line.indexOf("StationsValore['station003']"); //livello misa serra dei conti
             	String livello=line.substring(line.indexOf('=')+2,line.indexOf(';')-1);
             	livello = livello.replace(',', '.').trim();
             	value.set(7,livello);
             	Double diff=Double.parseDouble(livello)-rilievo_valore.get(2);
             	
             	if(rilievo_valore.get(2)>0.0 &&  Double.parseDouble(livello) != rilievo_valore.get(2)){
             		value.set(8,0.0);
             		BigDecimal bg = new BigDecimal(diff); 
             		bg = bg.setScale(2, BigDecimal.ROUND_HALF_UP);
             		value.set(8,bg.toString());
             	}
//             	livello_arcevia1.setOpaque(true);
//         		livello_arcevia2.setOpaque(true);
//         		livello_arcevia3.setOpaque(true);
         		
//             	if(Double.parseDouble(livello)>3.0){
//             		livello_arcevia1.setBackground(Color.ORANGE);
//             	}
//             	if(Double.parseDouble(livello)>4.0){
//             		livello_arcevia1.setBackground(Color.RED);
//             	}
//             	if(Double.parseDouble(livello)< rilievo_valore.get(2)){
//             		livello_arcevia2.setBackground(Color.GREEN);
//             		livello_arcevia3.setBackground(Color.GREEN);
//             	}else if(rilievo_valore.get(2)>0.0 && Double.parseDouble(livello)>rilievo_valore.get(2)){
//             		livello_arcevia2.setBackground(Color.BLUE);
//             		livello_arcevia3.setBackground(Color.BLUE);
//        	}
//             	else if(rilievo_valore.get(2)==Double.parseDouble(livello)){
//             		livello_arcevia2.setBackground(null);
//             		livello_arcevia3.setBackground(null);
//             	}
             	System.out.println("MEMORIZZATO "+rilievo_valore.get(2));
             	rilievo_valore.set(2,Double.parseDouble(livello));
             	
             	System.out.println("  "+livello +"   "+ rilievo_valore.get(2));
         	
             }
         	if(line.contains("StationsData['station120']")){
         		//int i=line.indexOf("StationsData['station120']"); //data nevola            	
         		String data_livello=line.substring(line.indexOf('=')+2,line.indexOf(';')-1);
         		//livello_nevola1.setText("Livello Nevola");
         		if(!rilievo_data.get(3).toString().equalsIgnoreCase(data_livello)){
         			value.set(9,data_livello);
         			 activity.runOnUiThread(new Runnable() {
        		       	  public void run() {
        		       		//((MypcandroidActivity)activity).flushlivellonevola();
        		       	  }
        		       	});
         			
//         		livello_nevola2.setBackground(null);
//         		livello_nevola3.setBackground(null);
//         		livello_nevola4.setBackground(null);
//         		livello_nevola4.setText("");
         		}
         		
         		
         		
         			rilievo_data.set(3,data_livello);
//         		
         		System.out.print("Livello Nevola "+data_livello);
         	}
         	if(line.contains("StationsValore['station120']")){
             	//int i=line.indexOf("StationsValore['station120']"); //livello nevola
             	String livello=line.substring(line.indexOf('=')+2,line.indexOf(';')-1);
             	livello = livello.replace(',', '.').trim();
             	value.set(10,livello);
             	
             	Double diff=Double.parseDouble(livello)-rilievo_valore.get(3);
             	
             	if(rilievo_valore.get(3)>0.0 && Double.parseDouble(livello) != rilievo_valore.get(3)){
             		value.set(11,0.0);
             		BigDecimal bg = new BigDecimal(diff); 
             		bg = bg.setScale(2, BigDecimal.ROUND_HALF_UP);
             		value.set(11,bg.toString());
             	}
             	
//             	livello_nevola1.setOpaque(true);
//         		livello_nevola2.setOpaque(true);
//         		livello_nevola3.setOpaque(true);
         		
//             	if(Double.parseDouble(livello)>3.0){
//             		livello_nevola1.setBackground(Color.ORANGE);
//
//             	}
//             	if(Double.parseDouble(livello)>4.0){
//             		livello_nevola1.setBackground(Color.RED);
////             		livello_nevola2.setBackground(Color.RED);
////             		livello_nevola3.setBackground(Color.RED);
//             	}
//             	if(Double.parseDouble(livello)< rilievo_valore.get(3)){
//             		livello_nevola2.setBackground(Color.GREEN);
//             		livello_nevola3.setBackground(Color.GREEN);
//             	}else if(rilievo_valore.get(3)>0.0 && Double.parseDouble(livello)>rilievo_valore.get(3)){
//             		livello_nevola2.setBackground(Color.BLUE);
//             		livello_nevola3.setBackground(Color.BLUE);
//             	}
             	System.out.println("MEMORIZZATO "+rilievo_valore.get(3));
             	rilievo_valore.set(3,Double.parseDouble(livello));
             	
             	System.out.println("  "+livello +"   "+ rilievo_valore.get(3));
             }
         	if(line.contains("StationsData['station026']")){
         		//int i=line.indexOf("StationsData['station026']"); //data misa bettolelle         	
         		String data_livello=line.substring(line.indexOf('=')+2,line.indexOf(';')-1);
//         		livello_bettolelle1.setText("Livello Bettolelle");
         		if(!rilievo_data.get(4).toString().equalsIgnoreCase(data_livello)){
         			value.set(12,data_livello);
         			  activity.runOnUiThread(new Runnable() {
         		       	  public void run() {
         		       		//((MypcandroidActivity)activity).flushlivellobettolelle();
         		       	  }
         		       	});
         			
//         		livello_bettolelle2.setBackground(null);
//         		livello_bettolelle3.setBackground(null);
//         		livello_bettolelle4.setBackground(null);
//         		livello_bettolelle4.setText("");
         			rilievo_data.set(4,data_livello);
         			
         		}
         		System.out.print("Livello Misa Bettolelle "+data_livello);
         	}
         	if(line.contains("StationsValore['station026']")){
             	//int i=line.indexOf("StationsValore['station026']"); //livello misa bettolelle
             	String livello=line.substring(line.indexOf('=')+2,line.indexOf(';')-1);
             	livello = livello.replace(',', '.').trim();
             	value.set(13,livello);
             	Double diff=Double.parseDouble(livello)-rilievo_valore.get(4);
             	
             	if(rilievo_valore.get(4)>0.0 && Double.parseDouble(livello) != rilievo_valore.get(4)){
             		value.set(14,0.0);
             		BigDecimal bg = new BigDecimal(diff); 
             		bg = bg.setScale(2, BigDecimal.ROUND_HALF_UP);
             		value.set(14,bg.toString());
             	}
//             	livello_bettolelle1.setOpaque(true);
//         		livello_bettolelle2.setOpaque(true);
//         		livello_bettolelle3.setOpaque(true);
         		
//             	if(Double.parseDouble(livello)>3.0){
//             		livello_bettolelle1.setBackground(Color.ORANGE);
//             	}
//             	if(Double.parseDouble(livello)>4.0){
//             		livello_bettolelle1.setBackground(Color.RED);
//             	}
//             	if(Double.parseDouble(livello)< rilievo_valore.get(4)){
//             		livello_bettolelle2.setBackground(Color.GREEN);
//             		livello_bettolelle3.setBackground(Color.GREEN);
//             	}else if(rilievo_valore.get(4)>0.0 && Double.parseDouble(livello) > rilievo_valore.get(4)){
//             		livello_bettolelle2.setBackground(Color.BLUE);
//             		livello_bettolelle3.setBackground(Color.BLUE);
//             	}
             	System.out.println("MEMORIZZATO "+rilievo_valore.get(4));
             	rilievo_valore.set(4,Double.parseDouble(livello));
             	
             	System.out.println("  "+livello +"   "+ rilievo_valore.get(4));
             }
         	}
         activity.runOnUiThread(new Runnable() {
       	  public void run() {
       		// ((MypcandroidActivity)activity).savedata();
       	  }
       	});
        
         	}
         
  
     catch(MalformedURLException ex){
         ex.printStackTrace();
     }
     catch(IOException ex){
         ex.printStackTrace();
     }
     
//    if(frame!=null){
//    	frame.dispose();
//    }
//     frame = new JFrame();
//     frame.setLayout(new GridLayout(2,1));
//     frame.setSize(550, 300);
//     JPanel paneltitle = new JPanel(new GridLayout(1,3));// places at the left
//     JPanel panel = new JPanel(new GridLayout(5,4));// places at the left
//     JPanel panelclose = new JPanel();// places at the left
//    
//     title1.setText("Ultimo Aggiornamento:");
     Date d=new Date (System.currentTimeMillis());
     
     DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, Locale.ITALIAN);
     DateFormat df1 = DateFormat.getTimeInstance(DateFormat.LONG, Locale.ITALIAN);
//     title2.setText(df.format(d)+" " + df1.format(d));
//     paneltitle.add(title1,0);
//     paneltitle.add(title2,1);
   
//     panel.add(pioggia_arcevia1,0);
//     panel.add(pioggia_arcevia2,1);
//     panel.add(pioggia_arcevia3,2);
//     panel.add(pioggia_arcevia4,3);
//     panel.add(pioggia_montecarotto1,4);
//     panel.add(pioggia_montecarotto2,5);
//     panel.add(pioggia_montecarotto3,6);
//     panel.add(pioggia_montecarotto4,7);
//     panel.add(livello_arcevia1, 8);
//     panel.add(livello_arcevia2, 9);
//     panel.add(livello_arcevia3, 10);
//     panel.add(livello_arcevia4, 11);
//     panel.add(livello_nevola1, 12);
//     panel.add(livello_nevola2, 13);
//     panel.add(livello_nevola3, 14);
//     panel.add(livello_nevola4, 15);
//     panel.add(livello_bettolelle1, 16);
//     panel.add(livello_bettolelle2, 17);
//     panel.add(livello_bettolelle3, 18);
//     panel.add(livello_bettolelle4, 19);
//     panel.setSize(550, 300);
//     
//    
//     panelclose.add(close);
//     paneltitle.add(panelclose,2);
//     frame.setTitle("MyProtezioneCivile");
//     frame.add(paneltitle);
//     frame.add(panel);
//    panelclose.add(close);
//     frame.add(panelclose);
    
//     frame.setVisible(true);
    	                    }
    	            },
    	            0,300000
    	    );
    }


}

