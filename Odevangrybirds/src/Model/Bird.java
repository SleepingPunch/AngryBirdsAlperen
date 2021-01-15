package Model;


import java.util.Observable;

import Kontrol.Koordinat;
import Goruntu.yapilar;


public class Bird extends Observable {

	private Koordinat koordinat, sonrakiKoordinat;
	private int Boyut;
	private yapilar yapilar;
	private boolean isFlying=false;
	private boolean bitis;
	

	public Bird(yapilar d){
		
		yapilar =d;
		Boyut = 25;
		this.koordinat = new Koordinat(-Boyut/2-40* yapilar.getkusx().size(),Boyut/2);
		sonrakiKoordinat = new Koordinat(-Boyut/2-20* yapilar.getkusx().size(),Boyut/2);
		bitis=false;
	}


	public void set_X(int i) {
		koordinat.setX(i);
		setChanged();
		notifyObservers();
	}
	
	
	public int get_X() {
		return koordinat.getX();
	}

	
	public void set_Y(int i) {
		koordinat.setY(i);
		setChanged();
		notifyObservers();
	}
	
	
	public int get_Y() {
		return koordinat.getY();
	}
	
	
	public Koordinat getCoord() {
		return koordinat;
	}
	
	
	public void setCoord(Koordinat c) {
		this.koordinat = c;
		setChanged();
		notifyObservers();
	}
	
	
	public Koordinat getSonrakiCoord(){
		return this.sonrakiKoordinat;
	}
	
	
	public void setSonrakiCoord(Koordinat c){
		sonrakiKoordinat = c;
		setChanged();
		notifyObservers();
	}
	

	public int getBoyut(){
		return Boyut;
	}
	
	
	public boolean isFlying(){
		return isFlying;
	}
	
	
	public void fly(){
		isFlying=true;
		setChanged();
		notifyObservers();
	}
	
	
	public boolean bitis(){
		return bitis;
		
	}
	
	
	public void son(){
		bitis=true;
		isFlying=false;
		setChanged();
		notifyObservers();
	}
	

	public void placerSurLP() {
		
		// TODO Auto-generated method stub
		this.setCoord(new Koordinat(0, yapilar.getYuksekLP()+Boyut/2));
		this.setSonrakiCoord(new Koordinat(20, yapilar.getYuksekLP()+Boyut/2));
		setChanged();
		notifyObservers();
		
		
	}
}
