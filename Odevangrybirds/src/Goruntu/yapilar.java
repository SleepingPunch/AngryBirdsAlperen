package Goruntu;




import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import temel.temel;
import Kontrol.Koordinat;
import Kontrol.YerCekimi;
import Kontrol.Sapan;
import Grafik.Menu;
import Main.Main;
import Model.Bird;
import Model.Hedef;

public class yapilar extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	private final Plan plan;
	private ArrayList<Bird> kuslar;
	private ArrayList<Hedef> hedef;
	private ArrayList<Koordinat> puan;

	private final int yukseklik;

	private final int genislik;

	private final int zeminYuksekligi;

	private final int konum;

	private final int yukseklikLD;

	private boolean surukle = false;

	private double aci, hiz;
	
	JLabel affScore;
	
	
	private int score;

	public int getScore() {
		return score;
	}
	// bilinmesi gerekenler
/*
//arka plan
		//g.setColor(new Color(91,158,238));
		//g.fillRect(0, 0, genislik, yukseklik);
		//g.setColor(new Color(103,198,55));
	//	g.fillRect(0, yukseklik-zeminYuksekligi, genislik, zeminYuksekligi);


 */



	public void setScore(int score) {
		this.score = score;
	}

	private Bird birdOnLp;

	public yapilar(int l, int h, int yuksekZemin, int konumuzantı, int highLP) {
		setLayout(null);

		hedef = new ArrayList<Hedef>();
		kuslar = new ArrayList<Bird>();
		puan = new ArrayList<Koordinat>();

		genislik = l;

		yukseklik = h;

		zeminYuksekligi = yuksekZemin;

		yukseklikLD = highLP;

		konum = konumuzantı;
		score = 0;
		affScore = new JLabel(""+score);
		affScore.setFont(new Font("",Font.BOLD,30));
		affScore.setBounds(10,10,200,100);

		plan = new Plan(new Koordinat(konum, yukseklik - zeminYuksekligi));
		this.setRequestFocusEnabled(true);
		this.requestFocus();
		repaint();
	
	
		this.addMouseMotionListener(new MouseMotionListener() {

			public void mouseMoved(MouseEvent arg0) {
			}
			// surukle bırak
			public void mouseDragged(MouseEvent arg0) {
				if (birdOnLp != null && !birdOnLp.isFlying() && !birdOnLp.bitis()) {
					Koordinat koordinat = plan.cisim_Plan(new Koordinat(arg0.getX(), arg0.getY()));
					int dist = new Koordinat(0, yukseklikLD).distance(koordinat);
					if (dist < 120) {
						birdOnLp.setCoord(koordinat);
						repaint();
						hiz = dist + 20;
						aci = 0;
						double tan = 0;
						if (birdOnLp.get_X() <= 0 && birdOnLp.get_Y() <= yukseklikLD) {
							double terstaraf = yukseklikLD - birdOnLp.get_Y();
							double duztaraf = -birdOnLp.get_X();
							tan = terstaraf / duztaraf;
							aci = Math.toDegrees(Math.atan(tan));

						}
						if (birdOnLp.get_X() < 0 && birdOnLp.get_Y() > yukseklikLD) {
							double terstaraf = birdOnLp.get_Y() - yukseklikLD;
							double duztaraf = -birdOnLp.get_X();
							tan = terstaraf / duztaraf;
							aci = -Math.toDegrees(Math.atan(tan));

						}
						if (birdOnLp.get_X() > 0 && birdOnLp.get_Y() > yukseklikLD) {
							double terstaraf = birdOnLp.get_Y() - yukseklikLD;
							double  duztaraf = birdOnLp.get_X();
							tan = terstaraf / duztaraf;
							aci = Math.toDegrees(Math.atan(tan)) + 180;
						}
						if (birdOnLp.get_X() >= 0 && birdOnLp.get_Y() <= yukseklikLD) {
							double terstaraf = birdOnLp.get_X();
							double duztaraf = yukseklikLD - birdOnLp.get_Y();
							tan = terstaraf / duztaraf;
							aci = 90 + Math.toDegrees(Math.atan(tan));
						}
						Koordinat koordinat2 = Sapan.koordinati(25, birdOnLp.getCoord(),
								new Koordinat(0, yukseklikLD + birdOnLp.getBoyut() / 2));
						birdOnLp.setSonrakiCoord(koordinat2);
						surukle = true;
					}
				}
			}
		});
		this.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent me) {
				
				if (surukle) {
					if (birdOnLp != null && !birdOnLp.isFlying() && !birdOnLp.bitis()) {
						sapan();

					}
					surukle = false;
				}

			}

			public void mousePressed(MouseEvent arg0) {
			}

			public void mouseExited(MouseEvent arg0) {
			}

			public void mouseEntered(MouseEvent arg0) {
			}

			public void mouseClicked(MouseEvent arg0) {
			}

		});
		
		
		JButton anaMenu = new JButton("Ana Menu");
		anaMenu.setBounds(760,30,200,80);
		anaMenu.setFont(new Font("", Font.ITALIC, 18));
		anaMenu.setForeground(Color.BLACK);
		anaMenu.setFocusPainted(false);
		anaMenu.setContentAreaFilled(false);
		anaMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				Main.getEkran().changerFond(new Menu());
			}
		});
		add(anaMenu);
		
		JButton bolum1 = new JButton("Bolum  1");
		bolum1.setBounds(360,30,200,80);
		bolum1.setFont(new Font("", Font.ITALIC, 18));
		bolum1.setForeground(Color.BLACK);
		bolum1.setFocusPainted(false);
		bolum1.setContentAreaFilled(false);
		bolum1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				yapilar yapilar = new yapilar(Main.getEkran().getContentPane().getWidth(),Main.getEkran().getContentPane().getHeight(),40,125,100);
				yapilar.eklekus(new Bird(yapilar));
				yapilar.eklekus(new Bird(yapilar));
				yapilar.eklekus(new Bird(yapilar));

				temel b = new temel(yapilar);
				b.tur();
				new YerCekimi(yapilar);
				Main.getEkran().changerFond(yapilar);
			}
		});
		add(bolum1);
		
		JButton bolum2 = new JButton("Bolum 2");
		bolum2.setBounds(560,30,200,80);
		bolum2.setFont(new Font("", Font.ITALIC, 18));
		bolum2.setForeground(Color.BLACK);
		bolum2.setFocusPainted(false);
		bolum2.setContentAreaFilled(false);
		bolum2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				yapilar yapilar = new yapilar(Main.getEkran().getContentPane().getWidth(),Main.getEkran().getContentPane().getHeight(),40,125,100);
				yapilar.eklekus(new Bird(yapilar));//kus kuş
				yapilar.eklekus(new Bird(yapilar));
				

				temel b = new temel(yapilar);
				b.tur2();
				new YerCekimi(yapilar);
				Main.getEkran().changerFond(yapilar);
			}
		});
		add(bolum2);
		
		add(affScore);
		
		}
		
		
	

	public Bird birdOnLp() {
		for (Bird o : kuslar) {
			if (!o.bitis()) {
				birdOnLp = o;
				o.placerSurLP();
				return o;
			}
		}
		return null;
	}

	public void sapan() {
		new Sapan(birdOnLp, this, hiz, aci);
		birdOnLp.fly();
	}

	
	public Plan getPlan() {
		return plan;
	}

	@Override
	public void paintComponent(Graphics g) {
		try {
			BufferedImage img = ImageIO.read(new File("img/fd.jpg"));	
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this); 
		} catch (IOException e){}

		score=0;
		//arka plan
		//g.setColor(new Color(91,158,238));
		//g.fillRect(0, 0, genislik, yukseklik);
		//g.setColor(new Color(103,198,55));
	//	g.fillRect(0, yukseklik-zeminYuksekligi, genislik, zeminYuksekligi);
		g.setColor(new Color(138,104,44));
		g.fillRect(konum-10, yukseklik-zeminYuksekligi-yukseklikLD,20, yukseklikLD);

		// bird color
		for (Bird o : kuslar) {
			ImageIcon imageIcon = new ImageIcon("img/images.jpg");
			    Image tmpImage = imageIcon.getImage();
			    BufferedImage image = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
			    image.getGraphics().drawImage(tmpImage, 0, 0, null);
			    tmpImage.flush();
						
			    Koordinat koordinatPos = plan.plan_Cisim(o.getCoord());
		    

			    g.drawImage(image, koordinatPos.getX() - o.getBoyut() / 2, koordinatPos.getY() - o.getBoyut() / 2, o.getBoyut(),
					o.getBoyut(), affScore);
			
		}

		
			for(Hedef c : hedef){
				if(c.dokunduysa()) score+=3;
				affScore.setText(""+score);
				if(score>=16){
					affScore.setText("kazandiniz !");
					kuslar.clear();
				}
				c.tasari(g);
			
			}
			try{
				for(Koordinat c : puan){
				g.setColor(Color.black);
				g.fillOval(plan.plan_Cisim(c).getX(), plan.plan_Cisim(c).getY(), 2, 2);
				}
			}catch(Exception e){
				
			}

		if (birdOnLp != null) {
			int dist = new Koordinat(0, yukseklikLD).distance(birdOnLp.getCoord());
			if (dist < 120) {
				Graphics2D g2 = (Graphics2D) (g);
				g2.setStroke(new BasicStroke(8));
				g2.setColor(new Color(238, 201, 0));
				Koordinat co = plan.plan_Cisim(birdOnLp.getCoord());
				Koordinat cl = plan.plan_Cisim(new Koordinat(0, yukseklikLD));
				g2.draw(new Line2D.Float(co.getX(), co.getY(), cl.getX(), cl.getY()));
			} else if (dist > 200) {
				Graphics2D g2 = (Graphics2D) (g);
				g2.setStroke(new BasicStroke(8));
				g2.setColor(new Color(238, 201, 0));
				Koordinat cl = plan.plan_Cisim(new Koordinat(0, yukseklikLD));
				g2.draw(new Line2D.Float(cl.getX() - 10, cl.getY() + 20, cl.getX(), cl.getY()));
			}
		}
	

		revalidate();
	}

	
	public int getYuksekLP() {
		return yukseklikLD;
	}

	
	public int getGenislik() {
		return genislik;
	}

	
	public int getYuksek() {
		// TODO Auto-generated method stub
		return yukseklik;
	}

	
	public int getYuksekSol() {
		// TODO Auto-generated method stub
		return zeminYuksekligi;
	}

	
	public int getkonum() {
		// TODO Auto-generated method stub
		return konum;
	}

	
	public ArrayList<Hedef> getHedefs() {
		// TODO Auto-generated method stub
		return hedef;
	}

	
	public boolean placeLibre(int x, int y, int boyut) {
		// TODO Auto-generated method stub
		for (Hedef c : hedef) {
			if (c.getCoord().distance(new Koordinat(x, y)) < (c.getBoyut() / 2 + boyut / 2))
				return false;
		}
		return true;
	}

	
	public boolean gagne() {
		for (Hedef c : hedef) {
			if (!c.dokunduysa())
				return false;
		}
		return true;
	}

	
	public ArrayList<Bird> getkusx() {
		// TODO Auto-generated method stub
		return kuslar;
	}

	
	public void ekleHedef(Hedef c) {
		// TODO Auto-generated method stub
		c.addObserver(this);
		hedef.add(c);
	}

	
	public void eklekus(Bird o) {
		// TODO Auto-generated method stub
		kuslar.add(o);
		o.addObserver(this);
		if (birdOnLp == null) {
			birdOnLp = o;
			o.placerSurLP();
		}
	}

	
	public void eklePoint(Koordinat c) {
		// TODO Auto-generated method stub
		puan.add(c);
	}

	
	public void bosPointsUzantı() {
		// TODO Auto-generated method stub
		puan = new ArrayList<Koordinat>();
	}

	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		repaint();
	}
}
