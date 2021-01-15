package Kontrol;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import Model.Bird;
import Model.Hedef;
import Goruntu.yapilar;



public class Animasyon {

	private yapilar d;
	double t;
	
	double ivme,acı;
// coord kordinatı
	
	
	public Animasyon(Bird o, yapilar d1, double v, double a){
		d=d1;
		ivme =v;
		acı=a;
		yorungeParabol(o);
	}



	public void yorungeParabol(final Bird o){
		t=0;
		if(acı<91 && acı>89) acı = (new Random().nextBoolean())? 91 : 89;
		final Timer timer = new Timer();
		TimerTask timerTask = new TimerTask(){
			@Override
			public void run()  {
				t+=0.015;
				o.setCoord(coordParabol(t,ivme,acı,0,d.getYuksekLP()));
				if(o.get_X()%12==0)d.eklePoint(o.getCoord());
				double t2 = t+0.002;
				o.setSonrakiCoord(coordParabol(t2,ivme,acı,0,d.getYuksekLP()));
				while(o.getCoord().distance(o.getSonrakiCoord()) < o.getBoyut()/2 + 10){
					t2+=0.002;
					o.setSonrakiCoord(coordParabol(t2,ivme,acı,0,d.getYuksekLP()));
				}
				for(Hedef c : d.getHedefs()){
					if(c.getCoord().distance(o.getCoord()) <= (c.getBoyut()/2 + o.getBoyut()/2)){
						c.dokunma();
						new KontrollerHedef(d,c,ivme);
					}
				}
				d.repaint();
				if(o.get_Y()<=0+o.getBoyut()/2 || t>=8000){
					o.son();
					d.bosPointsUzantı();
					timer.cancel();
					d.birdOnLp();
				}

			}
		};
		timer.scheduleAtFixedRate(timerTask,0,1);
	}
	
	
	static Koordinat coordParabol(double t, double ivme, double acımız, int konumX, int konumY){
		double rad = Math.toRadians(acımız);
		double x = ivme*Math.cos(rad)*t +konumX;
		
		
		double truc = x /(ivme*Math.cos(rad));
		final double G =9.81;
		 
		double y = (-(G/2))*truc*truc + ivme*Math.sin(rad)*truc +konumY;
		return new Koordinat((int)x, (int)y);

	}
	
}
