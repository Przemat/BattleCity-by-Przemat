package com.project.BattleCity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class Rules {
	Enemy[] enemy = new Enemy[5];
	Shot[] bullet = new Shot[6];
	JLabel bonus;
	private int level =0;
	public int getlevel(){
		return level;
	}
	public void setlevel(){
		level++;
	}
	private boolean base = true;
	private int live = 3;
	private int con = 3;
	public int getCon() {
		return con;
	}
	private int normal = 0;
	private int speed = 0;
	private int heavy = 0;
	private int power = 0;
	private int Tankqty = 0;
	public int getTankqty(){
		return Tankqty;
	}
	private int[] HS ={0,0,0,0};
	private Scanner tanks;
	private enum Game_enum {
		WIN, LOSE, NOTHING, END
	}
	public Rules(int l, int c,int lvl,int[] H){
		live = l;
		con = c;
		level = lvl;
		HS = H;
	}
	public void LoadTank() throws FileNotFoundException{
		int lvl = level/35;
		tanks = new Scanner(new File("level/"+(level+1-lvl)+"_t.txt"));
			normal = Integer.valueOf(tanks.nextInt())+5*lvl;
			speed = Integer.valueOf(tanks.nextInt())+5*lvl;
			heavy = Integer.valueOf(tanks.nextInt())+5*lvl;
			power = Integer.valueOf(tanks.nextInt())+5*lvl;
			Tankqty = normal+speed+heavy+power;
		
	}
	public Tank getTank(){
		Random newtank = new Random();
		Tank tank;
		switch(newtank.nextInt(4)){
		case 0:{
			if(normal>0){
				normal--;
				Tankqty = normal+speed+heavy+power;
			tank = Tank.NORMAL;
			break;
			}
		}
		case 1:{
			if(speed>0){
				speed--;
				Tankqty = normal+speed+heavy+power;
			tank = Tank.SPEED;
			break;
		}
		}
		case 2:{
			if(heavy>0){
			heavy--;
			Tankqty = normal+speed+heavy+power;
			tank = Tank.HEAVY;
			break;
		}
		}
		case 3:{
			if(power>0){
			power--;
			Tankqty = normal+speed+heavy+power;
			tank = Tank.POWER;
			break;
		}
		}
		default:{
			tank = null;
			break;
		}
		}
		return tank;
	}
	public String inGame(){
		if (base == false){
			if(con ==0)return Game_enum.END.name();
			else return Game_enum.LOSE.name();
		}
		if (live <0){
			if(con ==0)return Game_enum.END.name();
			else return Game_enum.LOSE.name();
		}
		if (normal == 0&&speed==0&&heavy==0&&power==0&&enemy[3]==null&&enemy[4]==null&&enemy[2]==null&&enemy[1]==null&&enemy[0]==null){
		return Game_enum.WIN.name();
		}	
		return Game_enum.NOTHING.name();
	}
	
	public void setbase(boolean set){
		base = set;
	}
	public int getlive(){
		return live;
	}
	public void setlive(int live){
		this.live =live;
	}
	public int[] getHS() {
		return HS;
	}
	public void setHS(int index) {
		HS[index]++;
	}
	public void spawnbonus(JLayeredPane sec, int x , int y, int b){
			bonus = new JLabel();
			bonus.setBounds(x, y, 64, 64);
			switch (b) {
			case 0:
				bonus.setIcon(new ImageIcon(HighScore.class.getResource("/com/project/BattleCity/image/powerup_star.png")));
				bonus.setName("SPEED");
				break;
			case 1:
				bonus.setIcon(new ImageIcon(HighScore.class.getResource("/com/project/BattleCity/image/powerup_helmet.png")));
				bonus.setName("SHIELD");
				break;
			case 2:
				bonus.setIcon(new ImageIcon(HighScore.class.getResource("/com/project/BattleCity/image/powerup_tank.png")));
				bonus.setName("LIVE");
				break;
			case 3:
				bonus.setIcon(new ImageIcon(HighScore.class.getResource("/com/project/BattleCity/image/powerup_grenade.png")));
				bonus.setName("BOMB");
				break;
			case 4:
				bonus.setIcon(new ImageIcon(HighScore.class.getResource("/com/project/BattleCity/image/powerup_shovel.png")));
				bonus.setName("BASE");
				break;
			case 5:
				bonus.setIcon(new ImageIcon(HighScore.class.getResource("/com/project/BattleCity/image/powerup_timer.png")));
				bonus.setName("CLOCK");
				break;
				
			default:
				break;
			}
			sec.add(bonus);
	}
	
}
