package Kontrol;

import java.util.Timer;
import java.util.TimerTask;

import Model.Bird;
import Goruntu.yapilar;


public class Sapan {

	private yapilar d;
	double t;
	
	double ivme,acı;


	
	public Sapan(Bird o, yapilar d1, double v, double a){
		d=d1;
		ivme =v;
		acı=a;
		sapan(o);
	}
	
	

	public void sapan(final Bird o){
		t=0;
		final Timer timer = new Timer(); 
		TimerTask timerTask = new TimerTask(){	
			@Override
			public void run()  { 
				t+=1;
				if(o.getCoord().distance(new Koordinat(0,d.getYuksekLP()+o.getBoyut()/2)) >0){
					o.setCoord(koordinati((int)t,o.getCoord(),new Koordinat(0,d.getYuksekLP()+o.getBoyut()/2)));
					o.setSonrakiCoord(koordinati((int)t+10,o.getCoord(),new Koordinat(0,d.getYuksekLP()+o.getBoyut()/2)));
					d.repaint();
				}
				else {
					timer.cancel();
					new Animasyon(o,d,ivme,acı);
				}
			}
		};
		timer.scheduleAtFixedRate(timerTask,0,5);
		}
	

	public static Koordinat koordinati(int t, Koordinat dep, Koordinat arr){
		Koordinat p = arr;
		
		while(p.distance(dep)>t){
			if(Math.abs(p.getX()-dep.getX()) >Math.abs(p.getY()-dep.getY())){
				if(p.getX()>dep.getX()) p.setX(p.getX()-1);
				else p.setX(p.getX()+1);
			}
			else {
				if(p.getY()>dep.getY()) p.setY(p.getY()-1);
				else p.setY(p.getY()+1);
			}
		}
		return p;

	}


	
	
	
	
}
