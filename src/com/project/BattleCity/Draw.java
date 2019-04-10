package com.project.BattleCity;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.Timer;

public class Draw {
	int water_time=0;
	public int[][] map = new int[26][26];
	JLabel[][] jtab = new JLabel[26][26];
	private ImageIcon trees;
	private ImageIcon trees2;
	private ImageIcon brick;
	private ImageIcon steel;
	private ImageIcon water;
	private ImageIcon water2;
	private ImageIcon eagle;
	private ImageIcon eagledest;
	private Scanner mapa;
	public Draw() throws FileNotFoundException {
		loadImage();
	}
	private void loadImage() {
		trees = new ImageIcon(Draw.class.getResource("/com/project/BattleCity/image/trees.png"));
		trees2 = new ImageIcon(Draw.class.getResource("/com/project/BattleCity/image/trees2.png"));
		brick = new ImageIcon(Draw.class.getResource("/com/project/BattleCity/image/wall_brick.png"));
		steel = new ImageIcon(Draw.class.getResource("/com/project/BattleCity/image/wall_steel.png"));
		water = new ImageIcon(Draw.class.getResource("/com/project/BattleCity/image/water_1.png"));
		water2 = new ImageIcon(Draw.class.getResource("/com/project/BattleCity/image/water_2.png"));
		eagle = new ImageIcon(Draw.class.getResource("/com/project/BattleCity/image/base.png"));
		eagledest = new ImageIcon(Draw.class.getResource("/com/project/BattleCity/image/base_destroyed.png"));
	}
	public void loadmap(String level) throws FileNotFoundException {
		int lvl = Integer.parseInt(level)%35;
		mapa = new Scanner(new File("level/"+(lvl+1)+".txt"));
		for (int i = 0; i < 26; i++) {
			for (int j = 0; j < 26; j++) {
				map[i][j] = Integer.valueOf(mapa.nextInt());
			}
		}
		
	}
	public void basebonus(JLayeredPane back){
		if(jtab[23][12]==null){
			 jtab[23][12]= new JLabel();
			 jtab[23][12].setName("steel");
			 jtab[23][12].setIcon(getImage(4));
			 jtab[23][12].setBounds(12*32, 23*32, 32,32);
			 back.add(jtab[23][12]);
		}else{
			jtab[23][12].setName("steel");
			jtab[23][12].setIcon(getImage(4));
		}
		if(jtab[23][13]==null){
			 jtab[23][13]= new JLabel();
			 jtab[23][13].setName("steel");
			 jtab[23][13].setIcon(getImage(4));
			 jtab[23][13].setBounds(13*32, 23*32, 32,32);
			 back.add(jtab[23][13]);
		}else{
			jtab[23][13].setName("steel");
			jtab[23][13].setIcon(getImage(4));
		}if(jtab[23][14]==null){
			 jtab[23][14]= new JLabel();
			 jtab[23][14].setName("steel");
			 jtab[23][14].setIcon(getImage(4));
			 jtab[23][14].setBounds(14*32, 23*32, 32,32);
			 back.add(jtab[23][14]);
		}else{
			jtab[23][14].setName("steel");
			jtab[23][14].setIcon(getImage(4));
		}if(jtab[23][11]==null){
			 jtab[23][11]= new JLabel();
			 jtab[23][11].setName("steel");
			 jtab[23][11].setIcon(getImage(4));
			 jtab[23][11].setBounds(11*32, 23*32, 32,32);
			 back.add(jtab[23][11]);
		}else{
			jtab[23][11].setName("steel");
			jtab[23][11].setIcon(getImage(4));
		}if(jtab[24][14]==null){
			 jtab[24][14]= new JLabel();
			 jtab[24][14].setName("steel");
			 jtab[24][14].setIcon(getImage(4));
			 jtab[24][14].setBounds(14*32, 24*32, 32,32);
			 back.add(jtab[24][14]);
		}else{
			jtab[24][14].setName("steel");
			jtab[24][14].setIcon(getImage(4));
		}if(jtab[24][11]==null){
			 jtab[24][11]= new JLabel();
			 jtab[24][11].setName("steel");
			 jtab[24][11].setIcon(getImage(4));
			 jtab[24][11].setBounds(11*32, 24*32, 32,32);
			 back.add(jtab[24][11]);
		}else{
			jtab[24][11].setName("steel");
			jtab[24][11].setIcon(getImage(4));
		}
		if(jtab[25][11]==null){
			 jtab[25][11]= new JLabel();
			 jtab[25][11].setName("steel");
			 jtab[25][11].setIcon(getImage(4));
			 jtab[25][11].setBounds(11*32, 25*32, 32,32);
			 back.add(jtab[25][11]);
		}else{
			jtab[25][11].setName("steel");
			jtab[25][11].setIcon(getImage(4));
		}
		if(jtab[25][14]==null){
			 jtab[25][14]= new JLabel();
			 jtab[25][14].setName("steel");
			 jtab[25][14].setIcon(getImage(4));
			 jtab[25][14].setBounds(14*32, 25*32, 32,32);
			 back.add(jtab[25][14]);
		}else{
			jtab[25][14].setName("steel");
			jtab[25][14].setIcon(getImage(4));
		}
		ActionListener baseAL = new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
					
					jtab[24][14].setName("brick");
					jtab[24][14].setIcon(getImage(3));
					jtab[24][11].setName("brick");
					jtab[24][11].setIcon(getImage(3));
					
					jtab[25][14].setName("brick");
					jtab[25][14].setIcon(getImage(3));
					jtab[25][11].setName("brick");
					jtab[25][11].setIcon(getImage(3));
					
					jtab[23][11].setName("brick");
					jtab[23][11].setIcon(getImage(3));
					jtab[23][12].setName("brick");
					jtab[23][12].setIcon(getImage(3));
					jtab[23][13].setName("brick");
					jtab[23][13].setIcon(getImage(3));
					jtab[23][14].setName("brick");
					jtab[23][14].setIcon(getImage(3));
					
					
					((Timer) e.getSource()).stop();				
			}
	};
	new Timer(10000, baseAL).start();
		
	}
	public void drawmap(JLayeredPane second, JLayeredPane back){
		
		for (int i = 0; i < 26; i++) {
			for (int j = 0; j < 26; j++) {
				if(map[i][j]==0)continue;
				else if (map[i][j]==1 || map[i][j]==2){
					 jtab[i][j] = new JLabel();
					 jtab[i][j].setName("tree");
					 jtab[i][j].setIcon(getImage(map[i][j]));
					 jtab[i][j].setBounds(j*32, i*32, getImage(map[i][j]).getIconWidth(), getImage(map[i][j]).getIconHeight());
					 second.add(jtab[i][j]);
				}
				else if (map[i][j]==3){
					 jtab[i][j] = new JLabel();
					 jtab[i][j].setName("brick");
					 jtab[i][j].setIcon(getImage(map[i][j]));
					 jtab[i][j].setBounds(j*32, i*32, getImage(map[i][j]).getIconWidth(), getImage(map[i][j]).getIconHeight());
					 back.add(jtab[i][j]);
				}else if (map[i][j]==4){
					 jtab[i][j] = new JLabel();
					 jtab[i][j].setName("steel");
					 jtab[i][j].setIcon(getImage(map[i][j]));
					 jtab[i][j].setBounds(j*32, i*32, getImage(map[i][j]).getIconWidth(), getImage(map[i][j]).getIconHeight());
					 back.add(jtab[i][j]);
				}else if (map[i][j]==5){
					 jtab[i][j] = new JLabel();
					 jtab[i][j].setName("water1");
					 jtab[i][j].setIcon(getImage(map[i][j]));
					 jtab[i][j].setBounds(j*32, i*32, getImage(map[i][j]).getIconWidth(), getImage(map[i][j]).getIconHeight());
					 back.add(jtab[i][j]);
				}else if (map[i][j]==6){
					 jtab[i][j] = new JLabel();
					 jtab[i][j].setName("water2");
					 jtab[i][j].setIcon(getImage(map[i][j]));
					 jtab[i][j].setBounds(j*32, i*32, getImage(map[i][j]).getIconWidth(), getImage(map[i][j]).getIconHeight());
					 back.add(jtab[i][j]);
				}else if (map[i][j]==7){
					 jtab[i][j] = new JLabel();
					 jtab[i][j].setName("eagle");
					 jtab[i][j].setIcon(getImage(map[i][j]));
					 jtab[i][j].setBounds(j*32, i*32, getImage(map[i][j]).getIconWidth(), getImage(map[i][j]).getIconHeight());
					 back.add(jtab[i][j]);
				}
				
	}
		
	}
	}
	public void drawwater(JLayeredPane back){
		if(water_time>30){
			for (int i = 0; i < 26; i++) {
				for (int j = 0; j < 26; j++) {
					if (jtab[i][j]==null)continue;
					switch(jtab[i][j].getName()){
					case "water1":{
						jtab[i][j].setName("water2");
						jtab[i][j].setIcon(getImage(6));
						
						break;
					}
					case "water2":{
						jtab[i][j].setName("water1");
						jtab[i][j].setIcon(getImage(5));
						
						break;
					}
					
					}
			water_time=0;
				}
			}
		
	
		}
		water_time++;
	}
	public void drawTank(JLabel tank, ImageIcon imageIcon, int x, int y, int w, int h){
		tank.setBounds(x, y, w, h);
		tank.setIcon(imageIcon);
	}
	private ImageIcon getImage(int element) {
	if (element == 0)return null;
	else if(element == 1)return trees;
	else if (element == 2)return trees2;
	else if (element== 3 )return brick;
	else if (element == 4)return steel;
	else if (element == 5)return water;
	else if (element == 6)return water2;
	else if (element == 7)return eagle;
	else if (element == 8)return eagledest;
	else return null;
	}
	public void basedest() {
		jtab[24][12].setIcon(getImage(8));
		// TODO Auto-generated method stub
		
	}
	}

