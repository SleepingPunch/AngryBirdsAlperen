package Goruntu;

import Kontrol.Koordinat;


public class Plan {
	private final Koordinat koordinatOrigin;
	
	
	public Plan(Koordinat koordinat){
		this.koordinatOrigin = koordinat;
	}
	
	
	public Koordinat getCoordOrigin(){
		return this.koordinatOrigin;
	}
	
	
	public int getXOrigin(){
		return this.koordinatOrigin.getX();
	}
	
	
	public int getYOrigin(){
		return this.koordinatOrigin.getY();
	}
	
	
	public Koordinat plan_Cisim(Koordinat koordinatPlan){
		return new Koordinat(koordinatPlan.getX()+getXOrigin(), getYOrigin()- koordinatPlan.getY());
	}
	
	public Koordinat cisim_Plan(Koordinat koordinatConc){
		return new Koordinat(koordinatConc.getX()-getXOrigin(), getYOrigin()- koordinatConc.getY());
	}



}
