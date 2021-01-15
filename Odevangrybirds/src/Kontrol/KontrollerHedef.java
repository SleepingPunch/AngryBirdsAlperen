package Kontrol;

import java.util.Timer;
import java.util.TimerTask;

import Model.Hedef;
import Goruntu.yapilar;



public class KontrollerHedef {


	double t = 0;
	boolean b = false;
	Hedef hedef;
	double ivme=0;
	
	
	public KontrollerHedef(yapilar d1, Hedef c, double v){
		hedef =c;
		yorungeHedef(c,d1,b);
		ivme=v;
		
	}
	
	
	public void yorungeHedef(final Hedef c, final yapilar d, final boolean b){
		final Timer timer = new Timer(); 
		TimerTask timerTask = new TimerTask(){	
			@Override
			public void run()  { 
				t+=1;
				if(t>ivme/10){
					timer.cancel();
				}
				else{ 
						c.setaci(c.getaci()+1);
						c.set_X(c.get_X()+1);
					d.repaint();
				}
			}
		};
		
		timer.scheduleAtFixedRate(timerTask,0,60);
	}
}
