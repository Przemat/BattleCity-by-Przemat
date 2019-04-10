package com.project.BattleCity;


import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JLabel;


public class Colision {

	private Rectangle[][] mapa_s = new Rectangle[26][26];
	private Rectangle[][] mapa_w = new Rectangle[26][26];
	private Rectangle[][] mapa_t = new Rectangle[26][26];
	private Rectangle player_i;
	private Rectangle[] enemy_i = new Rectangle[5];
	private Rectangle[] bullet_i = new Rectangle[6];
	private Rectangle bonus_i;
	
	private boolean Cmap;
	private boolean Cframe;
	private boolean CTanks;

	public void Colision_update(JLabel[][] mapa, JLabel player , Enemy[] enemy, Shot bullet[], int[][] map, JLabel bonus){
		//mapa
		for (int i = 0; i < 26; i++) {
			for (int j = 0; j < 26; j++) {
				if(mapa[i][j]!=null){
				if(mapa[i][j].getName()=="tree"||mapa[i][j].getName()=="eagle")continue;
				else mapa_t[i][j] = mapa[i][j].getBounds();
				if(mapa[i][j].getName()=="brick"&&mapa_s[i][j]!=null)mapa_s[i][j]=null;
				if(mapa[i][j].getName()=="steel")mapa_s[i][j]= mapa[i][j].getBounds();
				if(mapa[i][j].getName()=="water1"||mapa[i][j].getName()=="water2")mapa_w[i][j]= mapa[i][j].getBounds();
			}else mapa_t[i][j] = null;}
			
		}
		//gracz
		if(player!=null){
			if(player_i == null)player_i = new Rectangle(player.getBounds());
			else player_i.setBounds(player.getBounds());
		}
		else player_i = null;
		//wrogowie
		for (int i = 0; i < 5; i++) {
			if(enemy[i]==null)enemy_i[i] = null;
			else {
				if (enemy_i[i]==null)enemy_i[i] = new Rectangle(enemy[i].enemy.getBounds());
				else enemy_i[i].setBounds(enemy[i].enemy.getBounds());
			}
		}
		//pociski
		for (int i = 0; i < 6; i++) {
			if(bullet[i]==null)bullet_i[i] = null;
			else {
				if (bullet_i[i]==null)bullet_i[i] = new Rectangle(bullet[i].shot.getBounds());
				else bullet_i[i].setBounds(bullet[i].shot.getBounds());
			}
		}
		if(bonus!=null){
			if(bonus_i == null)bonus_i = new Rectangle(bonus.getBounds());
			else bonus_i.setBounds(bonus.getBounds());
		}
		else bonus_i = null;
	}
	public boolean Bonus_Colision(Rectangle player){
		if(bonus_i!=null){
		if(player.intersects(bonus_i))return true;
		else return false;
	}
		return false;}
	public boolean Colision_Tank(int index, int dx, int dy){
		if(index == 0){
			Rectangle r1 = new Rectangle(player_i.getBounds().x+dx, player_i.getBounds().y+dy, player_i.getBounds().width, player_i.getBounds().height);
			//z mapa
			Cmap = true;
			for (int i = 0; i < 26; i++) {
				for (int j = 0; j < 26; j++) {
					if (mapa_t[i][j]!=null){
					if(r1.intersects(mapa_t[i][j])){
						Cmap= false;
						
							}
						}
					}
				}
			CTanks =true;
			for (int i = 0; i < 5; i++) {
				if(enemy_i[i]!=null){
					if(r1.intersects(enemy_i[i])){
						CTanks = false;
					}
				}
			}
			
			//z ram¹
			if (player_i.getBounds().x+dx<0||player_i.getBounds().x+dx>768||player_i.getBounds().y+dy<0||player_i.getBounds().y+dy>768){
				Cframe= false;
			}else Cframe= true;
			if (Cmap&&Cframe&&CTanks){
				return true;
			}else{
				return false;
			}
			
		}
		//kolizja wrogów
		if(index > 0){
			Rectangle r2 = new Rectangle(enemy_i[index-1].getBounds().x+dx, enemy_i[index-1].getBounds().y+dy, enemy_i[index-1].getBounds().width, enemy_i[index-1].getBounds().height);
			//z mapa
			Cmap = true;
			for (int i = 0; i < 26; i++) {
				for (int j = 0; j < 26; j++) {
					if (mapa_t[i][j]!=null){
					if(r2.intersects(mapa_t[i][j])){
						Cmap= false;
						
							}
						}
					}
				}
			//z wrogami
			CTanks =true;
			for (int i = 0; i < 5; i++) {
				if(enemy_i[i]!=null&&i!=index-1){
					if(r2.intersects(enemy_i[i])){
						CTanks = false;
					}
				}
			}
			if(player_i != null){
			if(r2.intersects(player_i)) CTanks = false;
			}
			//z ram¹
			if (enemy_i[index-1].getBounds().x+dx<0||enemy_i[index-1].getBounds().x+dx>768||enemy_i[index-1].getBounds().y+dy<0||enemy_i[index-1].getBounds().y+dy>768){
				Cframe= false;
			}else Cframe= true;
			if (Cmap&&Cframe&&CTanks){
				return true;
			}else{
				return false;
			}
			
		}
		return false;
		
		
	
	
	}
	public ArrayList<String> Colision_Bullet(int index) {
		ArrayList<String> Colision = new ArrayList<String>();
			Rectangle r3 = new Rectangle(bullet_i[index].getBounds().x, bullet_i[index].getBounds().y, bullet_i[index].getBounds().width, bullet_i[index].getBounds().height);
			//z mapa
		
			for (int i = 0; i < 26; i++) {
				for (int j = 0; j < 26; j++) {
					if (mapa_t[i][j]!=null){
					if(r3.intersects(mapa_t[i][j])&&mapa_w[i][j]==null){
						if(mapa_s[i][j]==null){
						Colision.add("map");
						Colision.add(Integer.toString(i));
						Colision.add(Integer.toString(j));
						}
						else {
							Colision.add("steel");
						}
						}
						}
					}
				}
			//z wrogami
			for (int i = 0; i < 5; i++) {
				if(enemy_i[i]!=null){
					if(r3.intersects(enemy_i[i])){
						Colision.add("enemy");
						Colision.add(Integer.toString(i));
					}
				}
			}
			//z pociskami
			for (int i = 0; i < 6; i++) {
				if(bullet_i[i]!=null&&i!=index){
					if(r3.intersects(bullet_i[i])){
						Colision.add("bullet");
						Colision.add(Integer.toString(i));
					}
				}
			}
			//z graczem
			if(player_i != null){
			if(r3.intersects(player_i)){ 
				Colision.add("player");
			}
			}//z ram¹
			if (bullet_i[index].getBounds().x<0||bullet_i[index].getBounds().x>832||bullet_i[index].getBounds().y<0||bullet_i[index].getBounds().y>832){
				Colision.add("frame");
			}
			//z baza
			if(r3.intersects(384,768,64,64))Colision.add("eagle");
			// TODO Auto-generated method stub
			return Colision;
		}
	public boolean Spawn(int index){
		if(index == 0){
			Rectangle r1 = new Rectangle(256,704,64, 64);
			
			CTanks =true;
			for (int i = 0; i < 5; i++) {
				if(enemy_i[i]!=null){
					if(r1.intersects(enemy_i[i])){
						CTanks = false;
					}
				}
			}
			
		
	}
		if(index != 0){
			Rectangle r2 = new Rectangle(384*(index-1),0,64, 64);
			
			CTanks =true;
			for (int i = 0; i < 5; i++) {
				if(enemy_i[i]!=null){
					if(r2.intersects(enemy_i[i])){
						CTanks = false;
					}
				}
			}
			if(player_i!=null){
			if(r2.intersects(player_i)) CTanks = false;
			}
	}
		if(CTanks)return true;
		return false;

	}
}

