package Model;




import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Random;

import javax.imageio.ImageIO;

import Kontrol.Koordinat;
import Goruntu.yapilar;


public class Hedef extends Observable{

	

	private Koordinat koordinat;
	private boolean dokunma;
	private int boyut;
	private Koordinat vect;
	private boolean rond;
	private double aci;
	private yapilar yapilar;
	
	public double getaci() {
		return aci;
	}

	public void setaci(double aci) {
		this.aci = aci;
	}

	public boolean isRond() {
		return rond;
	}
	
	public Koordinat getVect() {
		return vect;
	}


	public void setVect(Koordinat vect) {
		this.vect = vect;
		setChanged();
		notifyObservers();
	}


	
	public Hedef(yapilar d){
		yapilar = d;
		dokunma = false;
		boyut = 50;

		
		//harita uzerinde rastgele hedeflerin koordinatlarını hesapla
		int x = d.getkonum()+ new Random().nextInt(d.getGenislik()-d.getkonum());
		int y = boyut/2 + new Random().nextInt(d.getYuksek()-boyut/2);
		while(!d.placeLibre(x, y, boyut)){
			x = d.getkonum()+ new Random().nextInt(d.getGenislik()-d.getkonum());
			y = boyut/2 + new Random().nextInt(d.getYuksek()-boyut/2);
		}
		this.koordinat = new Koordinat(x,y);
		this.vect = new Koordinat(new Random().nextInt(2),new Random().nextInt(2));
		rond = new Random().nextBoolean();
	}
	
	
	
	public Koordinat getCoord(){
		return koordinat;
	}
	
	
	public int get_X(){
		return koordinat.getX();
	}
	
	
	public void set_X(int x){
		koordinat.setX(x);
		setChanged();
		notifyObservers();
	}
	

	public int get_Y(){
		return koordinat.getY();
	}
	
	
	public void set_Y(int y){
		koordinat.setY(y);
		setChanged();
		notifyObservers();
	}
	
	
	public int getBoyut(){
		return boyut;
	}
	

	public boolean dokunduysa(){
		return dokunma;
	}
	

	public void dokunma(){
		dokunma = true;
		setChanged();
		notifyObservers();
	}


	public void setCoord(Koordinat c) {
		koordinat =c;
		setChanged();
		notifyObservers();
	}
	public void tasari(Graphics g) {
		
		
		try {
			BufferedImage img = ImageIO.read(new File("img/box.png"));
			AffineTransform tx = new AffineTransform();
		    tx.rotate(Math.toRadians(aci),getBoyut() / 2 ,getBoyut() / 2);
			//tx.rotate(Math.toRadians(ivme), get_X(), get_Y());

		    AffineTransformOp op = new AffineTransformOp(tx,
		        AffineTransformOp.TYPE_BILINEAR);
		    img = op.filter(img, null);
		    
		    Koordinat koordinat = yapilar.getPlan().plan_Cisim(this.koordinat);
		    g.drawImage(img, koordinat.getX()-getBoyut()/2 , koordinat.getY()-getBoyut()/2, null);
		   // System.out.println(get_X()+","+get_Y());
			
			} catch (IOException e){}
	}
}
