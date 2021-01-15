package Main;


import Grafik.Menu;
// 2019123058 Alperen Yaylaci// jdk13


public class Main {

	private static Pencere f;
	public static int Bilgisayar =0;
	
	public static void main(String[] args) {
		f = new Pencere(1024,576);
		f.setTitle("Alperen Yaylaci - Angry Birds");
		f.changerFond(new Menu());
	}

	public static Pencere getEkran(){
		return f;
		
	}

	public static Object getPencere() {
		// TODO Auto-generated method stub
		return null;
	}
}
