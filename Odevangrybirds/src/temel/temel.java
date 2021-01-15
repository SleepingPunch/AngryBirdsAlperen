package temel;

import Model.Hedef;
import Goruntu.yapilar;

public class temel {

	private yapilar yapilar;

	public temel(yapilar d) {
		yapilar = d;
	}

	public void tur() {

		// for(int i=1;i<2;i++){
		Hedef c = new Hedef(yapilar);
		c.set_X(yapilar.getkonum() + 100);
		c.set_Y(c.getBoyut() / 2);
		yapilar.ekleHedef(c);
		Hedef c2 = new Hedef(yapilar);
		c2.set_X(yapilar.getkonum() + 100);
		c2.set_Y(c.getBoyut() + c2.getBoyut() / 2);
		yapilar.ekleHedef(c2);
		Hedef c3 = new Hedef(yapilar);
		c3.set_X(yapilar.getkonum() + 100);
		c3.set_Y(c2.get_Y() + c2.getBoyut());
		yapilar.ekleHedef(c3);

		Hedef c4 = new Hedef(yapilar);
		c4.set_X(yapilar.getkonum() + 200);
		c4.set_Y(c4.getBoyut() / 2);
		yapilar.ekleHedef(c4);
		Hedef c5 = new Hedef(yapilar);
		c5.set_X(yapilar.getkonum() + 200);
		c5.set_Y(c4.getBoyut() + c5.getBoyut() / 2);
		yapilar.ekleHedef(c5);
		Hedef c6 = new Hedef(yapilar);
		c6.set_X(yapilar.getkonum() + 200);
		c6.set_Y(c5.get_Y() + c5.getBoyut());
		yapilar.ekleHedef(c6);
		Hedef c7 = new Hedef(yapilar);
		c7.set_X(yapilar.getkonum() + 200);
		c7.set_Y(c6.get_Y() + c6.getBoyut());
		yapilar.ekleHedef(c7);

	}

	public void tur2() {
		Hedef c = new Hedef(yapilar);
		c.set_X(yapilar.getkonum() + 100);
		c.set_Y(c.getBoyut() / 2);
		yapilar.ekleHedef(c);
		Hedef c2 = new Hedef(yapilar);
		c2.set_X(yapilar.getkonum() + 100);
		c2.set_Y(c.getBoyut() + c2.getBoyut() / 2);
		yapilar.ekleHedef(c2);
		Hedef c3 = new Hedef(yapilar);
		c3.set_X(yapilar.getkonum() + 100);
		c3.set_Y(c2.get_Y() + c2.getBoyut());
		yapilar.ekleHedef(c3);

		Hedef c4 = new Hedef(yapilar);
		c4.set_X(yapilar.getkonum() + 400);
		c4.set_Y(c4.getBoyut() / 2);
		yapilar.ekleHedef(c4);
		Hedef c5 = new Hedef(yapilar);
		c5.set_X(yapilar.getkonum() + 400);
		c5.set_Y(c4.getBoyut() + c5.getBoyut() / 2);
		yapilar.ekleHedef(c5);
		Hedef c6 = new Hedef(yapilar);
		c6.set_X(yapilar.getkonum() + 400);
		c6.set_Y(c5.get_Y() + c5.getBoyut());
		yapilar.ekleHedef(c6);

		Hedef c7 = new Hedef(yapilar);
		c7.set_X(yapilar.getkonum() + 500);
		c7.set_Y(c7.getBoyut() / 2);
		yapilar.ekleHedef(c7);
		Hedef c8 = new Hedef(yapilar);
		c8.set_X(yapilar.getkonum() + 500);
		c8.set_Y(c8.getBoyut() + c7.getBoyut() / 2);
		yapilar.ekleHedef(c8);


	}

}
