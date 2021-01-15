package Kontrol;



public class Koordinat {
	
	private int x,y;
	//vektör vect
	


	public Koordinat(int x1, int y1){
		this.x = x1;;
		this.y = y1;
	}
	
	public int getX(){
		return x;
	}
	
	
	public int getY(){
		return y;
	}
	

	public void setX(int x1){
		x = x1;
	}
	

	public void setY(int y1){
		y = y1;
	}
	

	public boolean equals(Koordinat c){
		return this.y== c.getY() && this.x == c.getX();
	}
	

	public int distance(Koordinat c){
		int distanceX = c.getX() - x;
		if(distanceX <0) distanceX*=-1;
		int distanceY = c.getY() - y;
		if(distanceY <0) distanceY*=-1;
		return (int)Math.sqrt(distanceX*distanceX + distanceY*distanceY);
	}

	public Koordinat somme(Koordinat vect) {
		// TODO Auto-generated method stub
		return new Koordinat(x+vect.getX(),y+vect.getY());
	}
	public Koordinat inverse(){
		return new Koordinat(x*-1,y*-1);
	}

	public Koordinat diff(Koordinat vect) {
		// TODO Auto-generated method stub
		return new Koordinat(x-vect.getX(),y-vect.getY());
	}

}
