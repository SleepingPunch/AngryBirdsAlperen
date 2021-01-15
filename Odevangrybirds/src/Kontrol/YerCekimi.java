package Kontrol;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import Model.Bird;
import Model.Hedef;
import Goruntu.yapilar;

public class YerCekimi {

	yapilar dekor;
	JPanel tasari;
	
	public YerCekimi(yapilar d){
		dekor = d;
		YerCekimi();
	}
	public YerCekimi(yapilar d, JPanel j){
		dekor = d;
		tasari=j;
		YerCekimi();
	}
	
	public static boolean durus(Hedef c, yapilar d){
		if(c.get_Y()<=c.getBoyut()/2 ) return true;
		else{
			for(Hedef c2 : d.getHedefs()){
				if(!c2.getCoord().equals(c.getCoord())){
					if(Math.abs(c2.get_X()-c.get_X()) <= c.getBoyut()/2+c2.getBoyut()/2 ){
						if(c2.get_Y()> c.get_Y() && c2.get_Y()<= c.get_Y()+c.getBoyut()/2+c2.getBoyut()/2) return true;
						else if(c.get_Y()<= c2.get_Y()+c2.getBoyut()/2+c.getBoyut()/2) return true;
					}
				}
			}
			for(Bird c2 : d.getkusx()){
				if(!c2.getCoord().equals(c.getCoord())){
					if(Math.abs(c2.get_X()-c.get_X()) <= c.getBoyut()/2+c2.getBoyut()/2 ){
						if(c2.get_Y()> c.get_Y() && c2.get_Y()<= c.get_Y()+c.getBoyut()/2+c2.getBoyut()/2) return true;
						else if(c.get_Y()<= c2.get_Y()+c2.getBoyut()/2+c.getBoyut()/2) return true;
					}
				}
			}
			
			
			
		}
		return false;
	}
	
	public static boolean sabit(yapilar d){
		for(Hedef c : d.getHedefs())
			if(!durus(c, d)) return false;
		return true;
	}
	
	
	public static boolean yerlesme(Hedef yukarida,Hedef altinda){
		if(yukarida.equals(altinda))return false;
		if(yukarida.get_Y()<altinda.get_Y()) return false;
		if(yukarida.get_Y()-altinda.get_Y() <= yukarida.getBoyut()/2 + altinda.getBoyut()/2){
			if(Math.abs(yukarida.get_X()-altinda.get_X())<= yukarida.getBoyut()/2 + altinda.getBoyut()/2 ) return true;
		}
	return false;
	}
	
	
	public void YerCekimi(){
	final Timer timer = new Timer(); 
	TimerTask timerTask = new TimerTask(){	
		@Override
		public void run()  { 
			for(Hedef c : dekor.getHedefs()){
				if(!durus(c,dekor)) c.set_Y(c.get_Y()-1);
				c.setaci(c.getaci()+ sabit(c,dekor));
				c.set_X(c.get_X()+sabit(c,dekor));
			}
			if(tasari!=null) tasari.repaint();
		}
	};
	timer.scheduleAtFixedRate(timerTask,0,2);
	
	}
	
	public static int sabit(Hedef c, yapilar d) {
		for(Hedef c2 : d.getHedefs()){
			if(yerlesme(c, c2)){
				if(c.get_X()<c2.get_X()) return -1;
				else if (c.get_X()>c2.get_X()) return +1;
			}
		}
		return 0;
	}
	
}
