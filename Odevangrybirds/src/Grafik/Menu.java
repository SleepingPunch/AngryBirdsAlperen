package Grafik;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import temel.temel;
import Kontrol.YerCekimi;
import Main.Main;
import Model.Bird;
import Goruntu.yapilar;



public class Menu extends JPanel{

	private static final long serialVersionUID = 1L;
	protected Image img;
	protected JLabel baslik , image, copyr;
	protected JButton oyun;
	
	
	public Menu(){

		super();
		setLayout(null);
		
		//this.setResizable(false); yoruma almayı unutma
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
		baslik = new JLabel(" ");
		baslik.setBounds(50,25,500,35);
		baslik.setFont(new Font("", Font.BOLD, 30));
		baslik.setForeground(Color.RED);
		add(baslik);
		
		baslik = new JLabel("Cumhuriyet Universitesi");
		baslik.setBounds(650,40,500,30);

		baslik.setFont(new Font("", Font.BOLD, 30));
		baslik.setForeground(Color.DARK_GRAY);
		add(baslik);
		
	
		
		oyun = new JButton("Oyna");
		oyun.setBounds(110,160,200,60);
		oyun.setFont(new Font("", Font.CENTER_BASELINE, 20));
		oyun.setForeground(Color.RED);
		oyun.setFocusPainted(false);
		oyun.setContentAreaFilled(false);
		oyun.addActionListener(new ActionListener() {
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
		add(oyun);
		
	
		copyr = new JLabel("Angry Birds");
		copyr.setBounds(700, 520, 290, 20);
		copyr.setFont(new Font("",Font.ITALIC, 12));
		copyr.setForeground(Color.WHITE);
		add(copyr);
		
	
	}
	

	@Override
	public void paintComponent(Graphics g){
		try {
			BufferedImage img = ImageIO.read(new File("img/fd.jpg"));	
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this); 
		} catch (IOException e){}
	}
	
}
